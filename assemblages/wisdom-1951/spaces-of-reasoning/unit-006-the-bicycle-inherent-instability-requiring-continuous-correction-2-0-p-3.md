---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-006-the-bicycle-inherent-instability-requiring-continuous-correction-2-0-p-3
id: CR-WISDOM-U006
title: "CR-WISDOM-U006 — The Bicycle: Inherent Instability Requiring Continuous Correction (§2.0, p. 3)"
source: J. O. Wisdom, "The Hypothesis of Cybernetics", BJPS 2(5), 1951, p. 2
basis_tier: read-together
standard: work/topic-98577-public-evolutionary-science/SPEC-close-reading-architecture.md (clj-spec v1.2)
pipeline: deepseek-v4.1-flash-1017 (native EDN)
---

# CR-WISDOM-U006 — The Bicycle: Inherent Instability Requiring Continuous Correction (§2.0, p. 3)

## Verbatim Text (zero ellipses)

> Riding a bicycle affords another example. When the rider falls slightly to the right, he turns his front wheel to the right, which stops his fall but leads to his being gently thrown over on the left. This he corrects by turning his wheel to his left. 2.I Feed-Back, Positive and Negative. These are all examples of 'negative feed-back '. In all of them the work done by a source of power is controlled-the machine is prevented from running away- by a mechanism that checks it and keeps the rate at which energy is expended hovering round a certain quantity. This feed-back is 'negative' because the work done by the feed-back mechanism opposes the working of the main driving force. Positive feed-back is also possible. Suppose, for instance, that instead of having a balloon in the gas pipe of the incubator, we have one outside, fixed in such a way that, as it expands through being heated, it pushes a lever of a tap, thus partially opening the tap.

## Triadic Semiotic Specification (clj-spec v1.2)

```clojure
{:close-read/id "CR-WISDOM-U006"
 :close-read/title "The Bicycle: Inherent Instability Requiring Continuous Correction"
 :seme/firstness {:quality/phenomenon :inherent-instability-of-moving-equilibrium
                  :perceptual/metaphor "A rider falling slightly to the right, wheel turned to catch the fall, only to be thrown gently over on the left"}
 :pheme/secondness {:encounter :continuous-corrective-counter-steering
                    :historical-anchors [:wisdom-1951-negative-feed-back :ashby-1948-homeostat]}
 :delome/thirdness (do
                     (defn correct [tilt wheel-angle]
                       {:pre [(unstable? tilt)]}
                       (let [counter (oppose tilt)]
                         (recur (overshoot tilt counter) counter)))
                     (defn stabilize [system]
                       (loop [state (perturb system)]
                         (if (hovering? state)
                           state
                           (recur (correct state (steer-against state)))))))
 :term/definitions [{:term/name :negative-feed-back
                     :term/def "Control in which the work done by the feed-back mechanism opposes the main driving force, keeping the rate of energy expenditure hovering round a certain quantity"}
                    {:term/name :inherent-instability
                     :term/def "A moving equilibrium that cannot persist without continuous corrective action, each correction itself inducing a counter-deviation"}]}
```

## Verify Notes

- **Basis tier:** `read-together` — Wisdom (1951), text p. 2.
- **Extraction:** Deterministic span extraction via PyMuPDF.
- **Spec verification:** Balanced AST bracket conformance verified (True).
