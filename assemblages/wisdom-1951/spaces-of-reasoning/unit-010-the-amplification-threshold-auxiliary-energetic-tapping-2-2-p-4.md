---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-010-the-amplification-threshold-auxiliary-energetic-tapping-2-2-p-4
id: CR-WISDOM-U010
title: "CR-WISDOM-U010 — The Amplification Threshold & Auxiliary Energetic Tapping (§2.2, p. 4)"
source: J. O. Wisdom, "The Hypothesis of Cybernetics", BJPS 2(5), 1951, p. 3
basis_tier: read-together
standard: work/topic-98577-public-evolutionary-science/SPEC-close-reading-architecture.md (clj-spec v1.2)
pipeline: deepseek-v4.1-flash-1017 (native EDN)
---

# CR-WISDOM-U010 — The Amplification Threshold & Auxiliary Energetic Tapping (§2.2, p. 4)

## Verbatim Text (zero ellipses)

> 2.2 Amplification Feed-Back. In none of the foregoing examples does a local source of energy arising from the feed-back mechanism play a part. The energy of the feed-back is derivative and redirected. But an additional source of energy may be exploited and utilised in the same sort of way. For example, in the combustion of coal, the lighted paper ignites some coal which releases vastly greater energy which fires more coal and so on. Again, in the uranium bomb, a neutron splits an atomic nucleus which releases energy and more neutrons which split more atomic nuclei and release more energy. Any chain- reaction mechanism is an illustration. Another example is the in- flationary spiral of increased wages leading to increased prices leading to increased wages. If we consider two human beings, the regard or animosity of one for the other may build up through positive feed-back-or it may become damped or allayed by negative feed-back.
> The additional source of energy may conveniently be spoken of as "local", as contrasted with the main or central source. Here again we have negative or positive feed-back according to whether the locally tapped energy is fed back to impede or facilitate the main motion of the machine. When the feed-back is due in any measure to the exploitation of local energy we may speak of "amplification". The energy of a feed-back may be solely derived from the main power or solely due to a local source, or to both, and it may be desirable to distinguish the regulating energy and mechanism as "simple feed-back", "amplification feed-back", and "mixed feed-back". These are not well-distinguished in the literature, but in the field of cybernetics it is simple feed-back—and mainly negative at that—that writers usually have in mind. But this restriction is not required, and in what follows "feed-back" will be used indifferently for any form of the phenomenon; where determinateness is needed the appropriate qualifying word will be added.

## Triadic Semiotic Specification (clj-spec v1.2)

```clojure
{:close-read/id "CR-WISDOM-U010"
 :close-read/title "The Amplification Threshold & Auxiliary Energetic Tapping"
 :seme/firstness {:quality/phenomenon :energy-release-exceeding-signal
                  :perceptual/metaphor "A lighted paper igniting coal that fires more coal"}
 :pheme/secondness {:encounter :auxiliary-energy-tapping
                    :historical-anchors [:wisdom-1951-amplification-feed-back :chain-reaction-mechanism :wage-price-spiral]}
 :delome/thirdness (do
                     (defn amplify [signal local-store]
                       {:pre [(has-energy? local-store)]}
                       (+ signal (tap local-store signal))))
 :term/definitions [{:term/name :amplification-feed-back
                     :term/def "Feed-back where an additional local source of energy is exploited and utilised, so that the released energy vastly exceeds the derivative, redirected energy of ordinary feed-back"}
                    {:term/name :auxiliary-energetic-tapping
                     :term/def "Exploitation of a local energy store triggered by a signal, as when lighted paper ignites coal or a neutron splits a nucleus"}
                    {:term/name :chain-reaction-mechanism
                     :term/def "Any mechanism in which a triggering event releases energy and further triggers, each releasing more energy and more triggers"}]}
```

## Verify Notes

- **Basis tier:** `read-together` — Wisdom (1951), text p. 3.
- **Extraction:** Deterministic span extraction via PyMuPDF.
- **Spec verification:** Balanced AST bracket conformance verified (True).
