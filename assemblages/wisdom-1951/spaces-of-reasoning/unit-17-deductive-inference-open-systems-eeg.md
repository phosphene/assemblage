---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-17-deductive-inference-open-systems-eeg
id: CR-WISDOM-U017
title: "Unit 17 — Deductive Inference, the Organism as an Open System, and EEG Scansion Synchronization (§4.5, §5, §6, pp. 18–21)"
source: J. O. Wisdom, "The Hypothesis of Cybernetics", BJPS 2(5), 1951, pp. 18–21
basis_tier: read-together
standard: work/topic-98577-public-evolutionary-science/SPEC-close-reading-architecture.md (clj-spec v1.2)
related:
  - unit-15-electroneural-analogies-reverberating-loops.md
  - unit-16-recognition-universals-scansion.md
  - ../deep-reads/wisdom-1951-hypothesis-of-cybernetics/L2-formalization/sec05-organism-as-open-system.md
  - ../deep-reads/wisdom-1951-hypothesis-of-cybernetics/L2-formalization/sec06-eeg-rhythms.md
  - ../deep-reads/wisdom-1951-hypothesis-of-cybernetics/derivations.md
---

# Unit 17 — Deductive Inference, the Organism as an Open System, and EEG

## Unit of Reason

The convergence of cybernetics with formal logic, general systems theory, and electrophysiology:
1. **Deductive Inference (§4.5):** Electroneural nets of regenerative loops have the formal properties of logical axioms (McCulloch & Pitts 1943) and Craik's symbolic models of purposive action.
2. **Open Systems & Equifinality (§5):** Bertalanffy and Burton's formulation of the organism as an open system exchanging energy and matter, where steady motion (homeostasis) characterizes open systems under suitable conditions, producing *equifinality* independent of initial conditions.
3. **Electro-encephalography (§6):** Cerebral rhythms (Grey Walter), epileptic seizure as rhythm synchronization, and the conjecture linking the alpha-rhythm directly to the visual scansion mechanism.

## Informal Concept

Section 4.5 closes the mental-operations loop by showing that logic does not require non-physical stuff: if neural circuits have the algebraic properties of axioms, deduction is mechanizable. Section 5 broadens the physical substrate: feedback is not enough if a system is closed; organisms must be *open systems* that import free energy and matter to maintain steady motion (homeostasis). Equifinality emerges as a thermodynamic necessity of open systems, not vitalistic entelechy. Section 6 provides the empirical electrical readout: brain waves are not mere static noise, but the macroscopic rhythm of regenerative scanning (alpha-waves) and resonant pathology (epileptic seizure under stroboscopic photic drive).

## Verbatim Text (zero ellipses)

> 4.5 Deductive inference. It is well known that logical machines exist. They are capable of giving deductions from data in a logical calculus. There is nothing very striking about this in principle. What is more interesting is the view that electroneural nets of regenerative loops have exactly the same formal properties as the axioms of mathematics and logic.² In one sense this tells us nothing new, for after all the brain must be such that we can carry out mathematical and logical inferences. The point of importance is that the concept of electroneural nets should have the formal properties required for this, i.e. some of the important properties it ought to have if it is to provide a reasonably good model of the brain. What this result gives, therefore, is not striking information about electroneural nets but support for the hypothesis of electroneural nets. Unfortunately the details involved here are technical.
> (§4.5, pp. 18–19)

> In this context we should notice an idea put forward by Craik.¹ He suggested that, when contemplating purposive action, one considers a 'symbolic model' of the action. Unfortunately his treatment was too sketchy to be illuminating. But he evidently entertained hope of being able to describe abstract conceptions, such as meaning, implication, number, and so on in terms of such symbolic models. There can be little doubt that all this is true in some sense or other; but whether it is true in the sense he sought, of models that could be built, cannot yet be answered.
> (§4.5, p. 19)

> 5 The Organism as an Open System. An interesting and important development that should be brought into relation with cybernetics is the interpretation of the organism as an open system. This is due to von Bertalanffy,² who has published a number of writings on system theory, and to Burton ³ who has briefly put forward the same idea. A dynamical system is 'closed' if it neither receives nor yields energy (nor exchanges material with its surroundings). For example, a perfect calorimeter containing a needle oscillating to and fro under the influence of a magnet would be a closed system. But the moving needle itself would constitute an 'open' system, for its energy would be passed to something outside it, say to the surrounding air. The chief link to be established is that between the open system and steady motion. Since an open system is one that exchanges energy with its surroundings, there are the possibilities that (i) it might lose energy until it attained dynamically stable equilibrium, (ii) it might absorb energy until it broke, (iii) it might oscillate, and (iv) it might attain steady motion, or at least perform small oscillations round the state of steady motion.
> (§5, pp. 19–20)

> In his mathematical and hypothetico-deductive development of the theory of systems, von Bertalanffy deduces that the properties of steady motion characterise in suitable conditions open systems and that they never characterise closed systems. Then, on the hypothesis that the organism is an open system, it must be capable of assuming steady motion ; but we have identified a state of adaptation with steady motion; we therefore have confirmation that the organism is an open system.
> (§5, p. 20)

> Thus von Bertalanffy goes on to develop from it the concept of 'equifinality' for final states, such as size attained by growth, which are by definition independent of initial conditions-he shows, for instance, that a man's size depends mainly on the relation between the weight and the surface area of the body, and not on conditions obtaining in the man's early history.
> (§5, p. 20)

