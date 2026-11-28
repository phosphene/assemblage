---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-004-seesaw-vs-feedback-pure-oscillation-vs-dynamic-maintenance-2-0-p-2
id: CR-WISDOM-U004
title: "CR-WISDOM-U004 — Seesaw vs. Feedback: Pure Oscillation vs. Dynamic Maintenance (§2.0, p. 2)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', The British Journal for the Philosophy of Science, Vol. II, No. 5, May 1951, p. 2"
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v4-deliberative-seme"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme"]
corpus_locus:
  source_file: "assemblages/wisdom-1951/primary/source-text.txt"
  sha256: "fb29b8c188d305d98fa846df7729c1211e8b7e47d577c2abaf765f0d920a6981"
  printed_folio: 2
  doc_page_index: 2
---

# CR-WISDOM-U004 — Seesaw vs. Feedback: Pure Oscillation vs. Dynamic Maintenance (§2.0, p. 2)

## 1. Verbatim Text (zero ellipses)

> These electronic machines are not merely 'automatic', in the sense in which a seesaw is automatic. They embody a mechanism known as "feed-back ", which is of the highest importance; they thus differ from purely Newtonian machines (the nature of this difference requires elucidation and will be discussed in the final section).
> (§2, p. 2)

## 2. Epistemic Gloss & Philosophical Carve

Wisdom distinguishes simple passive periodicity from active dynamic maintenance:
1. **The Seesaw Fallacy:** A seesaw is automatic in that potential energy shifts mechanically to kinetic energy and back, but it has no capacity to sense its own state or modify its energy expenditure to oppose disturbance.
2. **Newtonian vs. Cybernetic Machines:** A Newtonian machine transmits force deterministically; a cybernetic machine incorporates an internal informational loop that feeds back a fraction of its output to modulate its input.

## 3. Formal Symbolic Logic

Let $\mathcal{M}_{\text{seesaw}}$ be a conservative harmonic oscillator and $\mathcal{M}_{\text{cybernetic}}$ be an error-actuated regulator.
Let $x(t) \in \mathbb{R}$ denote the angular displacement from horizontal equilibrium over time lattice $T$.
Let $x_{\text{target}} = 0$ denote the desired equilibrium setpoint.
Let $\mathcal{E}(x, x_{\text{target}}) = x(t) - x_{\text{target}}$ denote sensory deviation error.

Passive Conservative Oscillation (Newtonian Kinematics):

$$\mathcal{M}_{\text{seesaw}}: \frac{d^2 x}{dt^2} + \omega^2 x = 0 \implies \text{PassiveOscillation}(\mathcal{M}_{\text{seesaw}})$$

Active Error-Actuated Regulation (Cybernetic Feedback):

$$\mathcal{M}_{\text{cybernetic}}: \frac{dx}{dt} = f(x) - k \cdot \mathcal{E}(x, x_{\text{target}}) \implies \text{DynamicMaintenance}(\mathcal{M}_{\text{cybernetic}})$$

Demarcation Rule of Automaticity vs Cybernetic Regulation:

$$\text{Automatic}(\mathcal{M}) \land \neg \text{HasFeedback}(\mathcal{M}) \implies \text{NonCybernetic}(\mathcal{M})$$

The warrant of cybernetic regulation rests upon closed-loop error-sensing and energy redirection rather than passive conservative oscillation.

## 4. Decomposed Semiotic S-Expressions

### 4.1 Firstness: Qualitative Phenomenon (Seme)

```clojure
{:seme/firstness
 {:quality/phenomenon :passive-oscillation-vs-active-governance
  :perceptual/metaphor "A seesaw tipping back and forth blindly under gravity vs. a governor checking runaway speed"}}
```

### 4.2 Secondness: Empirical Encounter & Historical Anchors (Pheme)

```clojure
{:pheme/secondness
 {:encounter :seesaw-mechanical-periodicity
  :historical-anchors [:wisdom-1951-feed-back :newtonian-mechanics-contrast]}}
```

### 4.3 Thirdness: Invariant Delome Rules & Causal Habits (Delome)

```clojure
{:delome/thirdness
 [(invariant :machine/operational-genus #{:purely-newtonian :cybernetic-feedback})
  (rule :automaticity-demarcation
    (if (= :energy-redirection :absent)
      (= :machine/operational-genus :purely-newtonian)
      (= :machine/operational-genus :cybernetic-feedback)))]}
```

### 4.4 Conceptual Term Definitions

```clojure
{:term/definitions
 [#:term{:name :seesaw-automaticity
         :def "Passive mechanical periodicity driven by external energy without internal circular self-correction"}
  #:term{:name :cybernetic-difference
         :def "The fundamental operational departure from purely Newtonian machines constituted by internal feedback loops"}]}
```

## 5. Verbatim Witness Register

```clojure
{:witness/selection
  {:witness/text "These electronic machines are not merely 'automatic', in the sense in which a seesaw is automatic. They embody a mechanism known as \"feed-back \", which is of the highest importance; they thus differ from purely Newtonian machines (the nature of this difference requires elucidation and will be discussed in the final section)."
   :witness/folio 2
   :witness/char-count 326
   :witness/zero-ellipses? true}}
```
## 6. Literate Semantic Demonstration & Bounded Synthesis

### 6.1 The Dialectical Demarcation
Wisdom demarcates passive mechanical periodicity from active homeostatic regulation. A seesaw or an undamped pendulum oscillates indefinitely under conservative gravitational mechanics; it is 'automatic' only in the trivial sense that potential energy continuously converts to kinetic energy without human agency. However, the seesaw possesses no capacity to perceive external displacement or re-channel energy to resist perturbation. A cybernetic system departs from Newtonian machinery by introducing an internal informational path where output trajectory directly modulates energetic drive.

### 6.2 Dynamical Formalization & Invariant Rules
In the formal notation of Section 3, the seesaw is a conservative harmonic oscillator $\ddot{x} + \omega^2 x = 0$ yielding passive oscillation without goal-directed error correction. The cybernetic machine incorporates an error-actuated regulator $\dot{x} = f(x) - k \cdot \mathcal{E}(x, x^*)$ that dynamically drives deviations toward an equilibrium setpoint. The Clojure rule `:automaticity-demarcation` in Section 4.3 codifies this distinction: if energy redirection based on deviation is absent, the mechanism belongs strictly to the Newtonian genus; if present, it enters the cybernetic feedback class.

### 6.3 Epistemic Bounds & Negative Horizon
1. **Periodic Oscillation is Not Feedback:** Repetitive mechanical movement is frequently conflated with regulatory control. This unit establishes that periodicity without error-sensing carries zero cybernetic warrant.
2. **Kinematic vs Informational Limits:** The seesaw obeys pure Newtonian energy conservation; cybernetic feedback requires informational steering that is kinematically unconstrained by simple input momentum.
