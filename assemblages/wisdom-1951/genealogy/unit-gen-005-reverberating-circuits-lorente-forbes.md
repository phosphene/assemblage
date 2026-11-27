---
registry.core/id: unit-gen-005-reverberating-circuits-lorente-forbes
corpus: wisdom-1951
line: genealogy
section: "§4.3 Electroneural Analogies (pp. 13–15)"
epistemic-status: :clinical-embodiment
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v4-deliberative-seme"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme"]
---

# GEN-005 — Closed Internuncial Chains: The Lineage of Reverberating Neural Circuits

## 1. Verbatim Textual Witness (zero ellipses)

> "when he spoke of " a sort of central reverberation " to account for the after-discharge characteristics of reflex contractions; the phrase " self-re-exciting" chains of neurons was used by Lorente de N6,5 working independently."

> "A chain is reverberating or self-reexciting if in a closed circuit one neuron fires, thereby leading its neighbour and in turn the next neighbour to fire, and so on, until the last one makes the first one fire again."

*Source: Wisdom (1951), pp. 14–15. Footnote anchors: Alexander Forbes (1929, "The Mechanism of Reaction", in *The Foundations of Experimental Psychology*, p. 152); S. W. Ranson and J. C. Hinsey (1930, "Reflexes in Hind Limbs of Cats after Transection of Spinal Cord at Various Levels", *Amer. Journ. Physiol.*, 94, 490–491); Rafael Lorente de Nó (1922, *Trabajos del laboratorio de investigaciones biológicas de la Universidad de Madrid*, 20, 41–47; and 1938, *J. Neurophysiol.*, 1, 207–244); E. D. Adrian (1947, *The Physical Background of Perception*, pp. 89–92); Wiener (1948, *Cybernetics*, p. 146). Exact contiguous substrings of `primary/source-text.txt`.*

## 2. Natural Language Gloss (Wisdom's epistemic move)

Wisdom traces the exact neuroanatomical lineage of the **reverberating circuit**—the biological structure that allows short-term memory, continuous reflex after-discharge, and cyclical neural oscillations to persist without external input:

1. **Forbes (1929):** First posited "a sort of central reverberation" to explain why motor reflexes continue firing long after the stimulus ceases (after-discharge).
2. **Ranson & Hinsey (1930):** Formalized the concept in feline spinal cord studies, coining the term "reverberating chains of neurons".
3. **Lorente de Nó (1922 / 1938):** Working independently in Madrid and the Rockefeller Institute, provided the anatomical histology showing closed circular loops of internuncial neurons ("self-re-exciting chains").

Wisdom connects this histological foundation to Wiener’s psychopathological analogy:
- **Normal function:** Circulating pulses in closed loops act as transient working memory, storing signals dynamically rather than chemically.
- **Pathological fixation:** When loop transmission gain exceeds unity or inhibitory control fails, reverberation locks the system into circular obsessions, melancholia, or functional psychoses.
- **Surgical leucotomy (Wiener 1948):** Wisdom notes that prefrontal lobotomy operates mechanically like an engineer cutting a runaway feedback wire in a jammed telephone switchboard, severing the reverberating internuncial tracts to halt runaway affective loops.

## 3. Formal Symbolic Logic

Closed internuncial cycle $\mathcal{C} = (v_1, v_2, \dots, v_k, v_1)$ with synaptic transmission weights $w_i \in (0, 1]$ and conduction delays $\tau_i$:

$$\nu_i(t) = \Theta\left(w_{i, i-1} \cdot \nu_{i-1}(t - \tau_i) - \theta_i\right)$$

Loop gain condition for regenerative reverberation:

$$\mathcal{G}_{\text{loop}} = \prod_{i=1}^k w_{i, i-1} \ge 1 \implies \nu_1(t + T_{\text{loop}}) = \nu_1(t) \quad (\text{Forbes-Ranson-Lorente Cycle})$$

Psychopathology as runaway loop gain vs Leucotomy as edge deletion:

$$\text{PathologicalFixation} \iff \mathcal{G}_{\text{loop}} \ge 1 \ \land\ \text{Inhibition}(\mathcal{C}) \to 0$$

$$\text{Leucotomy}(\mathcal{C}): \quad E \leftarrow E \setminus \{e_{ij}\} \implies \mathcal{G}_{\text{loop}} = 0 \quad (\text{Wiener Surgical Severance})$$

## 4. Relational Meta-Structure (EDN)

```clojure
{:genealogy/id "unit-gen-005-reverberating-circuits-lorente-forbes"
 :genealogy/target-claim "The neurophysiological substrate of memory and feedback is established by Forbes, Ranson & Hinsey, and Lorente de Nó: closed internuncial chains whose runaway gain models psychopathology and whose mechanical severance models leucotomy"
 :genealogy/epoch 1951
 :genealogy/epistemic-status :clinical-embodiment
 :genealogy/precursors ["unit-gen-003-pathological-hunting-ataxia-brazier-thompson"
                        "unit-gen-004-ultrastability-cartesian-ceiling-ashby-cannon"]
 :genealogy/successors ["unit-gen-006-universals-calculus-craik-mcculloch-pitts"
                        "unit-gen-007-open-systems-eeg-scansion-bertalanffy-walter"]
 :genealogy/references
 [{:author "Alexander Forbes" :year 1929
   :source "The Mechanism of Reaction"
   :role :first-hint-central-reverberation}
  {:author "S. W. Ranson, J. C. Hinsey" :year 1930
   :source "Reflexes in Hind Limbs of Cats"
   :role :reverberating-chains-coinage}
  {:author "R. Lorente de Nó" :year 1938
   :source "Analysis of the Activity of the Chains of Internuncial Neurons"
   :role :anatomical-closed-circuits}
  {:author "E. D. Adrian" :year 1947
   :source "The Physical Background of Perception"
   :role :frequency-modulation-witness}
  {:author "Norbert Wiener" :year 1948
   :source "Cybernetics"
   :role :telephone-overload-leucotomy-analogy}]
 :genealogy/epistemic-delta "anatomical histology grounds feedback loops in physical neural tissue"
 :genealogy/wisdom-synthesis :histological-loop-grounding}
```
