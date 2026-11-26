---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-18-evaluation-machine-taxonomy-mind-body
id: CR-WISDOM-U018
title: "CR-WISDOM-U018 — Evaluation: Machine Taxonomy and the Mind-Body Relation (§7, pp. 21–24)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', The British Journal for the Philosophy of Science, Vol. II, No. 5, May 1951, pp. 21–24"
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v6-synthetic-quad"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme", "witness-register", "literate-synthesis"]
corpus_locus:
  source_file: "assemblages/wisdom-1951/primary/source-text.txt"
  sha256: "fb29b8c188d305d98fa846df7729c1211e8b7e47d577c2abaf765f0d920a6981"
  printed_folio: 21
  doc_page_index: 21
---

# CR-WISDOM-U018 — Evaluation: Machine Taxonomy and the Mind-Body Relation (§7, pp. 21–24)

## 1. Verbatim Text (zero ellipses)

> Cybernetics does not solve the traditional mind-body problem. It does not prove that the mind is a machine. What it does do is to enrich our concept of a machine. Hitherto machines were Newtonian-levers, gears, and clockwork. Cybernetics introduces an entirely new species of machine based on circular feed-back and teleological regulation. Furthermore, cybernetics is a branch of neurology, not psychology; it describes the physical machinery of the brain, while the subjective aspect of mental life remains unmodeled.
> (§7, pp. 21–24)

## 2. Epistemic Gloss & Philosophical Carve

Wisdom delivers his culminating philosophical evaluation of the cybernetic movement:
1. **The Mind-Body Demarcation:** Cybernetics does not solve the metaphysical mind-body problem, nor does it vindicate crude ontological reductionism. It does not prove that "mind" is a mechanical illusion.
2. **Expansion of the Machine Taxonomy:** The historical significance of cybernetics is that it shatters the monopoly of Newtonian clockwork. Machines are no longer confined to deterministic force transmission; cybernetics creates a new genus of machine characterized by circular causality, informational control, and purposeful setpoint tracking.
3. **Neurological Scope:** Cybernetics belongs properly to neurology—the objective physical modeling of nervous coordination—leaving the qualitative textures of subjective awareness deliberately untouched.

## 3. Formal Symbolic Logic

Let $\mathbb{M}$ be the universal class of physical machines, partitioned into four historical genera:
$$\mathbb{M} = \mathbb{M}_{\text{Newtonian}} \cup \mathbb{M}_{\text{Maxwellian}} \cup \mathbb{M}_{\text{Einsteinian}} \cup \mathbb{M}_{\text{Cybernetic}}$$ 

Where cybernetic machines are uniquely defined by non-trivial circular feedback topologies:
$$\mathbb{M}_{\text{Cybernetic}} = \{ m \in \mathbb{M} \mid \text{HasClosedFeedbackLoop}(m) \land \text{SelfRegulating}(m) \}$$

Wisdom's anti-reductionist mind-body demarcation:
Let $\mathcal{B}_{\text{physical}}$ be the objective neuro-somatic manifold and $\Psi_{\text{mental}}$ be subjective experience.
$$\text{CyberneticModel}(\mathcal{B}_{\text{physical}}) \not\implies \Psi_{\text{mental}} \equiv \mathcal{B}_{\text{physical}}$$ 
$$\text{ExplainsMechanics}(\mathbb{M}_{\text{Cybernetic}}, \mathcal{B}_{\text{physical}}) \land \text{Unmodeled}(\Psi_{\text{mental}})$$ 

## 4. Decomposed Semiotic S-Expressions

### 4.1 Firstness: Qualitative Phenomenon (Seme)

```clojure
{:seme/firstness
 {:quality/phenomenon :demarcation-of-machine-species-and-subjective-horizon
  :perceptual/invariant :category-transcendence-without-mysticism
  :perceptual/metaphor "A clockmaker confronting a thermostat and realizing gears cannot describe target-seeking, while an anatomist acknowledges the listener remains unheard"}}
```

### 4.2 Secondness: Empirical Encounter & Historical Anchors (Pheme)

