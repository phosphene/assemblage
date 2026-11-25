---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-011-the-vicious-spiral-renal-hypertension-interpeduncular-cats-2-2-p-11
id: CR-WISDOM-U011
title: "CR-WISDOM-U011 — The Vicious Spiral: Renal Hypertension & Interpeduncular Cats (§2.2, p. 11)"
source: J. O. Wisdom, "The Hypothesis of Cybernetics", BJPS 2(5), 1951, p. 10
basis_tier: read-together
standard: work/topic-98577-public-evolutionary-science/SPEC-close-reading-architecture.md (clj-spec v1.2)
pipeline: deepseek-v4.1-flash-1017 (native EDN)
---

# CR-WISDOM-U011 — The Vicious Spiral: Renal Hypertension & Interpeduncular Cats (§2.2, p. 11)

## Verbatim Text (zero ellipses)

> interpeduncular nucleus in cats, carried out by Bailey, as a result of which they walk forward continuously till they die. Apparently the negative feed-back mechanisms were put out of action or removed. I have distinguished carefully between different types of feed- back, because it might be fruitful to explore the possibilities of each separately. This has led Thompson 1 to look for physiological ex- amples. He makes the interesting suggestion that a positive ampli- fication feed-back is to be found in some cases of renal disease, in which the renal damage leads to hypertension, which in turn causes further renal disease and still further hypertension-a vicious spiral which, once established, proves fatal. 4.2 Autonomic Reorganisation in a Machine. Can a machine re- organise itself when faced with a difficulty ? Ashby has given a striking design for a machine with this capacity, which has actually been constructed.2 It is called "a homeostat ".

## Triadic Semiotic Specification (clj-spec v1.2)

```clojure
{:close-read/id "CR-WISDOM-U011"
 :close-read/title "The Vicious Spiral: Renal Hypertension & Interpeduncular Cats"
 :seme/firstness {:quality/phenomenon :runaway-positive-amplification
                  :perceptual/metaphor "A cat walking forward continuously till it dies, its brakes removed"}
 :pheme/secondness {:encounter :positive-feedback-disinhibition
                    :historical-anchors [:bailey-interpeduncular-cats :thompson-1951-renal-hypertension :ashby-1948-homeostat]}
 :delome/thirdness (do
                     (defn vicious-spiral [damage]
                       {:pre [(pos? damage)]}
                       (let [hypertension (amplify damage)
                             further-damage (amplify hypertension)]
                         (recur (+ damage further-damage))))
                     (defn homeostat [disturbance]
                       {:pre [(difficulty? disturbance)]}
                       (reorganize disturbance)))
 :term/definitions [{:term/name :positive-amplification-feed-back
                     :term/def "Feed-back in which a disturbance is amplified through a chain of consequences, each reinforcing the next, so the deviation grows without correction"}
                    {:term/name :vicious-spiral
                     :term/def "A positive amplification feed-back in which renal damage causes hypertension which causes further renal damage and still further hypertension, fatal once established"}
                    {:term/name :negative-feed-back-disinhibition
                     :term/def "Removal or inactivation of negative feed-back mechanisms, as in interpeduncular-lesioned cats walking forward until death"}
                    {:term/name :autonomic-reorganisation
                     :term/def "A machine's capacity to reorganise itself when faced with a difficulty, exemplified by Ashby's homeostat"}]}
```

## Verify Notes

- **Basis tier:** `read-together` — Wisdom (1951), text p. 10.
- **Extraction:** Deterministic span extraction via PyMuPDF.
- **Spec verification:** Balanced AST bracket conformance verified (True).
