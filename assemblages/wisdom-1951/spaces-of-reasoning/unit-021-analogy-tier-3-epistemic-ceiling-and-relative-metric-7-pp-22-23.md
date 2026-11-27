---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-021-analogy-tier-3-epistemic-ceiling-and-relative-metric-7-pp-22-23
id: CR-WISDOM-U021
title: "CR-WISDOM-U021 — Analogy Tier 3: Epistemic Ceiling & The Relative Metric of Machines (§7, pp. 22–23)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', The British Journal for the Philosophy of Science, Vol. II, No. 5, May 1951, pp. 22–23"
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v6-synthetic-quad"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme", "witness-register", "literate-synthesis"]
corpus_locus:
  source_file: "assemblages/wisdom-1951/primary/source-text.txt"
  sha256: "fb29b8c188d305d98fa846df7729c1211e8b7e47d577c2abaf765f0d920a6981"
  printed_folio: 22
  doc_page_index: 22
  offset_start: 59029
  offset_end: 61127
  char_count: 550
tags:
  - analogy
  - epistemic-ceiling
  - relative-metric
  - radar-thermostats
  - verbatim-register
  - mode-d
  - mode-s
  - synthetic-quad
---

# CR-WISDOM-U021 — Analogy Tier 3: Epistemic Ceiling & The Relative Metric of Machines (§7, pp. 22–23)

## 1. Verbatim Text (zero ellipses)

> When Ashby shows that an organism has certain properties of a machine, this does not establish that they have only those properties. And it is obvious to anyone who has ever met a dog or a man that organisms are vastly different machines from electronic calculators. What cybernetics does is to show us at least one new kind of machine. One way of putting this is to say that they are not machines in the Newtonian sense. Nor, however, are they machines in the Maxwell or in the Einstein sense. A Newtonian machine, for example, is one for which we can calculate velocities and positions resulting from given velocities and positions. Now a cybernetic machine does not violate the principles of such a machine; it is simply that feed-back mechanisms go beyond them. From the time of Newton or Laplace the idea was in the air that human beings were machines; yet this was obviously incongruous. Cybernetics shows why : the Newtonian machine was not an adequate model of a human being. The question now arises whether the 22 This content downloaded from 136.159.235.223 on Mon, 25 May 2015 19:31:43 UTC All use subject to JSTOR Terms and Conditions THE HYPOTHESIS OF CYBERNETICS cybernetic model is adequate. Clearly it is not; there are too many ways in which any machine that can be envisaged at present fails to answer to human behaviour. The possibility remains open that with a sufficient number of highly complex feed-backs or with MacKay's mechanism it would turn out to be a satisfactory model. This would require lengthy discussion. On the other hand it is possible that yet another mechanism (or indeed many others), as yet unknown, may have to be built into our model before it can be human or even animal-like. Nonetheless, whatever mechanisms are discovered and built in and however much they pave the way to new species of machines, yet these new things will be machines. All that we know at present is that brains are less like levers and gears than like radar and thermostats ; the resemblance even to these is not very close, but it will have to serve until we find something better.
> (§7, pp. 22–23)

## 2. Epistemic Gloss & Philosophical Carve

Wisdom establishes the third and final tier of his epistemology of modeling: the **epistemic ceiling and relative topological metric of analogy**.
1. **The Fallacy of Exclusive Predication:** When a cyberneticist demonstrates that an organism possesses machine properties, this does not establish that the organism possesses *only* those properties. Living organisms depart radically from electronic computers.
2. **The Relative Metric:** Scientific progress does not require absolute ontology; it operates by comparative proximity. Brains are less like levers and gears than like radar and thermostats. The resemblance even to thermostats is imperfect, but it serves as an indispensable interim heuristic until a richer biological paradigm emerges.

## 3. Formal Symbolic Logic

Let $\mathcal{O}$ be an organism and $\mathbb{M}$ be the space of physical machines.
Let $\mathcal{P}(x)$ denote the set of properties of system $x$.

The Fallacy of Exclusive Predication:

$$\exists M \in \mathbb{M} \, [(\mathcal{P}(M) \cap \mathcal{P}(\mathcal{O}) \neq \emptyset) \centernot\implies (\mathcal{P}(\mathcal{O}) \subseteq \mathcal{P}(M))]$$

Relative topological metric on causal architectures $d_{\mathcal{K}}(\cdot, \cdot)$:

$$d_{\mathcal{K}}(\mathcal{O}_{\text{brain}}, \mathcal{M}_{\text{radar/thermostat}}) < d_{\mathcal{K}}(\mathcal{O}_{\text{brain}}, \mathcal{M}_{\text{levers/gears}})$$

The Epistemic Ceiling of Interim Heuristics:

$$\lim_{t \to \text{present}} d_{\mathcal{K}}(\mathcal{O}_{\text{brain}}, \mathcal{M}_{\text{radar/thermostat}}) = \epsilon > 0 \quad (\text{Bounded Approximation})$$

## 4. Decomposed Semiotic S-Expressions

### 4.1 Firstness: Qualitative Phenomenon (Seme)

```clojure
{:seme/firstness
 {:quality/phenomenon :comparative-proximity-across-imperfect-models
  :perceptual/invariant :relative-metric-over-absolute-identity
  :perceptual/metaphor "Navigating by radar and thermostats as interim stars, knowing they are vastly closer than levers and gears but still distant from the living shore"}}
```

