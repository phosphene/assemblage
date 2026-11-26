---
uri: assemblage/specs/close-reading-dual-disciplines
id: SPEC-CLOSE-READING-DUAL-DISCIPLINES
title: "Specification: The Dual Disciplines of Close Reading (Fast Projection vs. Deliberative Synthesis)"
owner: edphos
author: feelingflowingbot
status: living
version: 1.0.0
date: 2026-11-26
tags:
  - close-reading
  - reading-disciplines
  - fast-projection
  - deliberative-synthesis
  - verbatim-register
  - mode-d
  - mode-s
  - assemblage
  - edphos
---

# Specification: The Dual Disciplines of Close Reading

## 1. Context & Rationale

Close reading in the Phosphene platform serves two distinct and necessary epistemic functions:
1. **Scaffolding and ground-truth verification:** rapidly mapping the primary text, isolating exact contiguous token spans, checking syntax, and cataloging atomic terms.
2. **Deep structural discovery:** exploring conceptual nuance, identifying unmodeled residuals, formalizing cross-author analogies, and testing axiomatic self-consistency across the relational lattice.

Rather than treating high-throughput LLM pipelines and deep analytical synthesis as competing approaches or transitional phases, this specification codifies them as **two complementary, formal disciplines**:
- **Discipline A:** `style/fast-projection` (The Fast Harvester)
- **Discipline B:** `style/deliberative-synthesis` (The Deep Synthesizer)

---

## 2. Comparative Matrix

| Dimension | `style/fast-projection` (Fast Harvester) | `style/deliberative-synthesis` (Deep Synthesizer) |
|---|---|---|
| **Primary Objective** | Span harvesting, syntactic scaffolding, atomic trait extraction | Relational topology, analogy breakdown, epistemic boundary discovery |
| **Model Profile** | Low latency, non-reasoning / streaming (e.g. `deepseek-v4.1-flash`, reasoning=off) | Extended reasoning / high deliberation budget (`gemini-3.8-flash`/`pro`, `deepseek-r1`, reasoning=high) |
| **Throughput & Cadence** | Massively concurrent; ~1.5–2.5 s per unit; batch sweeps across whole chapters | Deliberative, turn-paced; multi-unit evaluation; synthesis across historical lineages |
| **Scope of Unit** | **Local / Atomic Unit of Reason:** Isolated contiguous span $\to$ Triad (`:seme`, `:pheme`, `:delome`) | **Systemic / Transversal Lattice:** Multi-unit tensions, cross-author comparisons, residual metrics |
| **Mode D Invariants** | Strict token contiguity (`exact-contiguous?`), `zero-ellipses?`, balanced EDN/AST brackets | Unmodeled residual tracking, non-reductionist bounds ($P(\mathcal{M}) \subset P(\mathcal{O}) \centernot\implies \mathcal{O}=\mathcal{M}$) |
| **Mode S Variance** | Constrained to local trait vocabulary | Open-world relational growth, novel structural analogies, subtle gradients |
| **Failure Mode Defended** | Contextual hallucination, bracket corruption, escaped-string decay | Reification of analogies, superficial mimicry, collapse of nuance into generic categories |

---

## 3. Discipline A: `style/fast-projection`

### 3.1 Contract
- Input: Bounded slice of primary corpus text $[i, j]$.
- Output: Atomic EDN AST conformed to `phosphene.assemblage.spec`.
- Invariant: Zero reasoning tokens allowed if they introduce formatting drift or latency penalties. Must output direct, unescaped EDN.

### 3.2 Metadata Block
```clojure
{:reading-discipline
 {:style :style/fast-projection
  :pipeline-version "v3-edn"
  :engine "deepseek/deepseek-v4.1-flash-1017"
  :reasoning-channel :off
  :target-register [:verbatim :atomic-ast :traits]}}
```

### 3.3 Verification Gate
1. `w/zero-ellipses?` must evaluate to `true`.
2. `w/exact-contiguous?` must evaluate to `true` against `primary/source-text.txt`.
3. EDN read must balance without error.

---

## 4. Discipline B: `style/deliberative-synthesis`

### 4.1 Contract
- Input: Multiple deposited units of reason, primary corpus text, and cross-author lineages.
- Output: Multi-tier Markdown files organized across four canonical registers:
  1. **Verbatim Register:** Contiguous, zero-ellipses token selections with verified byte offsets.
  2. **Natural Language Gloss & Conceptual Nuance:** Fine-grained analysis of the author's philosophical commitments and subtle distinctions.
  3. **Formal Logic:** First-order / set-theoretic definitions of mechanism, boundary, residual, and metric.
  4. **S-Expressions (`assemblage` EDN AST):** Executable Clojure models capturing predicates, relations, and open-world properties.

### 4.2 Metadata Block
```clojure
{:reading-discipline
 {:style :style/deliberative-synthesis
  :pipeline-version "v4-deliberative-edn"
  :engine "deliberative-reasoning-model"
  :reasoning-channel :extended
  :target-register [:verbatim :gloss :formal-logic :s-expressions]
  :evaluation-scope #{:cross-unit-consistency :residual-measurement :epistemic-bounds}}}
```

### 4.3 Verification Gate
1. Every embedded blockquote must pass `phosphene.assemblage.witness` verification.
2. Formal logic must define explicit boundaries and failure conditions (e.g. breakdown of deterministic state-transitions).
3. S-expressions must validate under `phosphene.assemblage.spec` while permitting unmodeled open-world keys.

---

## 5. Two-Stage Pipeline Lifecycle

When taking in a major foundational text:
1. **Stage 1 (Sweep):** Run `style/fast-projection` across candidate sections to deposit atomic spans, build the initial index, and establish baseline AST nodes.
2. **Stage 2 (Synthesize):** Run `style/deliberative-synthesis` across selected thematic clusters (e.g. Wisdom's theory of analogy, Ashby's homeostat, Bateson's schismogenesis) to formalize deeper conceptual nuance and build cross-unit transversal edges in the assemblage graph.

Both styles are permanently recorded in repository frontmatter, ensuring full auditability and epistemological provenance.
