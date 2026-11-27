---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-14-uniselector-step-function-adaptation
id: CR-WISDOM-U014
title: "CR-WISDOM-U014 — The Uniselector Step-Function: Adaptation by Trial and Error (§4.2, pp. 12–13)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', The British Journal for the Philosophy of Science, Vol. II, No. 5, May 1951, pp. 12–13"
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v6-synthetic-quad"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme", "witness-register", "literate-synthesis"]
corpus_locus:
  source_file: "assemblages/wisdom-1951/primary/source-text.txt"
  sha256: "fb29b8c188d305d98fa846df7729c1211e8b7e47d577c2abaf765f0d920a6981"
  printed_folio: 12
  doc_page_index: 12
---

# CR-WISDOM-U014 — The Uniselector Step-Function: Adaptation by Trial and Error (§4.2, pp. 12–13)

## 1. Verbatim Text (zero ellipses)

> But the limit is so chosen that the current reaches it only when the system is unstable and the magnets diverge. Hence, so long as instability remains, the uniselectors alter the feed-backs until eventually a stable arrangement comes about.
> (§4.2, pp. 12–13)

## 2. Epistemic Gloss & Philosophical Carve

Wisdom formulates the cybernetic resolution to Cartesian biological skepticism:
1. **Second-Order Step Adaptation:** Ordinary homeostasis adapts *within* a fixed wiring; Ashby's uniselectors adapt *the wiring itself*. When essential variables breach stability bounds, stepping switches randomize potentiometer values until a stable field configuration is stumbled upon—adaptation without teleological foresight.
2. **Refutation of Cartesian Causal Adequacy:** Descartes asserted that an automaton could never exhibit more variety or adaptation than was designed into it by its maker. The Homeostat refutes this: because the uniselectors step autonomously in response to environmental sabotage (e.g. crossing coils or breaking relays), the machine achieves equilibria that were entirely unforeseen and unpredictable by its builder.

## 3. Formal Symbolic Logic

Let $\mathbf{x} \in \mathbb{R}^4$ be magnet states and $\mathbf{P}_k \in \mathcal{P}_{\text{discrete}}$ be the parameter vector selected from $25^4$ uniselector configurations.
Let $\mathcal{E}(\mathbf{x}) = \|\mathbf{x}\|_{\infty}$ measure system displacement from homeostatic center.
Let $\mathcal{B}_{\text{essential}} = \{\mathbf{x} \mid \mathcal{E}(\mathbf{x}) \le L_{\text{crit}}\}$ be the basin of essential physiological bounds.

$$\mathbf{P}_{k+1} = \begin{cases} \mathbf{P}_k & \text{if } \mathbf{x}(t) \in \mathcal{B}_{\text{essential}} \\ g(\mathbf{P}_k) & \text{if } \mathbf{x}(t) \notin \mathcal{B}_{\text{essential}} \quad (\text{stochastic step-function}) \end{cases}$$

Convergence to ultrastability:

$$\exists k^* < \infty \quad \text{such that} \quad \forall t > t_{k^*}, \; \mathbf{x}(t) \in \mathcal{B}_{\text{essential}}$$

Refutation of Cartesian foresight bound:

$$\text{Behaviors}(\mathcal{M}) \not\subseteq \text{Foresight}(\text{Designer})$$

## 4. Decomposed Semiotic S-Expressions

### 4.1 Firstness: Qualitative Phenomenon (Seme)

```clojure
{:seme/firstness
 {:quality/phenomenon :uncanny-self-recovery
  :perceptual/invariant :adaptation-without-foresight
  :perceptual/metaphor "A wrecked engine that re-arranges its own valves until it runs again"}}
```

### 4.2 Secondness: Empirical Encounter & Historical Anchors (Pheme)

