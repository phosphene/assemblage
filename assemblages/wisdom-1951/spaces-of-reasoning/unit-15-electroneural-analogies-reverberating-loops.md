---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-15-electroneural-analogies-reverberating-loops
id: CR-WISDOM-U015
title: "Unit 15 — Electroneural Analogies: All-or-None Gates, the Telephone Exchange Overload, Non-Localised Memory, and the Reverberating Loop Genealogy (§4.3, pp. 13–15)"
source: J. O. Wisdom, "The Hypothesis of Cybernetics", BJPS 2(5), 1951, pp. 13–15
basis_tier: read-together
standard: work/topic-98577-public-evolutionary-science/SPEC-close-reading-architecture.md (clj-spec v1.2)
related:
  - unit-13-homeostat-four-magnets.md
  - unit-14-uniselector-step-function-adaptation.md
  - ../deep-reads/wisdom-1951-hypothesis-of-cybernetics/L2-formalization/sec04-adaptation-neural-nets-recognition.md
  - ../deep-reads/wisdom-1951-hypothesis-of-cybernetics/L4-recursive/structural-tensions-and-lineages.md
---

# Unit 15 — Electroneural Analogies: All-or-None Gates, the Telephone Exchange, Non-Localised Memory, and the Reverberating Loop

## Unit of Reason

The bridge layer between the homeostat (macro-scale adaptation) and the scansion
hypothesis (micro-scale perception): Wisdom establishes the *electroneural vocabulary* —
all-or-none firing, alternative routing, non-localised function — that §4.4's recognition
machinery presupposes. Three sub-machines are staged: the neuron as gate, the brain as
telephone exchange, and memory as a requisitioned circuit (condenser, then reverberating
loop), with the loop's genealogy traced through Forbes (1929) → Ranson & Hinsey (1930) →
Lorente de Nó (1922, independently) → Wiener.

## Informal Concept

The functional decomposition of §1 (machine = input/output + internal operations) is now
applied to the nervous system itself. Two structural correspondences carry the whole
section: (1) the all-or-none principle — electronic gates and neurons are both binary
threshold devices, so logic applies to tissue; (2) non-localisation — because alternative
circuits exist, destroying a part does not destroy a function. Memory is then modelled as
a *requisitioned* part of the neuron pool: held, unavailable for other use, and (critically
for §4.4) self-sustaining as a circulating impulse — the regenerative loop.

## Verbatim Text (zero ellipses)

> Neurons are the cell-units of the nervous system, and a set of neurons can form an
> electrical system. A neuron has a different electric potential inside and outside the
> cell-wall, and, when these potentials equalise, the neuron is said to " fire ". Impulses
> are transmitted from one neuron to another through their synapse (that is, the region
> between transmitting and receiving 'aerials '-an impulse is transmitted by the axon of
> one neuron and received by the dendrites of a neighbouring neuron). Signals are thus
> passed in the form of electroneural impulses, and a chain of neurons forms an
> electroneural circuit. It may be noted that in electronic machines an impulse is either
> received or not received, a 'gate' to a circuit is either open or closed, i.e. they work
> according to the 'all or none' principle; and the same is true of the nervous system-a
> neuron fires or does not fire.
> (§4.3, p. 13)

> He thinks that the brain may become overloaded like a busy telephone exchange. The
> telephone engineer may overcome this by cutting some subscribers' lines or by removing
> their instruments : the analogue would be the operations of pre-frontal leucotomy and
> lobotomy respectively.
> (§4.3, p. 13, on Wiener)

> Thus Wiener's analogy concerns traffic jams in both the exchange and the brain-too great
> density of impulses directed towards a certain route or too few alternative routes
> available. I would add that we could think of the transference in psycho-analysis as a
> means of earthing blocked charges.
> (§4.3, p. 13)

> Memory does not appear to be connected with any one location in the brain, for
> widespread destruction of very considerable areas, a frontal lobe or a cerebral
> hemisphere for instance, does not destroy, though it may impair, memory--that is,
> previous experiences can be remembered, though sometimes with greater difficulty.
> (§4.3–§4.3 → memory paragraph, pp. 13–14)

> And it suggests that a memory requires the requisitioning of a part of the neuron pool,
> which so long as the memory is not dissipated, is not available for other use. One of
> Wiener's ideas seems to be that a set of neurons might form a condenser maintaining a
> charge.
> (§4.3, p. 14)

> Here we have the conception of what was called " reverberating " chains of neurons by
> Ranson and Hinsey, following the first hint given by Forbes when he spoke of " a sort of
> central reverberation " to account for the after-discharge characteristics of reflex
> contractions; the phrase " self-re-exciting" chains of neurons was used by Lorente de Nó,
> working independently. A chain is reverberating or self-re-exciting if in a closed
> circuit one neuron fires, thereby leading its neighbour and in turn the next neighbour to
> fire, and so on, until the last one makes the first one fire again. In this way activity
> in the circuit continues indefinitely or regenerates itself, and the circuit thus comes
> to be called a " regenerative loop ".
> (§4.3, pp. 14–15)

> On the other hand, the mechanism cannot be such that when once it is tapped it is
> completely discharged-else we could recall an experience but once. Thus a regenerative
> loop would need in addition to have the property of a magnetic tape, which is used in
> recording machines, in order that the record of stored information should be ' playable'
> more than once. It remains to be seen whether such a model could be constructed.
> (§4.3 → §4.4 boundary, p. 15)

