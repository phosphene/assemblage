---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-019-analogy-tier-1-mechanism-carve-vs-mimicry-1-pp-1-2
id: CR-WISDOM-U019
title: "CR-WISDOM-U019 — Analogy Tier 1: Mechanism Carve vs. Behavioral Mimicry (§1, pp. 1–2)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', BJPS 2(5), 1951, pp. 1–2"
basis_tier: read-together
corpus_locus:
  source_file: "assemblages/wisdom-1951/primary/source-text.txt"
  offset_start: 2586
  offset_end: 2805
  char_count: 219
tags:
  - analogy
  - mechanism-carve
  - trivial-analogy
  - verbatim-register
  - mode-d
  - mode-s
---

# CR-WISDOM-U019 — Analogy Tier 1: Mechanism Carve vs. Behavioral Mimicry (§1, pp. 1–2)

## 1. Verbatim Text (zero ellipses)

> "If this were all, however, the analogy between the machine and the brain or mind (we need not here be precise which) would hardly be striking. What is important is that these machines embody a special type of mechanism."

## 2. Natural Language Gloss & Conceptual Nuance

Wisdom demarcates trivial functional analogy from scientifically fruitful structural analogy. Mere behavioral simulation (e.g. an automaton playing legal chess or calculating sums) exhibits input-output correspondence with human intellectual performance, but remains scientifically shallow if the underlying causal architecture is unexamined. 

The analogy between machine and brain becomes striking only when it isolates a distinct species of physical mechanism—namely, closed circular loops of feed-back that diverge fundamentally from Newtonian linear-deterministic machines. The analogy functions not as an identity claim, but as an empirical existence proof of physical teleology.

## 3. Formal Logic

Let $\mathcal{O}$ be an organism (brain/mind) and $\mathcal{M}$ be a mechanical system.
Let $P_{\text{IO}}(x)$ denote input-output behavioral properties (e.g. rule-following, chess calculation).
Let $\mathcal{K}(x)$ denote the internal causal mechanism.

$$\text{TrivialAnalogy}(\mathcal{M}, \mathcal{O}) \iff (P_{\text{IO}}(\mathcal{M}) \approx P_{\text{IO}}(\mathcal{O})) \land (\mathcal{K}(\mathcal{M}) \in \mathbb{M}_{\text{Newtonian}})$$

$$\text{MechanismCarve}(\mathcal{M}, \mathcal{O}) \iff \exists \mathcal{K}_{\text{loop}} \, [(\mathcal{K}_{\text{loop}} \sqsubseteq \mathcal{K}(\mathcal{M})) \land (\mathcal{K}_{\text{loop}} \sqsubseteq \mathcal{K}(\mathcal{O})) \land (\mathcal{K}_{\text{loop}} \notin \mathbb{M}_{\text{Newtonian}})]$$

$$\text{StrikingAnalogy}(\mathcal{M}, \mathcal{O}) \implies \text{MechanismCarve}(\mathcal{M}, \mathcal{O})$$

## 4. S-Expressions (`assemblage` EDN AST)

```clojure
{:close-read/id "CR-WISDOM-U019"
 :close-read/title "Analogy Tier 1: Mechanism Carve vs. Behavioral Mimicry"
 :epistemics/tier :tier-1/mechanism-carve
 :witness/selection
 {:witness/text "If this were all, however, the analogy between the machine and the brain or mind (we need not here be precise which) would hardly be striking. What is important is that these machines embody a special type of mechanism."
  :witness/char-count 219
  :witness/offset 2586
  :witness/zero-ellipses? true
  :witness/exact-contiguous? true
  :witness/verdict :verbatim}
 :semantics/relations
 {:relation/demarcates [:mechanism-carving :trivial-simulation]
  :relation/opposes #{:behavioral-mimicry :black-box-equivalence}
  :relation/requires-mechanism :feed-back}
 :ast/logic
 [(invariant :analogy/tier #{:tier-1-mechanism-carve :tier-2-negative-method :tier-3-epistemic-ceiling})
  (rule :striking-analogy-criterion
    (iff (and (contains? :mechanism/traits :closed-loop/feed-back)
              (not= :mechanism/causal :newtonian/clockwork))
         :striking-analogy))]
 :lexicon/terms
 [{:term/name :striking-analogy
   :term/def "An analogy grounded in shared novel mechanism rather than superficial behavioral resemblance"}
  {:term/name :special-type-of-mechanism
   :term/def "Closed-loop feedback control differentiating cybernetic automata from purely Newtonian machines"}]}
```
