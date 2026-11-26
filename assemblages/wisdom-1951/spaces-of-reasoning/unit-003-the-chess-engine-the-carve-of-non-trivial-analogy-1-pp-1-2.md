---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-003-the-chess-engine-the-carve-of-non-trivial-analogy-1-pp-1-2
id: CR-WISDOM-U003
title: "CR-WISDOM-U003 — The Chess Engine & The Carve of Non-Trivial Analogy (§1, pp. 1–2)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', The British Journal for the Philosophy of Science, Vol. II, No. 5, May 1951, pp. 1–2"
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v4-deliberative-seme"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme"]
corpus_locus:
  source_file: "assemblages/wisdom-1951/primary/source-text.txt"
  sha256: "fb29b8c188d305d98fa846df7729c1211e8b7e47d577c2abaf765f0d920a6981"
  printed_folio: 1
  doc_page_index: 1
---

# CR-WISDOM-U003 — The Chess Engine & The Carve of Non-Trivial Analogy (§1, pp. 1–2)

## 1. Verbatim Text (zero ellipses)

> Indeed it is possible to design a machine to play chess without breaking the rules and even to make ' sound' moves, once it has been provided with some criterion of evaluation of moves. If this were all, however, the analogy between the machine and the brain or mind (we need not here be precise which) would hardly be striking. What is important is that these machines embody a special type of mechanism.
> (§1, pp. 1–2)

## 2. Epistemic Gloss & Philosophical Carve

Wisdom here marks the decisive epistemological boundary between superficial behavioral simulation and genuine mechanistic explanation:
1. **Rule-Conformity vs. Mechanistic Grounding:** A machine can play chess according to formal rules without illustrating anything profound about neurological or mental mechanisms. Pure syntax or look-up tables do not constitute a striking analogy.
2. **The Carve of Non-Trivial Analogy:** The analogy between electronic calculating machines and the central nervous system becomes scientifically powerful only because both embody a *special type of mechanism*—namely, circular feedback circuits that govern their own ongoing operations.

## 3. Formal Predicate Logic

Let $\mathcal{M}$ be a mechanical automaton and $\mathcal{B}$ be a neuro-mental system. Let $\text{BehSim}(\mathcal{M}, \mathcal{B})$ denote behavioral simulation (e.g. playing chess according to rules). Let $\text{SharedMech}(\mathcal{M}, \mathcal{B}, \tau)$ denote sharing an internal operational mechanism-type $\tau$:

$$\text{BehSim}(\mathcal{M}, \mathcal{B}) \not\implies \text{StrikingAnalogy}(\mathcal{M}, \mathcal{B})$$

$$\text{SharedMech}(\mathcal{M}, \mathcal{B}, \text{feed-back}) \implies \text{StrikingAnalogy}(\mathcal{M}, \mathcal{B})$$

The warrant of cybernetic analogy rests upon mechanistic invariance rather than functional mimicry.

## 4. Decomposed Semiotic S-Expressions

### 4.1 Firstness: Qualitative Phenomenon (Seme)

The immediate qualitative impression of an autonomous game player executing moves without a living hand.

```clojure
{:seme/firstness
 {:quality/phenomenon :rule-conforming-sound-move-generation
  :perceptual/metaphor "A chessboard on which legal moves are made without a player"}}
```

### 4.2 Secondness: Empirical Encounter & Historical Anchors (Pheme)

The physical chess-playing automaton evaluated against human brain/mind operations, anchored in Wisdom's critique of trivial behavioral analogy.

```clojure
{:pheme/secondness
 {:encounter :chess-engine-rule-following
  :historical-anchors [:wisdom-1951-feed-back :turing-1950-computing-machinery :shannon-1950-chess]}}
```

### 4.3 Thirdness: Invariant Delome Rules & Causal Habits (Delome)

The epistemic demarcation rule: analogy between machine and mind is warranted if and only if the underlying mechanism is feedback-controlled rather than merely rule-abiding.

```clojure
{:delome/thirdness
 [(invariant :analogy/warrant-tier #{:trivial-mimicry :mechanistic-analogy})
  (invariant :analogy/mechanism-type #{:feed-back :purely-newtonian :combinatorial-rule-table})
  (rule :non-trivial-analogy-demarcation
    (iff (and (= :analogy/mechanism-type :feed-back)
              (not= :analogy/warrant-tier :trivial-mimicry))
         :striking-analogy))]}
```

### 4.4 Conceptual Term Definitions

Discrete operational and epistemic definitions projected into the assemblage dictionary.

```clojure
{:term/definitions
 [#:term{:name :feed-back-mechanism
         :def "A special type of mechanism embodied by electronic machines, distinct from merely automatic devices like a seesaw and from purely Newtonian machines"}
  #:term{:name :non-trivial-analogy
         :def "An analogy between machine and brain/mind that is striking because it rests on shared mechanism-type (feed-back) rather than mere rule-conformity or automaticity"}]}
```

## 5. Verbatim Witness Register

Contiguous textual excerpt extracted directly from the primary source without ellipses.

```clojure
{:witness/selection
 {:witness/text "Indeed it is possible to design a machine to play chess without breaking the rules and even to make ' sound' moves, once it has been provided with some criterion of evaluation of moves. If this were all, however, the analogy between the machine and the brain or mind (we need not here be precise which) would hardly be striking. What is important is that these machines embody a special type of mechanism."
  :witness/folio 1
  :witness/char-count 387
  :witness/zero-ellipses? true}}
```

## 6. Literate Semantic Demonstration & Bounded Synthesis

### 6.1 The Dialectical Demarcation
Wisdom's opening argument establishes the foundational demarcation criterion of cybernetic explanation: the rejection of black-box behavioral mimicry in favor of internal mechanistic invariance. An automaton may simulate human cognitive outputs — selecting legal, tactically sound chess moves — through lookup tables, brute-force search, or clockwork cams. In the formal notation of Section 3 this is boundary equivalence, $P_{\text{IO}}(\mathcal{M}) \approx P_{\text{IO}}(\mathcal{O})$; if the internal architecture $\mathcal{K}(\mathcal{M})$ remains Newtonian, the analogy is scientifically trivial. Identical behavioral surfaces can be generated by radically disjoint causal structures, so behavior alone explains nothing.

### 6.2 The Causal Sub-Mechanism Invariant
The analogy becomes striking precisely when artifact and organism share an invariant causal sub-mechanism: $\mathcal{K}_{\text{loop}} \sqsubseteq \mathcal{K}(\mathcal{M})$ and $\mathcal{K}_{\text{loop}} \sqsubseteq \mathcal{K}(\mathcal{O})$, with $\mathcal{K}_{\text{loop}}$ outside the Newtonian class. This sub-mechanism is closed-loop error-compensating feedback. The warrant of the cybernetic model is topological, not material: both systems steer their trajectories through state space by converting output error into corrective input modulation.

### 6.3 Epistemic Bounds & Negative Horizon
1. **No Substrate Identity:** Sharing a feedback sub-mechanism warrants mechanistic analogy, not physical identity; wetware and hardware diverge in thermodynamics, plasticity, and cellular agency.
2. **Behavioral Benchmarks Carry Zero Warrant:** Chess-playing efficacy or Turing-style performance is formally decoupled from cybernetic relevance. A grandmaster program without circular feedback is cybernetically vacuous; a four-magnet homeostat embodies the discipline's core.
