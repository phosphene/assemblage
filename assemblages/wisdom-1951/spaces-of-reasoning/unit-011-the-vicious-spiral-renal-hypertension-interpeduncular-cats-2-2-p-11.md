---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-011-the-vicious-spiral-renal-hypertension-interpeduncular-cats-2-2-p-11
id: CR-WISDOM-U011
title: "CR-WISDOM-U011 — The Vicious Spiral: Renal Hypertension and Neurological Cauterisation (§2.2, p. 11)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', The British Journal for the Philosophy of Science, Vol. II, No. 5, May 1951, p. 11"
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v4-deliberative-seme"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme"]
corpus_locus:
  source_file: "assemblages/wisdom-1951/primary/source-text.txt"
  sha256: "fb29b8c188d305d98fa846df7729c1211e8b7e47d577c2abaf765f0d920a6981"
  printed_folio: 11
  doc_page_index: 11
---

# CR-WISDOM-U011 — The Vicious Spiral: Renal Hypertension and Neurological Cauterisation (§2.2, p. 11)

## 1. Verbatim Text (zero ellipses)

> He mentions the interesting suggestion that a positive amplification feed-back is to be found in some cases of renal disease, in which the renal damage leads to hypertension, which in turn causes further renal disease and still further hypertension-a vicious spiral which, once established, proves fatal.
> (§11, p. 11)

## 2. Epistemic Gloss & Philosophical Carve

Wisdom applies cybernetic pathology to physiological breakdown:
1. **The Pathological Positive Loop:** When regulatory mechanisms fail, positive feedback manifests as a fatal 'vicious spiral'—renal damage elevates systemic arterial blood pressure, which causes microvascular destruction in the kidneys, precipitating lethal runaway hypertension.
2. **De-afferentation & Continuous Motor Discharge:** Without negative feedback inhibition, biological systems cannot halt forward movement (as in Bailey's interpeduncular cat preparations).

## 3. Formal Predicate Logic

Let $D_{\text{renal}}$ be renal tissue damage and $P_{\text{arterial}}$ be arterial pressure:
$$\frac{dP_{\text{arterial}}}{dt} = f(D_{\text{renal}}), \quad \frac{dD_{\text{renal}}}{dt} = g(P_{\text{arterial}})$$
$$\frac{\partial f}{\partial D} > 0 \land \frac{\partial g}{\partial P} > 0 \implies \text{Eigenvalue } \lambda > 0 \implies \text{LethalInstability}$$

## 4. Decomposed Semiotic S-Expressions

### 4.1 Firstness: Qualitative Phenomenon (Seme)

```clojure
{:seme/firstness
 {:quality/phenomenon :uncontrolled-escalating-pathological-loop
  :perceptual/metaphor "A ratchet clicking forward toward collapse where each damage increment accelerates the next"}}
```

### 4.2 Secondness: Empirical Encounter & Historical Anchors (Pheme)

```clojure
{:pheme/secondness
 {:encounter :renal-hypertension-vicious-spiral
  :historical-anchors [:wisdom-1951-vicious-spiral :thompson-1951-cybernetics-animal-integration]}}
```

### 4.3 Thirdness: Invariant Delome Rules & Causal Habits (Delome)

```clojure
{:delome/thirdness
 [(invariant :pathology/trajectory #{:stable-homeostasis :vicious-spiral})
  (rule :pathological-loop
    (implies (and (= :feedback/sign :positive) (= :context :physiological-regulation))
             :vicious-spiral))]}
```

### 4.4 Conceptual Term Definitions

```clojure
{:term/definitions
 [#:term{:name :vicious-spiral
         :def "A fatal pathological configuration where positive feedback amplifies tissue damage and functional stress without compensatory damping"}
  #:term{:name :renal-hypertension-loop
         :def "Reciprocal escalation of vascular pressure and kidney damage culminating in systemic failure"}]}
```

## 5. Verbatim Witness Register

```clojure
{:witness/selection
  {:witness/text "He mentions the interesting suggestion that a positive amplification feed-back is to be found in some cases of renal disease, in which the renal damage leads to hypertension, which in turn causes further renal disease and still further hypertension-a vicious spiral which, once established, proves fatal."
   :witness/folio 11
   :witness/char-count 304
   :witness/zero-ellipses? true}}
```
## 6. Literate Semantic Demonstration & Bounded Synthesis

### 6.1 The Dialectical Demarcation
Wisdom connects cybernetic mechanics directly to medical pathology: the 'vicious spiral.' When homeostatic damping fails in a complex physiological network, positive amplification transforms regulatory circuits into lethal cascades. In renal hypertension, kidney damage drives up arterial pressure, which causes microvascular destruction in the kidneys, precipitating further pressure elevations in a fatal circular escalation.

### 6.2 Dynamical Formalization & Invariant Rules
Section 3 represents the reciprocal pathology via coupled differentials $\dot{P}_{\text{arterial}} = f(D_{\text{renal}})$ and $\dot{D}_{\text{renal}} = g(P_{\text{arterial}})$ with strictly positive partial derivatives $\frac{\partial f}{\partial D} > 0, \frac{\partial g}{\partial P} > 0$. The resulting positive real eigenvalue $\lambda > 0$ indicates uncontrollable exponential divergence. Section 4.3 translates this into the rule `:pathological-loop`: positive feedback in a physiological regulation context is conformed to a `:vicious-spiral`.

### 6.3 Epistemic Bounds & Negative Horizon
1. **Pathology as Circuit Inversion:** Disease is here modeled not as a foreign invasion or vital exhaustion, but as a topological inversion of regulatory polarity from negative damping to positive amplification.
2. **Terminal Inevitability:** Without external clinical intervention to interrupt the causal loop, the vicious spiral terminates deterministically in systemic failure.
