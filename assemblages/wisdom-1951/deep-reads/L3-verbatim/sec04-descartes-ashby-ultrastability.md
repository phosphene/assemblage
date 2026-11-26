---
uri: work/topic-102248-cybernetics/docs/deep-reads/wisdom-1951-hypothesis-of-cybernetics/L3-verbatim/sec04-descartes-ashby-ultrastability
id: wisdom-1951-L3-sec04-descartes-ashby
title: "L3 Verbatim + Commentary — Section 4.2: The Homeostat, Uniselector Break, and the Refutation of Descartes"
type: deep-read-L3
status: living
owner: edphos
author: feelingflowingbot
date: 2026-11-24
source:
  author: "J. O. Wisdom"
  title: "The Hypothesis of Cybernetics"
  journal: "BJPS"
  year: 1951
  section: "4.2"
  page: 12
tags:
  - verbatim
  - L3-commentary
  - ultrastability
  - homeostat
  - descartes
  - cannon
related:
  - work/topic-102248-cybernetics/docs/deep-reads/wisdom-1951-hypothesis-of-cybernetics/L2-formalization/sec04-adaptation-neural-nets-recognition
  - work/topic-102248-cybernetics/scripts/ashby_homeostat.py
  - work/topic-102248-cybernetics/tests/test_ashby_homeostat.py
---

# L3 Verbatim: The Homeostat, Uniselector Break, and the Refutation of Descartes

*[read-together — Wisdom 1951 BJPS 2(5), p. 12]*

## 1. The Uniselector Mechanism (verbatim)

> "Hence, so long as instability remains, the uniselectors alter the feed-backs until eventually a stable arrangement comes about. If we knock out some of the uniselectors and alter the feed-backs by hand, the remaining uniselectors will find a stable arrangement. Ashby and his colleagues even played several tricks on the homeostat. For instance they crossed the wires from the mains, whereupon the magnets again achieved stability. In short the homeostat adapted itself to new conditions."

## 2. The Descartes Footnote (verbatim, footnote 1)

> "Ashby also makes the interesting observation that, while the ordinary electronic machine can perform only those operations for which it was designed, the homeostat holds out the very real possibility of our making a machine capable of performing operations beyond those for which it was designed—and beyond the knowledge of its designer.'"
>
> "¹ This would refute Descartes's principle that there must be at least as much reality and perfection in the cause as in the effect, a principle on which he based one of his proofs of the existence of God."

## 3. The Two Classes of Reversion (verbatim)

> "At this point it is worth drawing a distinction in connection with those processes that revert towards steady motion after disturbance. Some revert towards the same state of steady motion as they were in before being disturbed; but others are, as it were, content to aim at any steady motion. The one is illustrated by the steadiness of blood temperature; the other by bodily adjustments to a new diet, and by Ashby's machine with breaks :"

## 4. Ashby's Trial-and-Error Citation (verbatim, quoting Ashby 1945)

> "A simple breaking down of a completed machine is sufficient, by itself, to produce the objective features of adaptation by trial and error, at least in the simpler cases; i.e. every machine breaking down automatically re-adjusts its own internal organisation until adaptation is achieved."

*(Footnote 2: W. R. Ashby, "The Physical Origin of Adaptation by Trial and Error", The Journal of General Psychology, 1945, 32, 24. Footnote 3: W. B. Cannon, The Wisdom of the Body, London, 1932.)*

## 5. Why This Passage Matters

1. **The Cartesian threshold:** Descartes' causal principle (*causae non minor effectus perfectio*) held that the effect cannot exceed the cause in perfection. A machine operating "beyond the knowledge of its designer" violates this — the designer's *perfection* (foresight) is no longer the ceiling of the machine's behavior. Wisdom notes this principle underwrote one of Descartes' proofs of God's existence; the refutation therefore strikes at metaphysics, not merely at psychology of machines.
2. **The terminological question (Cannon):** Wisdom raises the open question of whether Ashby's ultrastability ("content to aim at *any* steady motion") falls under Cannon's "homeostasis," which Cannon primarily intended for reversion to *the same* steady state. Wisdom's answer is careful: "he does not seem to exclude from this heading processes leading to alternative states of steady" motion. This is the first-order/ultrastable distinction rendered as a terminology dispute — the platform records it as a live Frege-Tellman *sense* question (homeostasis vs. ultrastability).
3. **Robustness evidence:** The crossed-mains trick is an early adversarial perturbation test: the homeostat re-found stability under rewiring of its own energy supply. Wisdom treats this as behavioral confirmation of Ashby's reorganization thesis.

## 6. Technical Vocabulary Introduced

- **Uniselector:** rotary stepping switch performing the step-function re-randomization of feedback couplings.
- **Break:** the discrete reorganization event (distinct from continuous regulation).
- **Alternative steady states:** basins of attraction not identical to the pre-disturbance state.

## 7. Computational Binding

This passage binds directly to the verified implementation:

- `scripts/ashby_homeostat.py` — uniselector step-function as bounded re-randomization of the coupling matrix $A_{t+1} \sim \mathcal{U}(-1.2, 1.2)$.
- `tests/test_ashby_homeostat.py` — property tests: bounds viability, ultrastable step reorganization, determinism under seed, parameter invariance.

The "knock out some of the uniselectors" trick maps to *reduced reorganization capacity*: the platform's sweep harness should include a partial-uniselector ablation test — does the homeostat still find a stable arrangement with fewer step channels? *(Open derivation node: `derivations.md` §3.)*

## 8. Cross-References

- Platform wiki: `concepts/epistemic-levels` (Level 3 first-order commitment; Level 4 bridge analysis of the Cartesian refutation)
- Platform wiki: `concepts/specification-compression-relation` (Ashby 1945 quote as compressed mode; Wisdom's commentary as prolix mode)
- Downstream lineage: ultrastability → second-order cybernetics' self-organizing systems (von Foerster), and to active inference (Friston) as the modern re-derivation.
