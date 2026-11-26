---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-14-uniselector-step-function-adaptation
id: CR-WISDOM-U014
title: "Unit 14 — The Uniselector Step-Function: Adaptation by Trial and Error, the Descartes Refutation, the Cannon Naming Question (§4.2, pp. 12–13)"
source: J. O. Wisdom, "The Hypothesis of Cybernetics", BJPS 2(5), 1951, pp. 12–13
basis_tier: read-together
standard: work/topic-98577-public-evolutionary-science/SPEC-close-reading-architecture.md (clj-spec v1.2)
related:
  - unit-13-homeostat-four-magnets.md
  - ../deep-reads/wisdom-1951-hypothesis-of-cybernetics/L3-verbatim/sec04-descartes-ashby-ultrastability.md
  - ../deep-reads/wisdom-1951-hypothesis-of-cybernetics/L4-recursive/structural-tensions-and-lineages.md
---

# Unit 14 — The Uniselector Step-Function: Adaptation by Trial and Error

## Unit of Reason

The completion of the homeostat's dialectic: the step-function trigger, the empirical
tricks (knocked-out uniselectors, crossed mains wires), the "beyond the knowledge of its
designer" claim with its Descartes footnote, the two reverting classes (same steady state
vs. any steady motion), and the terminological audit against Cannon's "homeostasis".

## Informal Concept

Ordinary feedback adapts *within* a fixed wiring; the uniselector adapts *the wiring
itself*. When instability persists, the machine re-randomizes its own feed-back settings
until it stumbles into a stable arrangement — adaptation without foresight. This is the
textual basis for our homeostat step-function design in `scripts/ashby_homeostat.py`.

## Verbatim Text (zero ellipses)

> But the limit is so chosen that the current reaches it only when the system is unstable
> and the magnets diverge. Hence, so long as instability remains, the uniselectors alter
> the feed-backs until eventually a stable arrangement comes about. If we knock out some of
> the uniselectors and alter the feed-backs by hand, the remaining uniselectors will find a
> stable arrangement. Ashby and his colleagues even played several tricks on the homeostat.
> For instance they crossed the wires from the mains, whereupon the magnets again achieved
> stability. In short the homeostat adapted itself to new conditions.
> (§4.2, p. 12)

> Ashby also makes the interesting observation that, while the ordinary electronic machine
> can perform only those operations for which it was designed, the homeostat holds out the
> very real possibility of our making a machine capable of performing operations beyond
> those for which it was designed-and beyond the knowledge of its designer.¹
> (§4.2, p. 12)

> At this point it is worth drawing a distinction in connection with those processes that
> revert towards steady motion after disturbance. Some revert towards the same state of
> steady motion as they were in before being disturbed; but others are, as it were, content
> to aim at any steady motion. The one is illustrated by the steadiness of blood
> temperature; the other by bodily adjustments to a new diet, and by Ashby's machine with
> breaks:
> (§4.2, p. 12)

> A simple breaking down of a completed machine is sufficient, by itself, to produce the
> objective features of adaptation by trial and error, at least in the simpler cases; i.e.
> every machine breaking down automatically re-adjusts its own internal organisation until
> adaptation is achieved.²
> (§4.2, p. 12, quoting Ashby 1945)

> The homeostat embodies both classes of process or tendencies; the question therefore
> arises whether it is in line with the use of the word " homeostasis " which was
> introduced by Cannon.³ While Cannon was clearly concerned in the main with processes
> leading to the same state of steady motion, like steadiness of blood temperature, he does
> not seem to exclude from this heading processes leading to alternative states of steady
> (p. 12 → p. 13: sentence continues) motion; and this seems to be in the spirit of his
> work. The name " homeostat " therefore seems to have been suitably chosen, as being
> correlative to Cannon's use of " homeostasis "
> (§4.2, pp. 12–13)

**Footnote 1 (p. 12):**

> This would refute Descartes's principle that there must be at least as much reality and
> perfection in the cause as in the effect, a principle on which he based one of his proofs
> of the existence of God.

## Triadic Semiotic Specification

```clojure
{:close-read/id "CR-WISDOM-U014"
 :close-read/uri "work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-14-uniselector-step-function-adaptation.md"
 :close-read/unit-of-reason "Step-function reorganisation, the Cartesian refutation, and the Cannon naming audit"

 ;; Seme (Firstness): the uncanny quality of a machine recovering from sabotage
 :seme/firstness
 {:quality/phenomenon :uncanny-self-recovery
  :perceptual/invariant :adaptation-without-foresight
  :perceptual/metaphor "A wrecked engine that re-arranges its own valves until it runs again"}

 ;; Pheme (Secondness): the experiments and the naming lineage
 :pheme/secondness
 {:encounter :homeostat-tricks-and-terminology-audit
  :data {:experiments [:knock-out-uniselectors :cross-mains-wires]
         :reverting-classes [:same-steady-motion :any-steady-motion]
         :naming-authority :cannon-1932-wisdom-of-the-body}
  :historical-anchors [:ashby-1945-trial-and-error
                       :cannon-1932-wisdom-of-the-body
                       :descartes-causal-adequacy]}

 ;; Delome (Thirdness): the inferential rules
 :delome/thirdness
 (do
    (defn ultrastable-reorganisation [state]
      (loop [s state]
        (if (stable? s)
          s
          (recur (reset-feed-backs s :via :uniselectors)))))

    (def descartes-refutation
      '{:principle  (>= [reality cause] [reality effect])
        :architect  descartes
        :refuted-by (spontaneous-adaptation :homeostat)
        :because    (novel-behaviour :not-foresighted-by :designer)})

    (def reverting-classes
      {:same-steady-motion {:example :blood-temperature
                            :cannon-term :homeostasis}
       :any-steady-motion  {:example :diet-adjustment
                            :ashby-term :machine-with-breaks}}))}
```

## Expression & Term Definitions

| Term | Kind | Definition | Nominatum |
|---|---|---|---|
| `:term/step-function` | trigger rule | Discrete parameter change occurring only when instability threshold is crossed | Uniselector relay, pp. 11–12 |
| `:term/adaptive-trial-and-error` | process | Breakdown followed by automatic re-adjustment until adaptation | Ashby 1945 quote, p. 12 |
| `:term/reverting-class/same` | reversion type | Returns to the *same* steady state after disturbance | Blood temperature, p. 12 |
| `:term/reverting-class/any` | reversion type | Content to reach *any* steady motion after disturbance | Diet adjustment, homeostat breaks, p. 12 |
| `:term/descartes-refutation` | philosophical move | Machine behaviour exceeding designer's knowledge refutes causal adequacy | Footnote 1, p. 12 |
| `:term/cannon-naming-audit` | terminological check | Does "homeostat" square with Cannon's "homeostasis"? | pp. 12–13 |

## Verify Notes

- **Basis tier:** `read-together` — Wisdom 1951 BJPS 2(5), pp. 12–13; PDF p. = text p. + 1.
- **Second-order witnesses:** Ashby 1945 *J. Gen. Psych.* 32, 24 (fn 2); Cannon *The Wisdom
  of the Body*, London 1932 (fn 3); Descartes' causal adequacy principle (fn 1, no edition
  given — Wisdom cites the principle without textual apparatus). All route through Wisdom
  1951 until independently verified.
- **Open derivation node:** the Cannon sense question (does homeostasis admit alternative
  steady states?) is registered in `derivations.md` §3 and in L4
  `structural-tensions-and-lineages.md`.
- **Binding pending (no code per instruction):** "each configuration determines the one
  following it" class of claims maps to `scripts/ashby_homeostat.py` step-function
  invariants; see `derivations.md` §2.
