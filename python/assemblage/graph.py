"""
Assemblage Python Engine: Graph topology, transversal cycles, and hierarchy.
"""
from typing import Dict, Set, Tuple, List, Any
import networkx as nx

class AssemblageGraph:
    """
    Graph representation of genealogical trees with transversal cross-links
    and cycles.
    """
    def __init__(self):
        self.g = nx.MultiDiGraph()
        self.traits: Dict[str, Set[str]] = {}
        self.levels: Dict[str, int] = {}

    def add_element(self, element_id: str, traits: Set[str] = None, level: int = 0):
        self.g.add_node(element_id)
        self.traits[element_id] = set(traits or [])
        self.levels[element_id] = level

    def add_link(self, src: str, dst: str, kind: str = "genealogical", weight: float = 1.0):
        self.g.add_edge(src, dst, key=kind, kind=kind, weight=weight)

    def find_cross_links(self) -> List[Tuple[str, str, Dict[str, Any]]]:
        """Return all edges classified as transversal cross-links."""
        return [(u, v, d) for u, v, d in self.g.edges(data=True) if d.get("kind") == "transversal-cross-link"]

    def shared_traits(self, u: str, v: str) -> Set[str]:
        return self.traits.get(u, set()) & self.traits.get(v, set())

    def simple_cycles(self) -> List[List[str]]:
        """Detect directed feedback loops/cycles in the assemblage graph."""
        # Collapse multi-edges to simple DiGraph for cycle detection
        simple_g = nx.DiGraph(self.g)
        return list(nx.simple_cycles(simple_g))
