---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-007-the-simple-feedback-taxonomy-governors-escapements-thermostats-2-1-p-4
id: CR-WISDOM-U007
title: "CR-WISDOM-U007 — The Simple Feedback Taxonomy: Governors, Escapements, Thermostats (§2.1–2.2, pp. 3–4)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', The British Journal for the Philosophy of Science, Vol. II, No. 5, May 1951, pp. 3–4"
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v5-atomic-decomposition"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme"]
corpus_locus:
  source_file: "assemblages/wisdom-1951/primary/source-text.txt"
  sha256: "fb29b8c188d305d98fa846df7729c1211e8b7e47d577c2abaf765f0d920a6981"
  printed_folio: 3
  doc_page_index: 4
---

# CR-WISDOM-U007 — The Simple Feedback Taxonomy: Governors, Escapements, Thermostats (§2.1–2.2, pp. 3–4)

## 1. Verbatim Text (zero ellipses)

> mechanisms-governors, escapements, thermostats-are simply devices for re-directing some of the energy supplied by the source of power against the rest of that energy, and the positive feed-back mechan- isms redirect some of the energy towards increasing the energy supplied by the source of power-towards removing a blockage in fact. Both negative and positive types here described may be referred to as " simple feed-back mechanisms ". 2.2 Amplification Feed-Back. In none of the foregoing examples does a local source of energy arising from the feed-back mechanism play a part. The energy of the feed-back is derivative and redirected. But an additional source of energy may be exploited and utilised in the same sort of way. For example, in the combustion of coal, the lighted paper ignites some coal which releases vastly greater energy which fires more coal and so on.
> (§2.1–2.2, pp. 3–4)

## 2. Epistemic Gloss & Philosophical Carve

Wisdom completes the taxonomy of feedback energetics:
1. **Simple Feedback as Energy Redirection:** Both polarities share one structural signature — some of the source's energy is diverted. Negative redirection opposes the supply; positive redirection augments it (removing a blockage).
2. **The Amplification Boundary:** In simple feedback the diverted energy is derivative. When a *local* energy source is tapped (coal ignition, fission), the mechanism crosses into a distinct energetic tier: amplification feedback.

## 3. Formal Predicate Logic

$$\text{SimpleFeedback}(m) \iff \text{Redirect}(E_{fb}, E_{source}) \land \neg \text{TapsLocalEnergy}(m)$$

$$\text{Negative}(m) \equiv \text{Opposes}(E_{fb}, E_{drive}) \quad ; \quad \text{Positive}(m) \equiv \text{Augments}(E_{fb}, E_{drive})$$

$$\text{AmplificationFeedback}(m) \iff \text{TapsLocalEnergy}(m) \land \mathcal{G} \gg 1$$

## 4. Decomposed Semiotic S-Expressions

### 4.1 Firstness: Qualitative Phenomenon (Seme)

```clojure
{:seme/firstness
 {:quality/phenomenon :energy-redirection-against-itself
  :perceptual/metaphor "A governor diverting part of the steam against the rest of the steam"}}
```

### 4.2 Secondness: Empirical Encounter & Historical Anchors (Pheme)

```clojure
{:pheme/secondness
 {:encounter :simple-feedback-mechanism
  :historical-anchors [:wisdom-1951-simple-feedback :james-clerk-maxwell-governor :watt-centrifugal-governor]}}
```

### 4.3 Thirdness: Invariant Delome Rules & Causal Habits (Delome)

```clojure
{:delome/thirdness
 [(invariant :feedback/energetic-tier #{:simple-feed-back :amplification-feed-back :mixed-feed-back})
  (invariant :feedback/energy-source #{:derivative-main :local-auxiliary :hybrid})
  (rule :simple-feedback-criterion
    (iff (and (= :feedback/energetic-tier :simple-feed-back)
              (= :feedback/energy-source :derivative-main))
         :conservative-redirection))]}
```

### 4.4 Conceptual Term Definitions

```clojure
{:term/definitions
 [#:term{:name :simple-feed-back-mechanism
         :def "A device that redirects some of the energy supplied by the source of power against the rest of that energy, without exploiting any local source of energy arising from the feed-back mechanism"}
  #:term{:name :negative-simple-feed-back
         :def "Simple feed-back redirecting energy against the source's supply, as in governors, escapements, and thermostats"}
  #:term{:name :positive-simple-feed-back
         :def "Simple feed-back redirecting energy towards increasing the energy supplied by the source of power, towards removing a blockage"}]}
```

## 5. Verbatim Witness Register

```clojure
{:witness/selection
 {:witness/text "mechanisms-governors, escapements, thermostats-are simply devices for re-directing some of the energy supplied by the source of power against the rest of that energy, and the positive feed-back mechan- isms redirect some of the energy towards increasing the energy supplied by the source of power-towards removing a blockage in fact. Both negative and positive types here described may be referred to as \" simple feed-back mechanisms\"."
  :witness/folio 3
  :witness/zero-ellipses? true}}
```
## 6. Literate Semantic Demonstration & Bounded Synthesis

### 6.1 The Dialectical Demarcation
Wisdom unifies classic mechanical regulators—steam governors, clock escapements, and bi-metallic thermostats—under a rigorous definition of simple feedback. In all these devices, the energy that drives the self-regulatory loop is not supplied by an independent local generator, but is directly diverted from the main source of power driving the machine. Simple feedback is the conservative redirection of primary driving energy against or towards itself.

### 6.2 Dynamical Formalization & Invariant Rules
The predicate calculus in Section 3 formalizes simple feedback as $\text{SimpleFeedback}(m) \iff \text{Redirect}(E_{\text{fb}}, E_{\text{source}}) \land \neg \text{TapsLocalEnergy}(m)$. Redirection that opposes the prime mover defines negative feedback; redirection that accelerates supply defines positive feedback. The Clojure rule `:simple-feedback-criterion` in Section 4.3 asserts that when energetic tier is `:simple-feed-back` and the source is `:derivative-main`, the system is classified as `:conservative-redirection`.

### 6.3 Epistemic Bounds & Negative Horizon
1. **Conservative Energy Limit:** Simple feedback systems cannot amplify control signals beyond the total energetic budget of the main driving force.
2. **Derivative Nature:** The feedback signal carries no energetic sovereignty; shutting off the primary drive immediately extinguishes the regulatory loop.
