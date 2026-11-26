---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-004-seesaw-vs-feedback-pure-oscillation-vs-dynamic-maintenance-2-0-p-2
id: CR-WISDOM-U004
title: "CR-WISDOM-U004 — Seesaw vs. Feedback: Pure Oscillation vs. Dynamic Maintenance (§2.0, p. 2)"
source: J. O. Wisdom, "The Hypothesis of Cybernetics", BJPS 2(5), 1951, p. 1
basis_tier: read-together
standard: work/topic-98577-public-evolutionary-science/SPEC-close-reading-architecture.md (clj-spec v1.2)
pipeline: deepseek-v4.1-flash-1017 (native EDN)
---

# CR-WISDOM-U004 — Seesaw vs. Feedback: Pure Oscillation vs. Dynamic Maintenance (§2.0, p. 2)

## Verbatim Text (zero ellipses)

> These electronic machines are not merely 'automatic', in the sense in which a seesaw is automatic. They embody a mechanism known as "feed-back ", which is of the highest importance; they thus differ from purely Newtonian machines (the nature of this difference requires elucidation and will be discussed in the final section). A thermostat affords one very simple example of feed-back in everyday use. When the temperature rises too high, the extra heat operates in such a way as to cut off the source of heat, which reduces the temperature. Suppose an incubator, heated by gas, has a portion of gas pipe passing through it and that a tiny inflated balloon lies in the pipe. When the incubator gets hotter, the balloon expands and thus reduces the amount of gas passing through the pipe, perhaps even cutting off the supply. The burner of the incubator now receives less gas and cools.

## Triadic Semiotic Specification (clj-spec v1.2)

```clojure
{:close-read/id "CR-WISDOM-U004"
 :close-read/title "Seesaw vs. Feedback: Pure Oscillation vs. Dynamic Maintenance"
 :seme/firstness {:quality/phenomenon :automatic-motion-without-correction
                  :perceptual/metaphor "A seesaw rocking with no governor on its swing"}
 :pheme/secondness {:encounter :feedback-regulation-by-error-signal
                    :historical-anchors [:wisdom-1951-feedback-machines :thermostat-regulation :incubator-balloon-governor]}
 :delome/thirdness
 [(invariant :loop/sign #{:negative :positive :none})
  (invariant :loop/sensor-medium #{:expanding-gas :thermal-bimetal :centrifugal-mass :none})
  (invariant :loop/saturation boolean?)
  (rule :feedback-governance
    (implies (and (not= :loop/sensor-medium :none)
                  (= :loop/sign :negative))
             :error-opposing-maintenance))]
 :term/definitions [{:term/name :feed-back :term/def "Mechanism whereby a deviation from a condition operates to reduce that deviation, differing from purely Newtonian automatic motion such as a seesaw"}
                    {:term/name :pure-oscillation :term/def "Automatic motion lacking error-sensing correction, exemplified by the seesaw"}]}
```

## Verify Notes

- **Basis tier:** `read-together` — Wisdom (1951), text p. 1.
- **Extraction:** Deterministic span extraction via PyMuPDF.
- **Spec verification:** Balanced AST bracket conformance verified (True).
