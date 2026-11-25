---
uri: work/topic-102248-cybernetics/docs/deep-reads/wisdom-1951-hypothesis-of-cybernetics/derivations
id: wisdom-1951-derivations
title: "Derivations — Binding Wisdom (1951) Textual Mechanisms to Verified Computational Invariants"
type: deep-read-derivations
status: living
owner: edphos
author: feelingflowingbot
date: 2026-11-24
source: "Wisdom (1951) ↔ work/topic-102248-cybernetics/scripts/ashby_homeostat.py"
tags:
  - derivations
  - homeostat
  - ultrastability
  - feedback-taxonomy
related:
  - work/topic-102248-cybernetics/docs/deep-reads/wisdom-1951-hypothesis-of-cybernetics/L3-verbatim/sec04-descartes-ashby-ultrastability
  - work/topic-102248-cybernetics/docs/deep-reads/wisdom-1951-hypothesis-of-cybernetics/L2-formalization/sec02-feedback-amplification-taxonomy
  - work/topic-102248-cybernetics/scripts/ashby_homeostat.py
  - work/topic-102248-cybernetics/tests/test_ashby_homeostat.py
---

# Derivations: Text → Code Bindings

*[formalized — work/topic-102248-cybernetics/scripts/ashby_homeostat.py, tests/test_ashby_homeostat.py]*

## 1. Binding Table

| # | Wisdom (1951) textual mechanism | Computational formalization | Verified invariant |
|---|---|---|---|
| 1 | §4.2: "so long as instability remains, the uniselectors alter the feed-backs until eventually a stable arrangement comes about" | Ultrastable step-function: while any essential variable $E_i \notin [E_{\min}, E_{\max}]$, re-sample coupling matrix $A_{t+1} \sim \mathcal{U}(-1.2, 1.2)$ | `test_ultrastable_step_reorg`: step events increase monotonically with instability duration; convergence terminates |
| 2 | §4.0/§4.2: steady motion as "hovering in steady motion... perpetually making small errors and actively damping them" | Continuous regulation phase between breaks: $E_{t+1} = f(E_t, A_t)$ with values held inside bounds | `test_bounds_viability`: all essential values within limits at every retained step |
| 3 | §4.2: "adapted itself to new conditions" (crossed-mains trick) | Parameter perturbation under seed: same seed → same trajectory (determinism); different coupling regime → different but still viable attractor | `test_determinism`, `test_parameter_invariance` |
| 4 | §4.2 (Ashby 1945 quote): "every machine breaking down automatically re-adjusts its own internal organisation until adaptation is achieved" | Reorganization counter increments per break event; adaptation = existence of terminal stable state | `test_ultrastable_step_reorg`: `reorganizations` counter and terminal `is_stable` flag |
| 5 | §4.2: "If we knock out some of the uniselectors... the remaining uniselectors will find a stable arrangement" | **Open derivation node** — partial-uniselector ablation: re-randomize only a subset of coupling entries per break | Not yet implemented; see §3 below |
| 6 | §2.2: simple vs. amplification feedback (derivative vs. tapped energy) | **Open derivation node** — two-loop comparison model: gain-limited loop (parasitic) vs. actuator-saturated loop (tapped reservoir) | Not yet implemented; see §3 below |

## 2. The Hickey Value Discipline (inherited)

All bindings operate on immutable state values (`HomeostatState` frozen dataclass): every step returns a new snapshot; identity is the historical stream, not a mutable place. This enforces Wisdom's own distinction between static memory (inscribed place) and regenerative circulation (values over time) at the implementation level.

## 3. Open Derivation Nodes (OWA attachment points)

1. **Partial-uniselector ablation (Wisdom p. 12):** sweep over fraction of step channels available; measure convergence latency and failure rate. Prediction: convergence degrades gracefully until a minimum channel count, then fails — the platform's first quantitative reading of "the remaining uniselectors will find a stable arrangement."
2. **Simple vs. amplification hunting (Wisdom §2.1–2.2):** two-loop simulation where the simple loop's feedback energy is bounded by primary drive and the amplification loop taps an independent reservoir. Prediction: hunting amplitude saturates in the simple loop but grows without bound (up to actuator limits) in the amplification loop — formalizing Wisdom's parasitic/tapped distinction.
3. **Cannon sense separation (Wisdom p. 12):** two reversion classes — reversion to *the same* steady state vs. reversion to *any* steady state — as two modes of the same homeostat (fixed vs. re-randomized attractor targets). Directly operationalizes the Cannon/Ashby terminology tension recorded in `L4-recursive/structural-tensions-and-lineages.md` §1.

## 4. Verification Protocol

Every derivation node, once implemented, must:
1. Add its property tests to `tests/` and pass under `cd lib/python && uv run pytest ../../work/topic-102248-cybernetics/tests/`.
2. Update the binding table above with the verified invariant name.
3. Reference the exact Wisdom passage (section, page) it operationalizes — no mechanism without a textual witness, no witness without a mechanism.
