---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-009-the-futility-objection-controlled-build-up-driverless-train-2-1-pp-3-4
id: CR-WISDOM-U009
title: "CR-WISDOM-U009 — The Futility Objection Overcome: Controlled Build-up and the Driverless Train (§2.1, pp. 3–4)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', The British Journal for the Philosophy of Science, Vol. II, No. 5, May 1951, pp. 3–4"
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v4-deliberative-seme"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme"]
corpus_locus:
  source_file: "assemblages/wisdom-1951/primary/source-text.txt"
  sha256: "fb29b8c188d305d98fa846df7729c1211e8b7e47d577c2abaf765f0d920a6981"
  printed_folio: 3
  doc_page_index: 3
---

# CR-WISDOM-U009 — The Futility Objection Overcome: Controlled Build-up and the Driverless Train (§2.1, pp. 3–4)

## 1. Verbatim Text (zero ellipses)

> This may seem to be a futile type of mechanism-for why not remove the balloon, open the tap, and have the maximum flow of gas from the outset ? The reason is that there are circumstances when it is desirable to build up some required state at a slow pace. Suppose, for instance, we wish to run an electric train without a driver; we must not turn on full power at once; a positive feed-back must therefore be used to keep turning on more power gradually.
> (§3, p. 3)

## 2. Epistemic Gloss & Philosophical Carve

Wisdom anticipates and refutes the philosophical objection that positive feedback is futile engineering:
1. **The Futility Objection:** Why employ positive feedback if one could simply open the throttle fully from the start?
2. **Controlled Acceleration Horizon:** Turning on full power instantly causes mechanical destruction or catastrophic jerk. Positive feedback provides an autonomous pacing mechanism that ramps up power gradually without requiring a human driver.

## 3. Formal Predicate Logic

Let $P_{\text{max}}$ be full power. Instant step-input: $\Delta P(0) = P_{\text{max}} \implies \text{Jerk} = \infty \implies \text{Damage}$.
Autonomous ramp via positive feedback:
$$\frac{dP}{dt} = \gamma P \implies P(t) = P_0 e^{\gamma t} \quad \text{clamped at } P_{\text{max}}$$
$$\text{Warrant}(\text{PositiveFeedback}) \equiv \text{AutonomousControlledRamp}(P, t)$$

## 4. Decomposed Semiotic S-Expressions

### 4.1 Firstness: Qualitative Phenomenon (Seme)

```clojure
{:seme/firstness
 {:quality/phenomenon :controlled-ramp-up-of-power
  :perceptual/metaphor "A driverless electric train smoothly gathering momentum without a violent jolt"}}
```

### 4.2 Secondness: Empirical Encounter & Historical Anchors (Pheme)

```clojure
{:pheme/secondness
 {:encounter :driverless-train-acceleration
  :historical-anchors [:wisdom-1951-positive-feed-back :controlled-ramp-up]}}
```

### 4.3 Thirdness: Invariant Delome Rules & Causal Habits (Delome)

```clojure
{:delome/thirdness
 [(invariant :acceleration/profile #{:instantaneous-step :gradual-ramp})
  (rule :controlled-ramp-necessity
    (implies (= :acceleration/profile :gradual-ramp)
             :positive-feedback-warranted))]}
```

### 4.4 Conceptual Term Definitions

```clojure
{:term/definitions
 [#:term{:name :controlled-build-up
         :def "Gradual attainment of a required high-energy state at an autonomously governed pace"}
  #:term{:name :driverless-train-control
         :def "Autonomous progressive power application preventing mechanical failure from sudden load application"}]}
```

## 5. Verbatim Witness Register

```clojure
{:witness/selection
  {:witness/text "This may seem to be a futile type of mechanism-for why not remove the balloon, open the tap, and have the maximum flow of gas from the outset ? The reason is that there are circumstances when it is desirable to build up some required state at a slow pace. Suppose, for instance, we wish to run an electric train without a driver; we must not turn on full power at once; a positive feed-back must therefore be used to keep turning on more power gradually."
   :witness/folio 3
   :witness/char-count 454
   :witness/zero-ellipses? true}}
```
