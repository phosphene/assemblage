---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-008-negative-vs-positive-polarity-divergence-vs-equilibrium-2-1-p-3
id: CR-WISDOM-U008
title: "CR-WISDOM-U008 — Negative vs. Positive Polarity: Divergence vs. Equilibrium (§2.1, p. 3)"
source: J. O. Wisdom, "The Hypothesis of Cybernetics", BJPS 2(5), 1951, p. 2
basis_tier: read-together
standard: work/topic-98577-public-evolutionary-science/SPEC-close-reading-architecture.md (clj-spec v1.2)
pipeline: deepseek-v4.1-flash-1017 (native EDN)
---

# CR-WISDOM-U008 — Negative vs. Positive Polarity: Divergence vs. Equilibrium (§2.1, p. 3)

## Verbatim Text (zero ellipses)

> 2.I Feed-Back, Positive and Negative. These are all examples of 'negative feed-back '. In all of them the work done by a source of power is controlled-the machine is prevented from running away- by a mechanism that checks it and keeps the rate at which energy is expended hovering round a certain quantity. This feed-back is 'negative' because the work done by the feed-back mechanism opposes the working of the main driving force. Positive feed-back is also possible. Suppose, for instance, that instead of having a balloon in the gas pipe of the incubator, we have one outside, fixed in such a way that, as it expands through being heated, it pushes a lever of a tap, thus partially opening the tap. This will increase the heat of the incubator, and warm the balloon still more, so that the tap will be opened further; and so the process will continue.

## Triadic Semiotic Specification (clj-spec v1.2)

```clojure
{:close-read/id "CR-WISDOM-U008"
 :close-read/title "Negative vs. Positive Polarity: Divergence vs. Equilibrium"
 :seme/firstness {:quality/phenomenon :opposition-of-feed-back-to-driving-force
                  :perceptual/metaphor "A governor checking a runaway engine, holding energy expenditure hovering round a quantity"}
 :pheme/secondness {:encounter :polarity-of-feed-back-loop
                    :historical-anchors [:wisdom-1951-feed-back :cannon-1932-homeostasis :ashby-1948]}
 :delome/thirdness
 [(invariant :feedback/polarity #{:negative :positive})
  (invariant :system/regime #{:hovering-equilibrium :runaway-divergence})
  (rule :polarity-bifurcation
    (branch :feedback/polarity
            :negative :hovering-equilibrium
            :positive :runaway-divergence))]
 :term/definitions [{:term/name :negative-feed-back
                     :term/def "Feed-back whose mechanism opposes the main driving force, checking the machine and keeping energy expenditure hovering round a certain quantity"}
                    {:term/name :positive-feed-back
                     :term/def "Feed-back that reinforces the main driving force, so that deviation from a quantity compounds rather than corrects"}]}
```

## Verify Notes

- **Basis tier:** `read-together` — Wisdom (1951), text p. 2.
- **Extraction:** Deterministic span extraction via PyMuPDF.
- **Spec verification:** Balanced AST bracket conformance verified (True).
