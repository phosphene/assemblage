---
uri: assemblage/spec
title: "Specification: Combinatorics for Assemblage Architecture"
owner: edphos
author: feelingflowingbot
status: living
version: 0.1.0
tags:
  - assemblage
  - combinatorics
  - genealogy
  - transversal-relations
  - cybernetics
  - clj-spec
---

# Assemblage Architecture Specification

## 1. Mandate & Scope

`phosphene/assemblage` is the formal research engine and combinatorics toolkit for modeling:
1. **Genealogical Networks:** Branching trajectories, descent with modification, and non-linear historical lineages.
2. **Transversal Relations:** Cross-links, horizontal transfer, cycles, feedback, and shared trait patterns across distinct branches.
3. **Hierarchy & Multi-scale Topology:** Containment, stratification, modular closure, and multi-level selection.
4. **Hybrid Close Reading $\leftrightarrow$ Companion Software:** Linking close-read textual assertions directly to executable mathematical invariants and simulations.

## 2. Directory Structure

```
work/assemblage/
├── README.md
├── docs/
│   └── specs/                     # Formal specifications and model definitions
├── clojure/                       # Clojure combinatorics & AST engine
│   ├── deps.edn
│   ├── src/phosphene/assemblage/
│   │   ├── core.clj               # Core graph, traversal, and trait relations
│   │   ├── combinatorics.clj      # Combinatorial generators, cross-link metrics
│   │   └── spec.clj               # clj-spec v1.2 data & trait definitions
│   └── test/phosphene/assemblage/
│       └── core_test.clj
└── python/                        # Companion numerical & graph simulators
    ├── pyproject.toml
    ├── assemblage/
    │   ├── __init__.py
    │   ├── graph.py               # Transversal graph structures & cycle detection
    │   └── dynamics.py            # Dynamical feedback & trait distribution sweeps
    └── tests/
        └── test_graph.py
```

## 3. Epistemic Principles

- **Separation of Logic and State:** Structural operations on graphs and trait sets are pure functions returning immutable values.
- **Specification-Compression Parity:** Semiotic units extracted from textual close-reads map directly to topological graph primitives.
