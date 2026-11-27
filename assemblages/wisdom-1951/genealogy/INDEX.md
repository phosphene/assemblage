# Wisdom (1951) Empirical Genealogical Projection Line

This directory hosts the empirical genealogical projection line for J. O. Wisdom's *"The Hypothesis of Cybernetics"* (*British Journal for the Philosophy of Science*, 1951, 2(5), 1–24).

Adheres to:
- **Empirical Ground Conditions:** Every textual citation, footnote, and witness is verified as an exact contiguous substring of the primary corpus (`primary/source-text.txt` and `primary/footnotes.edn`).
- **4-Section Architecture:** Verbatim Textual Witness (`:witness/verbatim`), Natural Language Analytic Gloss (`:gloss/analytic`), Formal Symbolic Logic (`:logic/specification`), and Relational Graph Meta-Structure (`:genealogy/metadata` in native EDN).
- **Clojure Spec & Morphic Lenses:** Formalized via `phosphene.assemblage.spec` (`::genealogy-unit`, `::genealogy-reference`) and projected via `phosphene.assemblage.lens` (`project-genealogy-network`).

---

## 1. Primary Citation Atlas
- **Atlas Map:** [`citations.edn`](citations.edn) — 20 canonical citations catalogued with historical disciplines, footnote coordinates, and epistemic roles.

---

## 2. Genealogical Units Index

| Unit ID | Title & Epistemic Focus | Primary Precursors / Citations | Section / Coordinate |
| :--- | :--- | :--- | :--- |
| **[`GEN-000`](unit-gen-000-dual-genesis-ashby-wiener.md)** | **The Dual Genealogical Root**<br>*(Ashby 1940 & Rosenblueth-Wiener-Bigelow 1943)* | W. R. Ashby (1940), RWB (1943), N. Wiener (1948), G. B. Wilbur (1941) | §3 (pp. 5–6) |
| **[`GEN-001`](unit-gen-001-thermodynamic-steady-state-cannon-hill.md)** | **Steady Motion vs. Equilibrium**<br>*(Thermodynamic Rectification)* | J. C. Maxwell (1868), W. B. Cannon (1926), A. V. Hill (1930), Ashby (1947) | §2.0 / §4.0 (pp. 3–8) |
| **[`GEN-002`](unit-gen-002-teleological-demarcation-northrop-wiener.md)** | **Purposive vs. Teleological**<br>*(Mechanism Demarcation)* | F. S. C. Northrop (1948), Wiener (1948), RWB (1943) | §3 (pp. 5–6) |
| **[`GEN-003`](unit-gen-003-pathological-hunting-ataxia-brazier-thompson.md)** | **Pathological Hunting & Ataxia**<br>*(Clinicopathologic Confirmation)* | Wiener (1948), M. A. B. Brazier (1950), A. Thompson (1951) | §4.1 (pp. 10–12) |
| **[`GEN-004`](unit-gen-004-ultrastability-cartesian-ceiling-ashby-cannon.md)** | **Ultrastability & Cartesian Ceiling**<br>*(Adaptation by Breakdown)* | Ashby (1945, 1948), Cannon (1932), Descartes (*Meditations*) | §4.2 (pp. 11–13) |
| **[`GEN-005`](unit-gen-005-reverberating-circuits-lorente-forbes.md)** | **Closed Internuncial Chains**<br>*(Histological Loops & Leucotomy)* | A. Forbes (1929), Ranson & Hinsey (1930), Lorente de Nó (1938), Adrian (1947) | §4.3 (pp. 13–15) |
| **[`GEN-006`](unit-gen-006-universals-calculus-craik-mcculloch-pitts.md)** | **Universals & Logical Calculus**<br>*(Collicular Invariance & Formal Nets)* | K. J. W. Craik (1943), McCulloch & Pitts (1943), Pitts & McCulloch (1947) | §4.4 / §4.5 (pp. 15–18) |
| **[`GEN-007`](unit-gen-007-open-systems-eeg-scansion-bertalanffy-walter.md)** | **Open Systems & Cortical Scansion**<br>*(Dynamic Steady States & Alpha Rhythm)* | L. von Bertalanffy (1950), A. C. Burton (1939), W. Grey Walter et al. (1946) | §5 / §6 (pp. 19–22) |
| **[`GEN-008`](unit-gen-008-genealogical-synthesis-model-ceiling.md)** | **Genealogical Synthesis & Model Ceiling**<br>*(Existence Proof vs. Ontological Reduction)* | J. O. Wisdom (1951), Ashby (1948), Wiener (1948) | §7 (pp. 22–24) |

---

## 3. Epistemic Graph Topology

```
                         [THE DUAL ROOT: GEN-000]
                     Ashby (1940)     RWB (1943)
                         /                 \
                        /                   \
           [GEN-001: STEADY MOTION]     [GEN-002: TELEOLOGY]
             Cannon / Hill (1926/30)       Northrop (1948)
                    /       \                     |
                   /         \                    |
       [GEN-004: ULTRASTABILITY]     [GEN-003: ATAXIA / PATHOLOGY]
          Ashby / Cannon (1948)        Wiener / Brazier / Thompson
                   |                              |
                   +--------------+---------------+
                                  |
                   [GEN-005: REVERBERATING LOOPS]
                     Forbes / Lorente de Nó (1938)
                                  |
                   [GEN-006: UNIVERSALS & CALCULUS]
                     Craik / McCulloch & Pitts (1943/47)
                                  |
                   [GEN-007: OPEN SYSTEMS & EEG]
                     von Bertalanffy / Grey Walter (1950)
                                  |
                   [GEN-008: EPISTEMIC CEILING]
                      Model as Existence Proof
```
