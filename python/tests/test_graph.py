import unittest
import sys
from pathlib import Path

# Add src package to sys.path
sys.path.insert(0, str(Path(__file__).parent.parent))

from assemblage.graph import AssemblageGraph

class TestAssemblageGraph(unittest.TestCase):
    def test_assemblage_graph_creation(self):
        g = AssemblageGraph()
        g.add_element("organism_A", traits={"respiration", "negative-feedback"}, level=1)
        g.add_element("organism_B", traits={"negative-feedback", "homeostat"}, level=1)
        g.add_link("organism_A", "organism_B", kind="transversal-cross-link")

        self.assertIn("negative-feedback", g.shared_traits("organism_A", "organism_B"))
        self.assertEqual(len(g.find_cross_links()), 1)

    def test_assemblage_cycle_detection(self):
        g = AssemblageGraph()
        g.add_element("node_1")
        g.add_element("node_2")
        g.add_link("node_1", "node_2", kind="feed-forward")
        g.add_link("node_2", "node_1", kind="feed-back")

        cycles = g.simple_cycles()
        self.assertEqual(len(cycles), 1)
        self.assertEqual(set(cycles[0]), {"node_1", "node_2"})

if __name__ == "__main__":
    unittest.main()
