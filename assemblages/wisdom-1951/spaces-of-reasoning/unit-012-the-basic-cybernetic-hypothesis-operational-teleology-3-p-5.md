---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-012-the-basic-cybernetic-hypothesis-operational-teleology-3-p-5
id: CR-WISDOM-U012
title: "CR-WISDOM-U012 — The Basic Cybernetic Hypothesis: Operational Teleology and Central Nervous Mechanism (§3, p. 5)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', The British Journal for the Philosophy of Science, Vol. II, No. 5, May 1951, p. 5"
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v4-deliberative-seme"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme"]
corpus_locus:
  source_file: "assemblages/wisdom-1951/primary/source-text.txt"
  sha256: "fb29b8c188d305d98fa846df7729c1211e8b7e47d577c2abaf765f0d920a6981"
  printed_folio: 5
  doc_page_index: 5
---

# CR-WISDOM-U012 — The Basic Cybernetic Hypothesis: Operational Teleology and Central Nervous Mechanism (§3, p. 5)

## 1. Verbatim Text (zero ellipses)

> The basic hypothesis of cybernetics is that the chief mechanism of the central nervous system is one of negative feed-back. The field of study is not, however, restricted to feed-backs of the negative kind. Secondly, cybernetics makes the hypothesis that the negative feed-back mechanism explains 'purposive' and' adaptive' behaviour. Broadly speaking what the cybernetic model does for our outlook is to make us understand how purposive behaviour can be manifested by a machine, for " purposive " can now be defined in terms of negative feed-back.
> (§5, p. 5)

## 2. Epistemic Gloss & Philosophical Carve

Wisdom articulates the foundational core thesis of the entire cybernetic revolution:
1. **The CNS Mechanism Thesis:** The central nervous system's chief organizing and operational principle is closed-loop negative feedback.
2. **Operationalization of Teleology:** 'Purpose' and 'goal-seeking'—long dismissed as unscientific occult or vitalist concepts—are fully grounded in physical automata as error-compensating negative feedback towards a target state.

## 3. Formal Predicate Logic

Let $\text{TargetState}(S^*)$ and $\text{CurrentState}(S(t))$ define error $\mathcal{E}(t) = S(t) - S^*$:
$$\text{TeleologicalActuation}: \frac{dS}{dt} = - \mathbf{K} \cdot \mathcal{E}(t)$$
$$\lim_{t \to \infty} S(t) = S^* \implies \text{PurposiveBehavior}(\mathcal{M})$$
$$\text{Teleology}(\mathcal{M}) \equiv \text{NegativeFeedbackRegulator}(\mathcal{M}, S^*)$$

## 4. Decomposed Semiotic S-Expressions

### 4.1 Firstness: Qualitative Phenomenon (Seme)

```clojure
{:seme/firstness
 {:quality/phenomenon :error-compensation-toward-goal
  :perceptual/metaphor "A torpedo or eye steadily closing distance on a moving target, continuously eliminating error"}}
```

### 4.2 Secondness: Empirical Encounter & Historical Anchors (Pheme)

```clojure
{:pheme/secondness
 {:encounter :central-nervous-system-negative-feedback
  :historical-anchors [:wisdom-1951-basic-hypothesis :wiener-1948-cybernetics :rosenblueth-wiener-bigelow-1943]}}
```

### 4.3 Thirdness: Invariant Delome Rules & Causal Habits (Delome)

```clojure
{:delome/thirdness
 [(invariant :behaviour/type #{:teleological-purposive :ballistic-open-loop})
  (rule :operational-teleology-definition
    (iff (= :mechanism :negative-feed-back)
         (= :behaviour/type :teleological-purposive)))]}
```

### 4.4 Conceptual Term Definitions

```clojure
{:term/definitions
 [#:term{:name :basic-cybernetic-hypothesis
         :def "The hypothesis that the chief organizing mechanism of the central nervous system is negative feed-back"}
  #:term{:name :operational-teleology
         :def "The definition and manifestation of goal-directed, purposive behavior via continuous error-compensating feedback"}]}
```

## 5. Verbatim Witness Register

```clojure
{:witness/selection
  {:witness/text "The basic hypothesis of cybernetics is that the chief mechanism of the central nervous system is one of negative feed-back. The field of study is not, however, restricted to feed-backs of the negative kind. Secondly, cybernetics makes the hypothesis that the negative feed-back mechanism explains 'purposive' and' adaptive' behaviour. Broadly speaking what the cybernetic model does for our outlook is to make us understand how purposive behaviour can be manifested by a machine, for " purposive " can now be defined in terms of negative feed-back."
   :witness/folio 5
   :witness/char-count 548
   :witness/zero-ellipses? true}}
```
