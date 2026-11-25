"""
work/assemblage/python/tests/test_graph.py

Pytest suite validating AssemblageGraph topology invariants:
- Clean functional test structure (no unittest.TestCase inheritance)
- Node attribute preservation and transversal cross-link filtering
- Directed feedback cycle detection
- Commutativity of trait intersection
"""

import pytest
from assemblage.graph import AssemblageGraph


def test_assemblage_graph_creation_and_cross_links():
    g = AssemblageGraph()
    g.add_element("organism_A", traits={"respiration", "negative-feedback"}, level=1)
    g.add_element("organism_B", traits={"negative-feedback", "homeostat"}, level=1)
    g.add_link("organism_A", "organism_B", kind="transversal-cross-link")

    assert "negative-feedback" in g.shared_traits("organism_A", "organism_B")
    assert len(g.find_cross_links()) == 1
    src, dst, data = g.find_cross_links()[0]
    assert src == "organism_A"
    assert dst == "organism_B"
    assert data["kind"] == "transversal-cross-link"


def test_assemblage_cycle_detection():
    g = AssemblageGraph()
    g.add_element("node_1")
    g.add_element("node_2")
    g.add_link("node_1", "node_2", kind="feed-forward")
    g.add_link("node_2", "node_1", kind="feed-back")

    cycles = g.simple_cycles()
    assert len(cycles) == 1
    assert set(cycles[0]) == {"node_1", "node_2"}


def test_trait_intersection_commutativity():
    g = AssemblageGraph()
    g.add_element("alpha", traits={"trait_1", "trait_2", "trait_3"})
    g.add_element("beta", traits={"trait_2", "trait_4"})

    shared_ab = g.shared_traits("alpha", "beta")
    shared_ba = g.shared_traits("beta", "alpha")

    assert shared_ab == {"trait_2"}
    assert shared_ab == shared_ba


def test_acyclic_assemblage_has_no_cycles():
    g = AssemblageGraph()
    g.add_element("root", level=0)
    g.add_element("child_1", level=1)
    g.add_element("child_2", level=1)
    g.add_link("root", "child_1", kind="genealogical")
    g.add_link("root", "child_2", kind="genealogical")

    assert len(g.simple_cycles()) == 0
