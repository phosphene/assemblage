---
uri: work/topic-102248-cybernetics/docs/deep-reads/wisdom-1951-hypothesis-of-cybernetics/L3-verbatim/sec04-steady-motion-equilibrium-types
id: wisdom-1951-L3-sec04-steady-motion
title: "L3 Verbatim + Commentary — Section 4.0: Steady Motion vs. Dynamical Stable Equilibrium, Cannon/Hill Lineage, and Ashby's Step-Function Machine Definition"
authors: ["J. O. Wisdom"]
source_quality: primary
basis_tier: read-together
source: "Wisdom, J. O. 'The Hypothesis of Cybernetics', BJPS 2(5), May 1951, pp. 6–8 (§4.0). JSTOR 685605"
verify_ref: "lib/python/refverify — quote verbatim from PDF p.7–8; Cannon 1926 and Hill 1930 footnotes preserved"
related:
  - work/topic-102248-cybernetics/docs/deep-reads/wisdom-1951-hypothesis-of-cybernetics/L2-formalization/sec04-adaptation-neural-nets-recognition
  - work/topic-102248-cybernetics/docs/deep-reads/wisdom-1951-hypothesis-of-cybernetics/L3-verbatim/sec04-descartes-ashby-ultrastability
  - work/topic-102248-cybernetics/docs/deep-reads/wisdom-1951-hypothesis-of-cybernetics/L4-recursive/structural-tensions-and-lineages
  - work/research-wiki/wiki/sci.bio/hickey-rich
---

# L3 Verbatim — §4.0: Steady Motion vs. Dynamical Stable Equilibrium

## Witness 1 — The Ashby Correction: Equilibrium Misused (§4.0, p. 7)

> *"Now the pendulum and the thermostat are examples of two very different states, and in fact the rest of Ashby's argument shows that he is concerned not with dynamical stable equilibrium but with the state reached by a finely adjusted thermostat. He gives an indication that what he has in mind is the important concept known as 'steady motion', and also called 'a steady state'; but he continues, I think misleadingly, to use the word 'equilibrium'."*
> *(Section 4.0, p. 7 [PDF p. 8])*

### Annotation

*[read-together — Wisdom 1951 BJPS 2(5)]*

- **The Critique Move:** Wisdom is performing terminological hygiene on Ashby's *Design for a Brain* usage. The charge is not that Ashby is wrong about the physics — it is that the word "equilibrium" is a **false friend** importing pendulum intuitions (rest after damping) into a domain whose actual attractor is oscillation-about-a-mean.
- **Frege-Tellman Sense Split:** This is the exact passage recorded in L4 as the live Cannon/Ashby "homeostasis" sense question. The sign `equilibrium` carries two contesting senses: (i) dynamical stable equilibrium — velocities decay to zero; (ii) steady motion — velocities constant, energy flux continuous. Wisdom rules (ii) relevant and rules the word "equilibrium" misleading for it.
- **Platform resonance (`concepts/epistemic-levels` Level 2):** Distinguishing these two senses is prerequisite to any formalization: a regulator minimizing error to zero is a different mathematical object from a regulator maintaining a hovering envelope. Our `ashby_homeostat.py` bounds-check (`HomeostatBounds.is_viable`) formalizes the *envelope*, not a zero-attractor — this witness is the textual warrant for that design choice.

## Witness 2 — Steady Motion Characterized: The Spinning Top (§4.0, p. 7)

> *"A characteristic example of steady motion is that of a top spinning round a vertical axis—the velocities of the system are constant. Similarly, in suitable circumstances, a thermostat may attain such a state, i.e. one in which the temperature remains constant. But a top may spin without its axis being vertical; its axis will then revolve and describe a cone with its apex on the ground. Then the motion oscillates round the state of steady motion; likewise, with the thermostat, conditions may give rise to small oscillations round the steady state of approximately constant temperature. It is this concept, of approximation to a state of steady motion, and not the concept of dynamical stable equilibrium, that is relevant. One point here may cause confusion unless stressed. The numerical value of a state of steady motion differs from a constant in being the mean value of oscillations so small as to be approximately constant. The motion involved is thus not constant but smooth."*
> *(Section 4.0, p. 7 [PDF p. 8])*

### Annotation

*[read-together — Wisdom 1951 BJPS 2(5)]*

- **The Precession Image:** The coning top is the exemplary illustration: steady motion is not a point but a *region*, and real systems oscillate around it. Wisdom's "not constant but smooth" is the compressed statement of the regulated-band concept — the number reported is the mean of micro-oscillations.
- **Epistemic Stakes:** This is the conceptual foundation for distinguishing *hunting* (pathological overshoot, §4.1, witness in `sec04-cerebellar-tremor-hunting-ataxia.md`) from *healthy regulation* (small oscillation about the mean). Both are oscillations; the difference is amplitude relative to the viable envelope. A theory that models the regulated state as a fixed point cannot express this difference.
- **Platform resonance (`concepts/specification-compression-relation`):** "The motion involved is thus not constant but smooth" is the compressed mode; the top-precession exposition is the prolix mode. Same epistemic reality, dual registers.

## Witness 3 — The Cannon/Hill Lineage: Appearance of Equilibrium, Reality of Flux (§4.0, p. 7)

> *"The physical concept of steady motion may be used to characterise the state of adaptation (not the process of adaptation) in an organism. The link between them was first suggested by Cannon in 1926 and then by Hill in 1930. Their point was that the organism, or a system in it, merely gives the appearance of being in dynamical stable equilibrium, while in fact there is continuous interchange of material and energy which leaves characteristic quantities unaltered. Blood temperature would be an example."*
> *(Section 4.0, p. 7 [PDF p. 8]; footnotes: Cannon, Amer. J. Med. Sci. 171 (1926); Hill, 'Membrane-Phenomena in Living Matter: Equilibrium or Steady State', Trans. Faraday Soc. 26 (1930), 667–73)*

