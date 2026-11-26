---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-002-functional-decomposition-input-output-vs-internal-operations-1-p-1
id: CR-WISDOM-U002
title: "CR-WISDOM-U002 — Functional Decomposition: The Fourfold Architecture of the Calculating Engine (§1, p. 1)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', The British Journal for the Philosophy of Science, Vol. II, No. 5, May 1951, p. 1"
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v4-deliberative-seme"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme"]
corpus_locus:
  source_file: "assemblages/wisdom-1951/primary/source-text.txt"
  sha256: "fb29b8c188d305d98fa846df7729c1211e8b7e47d577c2abaf765f0d920a6981"
  printed_folio: 1
  doc_page_index: 1
  offset_start: 1989
  offset_end: 2262
  char_count: 273
tags:
  - cybernetics
  - wisdom-1951
  - functional-decomposition
  - von-neumann-architecture
  - seme
  - pheme
  - delome
  - mode-d
  - mode-s
---

# CR-WISDOM-U002 — Functional Decomposition: The Fourfold Architecture of the Calculating Engine (§1, p. 1)

## 1. Verbatim Text (zero ellipses)

> "Such machines had to be able (a) to be fed with information, (b) to store in some form the results of their preliminary calculations, (c) to make use of these results appropriately at the right time, i.e. to select from their 'files', and (d) to report their final results."

## 2. Natural Language Gloss & Conceptual Nuance

Having established the physical reality of the electronic calculator in Unit 1, Wisdom executes a sharp **functional decomposition** of its internal architecture into four necessary conditions $(a)–(d)$.

This decomposition anticipates the canonical von Neumann architecture (input, memory store, control unit / addressing, output), but Wisdom formulates it in purely operational terms:
1. **Intake ($(a)$):** Receptivity to external informational inscription (`to be fed with information`).
2. **Intermediate Persistence ($(b)$):** State retention across calculation steps (`to store in some form the results of their preliminary calculations`). Without intermediate storage, every operation is memoryless and complex compositions cannot be evaluated.
3. **Timed Addressable Selection ($(c)$):** The critical transition from passive storage to dynamic retrieval (`to make use of these results appropriately at the right time, i.e. to select from their 'files'`). Notice the scare-quotes around `'files'`: Wisdom recognizes that the machine's internal registers function as an active catalog or addressing space.
4. **Terminal Reporting ($(d)$):** Egress of the conformed result back to the human/operational environment (`to report their final results`).

Crucially, Wisdom treats this fourfold structure as an engineering necessity for *discrete calculation* before he turns (in the subsequent sentence) to evaluate whether this structure justifies an analogy to human intellect.

## 3. Formal Logic

Let $\mathcal{M}$ be an electronic calculating automaton defined over an alphabet $\Sigma$, an internal state space $S$, and a time lattice $T = \{t_0, t_1, \dots, t_n\}$.
The machine $\mathcal{M}$ is decomposed into a 4-tuple of causal operators:

$$\mathcal{M} = \langle \text{In}, \text{Store}, \text{Select}, \text{Out} \rangle$$

1. **Intake Operator (a):**
   $$\text{In}: \Sigma_{\text{ext}} \times T \to S_{\text{input}}$$
2. **Storage Operator (b):**
   $$\text{Store}: S_{\text{interim}} \times T \to \mathcal{M}_{\text{registers}}$$
   $$\forall t < t_{\text{final}}, \quad \text{State}(\text{calc}_i) \in \mathcal{M}_{\text{registers}}(t)$$
3. **Timed Selection Operator (c):**
   $$\text{Select}: \mathcal{M}_{\text{registers}} \times \text{Context}(t) \xrightarrow{\text{timing rule}} S_{\text{active}}$$
   where selection depends strictly on temporal and logical appropriateness ($t = t_{\text{right}}$).
4. **Terminal Reporting Operator (d):**
   $$\text{Out}: S_{\text{final}} \times T_{\text{final}} \to \Sigma_{\text{ext}}$$

