"""
work/assemblage/python/tests/test_graph.py

Graph invariant testing for AssemblageGraph:
- Data-driven topological parametrization across DAGs, self-loops, 2-cycles, disjoint cycles, and directed 3-cycles
- Canonical directed cycle representation (lexicographical minimum rotation) to preserve cycle orientation
- Strict decoupling: node attribute set algebra tested separately from topological edge classification
- Property-based testing via hypothesis: validating cycle vertex uniqueness and closed-walk connectivity
- Edge cases: disconnected nodes, empty sets, parallel edges, and non-existent nodes
"""

from typing import Any
import pytest
from hypothesis import given, strategies as st
from assemblage.graph import AssemblageGraph


# --- Canonical Representation Helper ---

def canonical_cycle(cycle: list[str]) -> tuple[str, ...]:
    """
    Rotate a directed cycle so its lexicographically smallest node is at index 0.
    Preserves exact traversal orientation while enabling deterministic sequence equality.
    e.g., ['b', 'c', 'a'] -> ('a', 'b', 'c')
          ['a', 'c', 'b'] -> ('a', 'c', 'b')
    """
    if not cycle:
        return ()
    min_idx = min(range(len(cycle)), key=cycle.__getitem__)
    return tuple(cycle[min_idx:] + cycle[:min_idx])


def build_graph(
    edges: list[tuple[str, str, dict[str, Any]]],
    nodes: dict[str, dict[str, Any]] | None = None,
) -> AssemblageGraph:
    """Helper to build an AssemblageGraph from pure declarative specifications."""
    g = AssemblageGraph()
    if nodes:
        for node_id, attrs in nodes.items():
            g.add_element(
                node_id,
                traits=attrs.get("traits"),
                level=attrs.get("level", 0),
            )
    for edge in edges:
        u, v = edge[0], edge[1]
        attrs = edge[2] if len(edge) > 2 else {}
        if u not in g.traits:
            g.add_element(u)
        if v not in g.traits:
            g.add_element(v)
        g.add_link(u, v, **attrs)
    return g


# --- 1. Topological Invariants & Cycle Detection ---

@pytest.mark.parametrize(
    "edges, expected_canonical_cycles",
    [
        # Acyclic DAG
        (
            [("a", "b", {}), ("b", "c", {}), ("a", "c", {})],
            [],
        ),
        # Single self-loop
        (
            [("a", "a", {})],
            [("a",)],
        ),
        # Simple 2-cycle
        (
            [("a", "b", {}), ("b", "a", {})],
            [("a", "b")],
        ),
        # Disjoint cycles
        (
            [("a", "b", {}), ("b", "a", {}), ("c", "d", {}), ("d", "c", {})],
            [("a", "b"), ("c", "d")],
        ),
        # Directed 3-cycle (orientation A -> B -> C -> A)
        (
            [("a", "b", {}), ("b", "c", {}), ("c", "a", {})],
            [("a", "b", "c")],
        ),
        # Reverse directed 3-cycle (orientation A -> C -> B -> A)
        (
            [("a", "c", {}), ("c", "b", {}), ("b", "a", {})],
            [("a", "c", "b")],
        ),
        # Empty graph
        (
            [],
            [],
        ),
    ],
    ids=[
        "dag-acyclic",
        "self-loop",
        "two-cycle",
        "disjoint-cycles",
        "directed-3cycle-forward",
        "directed-3cycle-reverse",
        "empty-graph",
    ],
)
def test_simple_cycles_topologies(edges, expected_canonical_cycles):
    g = build_graph(edges)
    raw_cycles = g.simple_cycles()
    canonical_results = {canonical_cycle(c) for c in raw_cycles}
    expected_set = set(expected_canonical_cycles)

    assert canonical_results == expected_set


