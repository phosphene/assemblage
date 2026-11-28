---
registry.core/id: unit-gen-003-pathological-hunting-ataxia-brazier-thompson
corpus: wisdom-1951
line: genealogy
section: "§4.1 Feed-Back in the Nervous System (pp. 10–12)"
epistemic-status: :clinical-embodiment
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v4-deliberative-seme"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme"]
---

# GEN-003 — Pathological Hunting, Ataxia, and Positive Feed-Back: Clinical Tests of the Hypothesis

## 1. Verbatim Textual Witness (zero ellipses)

> "Wiener handled the cybernetic hypothesis in the usual scientific way. He deduced a consequence from it and sought a test. When a thermostat corrects an error it makes a new one, but a smaller one, and this too it corrects by making a further, but smaller error : the negative feed-back leads to closer and closer focus on the target. In certain conditions, however, it will lead to oscillation. This comes about if the self-correcting mechanism overshoots its mark too far, i.e. if the error it makes is commensurate with the one it is correcting. Wiener considered that if the organism has negative feed-back this deduction would hold, and that it should be possible to find an organic example ofoscillation."

> "a patient with cerebellar disease cannot bring a glass of water smoothly to his mouth; he swings it past on one side, tries to reverse the motion but succeeds in swinging it past on the other side. This test is typical of the disorderly motor performance of patients with cerebellar disease. The analogy with the behavior of a machiine with undamped feed-back is so vivid that we venture to suggest that the main function of the cerebellum is the control of the feed-back"

> "He makes the interesting suggestion that a positive amplification feed-back is to be found in some cases of renal disease, in which the renal damage leads to hypertension, which in turn causes further renal disease and still further hypertension-a vicious spiral which, once established, proves fatal."

*Source: Wisdom (1951), pp. 10–12. Footnote anchors: Wiener (1948, *Cybernetics*, p. 5, p. 116); Rosenblueth, Wiener, & Bigelow (1943, p. 20); Mary A. B. Brazier (1950, "Neural Nets and the Integration of Behaviour", in *Perspectives in Neuropsychiatry*, ed. Derek Richter, p. 36); Alan Thompson (personal communication on renal hypertension, p. 11 n. 1). Contiguous substrings of `primary/source-text.txt`.*

## 2. Natural Language Gloss (Wisdom's epistemic move)

Wisdom highlights Wiener’s methodological move as standard hypothetico-deductive science: if the nervous system relies on negative feed-back, then under conditions of excessive gain or transmission delay ($\tau$), it must exhibit **undamped hunting oscillation**. Rather than treating cybernetics as a loose descriptive analogy, Wiener and Rosenblueth looked for an empirical clinicopathologic realization and found it in **cerebellar tremor (ataxia)**.

Wisdom pairs this negative feed-back pathology with a crucial clinical counterpart:
1. **Undamped Negative Feed-Back (Hunting):** The cerebellar patient overcorrects motor position; the error response is commensurate with the original displacement ($|\epsilon_{n+1}| \ge |\epsilon_n|$), producing wild oscillations around the target (drinking glass).
2. **Runaway Positive Amplification Feed-Back (Vicious Spiral):** Prompted by Wisdom's taxonomy in §2.2, Alan Thompson supplied a biological exemplar of positive amplification feedback: in chronic renal disease, ischemic kidney damage triggers renin-angiotensin hypertension, which causes further vascular renal damage, escalating into a fatal positive feedback loop.

Through these two clinical realizations, cybernetic feedback ceases to be an engineering metaphor and becomes a diagnostic and physiological reality.

## 3. Formal Symbolic Logic

Deductive condition for hunting oscillation in negative feed-back:

$$\dot{x}(t) = -k\,x(t - \tau), \qquad k > 0, \ \tau > 0$$

Instability boundary (Hunting / Cerebellar Ataxia):

$$k\,\tau > \frac{\pi}{2} \implies \lim_{t \to \infty} |x(t)| \not\to 0 \quad (\text{Self-Sustaining Oscillation / Ataxia})$$

Runaway positive amplification feed-back (Renal Vicious Spiral):

$$\frac{d(\text{Hypertension})}{dt} = \alpha \cdot \text{RenalDamage}(t), \quad \frac{d(\text{RenalDamage})}{dt} = \beta \cdot \text{Hypertension}(t)$$

$$\alpha \beta > 0 \implies \lambda_{\text{max}} > 0 \implies \lim_{t \to \infty} \begin{bmatrix} \text{Hypertension} \\ \text{RenalDamage} \end{bmatrix} = \infty \quad (\text{Fatal Divergence})$$

Hypothetico-deductive confirmation:

$$\text{CNS} \in \mathcal{FB}^- \implies \exists \text{Regime}(\text{Gain}, \tau) : \text{Oscillation} \equiv \text{CerebellarTremor}$$

## 4. Relational Meta-Structure (EDN)

```clojure
{:genealogy/id "unit-gen-003-pathological-hunting-ataxia-brazier-thompson"
 :genealogy/target-claim "The cybernetic hypothesis is empirically confirmed by clinicopathologic breakdown: cerebellar ataxia as undamped negative feedback hunting, and renal hypertension as fatal positive amplification feedback"
 :genealogy/epoch 1951
 :genealogy/epistemic-status :clinical-embodiment
 :genealogy/precursors ["unit-gen-002-teleological-demarcation-northrop-wiener"]
 :genealogy/successors ["unit-gen-004-ultrastability-cartesian-ceiling-ashby-cannon"
                        "unit-gen-005-reverberating-circuits-lorente-forbes"]
 :genealogy/references
 [{:author "Norbert Wiener" :year 1948
   :source "Cybernetics"
   :role :deductive-hunting-hypothesis}
  {:author "Arturo Rosenblueth, Norbert Wiener, Julian Bigelow" :year 1943
   :source "Behavior, Purpose, and Teleology"
   :role :cerebellar-clinical-witness}
  {:author "Mary A. B. Brazier" :year 1950
   :source "Neural Nets and the Integration of Behaviour"
   :role :neurophysiological-pathology}
  {:author "Alan Thompson" :year 1951
   :source "Personal communication on renal hypertension"
   :role :positive-feedback-vicious-spiral}]
 :genealogy/epistemic-delta "deductive pathology confirms physical reality of biological feedback loops"
 :genealogy/wisdom-synthesis :pathological-bifurcation-confirmation}
```
