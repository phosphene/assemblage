---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-16-recognition-universals-scansion
id: CR-WISDOM-U016
title: "CR-WISDOM-U016 — Recognition of Universals: Scansion and Centering Servos (§4.4, pp. 15–18)"
source: "J. O. Wisdom, 'The Hypothesis of Cybernetics', The British Journal for the Philosophy of Science, Vol. II, No. 5, May 1951, pp. 15–18"
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v6-synthetic-quad"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme", "witness-register", "literate-synthesis"]
corpus_locus:
  source_file: "assemblages/wisdom-1951/primary/source-text.txt"
  sha256: "fb29b8c188d305d98fa846df7729c1211e8b7e47d577c2abaf765f0d920a6981"
  printed_folio: 15
  doc_page_index: 15
---

# CR-WISDOM-U016 — Recognition of Universals: Scansion and Centering Servos (§4.4, pp. 15–18)

## 1. Verbatim Text (zero ellipses)

> McCulloch's hypothesis is that the superior colliculus computes this, (i.e. presumably is fitted with negative feed-backs whose function is equivalent to the pencil-and-paper calculation) ; the superior colliculus signals the resulting position, in the form of impulses proportional to the two co-ordinates, to the lateral and vertical eye-muscles; and the effect of these impulses on these muscles is to turn the visual axis from its fixation point to the centre of brightness. This continues until the fixation point coincides with the centre of brightness.' Thus we have a hypothetical radar-like negative feed-back by which the focus of attention gravitates towards the centre of brightness. McCulloch then concludes : 'If a square should appear anywhere in the field, the eyes turn until it is centered, and what they see is the same, whatever the initial position of the square.' 2 But unfortunately he does not explain how centering a square of brightness is equivalent to 'recognition' of squareness.
> (§4.4, pp. 15–16)

## 2. Epistemic Gloss & Philosophical Carve

Wisdom inspects the McCulloch-Pitts (1947) neural model for the perception of gestalt universals (how a square or chord is recognized as invariant despite shifts in size, position, or angle):
1. **The Centering Servo Fallacy:** McCulloch proposes that eye-muscle servos center the visual axis on the stimulus's center of brightness. Wisdom points out the critical philosophical non sequitur: centering an object does not constitute *recognizing* it. Centering standardizes the coordinates of a square, but leaves completely unexplained how the brain identifies it as a *square* rather than a circle.
2. **Cortical Scansion and Space-to-Time Transformation:** The deeper mechanism relies on rhythmic cortical scansion (alpha rhythms) performing continuous coordinate transformations over visual afferents, converting spatial forms into invariant temporal pulse sequences (analogous to Morse code where temporal tempo is invariant to absolute duration).

## 3. Formal Symbolic Logic

Let $I(x, y) \in \mathbb{R}^{\ge 0}$ be the retinal luminance distribution.
Let $(x_c, y_c)$ be the center of brightness:

$$(x_c, y_c) = \left( \frac{\iint x I(x, y) dx dy}{\iint I(x, y) dx dy}, \; \frac{\iint y I(x, y) dx dy}{\iint I(x, y) dx dy} \right)$$

Centering servo dynamics:

$$\dot{\mathbf{r}}_{\text{fixation}} = \mathbf{K} \cdot \left( [x_c, y_c]^T - \mathbf{r}_{\text{fixation}} \right)$$

Wisdom's epistemic demarcation:

$$\lim_{t \to \infty} \mathbf{r}_{\text{fixation}}(t) = [x_c, y_c]^T \centernot\implies \text{RecognizeForm}(I, \text{Square})$$

Let $G$ be the 2D affine Lie group (translation, scaling, rotation).
True universal form recognition requires an invariant group projection operator $\Phi$:

$$\forall g \in G, \quad \Phi(g \circ I) = \Phi(I) = \mathbf{v}_{\text{invariant}}$$

## 4. Decomposed Semiotic S-Expressions

### 4.1 Firstness: Qualitative Phenomenon (Seme)

```clojure
{:seme/firstness
 {:quality/phenomenon :same-square-seen-differently
  :perceptual/invariant :form-under-variation
  :perceptual/metaphor "The gaze sliding like a radar dish until brightness is centered, and a tune hummed fast or slow remaining the same tune"}}
```

### 4.2 Secondness: Empirical Encounter & Historical Anchors (Pheme)

