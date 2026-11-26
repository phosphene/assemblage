---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-005-error-without-constancy-the-clock-s-inherent-imprecision-2-0-p-3
id: CR-WISDOM-U005
title: "CR-WISDOM-U005 — Error-Without-Constancy: The Clock's Inherent Imprecision (§2.0, p. 3)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', The British Journal for the Philosophy of Science, Vol. II, No. 5, May 1951, p. 3"
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

# CR-WISDOM-U005 — Error-Without-Constancy: The Clock's Inherent Imprecision (§2.0, p. 3)

## 1. Verbatim Text (zero ellipses)

> Clearly the clock does not keep exact time. It is nearly always going slightly too fast or slightly too slow-only in between is it just right.
> (§3, p. 3)

## 2. Epistemic Gloss & Philosophical Carve

Wisdom articulates the foundational cybernetic ontological principle of error-actuated regulation:
1. **The Inherent Imprecision of Homeostasis:** A feedback regulator cannot maintain an absolute, static point. It operates strictly by sensing deviation; hence deviation (error) is a structural precondition of correction.
2. **Hovering Equilibrium:** The system hovers around its target value through slight alternating errors (slightly too fast, slightly too slow), crossing the ideal only in transient passing.

## 3. Formal Predicate Logic

Let $\theta(t)$ be the system parameter and $\theta^*$ be the ideal target. Let $e(t) = \theta(t) - \theta^*$:
$$\forall t, \; \exists \delta > 0 : |e(t)| > 0 \quad \text{a.e.}$$
$$e(t_1) > 0 \land e(t_2) < 0 \implies \exists t^* \in (t_1, t_2) : e(t^*) = 0$$
$$\text{RegulatoryEquilibrium}(\theta) \equiv \lim_{T \to \infty} \frac{1}{T} \int_0^T e(t) dt \approx 0 \land \forall t, e(t) \not\equiv 0$$

## 4. Decomposed Semiotic S-Expressions

### 4.1 Firstness: Qualitative Phenomenon (Seme)

```clojure
{:seme/firstness
 {:quality/phenomenon :oscillation-around-ideal
  :perceptual/metaphor "A clock pendulum or escapement that is nearly always slightly too fast or slightly too slow, just right only in between"}}
```

### 4.2 Secondness: Empirical Encounter & Historical Anchors (Pheme)

```clojure
{:pheme/secondness
 {:encounter :clockwork-escapement-hovering
  :historical-anchors [:wisdom-1951-clock-imprecision :cannon-1932-homeostasis]}}
```

### 4.3 Thirdness: Invariant Delome Rules & Causal Habits (Delome)

```clojure
{:delome/thirdness
 [(invariant :error/sign-persistence #{:oscillating :static-offset})
  (invariant :error/exactness-status #{:never-a-static-state :attained})
  (rule :hovering-error-without-constancy
    (implies (and (= :error/sign-persistence :oscillating)
                  (= :error/exactness-status :never-a-static-state))
             :hovering-equilibrium))]}
```

### 4.4 Conceptual Term Definitions

```clojure
{:term/definitions
 [#:term{:name :error-without-constancy
         :def "Inherent imprecision manifesting as continual slight deviation to either side of an ideal, corrected only in passing"}
  #:term{:name :hovering-equilibrium
         :def "Dynamic maintenance achieved through small alternating corrections rather than rigid static rest"}
  #:term{:name :negative-feed-back
         :def "The class of mechanism, exemplified by the clock among others, whose oppositional work keeps energy expenditure hovering round a certain quantity"}]}
```

## 5. Verbatim Witness Register

```clojure
{:witness/selection
  {:witness/text "Clearly the clock does not keep exact time. It is nearly always going slightly too fast or slightly too slow-only in between is it just right."
   :witness/folio 3
   :witness/char-count 142
   :witness/zero-ellipses? true}}
```
