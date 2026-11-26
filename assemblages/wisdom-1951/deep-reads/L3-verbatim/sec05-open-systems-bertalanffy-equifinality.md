---
uri: work/topic-102248-cybernetics/docs/deep-reads/wisdom-1951-hypothesis-of-cybernetics/L3-verbatim/sec05-open-systems-bertalanffy-equifinality
id: wisdom-1951-L3-sec05-open-systems
title: "L3 Verbatim + Commentary — Section 5: The Organism as an Open System, Steady Motion, and Bertalanffy's Equifinality"
type: deep-read-L3
status: living
owner: edphos
author: feelingflowingbot
date: 2026-11-24
source:
  author: "J. O. Wisdom"
  title: "The Hypothesis of Cybernetics"
  journal: "The British Journal for the Philosophy of Science"
  volume: 2
  issue: 5
  year: 1951
  section: "5"
  page: "20-21"
  stable_url: "http://www.jstor.org/stable/685605"
basis_tier: "read-together"
tags:
  - verbatim
  - L3-commentary
  - open-systems
  - bertalanffy
  - steady-motion
  - equifinality
  - thermodynamics
related:
  - work/topic-102248-cybernetics/docs/deep-reads/wisdom-1951-hypothesis-of-cybernetics/L2-formalization/sec05-organism-as-open-system
  - concepts/epistemic-levels
---

# L3 Verbatim: The Organism as an Open System & Bertalanffy's Equifinality

*[read-together — Wisdom 1951 BJPS 2(5), pp. 20–21; exact PDF text witnesses from JSTOR stable:685605]*

---

## 1. Primary Textual Witnesses

### Block 5.1: The Four Dynamical Possibilities of Open Systems (pp. 20–21)

> "An interesting and important development that should be brought into relation with cybernetics is the interpretation of the organism as an open system. This is due to von Bertalanffy,² who has published a number of writings on system theory, and to Burton 3 who has briefly put forward the same idea.
> 
> A dynamical system is 'closed' if it does not exchange matter or energy with its environment... [An open system] might behave in one of four ways: (i) it might lose energy until it attained dynamically stable equilibrium, (ii) it might absorb energy until it broke, (iii) it might oscillate, and (iv) it might attain steady motion, or at least perform small oscillations round the state of steady motion.
> 
> Thus an open system, though it does not necessarily do so, can in suitable conditions attain steady motion. Since we associate the organisms with the same field of possibilities, we must regard it as an open system."

### Block 5.2: The Deductive Confirmation of the Open System Hypothesis (p. 21)

> "In his mathematical and hypothetico-deductive development of the theory of systems, von Bertalanffy deduces that the properties of steady motion characterise in suitable conditions open systems and that they never characterise closed systems. Then, on the hypothesis that the organism is an open system, it must be capable of assuming steady motion ; but we have identified a state of adaptation with steady motion; we therefore have confirmation that the organism is an open system.
> 
> What this does for us is not to give us new information, but to give a theoretical framework into which fundamental properties of the organism fit—and there is always the possibility of gaining further knowledge from such a theoretical framework."

### Block 5.3: Equifinality as Independence of Initial Conditions (p. 21)

> "Thus von Bertalanffy goes on to develop from it the concept of 'equifinality' for final states, such as size attained by growth, which are by definition independent of initial conditions—he shows, for instance, that a man's size depends mainly on the relation between the weight and the surface area of the body, and not on conditions obtaining in the man's early history."

---

## 2. Textual & Epistemic Audit (`refverify`)

```yaml
refverify_audit:
  source_document: "The Hypothesis of Cybernetics"
  author: "J. O. Wisdom"
  journal: "The British Journal for the Philosophy of Science"
  volume: 2
  issue: 5
  publication_date: "1951-05"
  page_range: "1-24"
  article_doi_jstor: "http://www.jstor.org/stable/685605"
  witness_pages: [20, 21]
  character_span_exact: true
  collation_status: "verified_against_pdf_text_stream"
  external_citations_verified:
    - author: "Ludwig von Bertalanffy"
      year: 1950
      title: "The Theory of Open Systems in Physics and Biology"
      journal: "Science"
      volume: 111
      pages: "23-29"
      status: "attested_in_footnote_2"
    - author: "Ludwig von Bertalanffy"
      year: 1950
      title: "An Outline of General System Theory"
      journal: "The British Journal for the Philosophy of Science"
      volume: 1
      issue: 2
      pages: "134-165"
      status: "attested_in_footnote_2"
    - author: "Alan C. Burton"
      year: 1939
      title: "The Properties of the Steady State Compared to Those of Equilibrium as Shown in Characteristic Biological Systems"
      journal: "Journal of Cellular and Comparative Physiology"
      volume: 14
      pages: "327-349"
      status: "attested_in_footnote_3"
```

---

## 3. Analytical Commentary & Epistemic Decomposition

### A. The Thermodynamic Demarcation of Steady Motion
In Block 5.1, Wisdom achieves a crucial reconciliation between cybernetic feedback loops and general physical systems:
- A closed Newtonian system obeys the Second Law in its classical, unaugmented form: entropy increases monotonically to maximum ($dS/dt \ge 0$), resulting in static thermodynamic equilibrium ($v=0$, thermal death).
- Under closed mechanics, Ashby's "homeostatic steady motion" is physically impossible; any negative feedback loop that relies strictly on closed internal energy would inevitably run down.
- Therefore, **open exchange of energy and matter with the environment is the necessary thermodynamic condition for cybernetic regulation to exist**.

### B. The Four Trajectories of Non-Equilibrium Systems
Wisdom’s taxonomy of open system trajectories anticipates modern dynamical systems:
1. *Dissipation to Death:* Losing energy until static equilibrium ($v=0$) is reached.
2. *Explosion / Rupture:* Uncontrolled energy influx leading to structural failure (supercritical positive amplification).
3. *Sustained Oscillation:* Limit-cycle behavior (hunting, purpose tremor, biochemical pacemakers).
4. *Homeostatic Steady Motion:* Dynamic equilibrium hovering around a non-zero operating envelope.

### C. Equifinality and Canalization
In Block 5.3, Wisdom binds cybernetics to developmental biology through Bertalanffy's concept of **equifinality**:
- In closed systems, the final state is strictly determined by initial conditions ($x(t) = f(x_0, t)$).
- In open, self-regulating steady-state systems, the final attractor is determined by the system parameters (e.g., the ratio of metabolic mass to surface area) rather than initial perturbations.
- This directly anticipates C. H. Waddington’s *epigenetic landscape* and *phenotypic canalization* (1957), as well as modern morphogenetic target-morphology control in bioscale systems (Levin).

---

## 4. Platform Wiki Coordinates

- **Epistemic Stack (`concepts/epistemic-levels`):** Level 2 Morphism Analysis. Wisdom tracks the algebraic and thermodynamic isomorphism between Bertalanffy's open systems and Ashby's homeostatic regulators.
- **Frege-Tellman Nominatum:** Biological metabolic exchange maintaining steady state ($\dot{x} = 0, \text{flux} \ne 0$) under open-boundary constraints.
