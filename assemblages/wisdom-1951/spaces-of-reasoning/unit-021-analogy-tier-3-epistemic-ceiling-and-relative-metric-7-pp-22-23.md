---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-021-analogy-tier-3-epistemic-ceiling-and-relative-metric-7-pp-22-23
id: CR-WISDOM-U021
title: "CR-WISDOM-U021 — Analogy Tier 3: The Epistemic Ceiling & Relative Metric of Machines (§7, pp. 22–23)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', BJPS 2(5), 1951, pp. 22–23"
basis_tier: read-together
corpus_locus:
  source_file: "assemblages/wisdom-1951/primary/source-text.txt"
  offset_start: 59029
  offset_end: 59365
  char_count: 336
  secondary_offset_start: 60913
  secondary_offset_end: 61127
  secondary_char_count: 214
tags:
  - analogy
  - epistemic-ceiling
  - machine-taxonomy
  - relative-metric
  - interim-heuristic
  - verbatim-register
  - mode-d
  - mode-s
---

# CR-WISDOM-U021 — Analogy Tier 3: The Epistemic Ceiling & Relative Metric of Machines (§7, pp. 22–23)

## 1. Verbatim Text (zero ellipses)

### Witness Selection A (Offset 59029)
> "When Ashby shows that an organism has certain properties of a machine, this does not establish that they have only those properties. And it is obvious to anyone who has ever met a dog or a man that organisms are vastly different machines from electronic calculators. What cybernetics does is to show us at least one new kind of machine."

### Witness Selection B (Offset 60913)
> "All that we know at present is that brains are less like levers and gears than like radar and thermostats ; the resemblance even to these is not very close, but it will have to serve until we find something better."

## 2. Natural Language Gloss & Conceptual Nuance

The third tier of Wisdom's tripartite epistemology establishes the **epistemic ceiling**: the strict prohibition against reifying an analogy into an ontological identity. 

Showing that an organism satisfies certain formal axioms of a machine class does not establish that the organism possesses *only* those mechanical properties. Cybernetics does not reduce humans to machines; it expands our ontology by demonstrating the physical reality of a new species of machine (closed-loop circular systems). 

Wisdom formulates a comparative topological metric: living brains are closer in causal structure to radar and thermostats than to levers and gears, but even radar and thermostats are imperfect, interim heuristics. Scientific analogy is an operational scaffold to be utilized until a more comprehensive biological ontology is discovered.

## 3. Formal Logic

Let $\mathcal{O}$ be an organism. Let $\mathcal{P}(x)$ denote the set of all properties of system $x$.
Let $\mathbb{M}_{\text{Newtonian}}$ be the class of Newtonian machines (levers, gears).
Let $\mathbb{M}_{\text{Cybernetic}}$ be the class of circular feedback machines (radar, thermostats).

$$\forall M \in \mathbb{M} \, [(\mathcal{P}(M) \subset \mathcal{P}(\mathcal{O})) \centernot\implies (\mathcal{P}(\mathcal{O}) = \mathcal{P}(M))]$$

$$\forall M \in \mathbb{M}_{\text{Calculator}} \, [M \neq \mathcal{O}]$$

Let $d_{\text{resemblance}}(x, y) \in \mathbb{R}^+$ be a structural distance metric between causal topologies:
$$d_{\text{resemblance}}(\mathcal{O}, \mathbb{M}_{\text{Cybernetic}}) < d_{\text{resemblance}}(\mathcal{O}, \mathbb{M}_{\text{Newtonian}})$$

$$\text{EpistemicStatus}(\mathbb{M}_{\text{Cybernetic}}, \mathcal{O}) = \text{InterimHeuristic}(\text{until} \; \exists \mathbb{M}^* [d(\mathcal{O}, \mathbb{M}^*) \ll d(\mathcal{O}, \mathbb{M}_{\text{Cybernetic}})])$$

## 4. S-Expressions (`assemblage` EDN AST)

```clojure
{:close-read/id "CR-WISDOM-U021"
 :close-read/title "Analogy Tier 3: The Epistemic Ceiling & Relative Metric of Machines"
 :epistemics/tier :tier-3/epistemic-ceiling
 :witness/selections
 [{:witness/id :witness-3a
   :witness/text "When Ashby shows that an organism has certain properties of a machine, this does not establish that they have only those properties. And it is obvious to anyone who has ever met a dog or a man that organisms are vastly different machines from electronic calculators. What cybernetics does is to show us at least one new kind of machine."
   :witness/char-count 336
   :witness/offset 59029
   :witness/zero-ellipses? true
   :witness/exact-contiguous? true
   :witness/verdict :verbatim}
  {:witness/id :witness-3b
   :witness/text "All that we know at present is that brains are less like levers and gears than like radar and thermostats ; the resemblance even to these is not very close, but it will have to serve until we find something better."
   :witness/char-count 214
   :witness/offset 60913
   :witness/zero-ellipses? true
   :witness/exact-contiguous? true
   :witness/verdict :verbatim}]
 :semantics/relations
 {:relation/forbids #{:ontological-reductionism :identity-fallacy}
  :relation/expands-taxonomy :cybernetic-machine-species
  :relation/evaluates-metric {:closer-to :radar-and-thermostats
                              :distant-from :levers-and-gears}
  :relation/epistemic-status :interim-heuristic}
 :ast/logic
 [(taxonomy :relative-topological-metric
    {:brains-to-levers-and-gears        {:distance :vast :heuristic-value :low}
     :brains-to-radar-and-thermostats   {:distance :moderate :heuristic-value :interim-service}
     :brains-to-future-epistemic-model  {:distance :minimal :heuristic-value :sovereign}})
  (rule :epistemic-ceiling
    (implies (satisfies-machine-properties? organism)
             (not (has-only-machine-properties? organism))))]
 :lexicon/terms
 [{:term/name :non-exhaustion-principle
   :term/def "Showing an organism has properties of a machine does not establish it has only those properties"}
  {:term/name :interim-heuristic-status
   :term/def "The epistemological view of cybernetic models as temporary approximations to serve until a richer biological paradigm is found"}]}
```
