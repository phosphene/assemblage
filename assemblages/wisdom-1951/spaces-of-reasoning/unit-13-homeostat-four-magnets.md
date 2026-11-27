---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-13-homeostat-four-magnets
id: CR-WISDOM-U013
title: "CR-WISDOM-U013 — The Homeostat Machine: Four Magnets, Stable/Unstable Arrangements (§4.2, pp. 11–12)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', The British Journal for the Philosophy of Science, Vol. II, No. 5, May 1951, pp. 11–12"
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v6-synthetic-quad"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme", "witness-register", "literate-synthesis"]
corpus_locus:
  source_file: "assemblages/wisdom-1951/primary/source-text.txt"
  sha256: "fb29b8c188d305d98fa846df7729c1211e8b7e47d577c2abaf765f0d920a6981"
  printed_folio: 11
  doc_page_index: 11
---

# CR-WISDOM-U013 — The Homeostat Machine: Four Magnets, Stable/Unstable Arrangements (§4.2, pp. 11–12)

## 1. Verbatim Text (zero ellipses)

> He points out that negative feed-back is not sufficient to describe all facets of human behaviour. It does not by itself cover learning by experience-the feed-backs have to improve with the passage of time. The corresponding problem for the brain is that as a machine it has to work out an essential part of its own wiring. Ashby describes the homeostat in some detail, but all that is needed here is to mention some of its properties. Four magnets are suspended in a field due to electric current, so that they are free to move when the current is turned on; but feed-back mechanisms are present which are operated by the movements of the magnets, and these feed-backs alter the current. This causes fresh movements of the magnets, and so on.
> (§4.2, pp. 11–12)

## 2. Epistemic Gloss & Philosophical Carve

Wisdom compresses Ashby's Homeostat into its foundational dynamical vocabulary:
1. **The Inadequacy of First-Order Feedback:** Fixed negative feedback accounts for equilibrium maintenance around static parameters, but cannot account for experiential learning or adaptation. For adaptation, feedback pathways must themselves alter over time—the system must reconfigure its own wiring.
2. **The First-Order Physical Manifold:** Before parameter transitions can occur, the physical apparatus (four magnets suspended in mutually coupled electromagnetic fields) exhibits two mutually exclusive macroscopic behavior classes: stable resistance-to-displacement versus maximal divergent runaway.

## 3. Formal Symbolic Logic

Let $\mathbf{x}(t) = [x_1, x_2, x_3, x_4]^T \in \mathbb{R}^4$ be the angular displacements of the four magnets.
Let $\mathbf{A}(\mathbf{P}) \in \mathbb{R}^{4 \times 4}$ be the coupling matrix determined by the commutator/potentiometer parameter settings $\mathbf{P}$.

$$\dot{\mathbf{x}}(t) = \mathbf{A}(\mathbf{P})\mathbf{x}(t)$$

Let $\text{spec}(\mathbf{A}(\mathbf{P})) = \{\lambda_1, \lambda_2, \lambda_3, \lambda_4\}$ be the eigenvalues of the system matrix:

$$\text{StableArrangement}(\mathbf{P}) \iff \forall i \in \{1, \dots, 4\}, \; \text{Re}(\lambda_i) < 0$$

$$\text{UnstableArrangement}(\mathbf{P}) \iff \exists i \in \{1, \dots, 4\}, \; \text{Re}(\lambda_i) > 0$$

Let $I_{\text{mains}}(\mathbf{x})$ be the coils' mains current. Instability threshold:

$$I_{\text{mains}}(\mathbf{x}(t)) > I_{\text{crit}} \implies \text{CrossesInstabilityLimit}$$

## 4. Decomposed Semiotic S-Expressions

### 4.1 Firstness: Qualitative Phenomenon (Seme)

```clojure
{:seme/firstness
 {:quality/phenomenon :resist-versus-diverge
  :perceptual/invariant :displacement-response-as-behaviour-class
  :perceptual/metaphor "A spinning top that steadies when nudged, versus one that walks itself off the table"}}
```

### 4.2 Secondness: Empirical Encounter & Historical Anchors (Pheme)

