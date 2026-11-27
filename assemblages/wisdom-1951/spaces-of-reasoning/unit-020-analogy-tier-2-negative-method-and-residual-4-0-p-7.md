---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-020-analogy-tier-2-negative-method-and-residual-4-0-p-7
id: CR-WISDOM-U020
title: "CR-WISDOM-U020 — Analogy Tier 2: The Negative Method & The Unmodeled Residual (§4.0, p. 7)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', The British Journal for the Philosophy of Science, Vol. II, No. 5, May 1951, p. 7"
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v6-synthetic-quad"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme", "witness-register", "literate-synthesis"]
corpus_locus:
  source_file: "assemblages/wisdom-1951/primary/source-text.txt"
  sha256: "fb29b8c188d305d98fa846df7729c1211e8b7e47d577c2abaf765f0d920a6981"
  printed_folio: 7
  doc_page_index: 7
  offset_start: 23645
  offset_end: 23908
  char_count: 263
tags:
  - analogy
  - negative-method
  - ashby
  - indeterminacy
  - residual
  - verbatim-register
  - mode-d
  - mode-s
  - synthetic-quad
---

# CR-WISDOM-U020 — Analogy Tier 2: The Negative Method & The Unmodeled Residual (§4.0, p. 7)

## 1. Verbatim Text (zero ellipses)

> "It is doubtful whether Ashby's analogy is wholly correct, for his concept of a machine, broad though it is, is not so broad as it could be. His concept is such that each configuration determines its successor; but human machines may not always behave in this way."
> (§4.0, p. 7)

## 2. Epistemic Gloss & Philosophical Carve

Wisdom articulates the second tier of his epistemology of modeling: the **negative method of analogy**.

The negative method does not deploy an analogy to establish sweeping identity claims, but uses the model as an analytical probe to discover its exact breaking point. Wisdom inspects Ashby's foundational definition of a machine—a system where each internal configuration strictly determines its subsequent configuration. Wisdom challenges the completeness of this analogy: living organismic systems may exhibit genuine stochastic transitions, non-stationary state spaces, or uncertainty dynamics (anticipating Donald MacKay). The scientific payoff of Ashby's model lies precisely in measuring this unexplained residual.

## 3. Formal Symbolic Logic

Let $\mathcal{M}_{\text{Ashby}}$ be a machine characterized by a deterministic state transition operator:

$$\delta_{\mathcal{M}}: S \to S \quad \text{such that} \quad s_{t+1} = \delta_{\mathcal{M}}(s_t)$$

Let $\mathcal{O}_{\text{human}}$ be an organism with non-deterministic or stochastic state transitions:

$$\delta_{\mathcal{O}}: S \to \mathcal{P}(S) \quad \text{or} \quad \delta_{\mathcal{O}}: S \times \Omega \to S$$

The scope and residual of the mechanical analogy are formally demarcated:

$$\text{AnalogyScope}(\mathcal{M}_{\text{Ashby}}, \mathcal{O}_{\text{human}}) = \{ s \in S \mid |\delta_{\mathcal{O}}(s)| = 1 \land \delta_{\mathcal{O}}(s) = \{\delta_{\mathcal{M}}(s)\} \}$$

$$\text{Residual}(\mathcal{O}_{\text{human}}, \mathcal{M}_{\text{Ashby}}) = \{ s \in S \mid \delta_{\mathcal{O}}(s) 
\neq \{\delta_{\mathcal{M}}(s)\} \}$$

$$\text{NegativeMethodDemarcation}(\mathcal{M}, \mathcal{O}) \iff \text{Residual}(\mathcal{O}, \mathcal{M}) 
\neq \emptyset$$

## 4. Decomposed Semiotic S-Expressions

### 4.1 Firstness: Qualitative Phenomenon (Seme)

```clojure
{:seme/firstness
 {:quality/phenomenon :state-determined-succession-vs-organismic-indeterminacy
  :perceptual/metaphor "A clockwork stepping switch where each notch commands the next vs. a human mind hesitating across branching futures"}}
```

### 4.2 Secondness: Empirical Encounter & Historical Anchors (Pheme)

```clojure
{:pheme/secondness
 {:encounter :ashby-deterministic-machine-postulate
  :historical-anchors [:wisdom-1951-negative-method
                       :ashby-1948-electronic-engineering-20-379-83
                       :mackay-1951-uncertainty-mechanisms]}}
```

### 4.3 Thirdness: Invariant Delome Rules & Causal Habits (Delome)

```clojure
{:delome/thirdness
 ['(invariant :system/determinacy #{:strictly-state-determined :stochastic-branching :open-world})
  '(rule :negative-method-subtraction
    (implies (and (= :system/determinacy :stochastic-branching)
                  (not= (:transition/cardinality %) 1))
             :unmodeled-human-residual))
  '(rule :analogy-tier-2-warrant
    (iff (seq (:epistemic/residual %))
         (= (:analogy/tier %) :tier-2-negative-method)))]}
```

### 4.4 Conceptual Term Definitions

```clojure
{:term/definitions
 [#:term{:name :negative-method-of-analogy
         :def "The deliberate identification of points where a mechanical analogy breaks down against living organism dynamics"}
  #:term{:name :successor-determination
         :def "Ashby's postulate that every system configuration uniquely and deterministically dictates its subsequent configuration"}
  #:term{:name :unmodeled-residual
         :def "The subset of biological organism behaviors that cannot be mapped to deterministic state transitions"}]}
```

## 5. Verbatim Witness Register

```clojure
{:witness/selection
 {:witness/text "It is doubtful whether Ashby's analogy is wholly correct, for his concept of a machine, broad though it is, is not so broad as it could be. His concept is such that each configuration determines its successor; but human machines may not always behave in this way."
  :witness/folio 7
  :witness/char-count 263
  :witness/zero-ellipses? true}}
```

## 6. Literate Semantic Demonstration & Bounded Synthesis

### 6.1 The Dialectical Demarcation
Wisdom introduces a crucial methodological corrective to cybernetic overreach: the **negative method of analogy**. In naive modeling, an analogy is celebrated only when artifact and organism appear identical. In Wisdom's epistemology, an analogy achieves its greatest scientific utility at the exact boundary where it fails. By constructing a rigorous machine like Ashby's Homeostat and comparing it to living psychology, the differences that remain cannot be dismissed as vague mysticism; they constitute an empirically isolated residual.

### 6.2 The Determinism Postulate and its Organismic Breakdown
In Section 3, Ashby's machine definition is formalized as single-valued configuration determinism: $s_{t+1} = \delta_{\mathcal{M}}(s_t)$. Every state has one and only one successor. Wisdom doubts that human mental and nervous systems obey this absolute determinacy; organisms exhibit stochastic branching, developmental plasticity, and creative indeterminacy ($|\delta_{\mathcal{O}}(s)| > 1$). The formal set subtraction $\text{Residual} = \{s \in S \mid \delta_{\mathcal{O}}(s) \neq \{\delta_{\mathcal{M}}(s)\}\}$ provides an exact topological boundary: it delineates what Ashby's model successfully explains from what remains to be accounted for.

### 6.3 Epistemic Bounds & Negative Horizon
1. **Critique is Not Rejection:** Wisdom does not reject Ashby's machine; he honors it as the most rigorous model available, using its very clarity to expose where deterministic mechanics must be supplemented.
2. **Anticipation of Stochastic Automata:** This unit directly anticipates Donald MacKay's subsequent introduction of statistical and uncertainty mechanisms into cybernetic theory.