A machine satisfies the necessary functional conditions of calculation iff all four operators are realized:
$$\text{CalculatingEngine}(\mathcal{M}) \iff (\text{In} \neq \emptyset) \land (\text{Store} \neq \emptyset) \land (\text{Select} \neq \emptyset) \land (\text{Out} \neq \emptyset)$$

## 4. S-Expressions (`assemblage` & `phosphene/seme`)

```clojure
{:close-read/id "CR-WISDOM-U002"
 :close-read/title "Functional Decomposition: The Fourfold Architecture of the Calculating Engine"
 :close-read/source "Wisdom (1951), BJPS 2(5), p. 1"
 :reading-discipline {:style :style/deliberative-synthesis
                      :engine "deliberative-reasoning-seme"
                      :target-registers [:verbatim :gloss :formal-logic :seme]}

 ;; --- Mode D: Verbatim Register Witness ---
 :witness/selection
 {:witness/text "Such machines had to be able (a) to be fed with information, (b) to store in some form the results of their preliminary calculations, (c) to make use of these results appropriately at the right time, i.e. to select from their 'files', and (d) to report their final results."
  :witness/char-count 273
  :witness/offset 1989
  :witness/printed-folio 1
  :witness/doc-page 1
  :witness/zero-ellipses? true
  :witness/exact-contiguous? true
  :witness/verdict :verbatim}

 ;; --- Mode S: Peircean Semiotic Specification via phosphene/seme ---

 ;; 1. Firstness (Rheme / Qualitative Seme): Quadruple functional traits
 :seme/firstness
 {:rheme/id :sememe/functional-von-neumann-quadruple
  :rheme/semes
  [#:seme{:id :seme/information-intake
          :pred :positive
          :metadata "Ability (a): to be fed with information (input transducer)"}
   #:seme{:id :seme/intermediate-storage
          :pred :positive
          :metadata "Ability (b): to store preliminary calculations (memory store)"}
   #:seme{:id :seme/timed-selection
          :pred :positive
          :metadata "Ability (c): to make use of results at the right time from files (addressable retrieval)"}
   #:seme{:id :seme/terminal-reporting
          :pred :positive
          :metadata "Ability (d): to report final results (output transducer)"}]}

 ;; 2. Secondness (Pheme / Conformed Witness): Keyset lattice vertex
 :pheme/secondness
 {:pheme/claimed-vertex :vertex/functional-calculating-automaton
  :pheme/required-coordinates
  [:arch/input-channel
   :arch/memory-store
   :arch/addressable-selection
   :arch/output-channel]
  :pheme/empirical-encounter
  {:arch/input-channel true
   :arch/memory-store true
   :arch/addressable-selection true
   :arch/output-channel true
   :machine/architecture-type :discrete-calculating-engine
   :historical/era :early-digital}
  :pheme/conformance-status :conformed
  :pheme/historical-anchors [:von-neumann-1945-first-draft :turing-1936]}

 ;; 3. Thirdness (Delome / Governing Habit & Formal Predication):
 :delome/thirdness
 (do
   (clojure.spec.alpha/def :arch/input-channel boolean?)
   (clojure.spec.alpha/def :arch/memory-store boolean?)
   (clojure.spec.alpha/def :arch/addressable-selection boolean?)
   (clojure.spec.alpha/def :arch/output-channel boolean?)

   (defn functional-calculating-automaton? [machine]
     {:pre [(map? machine)]}
     (and (true? (:arch/input-channel machine))
          (true? (:arch/memory-store machine))
          (true? (:arch/addressable-selection machine))
          (true? (:arch/output-channel machine))))))

## 5. Conceptual Terms & Trait Register

| Term | Kind | Definition |
|---|---|---|
| `:term/information-feeding` | Architectural | The ingestion of problem parameters and instructions into machine state (Operation a) |
| `:term/preliminary-storage` | Memory | The retention of interim computational states across sequential calculation cycles (Operation b) |
| `:term/timed-file-selection` | Control | The addressable retrieval of stored interim results contingent on execution context (Operation c) |
| `:term/result-reporting` | Output | The terminal emission of conformed solutions to external sinks (Operation d) |
| `:term/functional-decomposition` | Methodological | Resolution of an automaton into distinct operational faculties prior to mechanical modeling |