```clojure
{:pheme/secondness
 {:encounter :recognition-of-universals-program
  :data {:hypotheses [:centre-of-brightness-feedback :cortical-scansion]
         :transforms [:fixation-centering :space-to-time-raster]
         :wisdom-objections [:centering-is-not-recognising :tempo-vs-pitch-regress]}
  :historical-anchors [:pitts-mcculloch-1947-how-we-know-universals
                       :mcculloch-pitts-1943-logical-calculus
                       :craik-1943-nature-of-explanation]}}
```

### 4.3 Thirdness: Invariant Delome Rules & Causal Habits (Delome)

```clojure
{:delome/thirdness
 [(rule :centering-servo-convergence
    (until (= :fixation-axis :brightness-centroid)
      (servo-shift :retina-origin :brightness-centroid)))
  (rule :wisdom-recognition-demarcation
    (implies (= :fixation-axis :brightness-centroid)
             (not-yet-proved :gestalt-recognition)))
  (rule :scansion-transformation
    (transforms :retinal-raster :impulse-train
                {:invariant-feature :pitch :scale-feature :tempo}))]}
```

### 4.4 Conceptual Term Definitions

```clojure
{:term/definitions
 [#:term{:name :centering-servo
         :def "Negative feedback circuit in the superior colliculus aligning the retinal fixation axis with the stimulus center of brightness"}
  #:term{:name :cortical-scansion
         :def "Rhythmic sweep of neural excitation across cortical layers converting spatial patterns into temporal frequency trains"}
  #:term{:name :gestalt-universals
         :def "Perceptual invariants (such as geometric forms or musical melodies) recognized as identical across scale, orientation, and position"}
  #:term{:name :wisdom-recognition-gap
         :def "The philosophical critique demonstrating that standardizing retinal coordinates does not explain categorical form recognition"}]}
```

## 5. Verbatim Witness Register

```clojure
{:witness/selection
 {:witness/text "McCulloch's hypothesis is that the superior colliculus computes this, (i.e. presumably is fitted with negative feed-backs whose function is equivalent to the pencil-and-paper calculation) ; the superior colliculus signals the resulting position, in the form of impulses proportional to the two co-ordinates, to the lateral and vertical eye-muscles; and the effect of these impulses on these muscles is to turn the visual axis from its fixation point to the centre of brightness. This continues until the fixation point coincides with the centre of brightness.' Thus we have a hypothetical radar-like negative feed-back by which the focus of attention gravitates towards the centre of brightness. McCulloch then concludes : 'If a square should appear anywhere in the field, the eyes turn until it is centered, and what they see is the same, whatever the initial position of the square.' 2 But unfortunately he does not explain how centering a square of brightness is equivalent to 'recognition' of squareness."
  :witness/folio 15
  :witness/char-count 1008
  :witness/zero-ellipses? true}}
```

## 6. Literate Semantic Demonstration & Bounded Synthesis

### 6.1 The Dialectical Demarcation
Wisdom mounts a sharp philosophical critique of Warren McCulloch's neurophysiological theory of perception. McCulloch attempted to explain the recognition of universals—how a geometric shape like a square is perceived as the same entity regardless of where it appears in the visual field—by invoking a negative feedback servo centered in the superior colliculus. The servo detects the center of brightness and drives the extraocular muscles until the visual axis aligns with it. Wisdom exposes the fatal non sequitur: centering is merely spatial normalization; it does not touch the problem of universal classification. Once centered, the brain must still distinguish a square from a triangle or a face.

### 6.2 The Cortical Scansion Alternative
To resolve true invariant form recognition, Section 3 formalizes the Pitts-McCulloch Lie group framework. Spatial invariance under translation, rotation, and dilation requires a group projection operator $\Phi(g \circ I) = \Phi(I)$. Pitts and McCulloch modeled this via rhythmic scansion: the alpha rhythm sweeps vertically across the six histological layers of the visual cortex, transforming spatial afferent arrays into temporal impulse trains. Just as a musical chord or a Morse letter (three short clicks and a long) is recognized as invariant whether hummed rapidly or slowly, scansion maps spatial geometric proportions to temporal ratios.

### 6.3 Epistemic Bounds & Negative Horizon
1. **Centering is Coordinate Standardisation, Not Recognition:** Motor alignment of sensory organs eliminates translation variance, but category recognition requires higher-order topological invariants.
2. **Infinite Regress of the Scanning Eye:** Wisdom cautions that scansion risks introducing an internal homunculus: an inner scanning observer reading the neural raster, merely pushing the problem of recognition one layer deeper.
