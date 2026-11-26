---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-002-functional-decomposition-input-output-vs-internal-operations-1-p-1
id: CR-WISDOM-U002
title: "CR-WISDOM-U002 — Functional Decomposition: Input/Output vs. Internal Operations (§1, p. 1)"
source: J. O. Wisdom, "The Hypothesis of Cybernetics", BJPS 2(5), 1951, p. 0
basis_tier: read-together
standard: work/topic-98577-public-evolutionary-science/SPEC-close-reading-architecture.md (clj-spec v1.2)
pipeline: deepseek-v4.1-flash-1017 (native EDN)
---

# CR-WISDOM-U002 — Functional Decomposition: Input/Output vs. Internal Operations (§1, p. 1)

## Verbatim Text (zero ellipses)

> Such machines had to be able (a) to be fed with information, (b) to store in some form the results of their preliminary calculations, (c) to make use of these results appropriately at the right time, i.e. to select from their 'files', and (d) to report their final results. There is an obvious analogy between such powers and those of the intellect- working out, storing, recalling, selecting, and communicating. Indeed it is possible to design a machine to play chess without breaking the rules and even to make ' sound' moves, once it has been provided with some criterion of evaluation of moves. If this were all, however, the analogy between the machine and the brain or mind (we need not here be precise which) would hardly be striking. What is important is that these machines embody a special type of mechanism.

## Triadic Semiotic Specification (clj-spec v1.2)

```clojure
{:close-read/id "CR-WISDOM-U002"
 :close-read/title "Functional Decomposition: Input/Output vs. Internal Operations"
 :seme/firstness {:quality/phenomenon :functional-analogy-machine-intellect
                  :perceptual/metaphor "A filing cabinet that calculates, recalls, and speaks"}
 :pheme/secondness {:encounter :input-store-select-report-decomposition
                    :historical-anchors [:wisdom-1951-machines-with-purpose :turing-1950-computing-machinery]}
 :delome/thirdness (do
                     (defn decompose-machine [machine]
                       {:pre [(has-criterion? machine)]}
                       {:input (feed machine)
                        :store (store-results machine)
                        :select (select-from-files machine)
                        :report (report-final machine)}))
 :term/definitions [{:term/name :functional-decomposition
                     :term/def "Analysis of a machine into input, storage, selection, and reporting operations analogous to intellect"}]}
```

## Verify Notes

- **Basis tier:** `read-together` — Wisdom (1951), text p. 0.
- **Extraction:** Deterministic span extraction via PyMuPDF.
- **Spec verification:** Balanced AST bracket conformance verified (True).