```clojure
{:pheme/secondness
 {:encounter :ashby-homeostat-1948
  :data {:machines [:four-magnets :electric-field :feed-back-mechanisms :uniselectors]
         :behaviour-classes [:stable-arrangement :unstable-arrangement]}
  :historical-anchors [:ashby-1948-electronic-engineering-20-379-83
                       :ashby-1945-physical-origin-adaptation
                       :thompson-1951-in-the-press]}}
```

### 4.3 Thirdness: Invariant Delome Rules & Causal Habits (Delome)

```clojure
{:delome/thirdness
 ['(taxonomy :system-order
    {:first-order  {:variables [:magnet-positions]
                    :parameters :fixed}
     :second-order {:variables [:magnet-positions :feed-back-settings]
                    :parameters :discrete-steps-via-uniselector}})
  '(rule :homeostat-arrangement
    (branch :magnet-response
            :resist-and-return :stable-arrangement
            :diverge-maximally :unstable-arrangement))
  '(rule :instability-trigger
    (implies (> :mains-current :instability-limit)
             (and (cut-off :relay-current)
                  (step-uniselectors :feed-backs))))]}
```

### 4.4 Conceptual Term Definitions

```clojure
{:term/definitions
 [#:term{:name :stable-arrangement
         :def "Configuration in which displaced magnets resist and return to equilibrium"}
  #:term{:name :unstable-arrangement
         :def "Configuration in which magnets diverge as far as possible toward maximal deflection"}
  #:term{:name :uniselector
         :def "Relay-operated stepping switch altering the parameter settings of feedback circuits"}
  #:term{:name :instability-limit
         :def "Mains-current threshold at which relay current cuts off to actuate parameter stepping"}
  #:term{:name :second-order-system
         :def "A cybernetic system whose operational parameters are themselves dynamical variables"}]}
```

## 5. Verbatim Witness Register

```clojure
{:witness/selection
 {:witness/text "He points out that negative feed-back is not sufficient to describe all facets of human behaviour. It does not by itself cover learning by experience-the feed-backs have to improve with the passage of time. The corresponding problem for the brain is that as a machine it has to work out an essential part of its own wiring. Ashby describes the homeostat in some detail, but all that is needed here is to mention some of its properties. Four magnets are suspended in a field due to electric current, so that they are free to move when the current is turned on; but feed-back mechanisms are present which are operated by the movements of the magnets, and these feed-backs alter the current. This causes fresh movements of the magnets, and so on."
  :witness/folio 11
  :witness/char-count 734
  :witness/zero-ellipses? true}}
```

## 6. Literate Semantic Demonstration & Bounded Synthesis

### 6.1 The Dialectical Demarcation
Wisdom isolates the precise conceptual frontier where first-order cybernetics (Wiener) yields to ultrastability (Ashby). Classical negative feedback regulates a designated variable within rigid, immutable circuit parameters. It models reflexive homeostatic maintenance, but is fundamentally incapable of learning or experiential adaptation. For an organism to learn, its feedback parameters must improve over ontogenetic time: the machine must synthesize its own operational wiring.

### 6.2 The First-Order Phase Space
Ashby's Homeostat provides the minimal mechanical substrate for this transition. Four magnets are suspended in mutually coupled electromagnetic fields, described by the linear system $\dot{\mathbf{x}} = \mathbf{A}(\mathbf{P})\mathbf{x}$. When perturbed, the system's trajectory depends strictly on the spectrum of $\mathbf{A}(\mathbf{P})$:
1. **Stable Arrangement:** If all real eigenvalue parts are negative, the four vanes resist external displacement and return to their central basins.
2. **Unstable Arrangement:** If any eigenvalue has a positive real part, the vanes diverge exponentially to their mechanical stops.
This bifurcation is essential: instability must manifest as an energetic excess ($I_{\text{mains}} > I_{\text{crit}}$) so that it can physically trip the stepping relay analyzed in Unit 14.

### 6.3 Epistemic Bounds & Negative Horizon
1. **First-Order Substrate Only:** Unit 013 specifies the continuous mechanical manifold before parameter change occurs; parameter stepping itself is mediated by the uniselector step-function formalized in Unit 014.
2. **Eigenvalue Sensitivity:** Stability is a property of the collective field coupling matrix $\mathbf{A}(\mathbf{P})$, not of any individual magnet in isolation.