def test_directed_3_cycle_orientation():
    """Explicitly verify that forward and reverse 3-cycles have distinct canonical signatures."""
    g_fwd = build_graph([("a", "b", {}), ("b", "c", {}), ("c", "a", {})])
    g_rev = build_graph([("a", "c", {}), ("c", "b", {}), ("b", "a", {})])

    cycles_fwd = {canonical_cycle(c) for c in g_fwd.simple_cycles()}
    cycles_rev = {canonical_cycle(c) for c in g_rev.simple_cycles()}

    assert cycles_fwd == {("a", "b", "c")}
    assert cycles_rev == {("a", "c", "b")}
    assert cycles_fwd != cycles_rev


# --- 2. Property-Based Testing via Hypothesis ---

@given(
    st.lists(
        st.tuples(st.integers(0, 8), st.integers(0, 8)),
        max_size=25,
    )
)
def test_every_detected_cycle_is_valid_closed_walk(edge_pairs):
    """
    Mathematical Invariant: Every simple cycle detected must be an elementary closed walk
    with unique vertices and valid directed edges between consecutive pairs.
    """
    g = AssemblageGraph()
    for u, v in edge_pairs:
        g.add_element(str(u))
        g.add_element(str(v))
        g.add_link(str(u), str(v))

    for cycle in g.simple_cycles():
        # Invariant 1: Simple cycles have no repeated vertices
        assert len(cycle) == len(set(cycle))
        # Invariant 2: Every consecutive pair (and wrap-around) is an actual edge
        for i in range(len(cycle)):
            src = cycle[i]
            dst = cycle[(i + 1) % len(cycle)]
            assert g.has_link(src, dst)


# --- 3. Decoupled Node Attribute Algebra (Trait Set Operations) ---

@pytest.fixture
def trait_graph() -> AssemblageGraph:
    g = AssemblageGraph()
    g.add_element("n1", traits={"t1", "t2"}, level=1)
    g.add_element("n2", traits={"t2", "t3"}, level=1)
    g.add_element("n3", traits=set(), level=2)
    return g


@pytest.mark.parametrize(
    "u, v, expected",
    [
        ("n1", "n2", {"t2"}),
        ("n1", "n3", set()),
        ("n1", "n1", {"t1", "t2"}),
    ],
    ids=["partial-overlap", "disjoint-empty", "reflexive-identity"],
)
def test_shared_traits(trait_graph, u, v, expected):
    # Invariant: Commutativity (u ∩ v == v ∩ u == expected)
    assert trait_graph.shared_traits(u, v) == expected
    assert trait_graph.shared_traits(v, u) == expected


def test_shared_traits_nonexistent_nodes():
    g = AssemblageGraph()
    g.add_element("u", traits={"t1"})

    # Non-existent node returns empty set gracefully without KeyError
    assert g.shared_traits("u", "ghost") == set()
    assert g.shared_traits("ghost", "phantom") == set()


# --- 4. Decoupled Topological Edge Classification (Cross-Links) ---

@pytest.mark.parametrize(
    "edges, expected_cross_link_pairs",
    [
        (
            [
                ("a", "b", {"kind": "transversal-cross-link"}),
                ("a", "c", {"kind": "genealogical"}),
                ("b", "d", {"kind": "feedback"}),
            ],
            [("a", "b")],
        ),
        (
            [
                ("a", "b", {"kind": "genealogical"}),
                ("b", "c", {"kind": "genealogical"}),
            ],
            [],
        ),
        (
            [
                ("a", "b", {"kind": "transversal-cross-link"}),
                ("c", "d", {"kind": "transversal-cross-link"}),
            ],
            [("a", "b"), ("c", "d")],
        ),
    ],
    ids=["mixed-edges", "no-cross-links", "multiple-cross-links"],
)
def test_find_cross_links_filtering(edges, expected_cross_link_pairs):
    g = build_graph(edges)
    cross_links = g.find_cross_links()
    extracted_pairs = [(u, v) for u, v, _ in cross_links]

    assert sorted(extracted_pairs) == sorted(expected_cross_link_pairs)
    for _, _, d in cross_links:
        assert d.get("kind") == "transversal-cross-link"