### Annotation

*[read-together — Wisdom 1951 BJPS 2(5)]*

- **State vs. Process Demarcation:** "the state of adaptation (not the process of adaptation)" — Wisdom brackets the *dynamic* question of how adaptation is achieved (that is §4.2's ultrastability territory) and fixes the *static* characterization of the adapted condition. The witness cleanly separates what later literature calls homeostasis (state) from ultrastability (process).
- **The Appearance/Reality Inversion:** The organism *looks* like a pendulum at rest and *is* a flux-balance. "Continuous interchange of material and energy which leaves characteristic quantities unaltered" is the precise, pre-Prigogine statement of the dissipative-structure idea — eight years before Schrödinger's *What is Life?* popularization is usually credited through Bertalanffy's open-system program (§5, witness in `sec05-open-systems-bertalanffy-equifinality.md`).
- **Hill's Title as a Witness in Itself:** Hill's 1930 title — "Equilibrium or Steady State" — shows the terminological dispute Wisdom corrects in Ashby was already a live issue in physiology 21 years earlier. Wisdom's §4.0 is thus not a novelty claim but a *cross-disciplinary alignment*: physiology (Cannon, Hill) had the concept; cybernetics (Ashby) had the formalism; the vocabulary lagged.
- **Historical Blind Spot (cross-ref L4):** Wisdom cites Cannon 1926 for the *concept* of homeostasis but does not cite Cannon's *The Wisdom of the Body* (1932) where the term "homeostasis" itself is coined. The sense question recorded in L4 (revert-to-same-state vs. aim-at-any-steady-motion) is therefore anchored here but remains open: Wisdom's own paper never uses "homeostasis" for the organism-level state, only "steady motion."

## Witness 4 — Ashby's Machine Definition and the Step-Function Unification (§4.0, p. 8)

> *"He considers a weight suspended by means of an elastic thread. When the weight is given a vertical motion, the weight and thread together constitute a dynamical system. Again if the thread is stretched far enough to break, the falling weight is also a dynamical system. Now ordinarily we should say that we had to do with two systems; but Ashby points out that we can treat them as one system that suffered a great change. He shows that this is easy to justify mathematically. We are familiar with the Newtonian equations of motion for the weight oscillating on the thread; these may be said to 'describe' the motion. We are also familiar with the equations of motion of a falling weight; these may be said to 'describe' its motion. Now the two 'descriptions' can be embraced under one wider 'description' (set of equations) by means of a mathematical step-function. The step-function is a device by which the general 'description', when a certain variable is below a certain limit, becomes equivalent to the first set of equations, i.e. to the 'description' of the oscillating motion, and, when the variable reaches that limit, becomes equivalent to the second set of equations, i.e. to the 'description' of the falling weight. Now a machine in Ashby's sense is such that each configuration of it determines the one following it; and dynamical systems of the kind discussed are machines in this sense."*
> *(Section 4.0, p. 8 [PDF p. 9])*

### Annotation

*[read-together — Wisdom 1951 BJPS 2(5)]*

- **The Ontological Braid:** This is the passage that legitimizes calling a *physics* (the weight-thread system) a *machine* (a configuration-determined sequence). Ashby's move: machine-hood is not a material property but a **determinism property** — "each configuration determines the one following it." Any dynamical system satisfying that clause is a machine.
- **The Step-Function as Identity Preserving Change:** The two Newtonian descriptions (oscillating vs. falling) are not two systems but one system that "suffered a great change" — unified under a single wider description with a threshold. This is precisely the mechanism our `ashby_homeostat.py` implements: continuity of identity across discrete reorganization events, with the uniselector break as the step-function.
- **Direct Code Warrant (derivations.md binding 1):** This witness is the textual ground for the frozen-dataclass state machine — each step emits a new immutable `HomeostatState` determined by the prior configuration; reorganization events are threshold-triggered transitions between equation regimes. No code is written yet per instruction; the binding is recorded, implementation deferred.
- **Platform resonance (`sci.bio/hickey-rich` values-over-places):** Ashby's "each configuration determines the one following it" is the 1948 statement of value-stream semantics — the history of configurations *is* the system; there is no persistent mutable place beneath it. Wisdom's careful reporting of the step-function device shows the idea predates its computational costume.

## Verification Audit

- **Provenance:** All four witnesses extracted verbatim from the JSTOR scan, PDF pp. 8–9, text pp. 6–8. Footnote apparatus (Ashby 1947 citation, Cannon 1926, Hill 1930) preserved in the Witness 3 header.
- **Refverify status:** Quotes cross-checked against PyMuPDF extraction on 2026-11-24. Scan artifacts corrected: "Ashby's aim was to give precision" truncation at p.7 boundary resolved against p.8 head; "I926"/"I930" old-style numeral rendering normalized to 1926/1930 in annotation but preserved in the source line.
- **Completeness:** §4.0's remaining content (Ashby's actual equilibrium quote 'It is an elementary property of all systems in equilibrium that they react so as to oppose disturbance', quoted from *Design for a Brain* p. 47 within Wisdom's text) is a second-order witness — a quote within a quote — and is flagged as such: any citation of it must go through Wisdom 1951 p. 7, not directly to Ashby, until the 1948/1952 Ashby editions are themselves verified.
- **Frontmatter parity:** uri/id/title/source_quality/related per care-package standard #102253.
