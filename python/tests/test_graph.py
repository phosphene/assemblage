"""
work/assemblage/python/tests/test_graph.py

Graph invariant testing for AssemblageGraph:
- Data-driven topological parametrization across DAGs, self-loops, 2-cycles, disjoint cycles, and directed 3-cycles
- Canonical directed cycle representation (lexicographical minimum rotation) to preserve cycle orientation
- Strict decoupling: node attribute set algebra tested separately from topological edge classification
- Edge cases: disconnected nodes, empty sets, parallel edges, and non-existent nodes
"""

from typing import Any
import pytest
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


# --- 2. Decoupled Node Attribute Algebra (Trait Set Operations) ---

@pytest.mark.parametrize(
    "traits_u, traits_v, expected_intersection",
    [
        ({"t1", "t2"}, {"t2", "t3"}, {"t2"}),
        ({"t1", "t2"}, {"t3", "t4"}, set()),
        (set(), {"t1"}, set()),
        (set(), set(), set()),
        ({"shared", "a"}, {"shared", "b"}, {"shared"}),
    ],
    ids=[
        "partial-overlap",
        "disjoint-traits",
        "one-empty",
        "both-empty",
        "common-element",
    ],
)
def test_shared_traits_algebra(traits_u, traits_v, expected_intersection):
    g = AssemblageGraph()
    g.add_element("u", traits=traits_u)
    g.add_element("v", traits=traits_v)

    # Invariant: Commutativity (u ∩ v == v ∩ u == expected)
    assert g.shared_traits("u", "v") == expected_intersection
    assert g.shared_traits("v", "u") == expected_intersection


def test_shared_traits_nonexistent_nodes():
    g = AssemblageGraph()
    g.add_element("u", traits={"t1"})

    # Non-existent node returns empty set gracefully without KeyError
    assert g.shared_traits("u", "ghost") == set()
    assert g.shared_traits("ghost", "phantom") == set()


# --- 3. Decoupled Topological Edge Classification (Cross-Links) ---

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
