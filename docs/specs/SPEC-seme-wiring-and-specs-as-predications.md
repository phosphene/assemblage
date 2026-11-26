---
uri: assemblage/specs/seme-wiring-and-specs-as-predications
id: SPEC-SEME-WIRING-PREDICATIONS
title: "Specification & Tickets: Wiring phosphene/seme & Specs as Predications in Close Reading"
owner: edphos
author: feelingflowingbot
status: living
version: 1.0.0
date: 2026-11-26
tags:
  - seme
  - assemblage
  - spec-alpha
  - specs-as-predications
  - peirce
  - hickey
  - tickets
  - mode-d
  - mode-s
---

# Specification & Tickets: Wiring `phosphene/seme` & Specs as Predications

## 1. Overview & Motivation

Following the close-reading methodology review in Topic 102248:
1. **The Seme Wiring Gap:** Naive close reads adopted triadic keywords (`:seme/firstness`, `:pheme/secondness`, `:delome/thirdness`) purely as descriptive metadata labels without pulling in the actual `phosphene/seme` deductive engine or invoking its core algebraic machinery (`rheme/sememe`, `pheme/conformed-witness`, `chain/validate-chain`, lattice meets/joins).
2. **Specs as Predications:** Clojure specs (`clojure.spec.alpha`) should not merely function as data validation boundary guards; they are **sovereign logical predications** in the Peircean sense—unsaturated predicates that characterize, classify, and gate empirical referents without mutating or closing their open-world extension.

---

## 2. Sprint Backlog & Ticket Registrations

| Ticket ID | Track | Pri | Title | Summary / Deliverables | Status |
|---|---|---|---|---|---|
| **T-427** | `platform.assemblage` | high | Wire `phosphene/seme` Engine into `assemblage` Pipeline | Add `phosphene/seme` to `assemblage/clojure/deps.edn`. Replace passive triadic markdown keyword annotations with active `phosphene.seme.core` functions (`sememe`, `conformed-witness`, `chain-grammar`). Verify AST blocks against Peircean triadic contracts in-process. | queued |
| **T-428** | `platform.epistemics` | high | Specs as Predications: Foundational Theory & Hermeneutic Architecture | Formalize Rich Hickey's axioms on specs as ontological predications rather than closed types. Establish theoretical grounding for using specs to model open-world scientific concepts, relational nuance, and empirical boundaries without type reification. | queued |
| **T-429** | `platform.seme` | med | Poset Lattice & Predicative Tension Ledger in Deliberative Synthesis | Connect `phosphene.seme.pheme` vertex subsumption (`subsumes?`, `vertex-meet`, `vertex-join`) and `phosphene.seme.delome` tension tracking to `assemblage.projector`, recording structural provocations when close reads encounter empirical anomalies. | queued |

---

## 3. Ticket Specifications

### T-427: Wire `phosphene/seme` Engine into `assemblage` Pipeline
- **Target Repo:** `phosphene/assemblage`
- **Dependencies:** Add `phosphene/seme` git dependency (or local path reference in dev) to `deps.edn`.
- **Scope:**
  1. Update `phosphene.assemblage.lens`:
     - Transform raw AST code blocks using `phosphene.seme.rheme/bundle-semes` for firstness.
     - Emit proof-carrying `phosphene.seme.pheme/conformed-witness` records for secondness.
     - Validate thirdness delome S-expressions via `phosphene.seme.delome` habit contracts.
  2. Integrate `phosphene.seme.markdown` for direct token-preserving AST ingest.
  3. Ensure all tests in `assemblage` pass with active semiotic verification.

### T-428: Specs as Predications: Foundational Theory & Hermeneutic Architecture
- **Target Location:** `meta/team/care-packages/` & `work/assemblage/docs/guides/`
- **Scope:**
  1. Synthesize the philosophical convergence between:
     - **Rich Hickey (`clojure.spec.alpha`):** Specifications are Boolean predicates over values; they don't form closed classes or types; they compose via logical conjunction (`s/and`), disjunction (`s/or`), and regular expressions (`s/cat`, `s/alt`).
     - **C. S. Peirce (Semiotics):** Firstness (qualitative possibility / seme), Secondness (brute fact / existence / pheme), Thirdness (law, mediation, habit / delome).
     - **J. O. Wisdom / Epistemic Demarcation:** Formulating machine models as provisional predicative approximations rather than ontological reductions.
  2. Document the "specs as predications" design pattern across the research workbench to guide incoming platform texts and research topics.