> 6 Electro-encephalography. Has electro-encephalography ¹ or EEG any bearing on cybernetics ? Broadly speaking, what happens is that if electrodes are put to the head, various rhythms or waves, which emanate from various parts of the brain, are recorded by EEG machines in the form of wave-like curves on paper. EEG is used as an instrument of research in psychiatry. Thus an epileptic patient, whose rhythm in a resting state was first obtained, was confronted with a light flashing at regular intervals; when the frequency of the flashes became equal to or a multiple of his rhythm when resting, his new rhythm showed the characteristic form associated with an epileptic attack. 'This observation appears to support the hypothesis, based on study of analysed epileptic records, that certain types of seizure are due to exact synchronization of cerebral rhythms previously slightly out of step.' ¹
> (§6, pp. 20–21)

> Here we confine ourselves to the alpha-rhythm. This is recorded when a subject is relaxed with his eyes closed, and the rhythm alters when the eyes are opened. In general there is nothing surprising in finding such rhythms; for from electrodynamics we should expect to find waves associated with electric circuits. What the precise connections between these waves and the various electroneural circuits are is unknown. But one particular link is widely assumed, and this relates EEG to cybernetics: it is conjectured that the alpha-rhythm is associated with the scansion mechanism.
> (§6, p. 21)

## Triadic Semiotic Specification

```clojure
{:close-read/id "CR-WISDOM-U017"
 :close-read/uri "work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-17-deductive-inference-open-systems-eeg.md"
 :close-read/unit-of-reason "Deductive neural logic, thermodynamic open systems, and EEG rhythm scanning"

 ;; Seme (Firstness): qualitative signatures of open process and rhythm
 :seme/firstness
 {:quality/phenomenon [:isomorphism-of-circuit-and-axiom
                       :invariance-under-metabolic-flux
                       :macroscopic-cerebral-pulsation]
  :perceptual/invariant :steady-motion-in-flux-and-wave
  :perceptual/metaphor ["A flame holding its shape while gas streams through"
                        "An ink-line rippling on drum paper, snapping into synchrony under strobed light"
                        "A mechanical clock whose ticking mirrors logical deduction"]}

 ;; Pheme (Secondness): empirical systems, publications, and clinic
 :pheme/secondness
 {:encounter :logic-thermodynamics-electrophysiology-crossroads
  :data {:models [:mcculloch-pitts-logical-calculus
                  :craik-symbolic-models
                  :bertalanffy-burton-open-system
                  :grey-walter-eeg-photic-synchronization]
         :phenomena [:equifinality-in-growth
                     :epileptic-seizure-synchrony
                     :alpha-wave-block-on-eye-opening]}
  :historical-anchors [:mcculloch-pitts-1943-bull-math-biophys-5-115-33
                       :craik-1943-nature-of-explanation-ch5
                       :bertalanffy-1950-science-111-23-29
                       :burton-1939-j-cell-comp-physiol-14-327-49
                       :walter-dovey-shipton-1946-nature-158-541]}

 ;; Delome (Thirdness): formal inferential rules and ASTs
 :delome/thirdness
 [(isomorphism :neural-logic-isomorphism
    {:electroneural-nets :regenerative-loops
     :logical-formalism  :boolean-axiomatic-calculus
     :isomorphic-mapping :homomorphism})
  (taxonomy :thermodynamic-system-class
    {:open-system   {:exchange [:energy :matter] :regimes #{:steady-motion :equifinality}}
     :closed-system {:exchange [:energy] :destination :maximum-entropy}})
  (invariant :alpha-rhythm-scansion
    {:band            :8-to-12-hz
     :carrier-wave    :retinal-scansion-frequency
     :state-condition {:eyes-closed :present :eyes-open :blocked}})]
}
```

## Expression & Term Definitions

| Term | Kind | Definition | Nominatum |
|---|---|---|---|
| `:term/neural-logic-isomorphism` | formal property | Electroneural nets of regenerative loops possess identical formal properties to mathematical axioms | McCulloch & Pitts 1943, pp. 18–19 |
| `:term/symbolic-model` | cognitive concept | Internalized physical or neural proxy manipulated prior to purposive action | Craik 1943, p. 19 |
| `:term/open-system` | thermodynamic type | System maintaining continuous exchange of energy and matter with surroundings | Bertalanffy 1950 / Burton 1939, pp. 19–20 |
| `:term/closed-system` | thermodynamic type | System exchanging neither energy nor matter; bounded to monotonic entropy increase | Perfect calorimeter, p. 19 |
| `:term/equifinality` | dynamical property | Attainment of a constant final state irrespective of initial conditions or developmental trajectory | Growth scaling (weight/surface), p. 20 |
| `:term/alpha-rhythm` | electrophysiological wave | Synchronous resting occipital potential (8–13 Hz) blocked by sensory ingress | p. 21 |
| `:term/photic-resonance` | clinical phenomenon | Induction of epileptic seizure by stroboscopic frequency matching cerebral resting rhythms | Grey Walter et al. 1946, pp. 20–21 |
| `:term/scansion-carrier` | cybernetic conjecture | Hypothesis that alpha wave acts as the clocking/scanning sweep for visual recognition | p. 21 |

## Verify Notes

- **Basis tier:** `read-together` — Wisdom 1951 BJPS 2(5), pp. 18–21; PDF p. = text p. + 1.
- **Second-order witnesses:** McCulloch & Pitts 1943 (*Bull. Math. Biophys.* 5, 115–33); Craik 1943 (*The Nature of Explanation*, Ch. V); Ludwig von Bertalanffy 1950 (*Science* 111, 23–29; *BJPS* 1, 134–65); A. C. Burton 1939 (*J. Cell. Comp. Physiol.* 14, 327–49); W. Grey Walter, V. J. Dovey, and H. Shipton 1946 (*Nature* 158, 541).
- **System Theory vs. Cybernetics demarcation:** Wisdom explicitly notes that System Theory is *not* a subfield of cybernetics, but an independent, more general discipline; open-system thermodynamics provides the necessary foundation for steady states, but cybernetic feedback provides the regulatory control mechanism.
