---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-010-the-amplification-threshold-auxiliary-energetic-tapping-2-2-p-4
id: CR-WISDOM-U010
title: "CR-WISDOM-U010 — The Amplification Threshold: Auxiliary Energetic Tapping (§2.2, p. 4)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', The British Journal for the Philosophy of Science, Vol. II, No. 5, May 1951, p. 4"
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v4-deliberative-seme"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme"]
corpus_locus:
  source_file: "assemblages/wisdom-1951/primary/source-text.txt"
  sha256: "fb29b8c188d305d98fa846df7729c1211e8b7e47d577c2abaf765f0d920a6981"
  printed_folio: 4
  doc_page_index: 4
---

# CR-WISDOM-U010 — The Amplification Threshold: Auxiliary Energetic Tapping (§2.2, p. 4)

## 1. Verbatim Text (zero ellipses)

> In none of the foregoing examples does a local source of energy arising from the feed-back mechanism play a part. The energy of the feed-back is derivative and redirected. But an additional source of energy may be exploited and utilised in the same sort of way. For example, in the combustion of coal, the lighted paper ignites some coal which releases vastly greater energy which fires more coal and so on. Again, in the uranium bomb, a neutron splits an atomic nucleus which releases energy and more neutrons which split more atomic nuclei and release more energy. Any chain-reaction mechanism is an illustration.
> (§4, p. 4)

## 2. Epistemic Gloss & Philosophical Carve

Wisdom introduces the crucial physical distinction between simple and amplified feedback:
1. **Simple vs. Amplified Feedback:** Simple feedback merely redirects a fraction of the existing main drive energy; amplified feedback triggers and releases an auxiliary, local reservoir of dormant energy.
2. **Chain Reactions & Catalytic Tapping:** A tiny input (a match, a neutron) triggers orders-of-magnitude larger releases, establishing that informational feedback can operate across energetic scale boundaries.

## 3. Formal Predicate Logic

Let $E_{\text{in}}$ be feedback signal energy and $E_{\text{rel}}$ be released energy:

$$\text{SimpleFeedback} \iff E_{\text{fb}} \le E_{\text{main}}$$

$$\text{AmplifiedFeedback} \iff E_{\text{released}} = \mathcal{G} \cdot E_{\text{trigger}}, \quad \mathcal{G} \gg 1$$

$$\text{ChainReaction} \implies \prod_i \mathcal{G}_i \to \infty$$

## 4. Decomposed Semiotic S-Expressions

### 4.1 Firstness: Qualitative Phenomenon (Seme)

```clojure
{:seme/firstness
 {:quality/phenomenon :catalytic-triggering-of-vast-reservoirs
  :perceptual/metaphor "A match lighting a coal furnace, or a single neutron splitting an atom to unleash a cascade"}}
```

### 4.2 Secondness: Empirical Encounter & Historical Anchors (Pheme)

```clojure
{:pheme/secondness
 {:encounter :auxiliary-local-energy-tapping
  :historical-anchors [:wisdom-1951-amplification :nuclear-fission-chain-reaction]}}
```

### 4.3 Thirdness: Invariant Delome Rules & Causal Habits (Delome)

```clojure
{:delome/thirdness
 [(invariant :energetics/type #{:simple-derivative :amplified-local})
  (rule :amplification-threshold
    (if (= :local-energy-source :exploited)
      (= :energetics/type :amplified-local)
      (= :energetics/type :simple-derivative)))]}
```

### 4.4 Conceptual Term Definitions

```clojure
{:term/definitions
 [#:term{:name :amplification-feed-back
         :def "A feedback mechanism in which a local auxiliary source of energy is tapped and modulated by the feedback signal"}
  #:term{:name :chain-reaction-catalysis
         :def "Cascading release of stored potential energy where each cycle yields more catalytic triggers than it consumed"}]}
```

## 5. Verbatim Witness Register

```clojure
{:witness/selection
  {:witness/text "In none of the foregoing examples does a local source of energy arising from the feed-back mechanism play a part. The energy of the feed-back is derivative and redirected. But an additional source of energy may be exploited and utilised in the same sort of way. For example, in the combustion of coal, the lighted paper ignites some coal which releases vastly greater energy which fires more coal and so on. Again, in the uranium bomb, a neutron splits an atomic nucleus which releases energy and more neutrons which split more atomic nuclei and release more energy. Any chain-reaction mechanism is an illustration."
   :witness/folio 4
   :witness/char-count 615
   :witness/zero-ellipses? true}}
```
## 6. Literate Semantic Demonstration & Bounded Synthesis

### 6.1 The Dialectical Demarcation
Wisdom crosses the crucial threshold separating simple feedback from amplification feedback. In simple feedback, control signals are constrained by the redirected energy of the primary drive. In amplification feedback, a microscopic control signal taps into a vast, dormant auxiliary reservoir of local potential energy—exemplified by a match igniting coal, a neutron triggering nuclear chain reactions, or a tiny grid voltage gating kilowatt plate currents.

### 6.2 Dynamical Formalization & Invariant Rules
Section 3 formalizes simple feedback by energetic bounded ratio ($E_{\text{fb}} \le E_{\text{main}}$), while amplification feedback is governed by high power gain ($E_{\text{released}} = \mathcal{G} \cdot E_{\text{trigger}}$ with $\mathcal{G} \gg 1$). Section 4.3 translates this into `:amplification-threshold`: when a local energy source is exploited, energetic tier shifts categorically to `:amplified-local`, liberating informational control from energetic constraints.

### 6.3 Epistemic Bounds & Negative Horizon
1. **Informational Decoupling:** Amplification allows minute informational signals (microwatts) to control massive mechanical outputs (megawatts), establishing cybernetics as an informational rather than thermodynamic science.
2. **Instability Vulnerability:** High-gain amplification dramatically exacerbates positive feedback hazards, turning minor noise into destructive runaway.
