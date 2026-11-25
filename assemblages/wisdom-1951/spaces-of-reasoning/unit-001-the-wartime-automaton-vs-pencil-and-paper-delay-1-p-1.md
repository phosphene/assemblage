---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-001-the-wartime-automaton-vs-pencil-and-paper-delay-1-p-1
id: CR-WISDOM-U001
title: "CR-WISDOM-U001 — The Wartime Automaton vs. Pencil-and-Paper Delay (§1, p. 1)"
source: J. O. Wisdom, "The Hypothesis of Cybernetics", BJPS 2(5), 1951, p. 0
basis_tier: read-together
standard: work/topic-98577-public-evolutionary-science/SPEC-close-reading-architecture.md (clj-spec v1.2)
pipeline: deepseek-v4.1-flash-1017 (native EDN)
---

# CR-WISDOM-U001 — The Wartime Automaton vs. Pencil-and-Paper Delay (§1, p. 1)

## Verbatim Text (zero ellipses)

> DURING World War II some of the research in theoretical physics and engineering, which was important both for attack and defence, required vast calculations to be carried out. The delay involved in doing these with pencil and paper would have been a serious drawback; but the difficulty was overcome by means of electronic calculating machines, which could solve in a matter of hours mathematical equations that would otherwise have taken months to work out. Such machines had to be able (a) to be fed with information, (b) to store in some form the results of their preliminary calculations, (c) to make use of these results appropriately at the right time, i.e. to select from their 'files', and (d) to report their final results. There is an obvious analogy between such powers and those of the intellect- working out, storing, recalling, selecting, and communicating.

## Triadic Semiotic Specification (clj-spec v1.2)

```clojure
{:close-read/id "CR-WISDOM-U001"
 :close-read/title "The Wartime Automaton vs. Pencil-and-Paper Delay"
 :seme/firstness {:quality/phenomenon :calculation-delay-exceeding-wartime-need
                  :perceptual/metaphor "Months of pencil-and-paper labor collapsing into hours of electronic computation"}
 :pheme/secondness {:encounter :electronic-calculating-machine-substitution
                    :historical-anchors [:world-war-ii-theoretical-physics :world-war-ii-engineering :pencil-and-paper-computation :electronic-calculating-machines]}
 :delome/thirdness (do
                     (defn compute [input store select report]
                       {:pre [(feedable? input) (storable? store) (selectable? select) (reportable? report)]}
                       (-> input
                           (store-preliminary)
                           (select-at-right-time)
                           (report-final)))
                     (defn intellect-analog [machine]
                       {:work-out (compute machine)
                        :store store
                        :recall select
                        :select select
                        :communicate report}))
 :term/definitions [{:term/name :electronic-calculating-machine
                     :term/def "Machine able to be fed information, store preliminary results, select them appropriately at the right time, and report final results"}
                    {:term/name :intellect-analogy
                     :term/def "Obvious analogy between machine powers and intellect: working out, storing, recalling, selecting, and communicating"}]}
```

## Verify Notes

- **Basis tier:** `read-together` — Wisdom (1951), text p. 0.
- **Extraction:** Deterministic span extraction via PyMuPDF.
- **Spec verification:** Balanced AST bracket conformance verified (True).
