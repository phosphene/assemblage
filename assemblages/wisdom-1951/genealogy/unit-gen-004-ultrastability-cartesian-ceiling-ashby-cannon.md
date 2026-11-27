---
registry.core/id: unit-gen-004-ultrastability-cartesian-ceiling-ashby-cannon
corpus: wisdom-1951
line: genealogy
section: "§4.2 Autonomic Reorganisation in a Machine (pp. 11–13)"
epistemic-status: :boundary-demarcation
---

# GEN-004 — Ultrastability, Adaptation by Breakdown, and the Cartesian Causal Ceiling

## 1. Verbatim Textual Witness (zero ellipses)

> "Can a machine reorganise itself when faced with a difficulty ? Ashby has given a striking design for a machine with this capacity, which has actually been constructed."

> "Ashby also makes the interesting observation that, while the ordinary electronic machine can perform only those operations for which it was designed, the homeostat holds out the very real possibility of our making a machine capable of performing operations beyond those for which it was designed-and beyond the knowledge of its designer."

> "This would refute Descartes's principle that there must be at least as much reality and perfection in the cause as in the effect, a principle on which he based one of his proofs of the existence of God."

> "At this point it is worth drawing a distinction in connection with those processes that revert towards steady motion after disturbance. Some revert towards the same state of steady motion as they were in before being disturbed; but others are, as it were, content to aim at any steady motion. The one is illustrated by the steadiness of blood temperature; the other by bodily adjustments to a new diet, and by Ashby's machine with breaks :"

> "A simple breaking down of a completed machine is sufficient, by itself, to produce the objective features of adaptation by trial and error, at least in the simpler cases; i.e. every machine breaking down automatically re-adjusts its own internal organisation until adaptation is achieved."

*Source: Wisdom (1951), pp. 11–13. Footnote anchors: W. R. Ashby (1948, "Design for a Brain", *Electronic Engineering*, 20, 379–383); Ashby (1945, "The Physical Origin of Adaptation by Trial and Error", *Journ. Gen. Psychol.*, 32, 13–25); W. B. Cannon (1932, *The Wisdom of the Body*); Descartes' *Meditations* (Third Meditation). Exact contiguous substrings of `primary/source-text.txt`.*

## 2. Natural Language Gloss (Wisdom's epistemic move)

Wisdom turns to Ashby’s physical Homeostat to mark the limit of first-order negative feed-back: standard error-correction keeps variables within bounds only *if the wiring is already adapted to the environment*. But living organisms learn and rewire themselves.

Wisdom extracts three decisive philosophical insights from Ashby's apparatus:
1. **The Cartesian Refutation:** Classical philosophy (Descartes, *Meditations III*) claimed an effect cannot contain more reality or perfection than its total cause ($C \ge E$). Ashby’s Homeostat produces stable adaptive behaviors unforeseen by and beyond the knowledge of its builder, refuting the dogma that machines can only mirror their designer’s explicit foresight.
2. **Cannon vs. Ashby (Same vs. Any Steady State):** Wisdom establishes a crucial taxonomic distinction within homeostasis:
   - *Cannon’s Homeostasis:* Reversion to **the same** steady state (e.g. constant $37^\circ\text{C}$ blood temperature maintained by fixed negative feedback loops).
   - *Ashby’s Ultrastability:* Reversion to **any** steady state (adaptation to structural shifts, new diets, or severed connections through stochastic step-function parameter switches).
3. **Adaptation by Mechanical Breakdown:** Adaptation does not require a benevolent vital force or an omniscient designer; it arises mechanically when uniselectors break circuit contact upon instability and step through parameter space until a quiescent field configuration is randomly discovered.

## 3. Formal Symbolic Logic

First-order state space with parameter vector $P_k$:

$$\dot{\mathbf{x}}(t) = \mathbf{f}(\mathbf{x}(t), P_k)$$

Essential physiological bounds $\mathcal{B}_{\text{essential}} = \{\mathbf{x} \mid \|\mathbf{x}\|_{\infty} \le L_{\text{crit}}\}$.

Ashby step-function switching rule:

$$P_{k+1} = \begin{cases} P_k & \text{if } \mathbf{x}(t) \in \mathcal{B}_{\text{essential}} \\ g(P_k) & \text{if } \mathbf{x}(t) \notin \mathcal{B}_{\text{essential}} \quad (\text{Breakdown Reorganisation}) \end{cases}$$

Cannon Homeostasis vs Ashby Ultrastability:

$$\text{Cannon}(\mathcal{S}): \quad \lim_{t \to \infty} \mathbf{x}(t) = \mathbf{x}^*_{\text{fixed}} \qquad (\text{Reversion to THE SAME state})$$

$$\text{Ashby}(\mathcal{S}): \quad \lim_{t \to \infty} \mathbf{x}(t) \in \bigcup_{j} \mathcal{A}_j \subseteq \mathcal{B}_{\text{essential}} \qquad (\text{Reversion to ANY viable attractor})$$

Refutation of Descartes's causal perfection inequality:

$$\text{Knowledge}(\text{Designer}) \not\supseteq \text{Behaviors}(\text{Homeostat}) \implies \text{Perfection}(E) \not\le \text{Perfection}(C)$$

## 4. Relational Meta-Structure (EDN)

```clojure
{:genealogy/id "unit-gen-004-ultrastability-cartesian-ceiling-ashby-cannon"
 :genealogy/target-claim "Ashby's Homeostat refutes Descartes' causal perfection ceiling and extends Cannon's homeostasis from reversion to the same steady state to reversion to any steady state via step-function breakdown"
 :genealogy/epoch 1951
 :genealogy/epistemic-status :boundary-demarcation
 :genealogy/precursors ["unit-gen-000-dual-genesis-ashby-wiener"
                        "unit-gen-001-thermodynamic-steady-state-cannon-hill"]
 :genealogy/successors ["unit-gen-005-reverberating-circuits-lorente-forbes"
                        "unit-gen-008-genealogical-synthesis-model-ceiling"]
 :genealogy/references
 [{:author "W. R. Ashby" :year 1948
   :source "Design for a Brain"
   :journal "Electronic Engineering" :volume 20 :pages "379-383"
   :role :homeostat-physical-apparatus}
  {:author "W. R. Ashby" :year 1945
   :source "The Physical Origin of Adaptation by Trial and Error"
   :journal "Journ. Gen. Psychol." :volume 32 :pages "13-25"
   :role :adaptation-by-breakdown-principle}
  {:author "W. B. Cannon" :year 1932
   :source "The Wisdom of the Body"
   :role :homeostasis-same-state-comparator}
  {:author "René Descartes" :year 1641
   :source "Meditationes de Prima Philosophia"
   :role :refuted-causal-ceiling}]
 :genealogy/epistemic-delta "homeostatic rewiring without designer foresight"
 :genealogy/wisdom-synthesis :ultrastable-adaptation-by-breakdown}
```