```clojure
{:pheme/secondness
 {:encounter :homeostat-tricks-and-terminology-audit
  :data {:experiments [:knock-out-uniselectors :cross-mains-wires]
         :reverting-classes [:same-steady-motion :any-steady-motion]
         :naming-authority :cannon-1932-wisdom-of-the-body}
  :historical-anchors [:ashby-1945-trial-and-error
                       :cannon-1932-wisdom-of-the-body
                       :descartes-causal-adequacy]}}
```

### 4.3 Thirdness: Invariant Delome Rules & Causal Habits (Delome)

```clojure
{:delome/thirdness
 ['(taxonomy :reverting-classes
    {:same-steady-motion {:example :blood-temperature :cannon-term :homeostasis}
     :any-steady-motion  {:example :diet-adjustment :ashby-term :machine-with-breaks}})
  '(isomorphism :descartes-refutation
    {:principle  (>= [reality cause] [reality effect])
     :refuted-by (spontaneous-adaptation :homeostat)
     :ground     (novel-behaviour :not-foresighted-by :designer)})
  '(rule :ultrastable-search
    (until (stable? state)
      (step-uniselector-parameters state)))]}
```

### 4.4 Conceptual Term Definitions

```clojure
{:term/definitions
 [#:term{:name :step-function
         :def "Discrete parameter change occurring only when an instability threshold is breached"}
  #:term{:name :adaptive-trial-and-error
         :def "Breakdown followed by automatic step-wise parameter re-adjustment until a stable field is attained"}
  #:term{:name :reverting-class-same
         :def "Reversion to the exact same steady state setpoint after disturbance (Cannon's homeostasis)"}
  #:term{:name :reverting-class-any
         :def "Reversion to any steady motion whatsoever after disturbance (Ashby's ultrastability)"}
  #:term{:name :descartes-refutation
         :def "Machine behavior exceeding the designer's foresight, refuting the Cartesian causal adequacy principle"}]}
```

## 5. Verbatim Witness Register

```clojure
{:witness/selection
 {:witness/text "But the limit is so chosen that the current reaches it only when the system is unstable and the magnets diverge. Hence, so long as instability remains, the uniselectors alter the feed-backs until eventually a stable arrangement comes about."
  :witness/folio 12
  :witness/char-count 240
  :witness/zero-ellipses? true}}
```

## 6. Literate Semantic Demonstration & Bounded Synthesis

### 6.1 The Dialectical Demarcation
Wisdom analyzes Ashby's resolution of blind adaptation: how a physical mechanism can organize itself to counter environmental sabotage without possessing internal foresight, representation, or intentional purpose. In classical teleology, adaptation required either an intelligent designer arranging parts in advance or an internal vital soul steering behavior. The Homeostat demonstrates that ultrastability is a strictly mathematical consequence of state-dependent parameter stepping.

### 6.2 The Ultrastable Step-Function Operator
In Section 3, the homeostat is formalized as a coupled two-tier dynamical system. The first tier consists of continuous magnet coordinates $\mathbf{x}(t)$ operating within parameter set $\mathbf{P}_k$. When an environmental shock (such as hand displacement or crossed wires) forces $\mathbf{x}(t)$ outside essential physiological bounds $\mathcal{B}_{\text{essential}}$, excessive current energizes the relay circuit. The relay cuts off holding current and steps the uniselectors to a new random parameter setting $\mathbf{P}_{k+1} = g(\mathbf{P}_k)$. This trial-and-error stepping continues autonomously until the system stumbles into a matrix whose eigenvalues are strictly negative, whereupon current drops below threshold and stepping instantly halts.

### 6.3 Epistemic Bounds & Negative Horizon
1. **Random Search Limitation:** The Homeostat does not "calculate" optimal wiring; it executes an unguided random walk through parameter space until trapped by an equilibrium basin.
2. **Reverting Class Distinction:** Wisdom rigorously distinguishes Cannon's narrow physiological *homeostasis* (returning to the exact *same* temperature or pH) from Ashby's broad *ultrastability* (content to settle into *any* stable dynamic regime).
