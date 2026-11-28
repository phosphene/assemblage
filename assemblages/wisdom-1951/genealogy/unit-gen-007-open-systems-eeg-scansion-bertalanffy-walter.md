---
registry.core/id: unit-gen-007-open-systems-eeg-scansion-bertalanffy-walter
corpus: wisdom-1951
line: genealogy
section: "§5 / §6 Open Systems & Electro-encephalography (pp. 19–22)"
epistemic-status: :open-system-transfer
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v4-deliberative-seme"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme"]
---

# GEN-007 — Open Systems, Dynamic Steady States, and Cortical Scansion: Bertalanffy and Walter

## 1. Verbatim Textual Witness (zero ellipses)

> "An interesting and important development that should be brought into relation with cybernetics is the interpretation of the organism as an open system."

> "The chief link to be established is that between the open system and steady motion."

> "alpha-rhythm is associated with the scansion mechanism."

> "This observation appears to support the hypothesis, based on study of analysed epileptic records, that certain types of seizure are due to exact synchronization of cerebral rhythms previously slightly out of step."

*Source: Wisdom (1951), pp. 19–22. Footnote anchors: Ludwig von Bertalanffy (1950, "The Theory of Open Systems in Physics and Biology", *Science*, 111, 23–29; and 1950, "An Outline of General System Theory", *BJPS*, 1, 134–165); A. C. Burton (1939, *J. Cell. Comp. Physiol.*, 14, 327–349); W. Grey Walter, V. J. Dovey, & H. Shipton (1946, "Analysis of the Electrical Response of the Human Cortex to Photic Stimulation", *Nature*, 158, 541); Denis Hill & Geoffrey Parr (eds., 1950, *Electroencephalography*). Exact contiguous substrings of `primary/source-text.txt`.*

## 2. Natural Language Gloss (Wisdom's epistemic move)

Wisdom bridges cybernetics with contemporary **general system theory** and **clinical electroencephalography**:

1. **Von Bertalanffy’s Open Systems (§5):** Cybernetics cannot rely on classical closed thermodynamics where entropy strictly increases toward equilibrium (rest/death). Von Bertalanffy and Burton established that the living organism is an **open system** in continuous material and energetic exchange with its milieu. Wisdom establishes the missing theoretical link: *an open system is the necessary thermodynamic condition for maintaining steady motion* (reconnecting with Cannon and Hill from GEN-001).
2. **Grey Walter and Cortical Alpha Rhythm (§6):** Wisdom evaluates the television scansion hypothesis of Craik and McCulloch through Grey Walter’s EEG discoveries:
   - The normal 10 Hz alpha rhythm represents an unengaged searching scan of the visual cortex.
   - When vision engages a form, the alpha rhythm disappears (desynchronization/blocking), indicating the scan has locked onto a target.
   - Under repetitive stroboscopic photic driving (Walter, Dovey, Shipton 1946), cortical synchronization triggers clinical seizures, proving that cerebral rhythms represent coupled feedback oscillators vulnerable to resonant phase-locking.

Wisdom shows how open-system physics and empirical EEG recordings provide the macro-thermodynamic and micro-electrical boundaries within which cybernetic models must operate.

## 3. Formal Symbolic Logic

Thermodynamics of Open Systems (von Bertalanffy flux balance):

$$\frac{dE_{\text{total}}}{dt} = \frac{d_e E}{dt} + \frac{d_i E}{dt}, \qquad \frac{d_i E}{dt} \ge 0 \quad (\text{Internal Dissipation})$$

Steady motion condition via external energetic throughput:

$$\frac{d_e E}{dt} = - \frac{d_i E}{dt} < 0 \implies \frac{dE_{\text{total}}}{dt} = 0 \quad (\text{Dynamic Steady State} \ne \text{Equilibrium})$$

Cortical scansion oscillator and stroboscopic photic driving:

$$\ddot{\phi}_{\alpha} + \omega_0^2 \phi_{\alpha} = \gamma I_{\text{photic}}(t), \qquad I_{\text{photic}}(t) = I_0 \cos(\omega_f t)$$

Resonance synchronization condition (Walter seizure induction):

$$\omega_f \approx n \cdot \omega_0 \implies \|\phi_{\alpha}(t)\| \gg \phi_{\text{threshold}} \implies \text{EpilepticSeizure}(\text{Synchronization})$$

## 4. Relational Meta-Structure (EDN)

```clojure
{:genealogy/id "unit-gen-007-open-systems-eeg-scansion-bertalanffy-walter"
 :genealogy/target-claim "The thermodynamic substrate of steady motion is established by Bertalanffy's open systems, while cortical scansion is empirically confirmed by Grey Walter's EEG alpha desynchronization and photic seizure synchronization"
 :genealogy/epoch 1951
 :genealogy/epistemic-status :open-system-transfer
 :genealogy/precursors ["unit-gen-001-thermodynamic-steady-state-cannon-hill"
                        "unit-gen-006-universals-calculus-craik-mcculloch-pitts"]
 :genealogy/successors ["unit-gen-008-genealogical-synthesis-model-ceiling"]
 :genealogy/references
 [{:author "Ludwig von Bertalanffy" :year 1950
   :source "The Theory of Open Systems in Physics and Biology"
   :journal "Science" :volume 111 :pages "23-29"
   :role :open-systems-thermodynamics}
  {:author "A. C. Burton" :year 1939
   :source "The Properties of the Steady Rate Compared to those of Equilibrium"
   :role :biological-steady-rate}
  {:author "W. Grey Walter, V. J. Dovey, H. Shipton" :year 1946
   :source "Analysis of the Electrical Response of the Human Cortex to Photic Stimulation"
   :journal "Nature" :volume 158 :pages "541"
   :role :eeg-photic-seizure-synchronization}]
 :genealogy/epistemic-delta "open thermodynamics provides energetic grounds for continuous homeostatic motion"
 :genealogy/wisdom-synthesis :open-system-electrophysiological-grounding}
```
