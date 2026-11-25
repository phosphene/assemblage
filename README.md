# assemblage

Combinatorics for assemblage: genealogy and transversal relations across cross-links, cycles, branching, shared traits, patterns, and hierarchy.

## Overview

`phosphene/assemblage` is the formal research engine and combinatorics toolkit for modeling complex relational systems across biology, evolutionary theory, cybernetics, and semiotics.

## Dual-Engine Architecture

This repository implements a **hybrid research structure** bridging formal algebraic specifications, Clojure combinatorial data structures, and companion Python numerical/graph simulators:

- **`docs/specs/`**: Formal specifications and architectural definitions (`SPEC-assemblage-combinatorics.md`).
- **`clojure/`**: Pure Clojure data structures and combinatorics (`phosphene.assemblage.core`).
- **`python/`**: Graph topology and simulation tools (`assemblage.graph`), tested under the platform Python stack (`networkx`, `numpy`).

## Running Tests

### Python Engine
From repo root using `uv`:
```bash
uv run python -m unittest discover -s python/tests -p "*.py"
```

### Clojure Engine
```bash
cd clojure && clj -M:test
```
