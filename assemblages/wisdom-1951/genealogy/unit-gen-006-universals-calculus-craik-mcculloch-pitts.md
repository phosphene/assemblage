---
registry.core/id: unit-gen-006-universals-calculus-craik-mcculloch-pitts
corpus: wisdom-1951
line: genealogy
section: "§4.4 / §4.5 Recognition of Universals & Deductive Inference (pp. 15–18)"
epistemic-status: :boundary-demarcation
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v4-deliberative-seme"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme"]
---

# GEN-006 — Universals and Logical Calculus: Craik, McCulloch, and Pitts

## 1. Verbatim Textual Witness (zero ellipses)

> "A particularly important application of these ideas is the attempt to explain recognition of universals, due to McCulloch and his collaborator Pitts. The problem is to know how it is that a square presented to vision at different distances and angles is recognisably the same square. The intellectualist school of philosophers regarded universals as 'innate ' or due to 'natural light'. The empiricists tried to build them up from particulars and naturally failed to produce a reasonable construction. But, if a machine can be made that will 'recognise' forms, we are on the road to a proper empirical account."

> "McCulloch's hypothesis is that the superior colliculus computes this, (i.e. presumably is fitted with negative feed-backs whose function is equivalent to the pencil-and-paper calculation)"

> "provides invariants of translation. If a square should appear anywhere in the field, the eyes turn until it is centered, and what they see is the same, whatever the initial position of the square."

> "a train of impulses in a regenerative loop preserves the form of the fact without reference to the one particular moment when it was experienced."

*Source: Wisdom (1951), pp. 15–18. Primary anchors: Walter Pitts & W. S. McCulloch (1947, "How We Know Universals: the Perception of Auditory and Visual Forms", *Bull. Math. Biophysics*, 9, 127–147); McCulloch & Pitts (1943, "A Logical Calculus of the Ideas Immanent in Nervous Activity", *Bull. Math. Biophysics*, 5, 115–133); K. J. W. Craik (1943, *The Nature of Explanation*, Ch. VI). Contiguous substrings of `primary/source-text.txt`.*

## 2. Natural Language Gloss (Wisdom's epistemic move)

Wisdom identifies the cybernetic attack on the classic philosophical problem of **universals** (how invariant shapes and propositions are recognized across varied presentations). Rather than choosing between Descartes’ innate ideas and British empiricism’s failed atomistic constructions, cybernetics offers a mechanical existence proof:

1. **McCulloch & Pitts (1947):** Formalized perception of geometric invariants via two physical hypotheses:
   - *Double Integration / Negative Feed-back:* The superior colliculus calculates the center of gravity of visual brightness and drives oculomotor negative feedback until the form is centered, providing translation invariance.
   - *Television Scansion (Craik 1943):* Visual spatial patterns are converted into temporal pulse trains swept across cortical sheets.
2. **Temporal Invariance via Regenerative Loops:** A circulating pulse train in a closed neural cycle preserves the *fact* of an experience detached from its chronological timestamp.
3. **McCulloch & Pitts (1943) Propositional Calculus:** Proves that an electroneural net of formal binary neurons possesses the necessary and sufficient algebraic power to compute any finite propositional deduction.

Wisdom boundaries the claim: the machine shows how recognition of universals *can be instantiated physically*, terminating the need for non-material mentalist faculties without claiming the biological brain is an idealized logic network.

## 3. Formal Symbolic Logic

Visual field brightness distribution $I(x, y)$ over retina $\Omega$:

$$\bar{x} = \frac{\iint_{\Omega} x \, I(x, y) \, dx dy}{\iint_{\Omega} I(x, y) \, dx dy}, \qquad \bar{y} = \frac{\iint_{\Omega} y \, I(x, y) \, dx dy}{\iint_{\Omega} I(x, y) \, dx dy}$$

Collicular oculomotor feedback error minimization:

$$\dot{\theta}_{\text{eye}} = -K \begin{bmatrix} \bar{x} - x_{\text{fix}} \\ \bar{y} - y_{\text{fix}} \end{bmatrix} \implies \lim_{t \to \infty} (\bar{x}, \bar{y}) = (x_{\text{fix}}, y_{\text{fix}}) \quad (\text{Translation Invariance})$$

Temporal invariance in regenerative loop $\mathcal{C}$:

$$\mathcal{T}(\text{Fact}) = \nu_{\mathcal{C}}(t) = \nu_{\mathcal{C}}(t + k \cdot T_{\text{loop}}) \qquad (\text{Form preserved invariant of } t_0)$$

McCulloch-Pitts logical threshold neuron equivalence:

$$\nu_i(t+1) = \Theta\left(\sum_j w_{ij} \nu_j(t) - \theta_i\right) \iff \text{PropositionalLogic}(\land, \lor, \neg)$$

## 4. Relational Meta-Structure (EDN)

```clojure
{:genealogy/id "unit-gen-006-universals-calculus-craik-mcculloch-pitts"
 :genealogy/target-claim "Recognition of universals and deductive inference are given physical mechanistic models: translation invariance via collicular feedback, temporal invariance via regenerative loops, and propositional logic via neural thresholds"
 :genealogy/epoch 1951
 :genealogy/epistemic-status :boundary-demarcation
 :genealogy/precursors ["unit-gen-002-teleological-demarcation-northrop-wiener"
                        "unit-gen-005-reverberating-circuits-lorente-forbes"]
 :genealogy/successors ["unit-gen-007-open-systems-eeg-scansion-bertalanffy-walter"
                        "unit-gen-008-genealogical-synthesis-model-ceiling"]
 :genealogy/references
 [{:author "Walter Pitts, W. S. McCulloch" :year 1947
   :source "How We Know Universals: the Perception of Auditory and Visual Forms"
   :role :visual-invariance-collicular-hypothesis}
  {:author "W. S. McCulloch, Walter Pitts" :year 1943
   :source "A Logical Calculus of the Ideas Immanent in Nervous Activity"
   :role :neural-net-propositional-calculus}
  {:author "K. J. W. Craik" :year 1943
   :source "The Nature of Explanation"
   :role :television-scansion-and-internal-model}]
 :genealogy/epistemic-delta "philosophical universals resolved into operational feedback and loop invariance"
 :genealogy/wisdom-synthesis :mechanistic-epistemology-grounding}
```
