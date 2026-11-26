---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-005-error-without-constancy-the-clock-s-inherent-imprecision-2-0-p-3
id: CR-WISDOM-U005
title: "CR-WISDOM-U005 — Error-Without-Constancy: The Clock's Inherent Imprecision (§2.0, p. 3)"
source: J. O. Wisdom, "The Hypothesis of Cybernetics", BJPS 2(5), 1951, p. 2
basis_tier: read-together
standard: work/topic-98577-public-evolutionary-science/SPEC-close-reading-architecture.md (clj-spec v1.2)
pipeline: deepseek-v4.1-flash-1017 (native EDN)
---

# CR-WISDOM-U005 — Error-Without-Constancy: The Clock's Inherent Imprecision (§2.0, p. 3)

## Verbatim Text (zero ellipses)

> Clearly the clock does not keep exact time. It is nearly always going slightly too fast or slightly too slow-only in between is it just right. Riding a bicycle affords another example. When the rider falls slightly to the right, he turns his front wheel to the right, which stops his fall but leads to his being gently thrown over on the left. This he corrects by turning his wheel to his left. 2.I Feed-Back, Positive and Negative. These are all examples of 'negative feed-back '. In all of them the work done by a source of power is controlled-the machine is prevented from running away- by a mechanism that checks it and keeps the rate at which energy is expended hovering round a certain quantity. This feed-back is 'negative' because the work done by the feed-back mechanism opposes the working of the main driving force.

## Triadic Semiotic Specification (clj-spec v1.2)

```clojure
{:close-read/id "CR-WISDOM-U005"
 :close-read/title "Error-Without-Constancy: The Clock's Inherent Imprecision"
 :seme/firstness {:quality/phenomenon :oscillation-around-ideal
                  :perceptual/metaphor "A clock that is nearly always slightly too fast or slightly too slow, just right only in between"}
 :pheme/secondness {:encounter :negative-feed-back-correction
                    :historical-anchors [:wisdom-1951-clock-imprecision :wisdom-1951-bicycle-balance :cannon-1932-homeostasis]}
 :delome/thirdness (do
                     (defn hover-around [rate target tolerance]
                       {:pre [(pos? tolerance)]}
                       (let [error (- rate target)]
                         (if (<= (abs error) tolerance)
                           rate
                           (- rate (signum error)))))
                     (defn negative-feed-back [main-force check-mechanism]
                       (check-mechanism (main-force))))
 :term/definitions [{:term/name :negative-feed-back
                     :term/def "Control in which the work done by a feed-back mechanism opposes the main driving force, keeping the rate of energy expenditure hovering around a certain quantity and preventing the machine from running away"}
                    {:term/name :error-without-constancy
                     :term/def "Inherent imprecision manifesting as continual slight deviation to either side of an ideal, corrected only in passing"}]}
```

## Verify Notes

- **Basis tier:** `read-together` — Wisdom (1951), text p. 2.
- **Extraction:** Deterministic span extraction via PyMuPDF.
- **Spec verification:** Balanced AST bracket conformance verified (True).
