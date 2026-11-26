---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-003-the-chess-engine-the-carve-of-non-trivial-analogy-1-pp-1-2
id: CR-WISDOM-U003
title: "CR-WISDOM-U003 — The Chess Engine & The Carve of Non-Trivial Analogy (§1, pp. 1–2)"
source: J. O. Wisdom, "The Hypothesis of Cybernetics", BJPS 2(5), 1951, p. 0
basis_tier: read-together
standard: work/topic-98577-public-evolutionary-science/SPEC-close-reading-architecture.md (clj-spec v1.2)
pipeline: deepseek-v4.1-flash-1017 (native EDN)
---

# CR-WISDOM-U003 — The Chess Engine & The Carve of Non-Trivial Analogy (§1, pp. 1–2)

## Verbatim Text (zero ellipses)

> Indeed it is possible to design a machine to play chess without breaking the rules and even to make ' sound' moves, once it has been provided with some criterion of evaluation of moves. If this were all, however, the analogy between the machine and the brain or mind (we need not here be precise which) would hardly be striking. What is important is that these machines embody a special type of mechanism. A I This content downloaded from 136.159.235.223 on Mon, 25 May 2015 19:31:43 UTC All use subject to JSTOR Terms and Conditions J. o. WISDOM 2 Feed-Back 2.0 Examples of Feed-Back. These electronic machines are not merely 'automatic', in the sense in which a seesaw is automatic. They embody a mechanism known as "feed-back ", which is of the highest importance; they thus differ from purely Newtonian machines (the nature of this difference requires elucidation and will be discussed in the final section).

## Triadic Semiotic Specification (clj-spec v1.2)

```clojure
{:close-read/id "CR-WISDOM-U003"
 :close-read/title "The Chess Engine & The Carve of Non-Trivial Analogy"
 :seme/firstness {:quality/phenomenon :rule-conforming-sound-move-generation
                  :perceptual/metaphor "A chessboard on which legal moves are made without a player"}
 :pheme/secondness {:encounter :analogy-carving-by-mechanism-type
                    :historical-anchors [:wisdom-1951-feed-back :newtonian-machine-contrast :seesaw-automaticity]}
 :delome/thirdness (do
                     (defn non-trivial-analogy? [machine brain]
                       {:pre [(rule-conforming? machine)
                              (sound-moves? machine)]}
                       (and (embodies? machine :feed-back)
                            (not (merely-automatic? machine :seesaw))
                            (differs-from? machine :newtonian)))
                     (defn carve-analogy [machine brain]
                       (when (non-trivial-analogy? machine brain)
                         :striking)))
 :term/definitions [{:term/name :feed-back-mechanism :term/def "A special type of mechanism embodied by electronic machines, distinct from merely automatic devices like a seesaw and from purely Newtonian machines"}
                    {:term/name :non-trivial-analogy :term/def "An analogy between machine and brain/mind that is striking because it rests on shared mechanism-type (feed-back) rather than mere rule-conformity or automaticity"}]}
```

## Verify Notes

- **Basis tier:** `read-together` — Wisdom (1951), text p. 0.
- **Extraction:** Deterministic span extraction via PyMuPDF.
- **Spec verification:** Balanced AST bracket conformance verified (True).
