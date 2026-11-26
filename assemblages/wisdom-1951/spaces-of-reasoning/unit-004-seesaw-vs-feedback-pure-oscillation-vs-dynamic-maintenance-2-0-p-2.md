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

## 3. Formal Predicate Logic

Let $\mathcal{M}_{\text{seesaw}}$ be a conservative harmonic oscillator and $\mathcal{M}_{\text{cybernetic}}$ be an error-actuated regulator:
$$\mathcal{M}_{\text{seesaw}}: \frac{d^2 x}{dt^2} + \omega^2 x = 0 \implies \text{PassiveOscillation}$$
$$\mathcal{M}_{\text{cybernetic}}: \frac{dx}{dt} = f(x) - k \cdot \mathcal{E}(x, x_{\text{target}}) \implies \text{DynamicMaintenance}$$
$$\text{Automatic}(\mathcal{M}_{\text{seesaw}}) \land \neg \text{HasFeedback}(\mathcal{M}_{\text{seesaw}}) \implies \text{NonCybernetic}(\mathcal{M}_{\text{seesaw}})$$

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
  {:witness/text "These electronic machines are not merely 'automatic', in the sense in which a seesaw is automatic. They embody a mechanism known as "feed-back ", which is of the highest importance; they thus differ from purely Newtonian machines (the nature of this difference requires elucidation and will be discussed in the final section)."
   :witness/folio 2
   :witness/char-count 326
   :witness/zero-ellipses? true}}
```
