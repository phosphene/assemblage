---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-020-analogy-tier-2-negative-method-and-residual-4-0-p-7
id: CR-WISDOM-U020
title: "CR-WISDOM-U020 — Analogy Tier 2: The Negative Method & The Unmodeled Residual (§4.0, p. 7)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', BJPS 2(5), 1951, p. 7"
basis_tier: read-together
corpus_locus:
  source_file: "assemblages/wisdom-1951/primary/source-text.txt"
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
---

# CR-WISDOM-U020 — Analogy Tier 2: The Negative Method & The Unmodeled Residual (§4.0, p. 7)

## 1. Verbatim Text (zero ellipses)

> "It is doubtful whether Ashby's analogy is wholly correct, for his concept of a machine, broad though it is, is not so broad as it could be. His concept is such that each configuration determines its successor; but human machines may not always behave in this way."

## 2. Natural Language Gloss & Conceptual Nuance

The second tier of Wisdom's tripartite epistemology of analogy is the **negative method**: using an analogy specifically to discover its breaking points and measure the unexplained residual. 

Wisdom inspects W. Ross Ashby's analogy between the Homeostat/uniselector machine and biological adaptation. Ashby defines a machine as a system where every state configuration strictly determines its successor. Wisdom doubts the complete correctness of the analogy on precisely these grounds: human organisms exhibit behavioral indeterminacy, open-world reorganization, and stochastic transitions (anticipating Donald MacKay's machines with uncertainty mechanisms). The scientific power of the analogy lies not in declaring the brain a deterministic machine, but in using the breakdown of that analogy to demarcate the exact boundary where deterministic mechanics fails to account for living behavior.

## 3. Formal Logic

Let $\mathcal{M}_{\text{Ashby}}$ be a machine characterized by a deterministic state transition operator:
$$\delta_{\mathcal{M}}: S \to S \quad \text{such that} \quad s_{t+1} = \delta_{\mathcal{M}}(s_t)$$

Let $\mathcal{O}_{\text{human}}$ be an organism with state transition dynamics:
$$\delta_{\mathcal{O}}: S \times \Omega \to \mathcal{P}(S)$$

The negative method defines the validity and limits of the analogy:
$$\text{AnalogyScope}(\mathcal{M}_{\text{Ashby}}, \mathcal{O}_{\text{human}}) = \{ s \in S \mid |\delta_{\mathcal{O}}(s)| = 1 \land \delta_{\mathcal{O}}(s) = \{\delta_{\mathcal{M}}(s)\} \}$$

$$\text{Residual}(\mathcal{O}_{\text{human}}, \mathcal{M}_{\text{Ashby}}) = \{ s \in S \mid \delta_{\mathcal{O}}(s) \neq \{\delta_{\mathcal{M}}(s)\} \}$$

$$\text{Breakdown}(\mathcal{M}_{\text{Ashby}}, \mathcal{O}_{\text{human}}) \iff \text{Residual}(\mathcal{O}_{\text{human}}, \mathcal{M}_{\text{Ashby}}) \neq \emptyset$$

## 4. S-Expressions (`assemblage` EDN AST)

```clojure
{:close-read/id "CR-WISDOM-U020"
 :close-read/title "Analogy Tier 2: The Negative Method & The Unmodeled Residual"
 :epistemics/tier :tier-2/negative-method-and-residual
 :witness/selection
 {:witness/text "It is doubtful whether Ashby's analogy is wholly correct, for his concept of a machine, broad though it is, is not so broad as it could be. His concept is such that each configuration determines its successor; but human machines may not always behave in this way."
  :witness/char-count 263
  :witness/offset 23645
  :witness/zero-ellipses? true
  :witness/exact-contiguous? true
  :witness/verdict :verbatim}
 :semantics/relations
 {:relation/critiques :ashby-homeostat-analogy
  :relation/isolates-residual #{:behavioral-indeterminacy :stochastic-branching}
  :relation/anticipates :mackay-uncertainty-machine}
 :ast/logic
 (do
   (defn deterministic-successor? [machine]
     (:dynamics/configuration-determines-successor? machine))

   (defn measure-residual [candidate-machine organism]
     (let [mach-det? (deterministic-successor? candidate-machine)
           org-det? (deterministic-successor? organism)]
       {:analogy/wholly-correct? (= mach-det? org-det?)
        :residual/unmodeled-dynamics (when (and mach-det? (not org-det?))
                                       :non-deterministic-transitions)})))
 :lexicon/terms
 [{:term/name :negative-method-of-analogy
   :term/def "The deliberate identification of points where a mechanical analogy fails to hold against living organism dynamics"}
  {:term/name :successor-determination
   :term/def "Ashby's deterministic machine postulate that every system configuration uniquely dictates its subsequent configuration"}]}
```
