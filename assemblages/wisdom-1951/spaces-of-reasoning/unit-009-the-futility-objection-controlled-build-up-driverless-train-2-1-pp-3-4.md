---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-009-the-futility-objection-controlled-build-up-driverless-train-2-1-pp-3-4
id: CR-WISDOM-U009
title: "CR-WISDOM-U009 — The Futility Objection: Controlled Build-Up (Driverless Train) (§2.1, pp. 3–4)"
source: J. O. Wisdom, "The Hypothesis of Cybernetics", BJPS 2(5), 1951, p. 2
basis_tier: read-together
standard: work/topic-98577-public-evolutionary-science/SPEC-close-reading-architecture.md (clj-spec v1.2)
pipeline: deepseek-v4.1-flash-1017 (native EDN)
---

# CR-WISDOM-U009 — The Futility Objection: Controlled Build-Up (Driverless Train) (§2.1, pp. 3–4)

## Verbatim Text (zero ellipses)

> This may seem to be a futile type of mechanism-for why not remove the balloon, open the tap, and have the maximum flow of gas from the outset ? The reason is that there are circumstances when it is desirable to build up some required state at a slow pace. Suppose, for instance, we wish to run an electric train without a driver; we must not turn on full power at once; a positive feed-back must therefore be used to keep turning on more power gradually. In all these mechanisms the energy of the feed-back comes from the given source of power driving the machine; the negative feed-back 1 What is known as the " angular momentum " of the escapement is adjusted very simply in this clock, but the details need not be considered here. 3 This content downloaded from 136.159.235.223 on Mon, 25 May 2015 19:31:43 UTC All use subject to JSTOR Terms and Conditions J.

## Triadic Semiotic Specification (clj-spec v1.2)

```clojure
{:close-read/id "CR-WISDOM-U009"
 :close-read/title "The Futility Objection: Controlled Build-Up (Driverless Train)"
 :seme/firstness {:quality/phenomenon :gradual-state-build-up-requiring-restraint
                  :perceptual/metaphor "A driverless train that must not be given full power at once"}
 :pheme/secondness {:encounter :positive-feed-back-gradual-power-application
                    :historical-anchors [:wisdom-1951-futile-mechanism :driverless-train-controlled-build-up]}
 :delome/thirdness
 [(invariant :train/power-state (bounded 0 100))
  (invariant :acceleration/trajectory #{:instant-step :rate-limited-ramp})
  (rule :non-futile-positive-build-up
    (implies (and (:requires-gradual-buildup? system)
                  (:forbid-instant-saturation? system))
             :rate-limited-positive-feedback))]
 :term/definitions [{:term/name :futility-objection :term/def "Objection that a mechanism is futile since the balloon could simply be removed and the tap opened for maximum flow"}
                     {:term/name :controlled-build-up :term/def "Building up a required state at a slow pace rather than at full power from the outset"}
                     {:term/name :positive-feed-back :term/def "Feed-back that keeps turning on more power gradually, its energy drawn from the given source of power driving the machine"}]}
```

## Verify Notes

- **Basis tier:** `read-together` — Wisdom (1951), text p. 2.
- **Extraction:** Deterministic span extraction via PyMuPDF.
- **Spec verification:** Balanced AST bracket conformance verified (True).
