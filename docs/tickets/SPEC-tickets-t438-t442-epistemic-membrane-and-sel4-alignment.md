---
uri: meta/assemblage/tickets/epistemic-membrane-and-sel4-alignment
owner: feelingflowingbot
status: active
date: 2026-11-27
tickets: [T-438, T-439, T-440, T-441, T-442]
precursors: [T-430, T-431, T-432, T-433, T-434, T-435, T-436, T-437]
audit-source: "ASKS-FOR-ASSEMBLAGE-MAINTAINERS.md (seL4-harness review)"
architectural-foundation: "research_problem_space_algebraic_structure_vs_open_world_natural_language.md (:research.epistemic-architecture/problem-space-v3)"
---

# Sprint Specification: Epistemic Membrane & seL4 Boundary Hardening (T-438 to T-442)

## Architectural Motivation

Following the empirical replication review by EschaBot (`ASKS-FOR-ASSEMBLAGE-MAINTAINERS.md`) and Ed Phil's foundational problem formulation (`:research.epistemic-architecture/problem-space-v3`), this sprint constructs the **Epistemic Membrane** separating internal exploratory modeling (**Space-O**) from external contract-driven verification harnesses (**Space-D**).

The sprint de-complects internal Open-World Assumption (OWA) fluidity from external fail-closed boundary contracts, establishes deterministic code-located textual anchors, backports exact-contiguous witness verification across all shipped units, and guarantees cross-runtime hash parity.

---

## Ticket Breakdown

### T-438: Deterministic Verbatim Witness Engine & Corpus Offset Normalizer
- **Domain:** Verbatim Textual Ground Condition (EschaBot Reqs 1, 3, 4, 6, 7, 8, 9)
- **Problem:**
  1. `witness_test.clj` previously validated only `L3-verbatim` staging files, allowing legacy units in `spaces-of-reasoning/` to ship with unverified quotes, manual offset approximations, or string-asserted verdicts (`"Zero ellipses"`).
  2. In-body footnotes caused split spans or false-negative contiguous checks.
  3. Source text hash and hyphenation normalizer rules had minor discrepancies between prose documentation and implementation.
- **Specification:**
  - Standardize `phosphene.assemblage.witness/locate` to compute `{source sha256, region, start, end, occurrences}` deterministically.
  - Enforce single-occurrence uniqueness (`(= 1 occurrences)`). Refuse 0 or $>1$ occurrences at the gate.
  - Publish the normalizer as an ordered rule list in data with published test vectors.
  - Update `witness_test.clj` to run `verify-witness` over every shipped unit's rendered Markdown blockquote and its underlying `:witness/text` record.

### T-439: Monotonic Zipper Backport Across Spaces-of-Reasoning Units
- **Domain:** Corpus Remediation via Nanopass AST Surgery (EschaBot Reqs 2 & 5)
- **Problem:**
  - Units U011, U014, U015, U017, and U018 contained paraphrased fragments or split quotes.
  - Commit `cba2a37` introduced escaped literal `\"` inside Markdown blockquotes in U004 and U012.
- **Specification:**
  - Using `phosphene.assemblage.nanopass` and `phosphene.stanza.zipper`, execute monotonic subtree transformations targeting Gate 1 (`## 1. Verbatim Text`) across all 21 `spaces-of-reasoning` units.
  - Repair literal backslash-escaped quotes in U004 and U012.
  - Replace paraphrased or joined spans with exact-contiguous textual witnesses anchored to `primary/source-text.txt`.
  - Guarantee 100% bitwise invariance of surrounding expository commentary, YAML frontmatter, and mathematical logic.

### T-440: Epistemic Membrane Egress Gate & Closed Outbound Specs
- **Domain:** Boundary Contract & Capability Hardening (EschaBot Reqs 10, 11, 12, 13, 14)
- **Problem:**
  - Under OWA, Clojure's bare `(s/keys)` and open lenses accept unmodeled keys (e.g. `:evil/grant`) and permissive migration shims (`:legacy-form any?`), which violates fail-closed security when exporting atoms to the seL4 capability harness.
- **Specification:**
  - Preserve bare `(s/keys)` and open lenses internally for exploratory AST manipulation in Space-O.
  - Construct a dedicated, closed egress membrane spec `phosphene.assemblage.membrane/export-unit` for Space-D export:
    - Rejects unknown keys at all depths (`(s/and (s/keys ...) closed-keys?)`).
    - Eliminates `:legacy-form any?` and `:open-map` shims for exported forms.
    - Fully specifies `:genealogy/roots`, `:genealogy/coined-by`, and `:wisdom-synthesis`.
    - Enforces sovereign IDs derived strictly from YAML frontmatter (missing frontmatter ID produces a typed refusal).
    - Fails closed with typed anomalies (`:cognitect.anomalies/incorrect`) on merge collisions or empty inputs.

### T-441: Cross-Runtime Canonical JSON Serialization (RFC 8785 / JCS)
- **Domain:** Multi-Language Content Addressing & Sovereign IDs (EschaBot Req 16)
- **Problem:**
  - Hashing raw EDN strings causes hash drift between JVM Clojure and Python/seL4 due to whitespace differences, key ordering, and floating-point formatting variations (`1.0E-4` vs `0.0001`).
- **Specification:**
  - Implement `phosphene.assemblage.canon` adhering to RFC 8785 (JSON Canonicalization Scheme / JCS):
    - UTF-8 byte stream.
    - Deterministic dictionary key sorting by Unicode code points.
    - IEEE 754 float representation standardization.
  - Validate across the published 11 test vectors to ensure bit-level SHA-256 agreement across Clojure, Python, and the seL4 harness.

### T-442: Full Projector Integration & Root CI Harness
- **Domain:** Verification, Indexing & Continuous Integration (EschaBot Reqs 15 & 17)
- **Problem:**
  - The repository lacked a top-level CI workflow running from the repository root, causing relative path failures when invoked from subdirectories.
  - Shipped units lacked structured provenance metadata (model, parameters, pipeline version, basis tier).
- **Specification:**
  - Update `projector.clj` to compose both the 21 spaces-of-reasoning units and the 9 genealogical units (`GEN-000` to `GEN-008`) into a unified multi-lensed graph.
  - Replace asserted strings in the projector with computed verification outputs from `verify-witnesses`.
  - Add GitHub Actions CI workflow running `clojure -M:test` from repo root.
  - Ensure all units record accurate basis tiers and pipeline run metadata.

---

## Ticket Dependency Graph

```
                   +------------------------+
                   |  T-438: Witness Engine |
                   +-----------+------------+
                               |
                               v
                   +------------------------+
                   |  T-439: Zipper Surgery |
                   +-----------+------------+
                               |
            +------------------+------------------+
            |                                     |
            v                                     v
+-----------------------+             +-----------------------+
|  T-440: Membrane Gate |             |  T-441: RFC 8785 JCS  |
+-----------+-----------+             +-----------+-----------+
            |                                     |
            +------------------+------------------+
                               |
                               v
                   +------------------------+
                   |  T-442: CI & Projector |
                   +------------------------+
```
