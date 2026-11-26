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
