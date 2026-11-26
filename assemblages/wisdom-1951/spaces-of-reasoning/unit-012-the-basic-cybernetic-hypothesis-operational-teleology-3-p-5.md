---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-012-the-basic-cybernetic-hypothesis-operational-teleology-3-p-5
id: CR-WISDOM-U012
title: "CR-WISDOM-U012 — The Basic Cybernetic Hypothesis & Operational Teleology (§3, p. 4)"
source: J. O. Wisdom, "The Hypothesis of Cybernetics", BJPS 2(5), 1951, p. 4
basis_tier: read-together
standard: work/topic-98577-public-evolutionary-science/SPEC-close-reading-architecture.md (clj-spec v1.2)
pipeline: deepseek-v4.1-flash-1017 (native EDN)
---

# CR-WISDOM-U012 — The Basic Cybernetic Hypothesis & Operational Teleology (§3, p. 4)

## Verbatim Text (zero ellipses)

> 3 The Basic Cybernetic Hypothesis We may describe machines that embody negative feed-back mechanisms as proceeding by "trial and error ", or as error- compensating ", or-best-as " self-correcting "; and we may define "a simple negative feed-back mechanism" as a mechanism by which part of the input-energy of a machine is utilised at intervals to impose a check on the output-energy. The basic hypothesis of cybernetics is that the chief mechanism of the central nervous system is one of negative feed-back. The field of study is not, however, restricted to feed-backs of the negative kind. Secondly, cybernetics makes the hypothesis that the negative feed-back mechanism explains 'purposive' and' adaptive' behaviour. Broadly speaking what the cybernetic model does for our outlook is to make us understand how purposive behaviour can be manifested by a machine, for " purposive " can now be defined in terms of negative feed-back. An anti-aircraft gun is 'purposive' in the sense that it is able by means of radar to follow its target.1 Predictive behaviour is also accounted for

## Triadic Semiotic Specification (clj-spec v1.2)

```clojure
{:close-read/id "CR-WISDOM-U012"
 :close-read/title "The Basic Cybernetic Hypothesis & Operational Teleology"
 :seme/firstness {:quality/phenomenon :teleology-reduced-to-error-compensation
                  :perceptual/metaphor "A guided anti-aircraft gun homing continuously on its target via radar"}
 :pheme/secondness {:encounter :central-nervous-system-negative-feedback
                    :historical-anchors [:wiener-rosenblueth-bigelow-1943-teleology :radar-guided-anti-aircraft-gun]}
 :delome/thirdness (do
                     (defn purposive-step [target-state current-state]
                       (let [error (- target-state current-state)]
                         (if (zero? error)
                           current-state
                           (+ current-state (compensate-error error))))))
 :term/definitions [{:term/name :basic-cybernetic-hypothesis
                     :term/def "The chief mechanism of the central nervous system is one of negative feed-back"}
                    {:term/name :operational-purposiveness
                     :term/def "Purposive behaviour defined as error-compensation by negative feed-back"}]}
```

## Verify Notes

- **Basis tier:** `read-together` — Wisdom (1951), text p. 4.
- **Extraction:** Deterministic span extraction from Section 3 body text (Doc page 5).
- **Spec verification:** Balanced AST bracket conformance verified.