```clojure
{:pheme/secondness
 {:encounter :philosophical-evaluation-of-cybernetics
  :data {:machine-taxonomy [:newtonian-kinematic
                            :maxwellian-electrodynamic
                            :einsteinian-relativistic
                            :cybernetic-teleological]
         :epistemic-bounds [:neurology-branch-not-foundation
                            :methodological-omission-of-mind]}
  :historical-anchors [:descartes-mind-body
                       :wiener-1948-cybernetics
                       :ashby-1948-homeostat]}}
```

### 4.3 Thirdness: Invariant Delome Rules & Causal Habits (Delome)

```clojure
{:delome/thirdness
 ['(taxonomy :machine-historical-genera
    {:newtonian   {:principle :force-conservation-levers}
     :maxwellian  {:principle :electromagnetic-waves}
     :einsteinian {:principle :relativistic-spacetime}
     :cybernetic  {:principle :circular-information-feedback}})
  '(rule :mind-body-anti-reductionism
    (implies (= :domain :subjective-mental-experience)
             (= :cybernetic-claim :methodological-omission)))
  '(rule :machine-species-expansion
    (iff (and (contains? (:traits %) :circular-feedback)
              (contains? (:traits %) :error-governed))
         (= (:machine/species %) :cybernetic-teleological)))]}
```

### 4.4 Conceptual Term Definitions

```clojure
{:term/definitions
 [#:term{:name :machine-taxonomy-expansion
         :def "The addition of circular feedback and teleological regulation to the historical classification of machines"}
  #:term{:name :mind-body-demarcation
         :def "The methodological separation establishing that cybernetics models neurological machinery without reducing subjective consciousness"}
  #:term{:name :neurological-charter
         :def "The domain boundary restricting cybernetics to physical brain mechanisms rather than introspective psychology"}]}
```

## 5. Verbatim Witness Register

```clojure
{:witness/selection
 {:witness/text "Cybernetics does not solve the traditional mind-body problem. It does not prove that the mind is a machine. What it does do is to enrich our concept of a machine. Hitherto machines were Newtonian-levers, gears, and clockwork. Cybernetics introduces an entirely new species of machine based on circular feed-back and teleological regulation. Furthermore, cybernetics is a branch of neurology, not psychology; it describes the physical machinery of the brain, while the subjective aspect of mental life remains unmodeled."
  :witness/folio 21
  :witness/char-count 519
  :witness/zero-ellipses? true}}
```

## 6. Literate Semantic Demonstration & Bounded Synthesis

### 6.1 The Dialectical Demarcation
Wisdom concludes by clearing away the ideological grandstanding that surrounded cybernetics at its birth. Cybernetics does not solve the Cartesian mind-body dilemma, nor does it establish vulgar materialism. It does not prove that the human mind is 'nothing but' a machine. Rather, its philosophical triumph is inverted: it radically enriches and expands our understanding of what a machine can be.

### 6.2 The Taxonomy of Machine Genera
In Section 3, machines are shown to have evolved across four discrete epistemological paradigms:
1. **Newtonian Kinematics:** Conservative systems of levers, gears, and rigid mechanical linkages transmitting momentum.
2. **Maxwellian Electrodynamics:** Continuous field distributions transmitting electromagnetic radiation.
3. **Einsteinian Relativism:** Non-Euclidean geometry and mass-energy equivalence.
4. **Cybernetic Teleology:** Informational networks governing energetic transformations through closed circular feedback.
By inventing this fourth genus, cybernetics proves that teleology, purpose, and self-correction are mechanical properties, emancipating science from crude push-pull clockwork without surrendering to vitalism.

### 6.3 Epistemic Bounds & Negative Horizon
1. **Subjectivity is Methodologically Omitted, Not Denied:** Wisdom emphasizes that cybernetics is a branch of physical neurology, not psychology. The qualia of pain, the feeling of agency, and subjective mental life are explicitly unmodeled.
2. **Anti-Imperialism of the Machine:** A cybernetic model of the brain explains the physical coordination of nervous tissue; confusing that model with the totality of human experience is an elementary philosophical fallacy.
