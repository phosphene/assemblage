---
uri: work/topic-102248-cybernetics/docs/deep-reads/wisdom-1951-hypothesis-of-cybernetics/L3-verbatim/sec03-basic-hypothesis-teleology-northrop
id: wisdom-1951-L3-sec03-basic-hypothesis-northrop
title: "L3 Verbatim + Commentary — Section 3: The Basic Cybernetic Hypothesis, Error-Compensation, and Northrop's Teleology"
type: deep-read-L3
status: living
owner: edphos
author: feelingflowingbot
date: 2026-11-24
source:
  author: "J. O. Wisdom"
  title: "The Hypothesis of Cybernetics"
  journal: "The British Journal for the Philosophy of Science"
  volume: 2
  issue: 5
  year: 1951
  section: "3"
  page: "5-6"
  stable_url: "http://www.jstor.org/stable/685605"
basis_tier: "read-together"
tags:
  - verbatim
  - L3-commentary
  - basic-hypothesis
  - teleology
  - error-compensation
  - northrop
  - prediction
related:
  - work/topic-102248-cybernetics/docs/deep-reads/wisdom-1951-hypothesis-of-cybernetics/L2-formalization/sec03-basic-cybernetic-hypothesis
  - concepts/epistemic-levels
  - concepts/specification-compression-relation
---

# L3 Verbatim: The Basic Cybernetic Hypothesis & Northrop's Teleological Carve

*[read-together — Wisdom 1951 BJPS 2(5), pp. 5–6; exact PDF text witnesses from JSTOR stable:685605]*

---

## 1. Primary Textual Witnesses

### Block 3.1: Definitional Foundation and the Twin Hypotheses (p. 5)

> "3 The Basic Cybernetic Hypothesis
> 
> "We may describe machines that embody negative feed-back mechanisms as proceeding by "trial and error", or as error-compensating", or—best—as "self-correcting"; and we may define "a simple negative feed-back mechanism" as a mechanism by which part of the input-energy of a machine is utilised at intervals to impose a check on the output-energy."
> 
> The basic hypothesis of cybernetics is that the chief mechanism of the central nervous system is one of negative feed-back. The field of study is not, however, restricted to feed-backs of the negative kind. Secondly, cybernetics makes the hypothesis that the negative feed-back mechanism explains 'purposive' and 'adaptive' behaviour."

### Block 3.2: Redefining Purpose, Predictive Tracking, and the Northrop Carve (pp. 5–6)

> "Broadly speaking what the cybernetic model does for our outlook is to make us understand how purposive behaviour can be manifested by a machine, for "purposive" can now be defined in terms of negative feed-back. An anti-aircraft gun is 'purposive' in the sense that it is able by means of radar to follow its target.¹ Predictive behaviour is also accounted for by the same mechanism, as illustrated by a gun firing not at a point where an aeroplane is but at a point where it will be—just as a cat chasing a mouse extrapolates the mouse's path.
> 
> Support for this striking hypothesis, which is of fundamental importance, is to be found both in rather general resemblances between organisms and electronic machines and in resemblances of more special kinds."

### Block 3.3: Footnote 1 — Northrop on Purposive vs. Teleological (p. 5)

> "Some readers may find it conducive to clarity to distinguish with Northrop between 'purposive' and 'teleological'. A machine may start towards a goal, but after it deviates it may make no attempt to pursue that goal. It would then be in one sense 'purposive' but not 'teleological'. To be 'teleological' it must continue to seek its goal, which requires negative feed-back. (F. S. C. Northrop, "The Neurological and Behavioristic Psychological Basis of the Ordering of Society by Means of Ideas", Science, New York, 1948, 107, 415.) In ordinary usage, of course, "purposive" has the sense here ascribed to "telelogical"."

---

## 2. Textual & Epistemic Audit (`refverify`)

```yaml
refverify_audit:
  source_document: "The Hypothesis of Cybernetics"
  author: "J. O. Wisdom"
  journal: "The British Journal for the Philosophy of Science"
  volume: 2
  issue: 5
  publication_date: "1951-05"
  page_range: "1-24"
  article_doi_jstor: "http://www.jstor.org/stable/685605"
  witness_pages: [5, 6]
  character_span_exact: true
  collation_status: "verified_against_pdf_text_stream"
  external_citations_verified:
    - author: "F. S. C. Northrop"
      year: 1948
      title: "The Neurological and Behavioristic Psychological Basis of the Ordering of Society by Means of Ideas"
      journal: "Science"
      volume: 107
      page: 415
      status: "attested_in_footnote_1"
```

---

## 3. Analytical Commentary & Epistemic Decomposition

### A. The Separation of the Mechanical Hypothesis from the Explanatory Hypothesis
Wisdom's formulation in Block 3.1 is philosophically superior to Norbert Wiener's rhetorical prose because he explicitly decouples the claim into two distinct hypotheses:
1. **Hypothesis 1 (Physiological / Structural):** The anatomical substrate of the CNS is organized around circular negative feedback loops.
2. **Hypothesis 2 (Behavioral / Teleological):** Negative feedback is the necessary and sufficient physical mechanism accounting for purpose, adaptation, and target tracking.

This decoupling is vital for empirical falsification. One could conceivably imagine a nervous system containing negative feedback loops that nonetheless failed to explain higher purposive behaviors, or conversely, a cybernetic device manifesting purposive trajectory correction without biological neural circuits.

### B. Northrop's Precision: Ballistic vs. Teleological Trajectories
Footnote 1 introduces a critical distinction from philosopher F. S. C. Northrop (1948):
- **Ballistic Purposiveness:** An artillery shell fired along a calculated parabolic arc is directed toward a target. It has an initial aim, but if a wind gust or atmospheric pressure displaces it, it has zero capacity to detect the error or alter its path.
- **Cybernetic Teleology:** A system that actively continuously senses its output deviation from the target ($e(t) = y^*(t) - y(t)$) and modulates its control surfaces to nullify the error. Teleology is not *aim*; it is **persistent corrective tracking under perturbation**.

### C. Predictive Extrapolation as a Loop Dynamic
In Block 3.2, Wisdom joins target tracking with prediction:
- A radar-guided gun does not track where the aircraft *is*; it computes derivatives to project where it *will be*.
- Wisdom directly equates the servomechanism's mathematical lead-angle calculation with the biological predatory arc: *"just as a cat chasing a mouse extrapolates the mouse's path."*
- This unites Wiener’s wartime anti-aircraft work with animal ethology: predictive extrapolation is not a mysterious disembodied foresight, but a standard mathematical filter built into negative feedback circuits.

---

## 4. Platform Wiki Coordinates

- **Epistemic Stack (`concepts/epistemic-levels`):** Level 3 First-Order Claim. Rosenblueth, Wiener, and Bigelow's (1943) identification of teleology with feedback is treated as a local, testable research program commitment.
- **Naming Standards (`meta/reference/naming-principles.md`):** The sign `teleological` is bounded by the sense of *active error-sensing closed-loop regulation*, strictly excluding open-loop ballistic trajectories.
