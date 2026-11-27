---
registry.core/id: unit-gen-001-thermodynamic-steady-state-cannon-hill
corpus: wisdom-1951
line: genealogy
section: "§2.0 / §4.0 (pp. 3–8)"
epistemic-status: :terminological-rectification
---

# GEN-001 — Steady Motion vs. Equilibrium: Wisdom Corrects Ashby through Cannon and Hill

## 1. Verbatim Textual Witness (zero ellipses)

> "Now the pendulum and the thermostat are examples of two very different states, and in fact the rest of Ashby's argument shows that he is concerned not with dynamical stable equilibrium but with the state reached by a finely adjusted thermostat."

> "He gives an indication that what he has in mind is the important concept known as "steady motion ", and also called "a steady state "; but he continues, I think misleadingly, to use the word " equilibrium "."

> "The physical concept of steady motion may be used to characterise the state of adaptation (not the process of adaptation) in an organism. The link between them was first suggested by Cannon in I926"

> "Their point was that the organism, or a system in it, merely gives the appearance of being in dynamical stable equilibrium, while in fact there is continuous interchange of material and energy which leaves characteristic quantities unaltered. Blood temperature would be an example."

*Source: Wisdom (1951), pp. 7–8. Footnote register: `primary/footnotes.edn` — Cannon 1926 (*American Journal of the Medical Sciences*, 171, 1–20), Hill 1930 (*Trans. Faraday Soc.*, 26, 667–673). Exact contiguous substrings of `primary/source-text.txt`.*

## 2. Natural Language Gloss (Wisdom's epistemic move)

Wisdom reads Ashby's 1947 *Mind* paper with a mechanic's eye and catches a category slip at its foundations: Ashby illustrates his concept with a **pendulum** (dynamical stable equilibrium — velocities decay to zero) and then immediately illustrates again with a **thermostat** (continuous energy throughput hovering about a mean). These are physically different regimes, and Ashby's own later argument shows he means the second — yet he keeps the word "equilibrium", which Wisdom flags as "I think misleadingly".

The rectification is not pedantry. It recruits an older physiological vocabulary that had already solved the problem:

- **Cannon (1926):** steady motion as the state of adaptation in organisms.
- **Hill (1930):** membrane phenomena in living matter are *not* equilibrium states — the appearance of rest conceals continuous interchange of material and energy with invariant characteristic quantities.

Blood temperature is Hill's kind of steady state, not a ball's equilibrium. Wisdom thereby corrects the younger field (cybernetics) using the elder field's rigor (thermoregulatory physiology), and simultaneously marks the semantic payload that the distinction carries: equilibrium is a *dead* attractor of a closed system; steady motion is a *live* mean of an open, energy-fluxing one. This is the terminological hinge on which the whole organism–machine analogy later turns.

## 3. Formal Symbolic Logic

Two distinct attractor regimes:

$$\text{Equilibrium: } \dot{\mathbf{x}}(t) \to 0 \ \land\ \|\dot{E}\| \to 0 \qquad \text{(closed, dissipative-to-rest)}$$

$$\text{SteadyMotion: } \mathbf{x}(t) \approx \bar{\mathbf{x}} \ \land\ \dot{M}_{\text{in}} = \dot{M}_{\text{out}} \ne 0 \ \land\ \dot{E}_{\text{in}} = \dot{E}_{\text{out}} \ne 0 \qquad \text{(open, flux-balanced)}$$

Wisdom's demarcation of Ashby's target regime:

$$\text{Thermostat} \in \text{SteadyMotion} \ \land\ \text{Pendulum} \in \text{Equilibrium} \implies \text{Ashby}_{1947} \text{ targets SteadyMotion but says "equilibrium"}$$

Physiological instantiation (Hill/Cannon transfer):

$$\text{Adaptation}(\mathcal{O}) \equiv \text{SteadyMotion}(\mathcal{O}), \qquad \text{e.g. } T_{\text{blood}}(t) \approx \bar{T} \ \text{under} \ \Phi_{\text{metabolic}} \ne 0$$

The error bound Wisdom imposes on the vocabulary:

$$\|\text{"equilibrium"}_{\text{Ashby}} - \text{steady motion}\| = \text{misleading} \implies \text{vocabulary must be rectified before mechanization}$$

## 4. Relational Meta-Structure (EDN)

```clojure
{:genealogy/id "unit-gen-001-thermodynamic-steady-state-cannon-hill"
 :genealogy/target-claim "Ashby's 'equilibrium' must be rectified to steady motion; physiology had the correct vocabulary 20 years earlier"
 :genealogy/epoch 1951
 :genealogy/epistemic-status :terminological-rectification
 :genealogy/precursors ["unit-gen-000-dual-genesis-ashby-wiener"]
 :genealogy/successors ["unit-gen-002-teleological-demarcation-northrop-wiener"
                        "unit-gen-004-ultrastability-cartesian-ceiling-ashby-cannon"
                        "unit-gen-007-open-systems-eeg-scansion-bertalanffy-walter"]
 :genealogy/references
 [{:author "W. R. Ashby" :year 1947
   :source "The Nervous System as Physical Machine"
   :journal "Mind" :volume 56 :pages "44-59"
   :role :rectified-object}
  {:author "W. B. Cannon" :year 1926
   :source "Some General Features of Endocrine Influence on Metabolism"
   :journal "American Journal of the Medical Sciences" :volume 171 :pages "1-20"
   :role :steady-motion-first-suggestion}
  {:author "A. V. Hill" :year 1930
   :source "Membrane-Phenomena in Living Matter: Equilibrium or Steady State"
   :journal "Trans. Faraday Soc." :volume 26 :pages "667-673"
   :role :thermodynamic-demarcation}]
 :genealogy/epistemic-delta "closed-system equilibrium vs open-system steady state"
 :genealogy/wisdom-synthesis :elder-discipline-corrects-younger}
```