### T-429: Poset Lattice & Predicative Tension Ledger in Deliberative Synthesis
- **Target Repo:** `phosphene/assemblage` & `lib/clojure/seme`
- **Scope:**
  1. Map `assemblage` units of reason into a `phosphene.seme.pheme` vertex lattice.
  2. Implement automated trait subsumption checking: determine whether unit $U_A$ structurally subsumes $U_B$ based on shared distinctive semes.
  3. When an empirical witness fails a predicative contract (e.g. Ashby's machine model failing on human behavioral indeterminacy), log the failure into `seme.delome`'s axiomatic tension ledger rather than halting pipeline execution.

### T-430: Two-Tier Boundary Lexer & Safe Pattern Compilation (Pattern 1 & Antipattern 5)
- **Target Repo:** `phosphene/assemblage` (`ast.clj`, `client.clj`)
- **Scope:**
  1. Refactor `ast/parse-yaml-frontmatter` from loose, ad-hoc string regex matching (`re-matches #"^([a-zA-Z0-9_\-\./]+)\s*:\s*(.*)$"`) into a Two-Tier Lexer boundary pattern with explicit token conformance.
  2. Eliminate manual string escaping and regex parsing of JSON completion streams in `client/request-edn-projection` (`re-find #""content":\s*"((?:\"|[^"])*)""`) to prevent escape injection vulnerabilities and catastrophic backtracking.
  3. Ensure all host `java.util.regex.Pattern` usages are strictly confined to primitive string scanning at the outer boundary, immediately lifting results into tagged EDN values.

### T-431: Spec Regex AST Boundary Hygiene & Nested Splicing Safeguards (Antipattern 1 & CLJ-007)
- **Target Repo:** `phosphene/assemblage` (`spec.clj`, `lens.clj`)
- **Scope:**
  1. Enforce strict `(s/spec ...)` wrappers around all nested sequence grammars in `spec.clj` to prevent default sequence splicing/flattening traps.
  2. Ensure compound data grammars (such as `:delome/thirdness` and AST children sequences) distinguish between flat element streams and nested S-expression sub-trees.
  3. Add property-based verification checks (`delome_spec_test.clj`) confirming that multi-level nested forms do not splice inappropriately into flat parent clauses.

### T-432: Non-Backtracking Grammar Intersections & Invariant Cross-Checks via `s/&` (Pattern 3)
- **Target Repo:** `phosphene/assemblage` & `lib/clojure/seme`
- **Scope:**
  1. Replace any multi-pass filtering or lookahead assertions across close-read sequences and graph traversals with `s/&` intersection specs.
  2. Formalize cross-element invariants on reasoning sequences (e.g., verifying that a sequence of inference rules contains at least one ground antecedent and satisfies bounded complexity) without complecting the sequence grammar.

### T-433: Bidirectional Conformation & Unforming for Sovereign Identifiers (Pattern 6)
- **Target Repo:** `phosphene/assemblage` (`spec.clj`, `witness.clj`)
- **Scope:**
  1. Refactor structured identifiers (`:close-read/id`, `CR-WISDOM-UXXX`, `:node/id`) to use character-level sequence specs with bidirectional `s/conformer` pairs.
  2. Enable lossless symmetric round-trip: `(s/conform ::close-read-id (seq "CR-WISDOM-U001"))` $\leftrightarrow$ `(s/unform ::close-read-id conformed-val)`.
  3. Provide full generative testing generators for valid IDs using `s/exercise` and `test.check` without hand-rolled string generators.

### T-434: Quarantine Protocol & Lifting Unqualified Boundary Keys into Sovereign Qualified Senses
- **Target Repo:** `phosphene/assemblage` (`ast.clj`, `spec.clj`, `lens.clj`)
- **Scope:**
  1. Audit all map structures originating from Markdown AST (`nextjournal.markdown`), YAML frontmatter, and external JSON inputs.
  2. Implement the Mode-S to Mode-D quarantine boundary: ensure external unqualified keys (e.g. `:type`, `:text`, `:content`, `:info`) never circulate past the parser boundary.
  3. Replace `:req-un` definitions in `spec.clj` (`:node/entity`, `:ast.node/text-node`, `:ast.node/heading-node`, `:ast.node/code-node`, etc.) with sovereign qualified attributes (`:ast.node/type`, `:ast.text/content`, `:ast.heading/level`).
  4. Ensure outward projection functions project to unqualified external formats strictly at system egress.

### T-435: Bare `(s/keys)` Universal Fact Validation & Point-of-Use Destructuring
- **Target Repo:** `phosphene/assemblage` (`spec.clj`, `lens.clj`, `projector.clj`, `witness.clj`)
- **Scope:**
  1. De-complect static aggregate schemas: dismantle composite `s/keys` specs that bundle `:req` and `:opt` vectors (`::close-read-triad`, `::assemblage-graph`).
  2. Register all atomic attributes under sovereign namespaced coordinates with global timeless Senses via `s/def`.
  3. Replace rigid aggregate spec gates with universal bare `(s/keys)` open-world validation gates (`{:pre [(s/valid? (s/keys) m)]}`).
  4. Formalize local Presence via Clojure point-of-use destructuring (keyset extraction, defaults, and nested path navigation) inside consumer functions (`project-close-read`, `project-assemblage-elements`, `verify-witness`).

### T-436: Generative Testing via Character-Level Sequence Grammars & `test.check`
- **Target Repo:** `phosphene/assemblage` (`spec.clj`, `delome_spec_test.clj`)
- **Scope:**
  1. Formulate character-level sequence specs for sovereign identifiers (`CR-WISDOM-UXXX`, `:node/id`, `:concept/id`) paired with bidirectional conformers (`s/conformer`).
  2. Wire `clojure.spec.gen.alpha` and `test.check` to generate valid test instances automatically without hand-rolled regex string generators.
  3. Implement round-trip property tests: `(= id (s/unform ::sovereign-id (s/conform ::sovereign-id id)))`.
