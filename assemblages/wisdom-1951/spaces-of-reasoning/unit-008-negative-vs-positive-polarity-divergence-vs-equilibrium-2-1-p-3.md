---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-008-negative-vs-positive-polarity-divergence-vs-equilibrium-2-1-p-3
id: CR-WISDOM-U008
title: "CR-WISDOM-U008 — Negative vs. Positive Polarity: Divergence vs. Equilibrium (§2.1, p. 3)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', The British Journal for the Philosophy of Science, Vol. II, No. 5, May 1951, p. 3"
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v5-atomic-decomposition"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme"]
corpus_locus:
  source_file: "assemblages/wisdom-1951/primary/source-text.txt"
  sha256: "fb29b8c188d305d98fa846df7729c1211e8b7e47d577c2abaf765f0d920a6981"
  printed_folio: 3
  doc_page_index: 3
---

# CR-WISDOM-U008 — Negative vs. Positive Polarity: Divergence vs. Equilibrium (§2.1, p. 3)

## 1. Verbatim Text (zero ellipses)

> 2.I Feed-Back, Positive and Negative. These are all examples of 'negative feed-back '. In all of them the work done by a source of power is controlled-the machine is prevented from running away- by a mechanism that checks it and keeps the rate at which energy is expended hovering round a certain quantity. This feed-back is 'negative' because the work done by the feed-back mechanism opposes the working of the main driving force. Positive feed-back is also possible. Suppose, for instance, that instead of having a balloon in the gas pipe of the incubator, we have one outside, fixed in such a way that, as it expands through being heated, it pushes a lever of a tap, thus partially opening the tap. This will increase the heat of the incubator, and warm the balloon still more, so that the tap will be opened further; and so the process will continue.
> (§2.1, p. 3)

## 2. Epistemic Gloss & Philosophical Carve

Wisdom establishes the polarity bifurcation of feedback loops:
1. **Negative Polarity → Equilibrium:** Oppositional work holds the system in a hovering regime, preventing runaway.
2. **Positive Polarity → Divergence:** Reinforcing work compounds deviation — the incubator balloon progressively opens its own gas tap in an accelerating cumulative loop.

## 3. Formal Predicate Logic

$$\text{Negative}(m) \iff \text{Opposes}(W_{fb}, F_{drive}) \implies \text{HoveringEquilibrium}$$
$$\text{Positive}(m) \iff \text{Reinforces}(W_{fb}, F_{drive}) \implies \text{RunawayDivergence}$$

## 4. Decomposed Semiotic S-Expressions

### 4.1 Firstness: Qualitative Phenomenon (Seme)

```clojure
{:seme/firstness
 {:quality/phenomenon :opposition-of-feed-back-to-driving-force
  :perceptual/metaphor "A governor checking a runaway engine, holding energy expenditure hovering round a quantity"}}
```

### 4.2 Secondness: Empirical Encounter & Historical Anchors (Pheme)

```clojure
{:pheme/secondness
 {:encounter :polarity-of-feed-back-loop
  :historical-anchors [:wisdom-1951-feed-back :cannon-1932-homeostasis :ashby-1948]}}
```

### 4.3 Thirdness: Invariant Delome Rules & Causal Habits (Delome)

```clojure
{:delome/thirdness
 [(invariant :feedback/polarity #{:negative :positive})
  (invariant :system/regime #{:hovering-equilibrium :runaway-divergence})
  (rule :polarity-bifurcation
    (branch :feedback/polarity
            :negative :hovering-equilibrium
            :positive :runaway-divergence))]}
```

### 4.4 Conceptual Term Definitions

```clojure
{:term/definitions
 [#:term{:name :negative-feed-back
         :def "Feed-back whose mechanism opposes the main driving force, checking the machine and keeping energy expenditure hovering round a certain quantity"}
  #:term{:name :positive-feed-back
         :def "Feed-back that reinforces the main driving force, so that deviation from a quantity compounds rather than corrects"}]}
```

## 5. Verbatim Witness Register

```clojure
{:witness/selection
 {:witness/text "These are all examples of 'negative feed-back '. In all of them the work done by a source of power is controlled-the machine is prevented from running away- by a mechanism that checks it and keeps the rate at which energy is expended hovering round a certain quantity. This feed-back is 'negative' because the work done by the feed-back mechanism opposes the working of the main driving force."
  :witness/folio 3
  :witness/zero-ellipses? true}}
```
## 6. Literate Semantic Demonstration & Bounded Synthesis

### 6.1 The Dialectical Demarcation
Wisdom sets forth the fundamental polarity bifurcation of cybernetic dynamics: negative feedback produces equilibrium, while positive feedback induces runaway divergence. Negative feedback performs oppositional work that clamps explosive dissipation and confines system state to a bounded envelope. Positive feedback performs additive work that reinforces existing displacement, driving variables exponentially toward capacity limits or destruction (illustrated by the heating balloon lever opening its own gas tap).

### 6.2 Dynamical Formalization & Invariant Rules
Section 3 models this polarity through energetic alignment: $\text{Opposes}(W_{\text{fb}}, F_{\text{drive}}) \implies \text{HoveringEquilibrium}$, whereas $\text{Reinforces}(W_{\text{fb}}, F_{\text{drive}}) \implies \text{RunawayDivergence}$. The Clojure rule `:polarity-bifurcation` in Section 4.3 maps feedback polarity directly to dynamical regime: negative polarity selects `:hovering-equilibrium`, while positive polarity triggers `:runaway-divergence`.

### 6.3 Epistemic Bounds & Negative Horizon
1. **Polarity is Dynamical, Not Normative:** Positive feedback is not an engineering error; it is a mathematical regime of explosive growth required for rapid state transitions (e.g. action potentials, controlled power ramp-up).
2. **Destruction Threshold:** Without negative feedback damping, unconstrained positive feedback inevitably terminates in physical rupture or saturation.
