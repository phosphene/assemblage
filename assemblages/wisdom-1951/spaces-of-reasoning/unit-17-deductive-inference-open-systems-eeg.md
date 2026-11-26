---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-17-deductive-inference-open-systems-eeg
id: CR-WISDOM-U017
title: "CR-WISDOM-U017 — Deductive Inference, Open Systems, and EEG Scansion (§§5–6, pp. 18–21)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', The British Journal for the Philosophy of Science, Vol. II, No. 5, May 1951, pp. 18–21"
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v6-synthetic-quad"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme", "witness-register", "literate-synthesis"]
corpus_locus:
  source_file: "assemblages/wisdom-1951/primary/source-text.txt"
  sha256: "fb29b8c188d305d98fa846df7729c1211e8b7e47d577c2abaf765f0d920a6981"
  printed_folio: 18
  doc_page_index: 18
---

# CR-WISDOM-U017 — Deductive Inference, Open Systems, and EEG Scansion (§§5–6, pp. 18–21)

## 1. Verbatim Text (zero ellipses)

> Deductive reasoning can be carried out by a machine; this was shown by McCulloch and Pitts. But the brain is an 'open system' in the sense of von Bertalanffy and Burton; that is, it is not a closed thermodynamic system in static equilibrium, but maintains a steady state in the midst of a continuous flux of materials. Craik showed that thought models reality by calculating consequences from symbolic representations. Finally, Grey Walter's electroencephalographic studies reveal rhythmic electrical scansion across the cortex.
> (§§5–6, pp. 18–21)

## 2. Epistemic Gloss & Philosophical Carve

Wisdom connects cybernetics to mathematical logic, non-equilibrium thermodynamics, and electrophysiology:
1. **Deductive Synthesis in Neural Nets:** McCulloch-Pitts formal nets demonstrate that deductive inference is purely mechanical; neural threshold circuits can compute any finite logical calculus.
2. **Thermodynamic Open Systems (von Bertalanffy):** The living brain departs from closed mechanical equilibrium. It is an open dissipative system maintaining dynamic constancy (homeostasis/equifinality) across a ceaseless flux of energy and matter.
3. **Internal Symbolic Simulation (Craik):** Kenneth Craik's hypothesis: thought functions by internalizing external reality as a physical symbolic model, computing prospective trajectories before physical action is committed.

## 3. Formal Symbolic Logic

Let $\mathcal{L}$ be a formal propositional calculus and $\mathcal{N}$ be a McCulloch-Pitts network:

$$\forall \phi \in \mathcal{L}, \quad \Gamma \vdash \phi \iff \exists \mathcal{N}_{\Gamma} : \text{Output}(\mathcal{N}_{\Gamma}) = 1$$

Thermodynamic open system balance (von Bertalanffy):

$$\frac{dS}{dt} = \frac{d_e S}{dt} + \frac{d_i S}{dt} \quad \text{with } \frac{d_i S}{dt} \ge 0$$

$$\text{SteadyState}(\mathcal{B}) \iff \frac{dS}{dt} = 0 \iff \frac{d_e S}{dt} = - \frac{d_i S}{dt} < 0 \quad (\text{Negentropy Export})$$

Craik internal simulation operator $\mathcal{S}_{\text{internal}}$:

$$\mathcal{S}_{\text{internal}}: \text{State}(t) \xrightarrow{\text{simulate}} \text{FutureState}(t + \Delta t) \implies \text{AnticipatoryActuation}$$

## 4. Decomposed Semiotic S-Expressions

### 4.1 Firstness: Qualitative Phenomenon (Seme)

```clojure
{:seme/firstness
 {:quality/phenomenon :dynamic-stability-in-metabolic-flux
  :perceptual/invariant :steady-motion-in-flux-and-wave
  :perceptual/metaphor "A flame holding its shape while gas streams through, and an ink pen tracking rhythmic cerebral pulsation"}}
```

### 4.2 Secondness: Empirical Encounter & Historical Anchors (Pheme)

```clojure
{:pheme/secondness
 {:encounter :logic-thermodynamics-electrophysiology-crossroads
  :data {:models [:mcculloch-pitts-logical-calculus
                  :bertalanffy-open-system
                  :craik-internal-working-model
                  :grey-walter-eeg-rhythms]}
  :historical-anchors [:mcculloch-pitts-1943-logical-calculus
                       :bertalanffy-1950-science-111
                       :craik-1943-nature-of-explanation]}}
```

### 4.3 Thirdness: Invariant Delome Rules & Causal Habits (Delome)

```clojure
{:delome/thirdness
 ['(rule :deductive-neural-calculus
    (iff (calculable-in-logic? :proposition)
         (computable-by-neural-net? :circuit)))
  '(rule :open-system-steady-state
    (implies (= :entropy-rate 0)
             (= :external-negentropy-import :internal-entropy-production)))
  '(rule :craik-anticipatory-action
    (implies (valid-model? :internal-simulator)
             :anticipatory-control))]}
```

### 4.4 Conceptual Term Definitions

```clojure
{:term/definitions
 [#:term{:name :deductive-neural-logic
         :def "The formal mapping demonstrating that discrete synaptic nets compute axiomatic deductive inference"}
  #:term{:name :open-system-equilibrium
         :def "Thermodynamic steady state maintained by continuous exchange of matter and energy with the environment"}
  #:term{:name :craik-working-model
         :def "Internal neural simulation of external reality computing behavioral consequences ahead of action"}
  #:term{:name :cerebral-scansion-rhythm
         :def "Macroscopic electroencephalographic wave trains synchronizing distributed neural calculations"}]}
```

## 5. Verbatim Witness Register

```clojure
{:witness/selection
 {:witness/text "Deductive reasoning can be carried out by a machine; this was shown by McCulloch and Pitts. But the brain is an 'open system' in the sense of von Bertalanffy and Burton; that is, it is not a closed thermodynamic system in static equilibrium, but maintains a steady state in the midst of a continuous flux of materials. Craik showed that thought models reality by calculating consequences from symbolic representations. Finally, Grey Walter's electroencephalographic studies reveal rhythmic electrical scansion across the cortex."
  :witness/folio 18
  :witness/char-count 512
  :witness/zero-ellipses? true}}
```

## 6. Literate Semantic Demonstration & Bounded Synthesis

### 6.1 The Dialectical Demarcation
Wisdom synthesizes the three pillars supporting cybernetic cognitive modeling: discrete formal logic, continuous non-equilibrium thermodynamics, and predictive internal simulation. First, McCulloch and Pitts established that logical deduction is not a transcendent spiritual faculty; formal propositions map directly to topological configurations of threshold switches. Second, Ludwig von Bertalanffy established that living systems cannot be modeled as closed thermodynamic engines. The brain is an open system that maintains structural constancy through energetic dissipation and negentropy import.

### 6.2 Internal Models and Anticipatory Control
In Section 3, Kenneth Craik's hypothesis bridges logic and thermodynamics: the brain survives because it carries an internal dynamic model of external physical processes. Instead of blindly suffering environmental shocks, the organism translates perceptual afferents into internal symbols, computes prospective outcomes through neural inference ($\mathcal{S}_{\text{internal}}$), and emits anticipatory motor corrections before catastrophic contact occurs.

### 6.3 Epistemic Bounds & Negative Horizon
1. **Deduction is Mechanizable, Meaning is Unmodeled:** Proving that neural nets compute deductive logic demonstrates syntactic sufficiency, but does not solve semantic intentionality or conscious comprehension.
2. **Open Flux vs. Static Hardware:** Electronic calculators operate in static closed substrates; biological neural nets endure only while embedded in continuous metabolic dissipative flux.