## Triadic Semiotic Specification

```clojure
{:close-read/id "CR-WISDOM-U015"
 :close-read/uri "work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-15-electroneural-analogies-reverberating-loops.md"
 :close-read/unit-of-reason "The electroneural vocabulary: gates, exchanges, requisitioned circuits, regenerative loops"

 ;; Seme (Firstness): three qualitative textures
 :seme/firstness
 {:quality/phenomenon [:threshold-firing :routing-under-overload :circulating-vivacity]
  :perceptual/invariant :sameness-of-kind-across-substrate
  :perceptual/metaphor ["A gate that is either shut or open, nothing between"
                        "A jammed exchange holding at full efficiency until sudden collapse"
                        "A pulse chasing itself round a ring, refusing to die"]}

 ;; Pheme (Secondness): the neurophysiological and clinical witnesses
 :pheme/secondness
 {:encounter :electroneural-analogy-lineage
  :data {:devices [:neuron-as-gate :automatic-telephone-exchange :condenser :regenerative-loop]
         :clinical-analogues [:pre-frontal-leucotomy :lobotomy :psychotic-sudden-onset
                              :transference-as-earthing]
         :loop-genealogy [:forbes-1929-central-reverberation
                          :ranson-hinsey-1930-reverberating-chains
                          :lorente-de-no-1922-self-re-exciting
                          :wiener-1948-condenser-memory]}
  :historical-anchors [:wiener-cybernetics-1948-pp-175-6-146-31
                       :adrian-1947-physical-background-perception
                       :ranson-hinsey-1930-am-j-physiology-94-490
                       :forbes-1929-foundations-exp-psychology-ch3-p152
                       :lorente-de-no-1922-trabajos-madrid-20-41-47]}

 ;; Delome (Thirdness): the inferential rules
 :delome/thirdness
 (do
    (def all-or-none-principle
      '{:device electronic-machine {:gate-state #{:open :closed}}
        :device neuron            {:firing-state #{:fires :does-not-fire}}
        :invariant :binary-threshold-across-substrates})

    (defn routing-capacity [system routes load]
      (cond (>= load (alt-route-threshold routes)) :widespread-dislocation
            :else :normal-performance))

    (defn requisition [neuron-pool memory]
      {:held-circuit (select-circuit neuron-pool memory)
       :side-effect :unavailable-for-other-use
       :persists-until :dissipation})

    (defn regenerative-loop [closed-circuit]
      (loop [firing (first closed-circuit)]
        (let [next (fire-neighbour firing)]
          (if (not (starts-first? next closed-circuit))
            (recur next)
            (recur (fire-neighbour next))))))

    (def recall-constraint
      '{:requirement :memory-playable-more-than-once
        :forbids :complete-discharge-on-tap
        :needed-property :magnetic-tape-persistence
        :status :open-whether-constructible}))}
```

## Expression & Term Definitions

| Term | Kind | Definition | Nominatum |
|---|---|---|---|
| `:term/all-or-none` | principle | Impulse transmission admits no intermediate state: gate open/closed, neuron fires/does not fire | p. 13 |
| `:term/non-localised` | structural property | Damage to a part does not in general wipe out the function, because alternative circuits exist | pp. 13–14 |
| `:term/overload-analogy` | clinical mapping | Brain overloads like a telephone exchange; leucotomy = cutting lines, lobotomy = removing instruments | p. 13 |
| `:term/neuron-pool-requisition` | memory model | A memory holds part of the neuron pool, unavailable for other use until dissipated | p. 14 |
| `:term/reverberating-chain` | circuit | Closed circuit in which each neuron fires its neighbour until the last re-fires the first | pp. 14–15 |
| `:term/regenerative-loop` | circuit | A reverberating chain regarded as regenerating its own activity indefinitely | p. 15 |
| `:term/repression-as-blockage` | speculative bridge | Repressed experiences as badly blocked regenerative loops | p. 15 |
| `:term/magnetic-tape-requirement` | design constraint | Memory must be 'playable' more than once; total discharge on recall is disallowed | p. 15 |

## Verify Notes

- **Basis tier:** `read-together` — Wisdom 1951 BJPS 2(5), pp. 13–15; PDF p. = text p. + 1.
- **Second-order witnesses (route through Wisdom 1951 until independently verified):**
  Wiener, *Cybernetics* pp. 175–6 (overload), p. 146 (condenser), p. 31 (printed page,
  cited at §4.4); Adrian 1947 pp. 89–92; Ranson & Hinsey 1930 *Am. J. Physiol.* 94, 490;
  Forbes 1929; Lorente de Nó 1922 — Wisdom flags Lorente de Nó as "not seen by me;
  referred to by the author in a later paper", itself a second-order-of-second-order
  witness (tertiary by our ladder). Flag accordingly.
- **Wisdom's own additions:** the transference-as-earthing remark and the repression/
  blocked-loop bridge are *Wisdom's* extensions of Wiener, not Wiener's. Attribution kept
  explicit in the pheme block.
- **Epistemic-status flag:** Wisdom marks the Wiener analogies "speculative in that they
  are not yet confirmed" — the seme/pheme projection must not upgrade them to established.
