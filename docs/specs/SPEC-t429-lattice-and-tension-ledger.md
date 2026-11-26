---
uri: assemblage/specs/t429-lattice-and-tension-ledger
id: SPEC-T429-LATTICE-TENSION-LEDGER
title: "Specification: Poset Lattice & Predicative Tension Ledger (T-429)"
owner: edphos
author: feelingflowingbot
status: living
version: 1.0.0
date: 2026-11-26
tags:
  - t-429
  - seme
  - pheme
  - assemblage
  - lattice
  - tension-ledger
  - wisdom-1951
  - ashby
  - mackay
---

# Specification: Poset Lattice & Predicative Tension Ledger (T-429)

## 1. Overview & Architecture

Ticket **T-429** implements the integration between `phosphene/assemblage` and `phosphene/seme`, bringing Peircean Secondness (empirical encounters) and Hickey's open-world keyset lattices directly into the close-reading workbench.

```
                      +-----------------------------+
                      |   Assemblage Unit of Reason |
                      |    (Level 1 Knowledge Node) |
                      +--------------+--------------+
                                     |
                                     v
                      [phosphene.assemblage.lattice]
                         unit->vertex morphism
                                     |
                                     v
                        [phosphene.seme.pheme]
                 +-------------------+-------------------+
                 |                                       |
                 v                                       v
         Poset Keyset Lattice               Lattice Provocation Gate
        - `subsumes?` (Va >= Vb)            - `retract-vertex`
        - `vertex-meet` (∧ consensus)       - `lattice-provocation`
        - `vertex-join` (∨ composite)       - Proof-Carrying Tension Record
                                                         |
                                                         v
                                           Predicative Tension Ledger
                                           - Retracted Lower Bound
                                           - Falsified vs Preserved Axioms
                                           - Numerical Residual Metric
```

---

## 2. Formal Invariants

1. **Subsumption Directionality:**
   In keyset lattices, a vertex $V_A$ subsumes $V_B$ ($V_A \ge V_B$) iff $\text{Req}(V_B) \subseteq \text{Req}(V_A)$. A more constrained model requires all that the general model requires, plus additional commitments. Any empirical referent satisfying $V_A$ is guaranteed to satisfy $V_B$.
2. **Consensus via Meet ($V_A \wedge V_B$):**
   The meet computes the greatest lower bound: $\text{Req}(V_A \wedge V_B) = \text{Req}(V_A) \cap \text{Req}(V_B)$. It captures the shared invariant genus between competing theoretical models (e.g. Ashby homeostatic machines vs. MacKay uncertainty machines).
3. **Decidable Tension & Retraction:**
   When an empirical referent $R$ falsifies one or more required coordinates of a claimed vertex $V_{\text{claimed}}$, the engine does not abort. Instead, it computes the maximal sound lower bound $V_{\text{retracted}}$ where:
   $$\text{Req}(V_{\text{retracted}}) = \text{AdmittedKeys}(R, V_{\text{claimed}})$$
   and emits an immutable proof record documenting the exact axiomatic provocation.
