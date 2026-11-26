---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-007-the-simple-feedback-taxonomy-governors-escapements-thermostats-2-1-p-4
id: CR-WISDOM-U007
title: "CR-WISDOM-U007 — The Simple Feedback Taxonomy: Governors, Escapements, Thermostats (§2.1, p. 4)"
source: J. O. Wisdom, "The Hypothesis of Cybernetics", BJPS 2(5), 1951, p. 3
basis_tier: read-together
standard: work/topic-98577-public-evolutionary-science/SPEC-close-reading-architecture.md (clj-spec v1.2)
pipeline: deepseek-v4.1-flash-1017 (native EDN)
---

# CR-WISDOM-U007 — The Simple Feedback Taxonomy: Governors, Escapements, Thermostats (§2.1, p. 4)

## Verbatim Text (zero ellipses)

> mechanisms-governors, escapements, thermostats-are simply devices for re-directing some of the energy supplied by the source of power against the rest of that energy, and the positive feed-back mechan- isms redirect some of the energy towards increasing the energy supplied by the source of power-towards removing a blockage in fact. Both negative and positive types here described may be referred to as " simple feed-back mechanisms ". 2.2 Amplification Feed-Back. In none of the foregoing examples does a local source of energy arising from the feed-back mechanism play a part. The energy of the feed-back is derivative and redirected. But an additional source of energy may be exploited and utilised in the same sort of way. For example, in the combustion of coal, the lighted paper ignites some coal which releases vastly greater energy which fires more coal and so on.

## Triadic Semiotic Specification (clj-spec v1.2)

```clojure
{:close-read/id "CR-WISDOM-U007"
 :close-read/title "The Simple Feedback Taxonomy: Governors, Escapements, Thermostats"
 :seme/firstness {:quality/phenomenon :energy-redirection-against-itself
                  :perceptual/metaphor "A governor diverting part of the steam against the rest of the steam"}
 :pheme/secondness {:encounter :simple-feedback-mechanism
                    :historical-anchors [:wisdom-1951-simple-feedback :james-clerk-maxwell-governor :watt-centrifugal-governor]}
 :delome/thirdness
 [(invariant :feedback/energetic-tier #{:simple-feed-back :amplification-feed-back :mixed-feed-back})
  (invariant :feedback/energy-source #{:derivative-main :local-auxiliary :hybrid})
  (rule :simple-feedback-criterion
    (iff (and (= :feedback/energetic-tier :simple-feed-back)
              (= :feedback/energy-source :derivative-main))
         :conservative-redirection))]
 :term/definitions [{:term/name :simple-feed-back-mechanism
                     :term/def "A device that redirects some of the energy supplied by the source of power against the rest of that energy, without exploiting any local source of energy arising from the feed-back mechanism"}
                    {:term/name :negative-simple-feed-back
                     :term/def "Simple feed-back redirecting energy against the source's supply, as in governors, escapements, and thermostats"}
                    {:term/name :positive-simple-feed-back
                     :term/def "Simple feed-back redirecting energy towards increasing the energy supplied by the source of power, towards removing a blockage"}]}
```

## Verify Notes

- **Basis tier:** `read-together` — Wisdom (1951), text p. 3.
- **Extraction:** Deterministic span extraction via PyMuPDF.
- **Spec verification:** Balanced AST bracket conformance verified (True).