### 4.2 Secondness: Empirical Encounter & Historical Anchors (Pheme)

```clojure
{:pheme/secondness
 {:encounter :relative-metric-of-machine-analogies
  :data {:closer-heuristic :radar-and-thermostats
         :distant-heuristic :levers-and-gears
         :empirical-reality :dogs-and-men}
  :historical-anchors [:ashby-1948-homeostat
                       :wisdom-1951-epistemic-ceiling
                       :craik-1943-nature-of-explanation]}}
```

### 4.3 Thirdness: Invariant Delome Rules & Causal Habits (Delome)

```clojure
{:delome/thirdness
 ['(rule :relative-topological-metric
    (asserts (< (causal-distance :brain :radar-and-thermostat)
                (causal-distance :brain :levers-and-gears))))
  '(rule :anti-reductionist-property-bound
    (implies (shares-properties? :organism :machine)
             (not-asserts (subset-of? :organism-properties :machine-properties))))
  '(rule :interim-heuristic-warrant
    (implies (= :analogy/tier :tier-3-epistemic-ceiling)
             (= :scientific-status :provisional-scaffolding)))]}
```

### 4.4 Conceptual Term Definitions

```clojure
{:term/definitions
 [#:term{:name :relative-metric-of-analogy
         :def "Comparative topological ordering asserting that brains are structurally closer to thermostats than to gears, without claiming identity"}
  #:term{:name :fallacy-of-exclusive-predication
         :def "The erroneous inference that because an organism shares some machine properties, it possesses only machine properties"}
  #:term{:name :interim-heuristic
         :def "A provisional scientific model that serves as necessary scaffolding until a more adequate paradigm is discovered"}]}
```

## 5. Verbatim Witness Register

```clojure
{:witness/selection
 {:witness/text "When Ashby shows that an organism has certain properties of a machine, this does not establish that they have only those properties. And it is obvious to anyone who has ever met a dog or a man that organisms are vastly different machines from electronic calculators. What cybernetics does is to show us at least one new kind of machine. One way of putting this is to say that they are not machines in the Newtonian sense. Nor, however, are they machines in the Maxwell or in the Einstein sense. A Newtonian machine, for example, is one for which we can calculate velocities and positions resulting from given velocities and positions. Now a cybernetic machine does not violate the principles of such a machine; it is simply that feed-back mechanisms go beyond them. From the time of Newton or Laplace the idea was in the air that human beings were machines; yet this was obviously incongruous. Cybernetics shows why : the Newtonian machine was not an adequate model of a human being. The question now arises whether the 22 This content downloaded from 136.159.235.223 on Mon, 25 May 2015 19:31:43 UTC All use subject to JSTOR Terms and Conditions THE HYPOTHESIS OF CYBERNETICS cybernetic model is adequate. Clearly it is not; there are too many ways in which any machine that can be envisaged at present fails to answer to human behaviour. The possibility remains open that with a sufficient number of highly complex feed-backs or with MacKay's mechanism it would turn out to be a satisfactory model. This would require lengthy discussion. On the other hand it is possible that yet another mechanism (or indeed many others), as yet unknown, may have to be built into our model before it can be human or even animal-like. Nonetheless, whatever mechanisms are discovered and built in and however much they pave the way to new species of machines, yet these new things will be machines. All that we know at present is that brains are less like levers and gears than like radar and thermostats ; the resemblance even to these is not very close, but it will have to serve until we find something better."
  :witness/folio 22
  :witness/char-count 2098
  :witness/zero-ellipses? true}}
```

## 6. Literate Semantic Demonstration & Bounded Synthesis

### 6.1 The Dialectical Demarcation
Wisdom's final tier establishes the humility of mature scientific epistemics: the relative metric and epistemic ceiling of models. He directly refutes the naive reductionist claim that cybernetics has solved biology or proven that animals are computers. Anyone who has ever interacted with a dog or a human being recognizes immediately that living organisms differ profoundly from electronic calculators. Showing that an organism possesses machine properties never warrants the conclusion that it possesses only machine properties.

### 6.2 The Relative Topological Metric
In Section 3, scientific modeling is formalized not as binary truth or falsehood, but as a comparative metric over causal topologies:
$$d_{\mathcal{K}}(\mathcal{O}_{\text{brain}}, \mathcal{M}_{\text{radar/thermostat}}) < d_{\mathcal{K}}(\mathcal{O}_{\text{brain}}, \mathcal{M}_{\text{levers/gears}})$$
Brains are demonstrably less like clocks and pulleys than like radar tracking systems and homeostatic thermostats. Even the resemblance to radar is imperfect and distant, but it constitutes genuine scientific progress because it moves closer in causal structure. Cybernetic models are interim scaffolds—provisional heuristics that serve until biology develops its own autonomous theoretical vocabulary.

### 6.3 Epistemic Bounds & Negative Horizon
1. **Scaffolding is Not Destination:** The goal of cybernetics is not to freeze living nature into silicon or electronic categories, but to provide provisional bridges toward a mature biological science.
2. **Rejection of Totalizing Claims:** Any modeling paradigm that claims total exhaustion of organismic life violates the epistemic ceiling, committing the fallacy of exclusive predication.
