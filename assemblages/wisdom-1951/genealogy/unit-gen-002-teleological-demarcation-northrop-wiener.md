---
registry.core/id: unit-gen-002-teleological-demarcation-northrop-wiener
corpus: wisdom-1951
line: genealogy
section: "§3 The Basic Cybernetic Hypothesis (pp. 5–6)"
epistemic-status: :boundary-demarcation
basis_tier: read-together
reading_discipline:
  style: deliberative-synthesis
  pipeline_version: "v4-deliberative-seme"
  target_registers: ["verbatim", "gloss", "formal-logic", "s-expressions-seme"]
---

# GEN-002 — Purposive vs. Teleological: The Negative-Feed-Back Demarcation

## 1. Verbatim Textual Witness (zero ellipses)

> "The basic hypothesis of cybernetics is that the chief mechanism of the central nervous system is one of negative feed-back."

> "An anti-aircraft gun is 'purposive' in the sense that it is able by means of radar to follow its target."

> "A machine may start towards a goal, but after it deviates it may make no attempt to pursue that goal. It would then be in one sense' purposive' but not' teleological '. To be' teleological' it must continue to seek its goal, which requires negative feed-back."

> "In ordinary usage, of course, " purposive" has the sense here ascribed to " telelogical "."

*Source: Wisdom (1951), pp. 5–6, including footnote 1 (the Northrop distinction, citing F. S. C. Northrop, "The Neurological and Behavioristic Psychological Basis of the Ordering of Society by Means of Ideas", *Science*, 1948, 107, 415). Exact contiguous substrings of `primary/source-text.txt` / footnote register `primary/footnotes.edn` :page/6.*

## 2. Natural Language Gloss (Wisdom's epistemic move)

Wisdom's statement of the basic hypothesis is two-tiered: first, the CNS's chief mechanism is negative feed-back; second, this mechanism explains 'purposive' and 'adaptive' behaviour. The load-bearing analytical work happens in footnote 1, where he imports **Northrop's (1948) distinction** and makes it carry an engineering meaning:

- **Purposive (weak):** a one-shot trajectory toward a goal; deviation is not corrected. A ballistic object "aimed" at a target qualifies.
- **Teleological (strong):** continuous pursuit of the goal under perturbation — and this *requires negative feed-back*. Teleology is thereby given a necessary mechanism condition, not a mentalistic one.

Wisdom's empirical exemplar is the radar-guided anti-aircraft gun: it is 'purposive' in exactly the sense that it tracks its target by feed-back, with no mind, no deliberation, no vital essence. He also notes the lexical inversion with everyday usage: ordinary language uses "purposive" for what he (following Northrop) calls "teleological" — flagging that the technical vocabulary must override folk usage inside the discipline.

This is the same demarcating move we see throughout the paper: **a behavioral predicate is accepted only when a mechanism condition can be stated for it.** The gun is not a metaphor for purpose; it is purpose, operationalized as error-correcting negative feed-back — exactly the position Rosenblueth–Wiener–Bigelow (1943) established, which Wisdom has already made one of his two roots (GEN-000).

## 3. Formal Symbolic Logic

Let $g$ be the goal state, $\epsilon(t) = d(x(t), g)$ the deviation, and $\mathcal{FB}^-$ the negative feed-back operator.

$$\text{Purposive}(x) \iff \exists g : x(0) \to g \ \text{(one-shot; deviation tolerated)}$$

$$\text{Teleological}(x) \iff \exists g : \forall t,\ \dot{\epsilon}(t) \approx -k\,\epsilon(t),\ k > 0 \quad (\mathcal{FB}^- \text{ active under perturbation})$$

Necessary mechanism condition (the Northrop–Wisdom conditional):

$$\text{Teleological}(x) \implies \mathcal{FB}^{-}(x) \ \ne\ \text{Purposive}(x) \implies \mathcal{FB}^{-}(x)$$

Anti-aircraft gun instantiation (radar-servomechanism):

$$\theta_{\text{gun}}(t) \to \theta_{\text{target}}(t) \ \text{via} \ \dot{\theta}_{\text{gun}} = f(\theta_{\text{target}} - \theta_{\text{gun}}) \qquad \text{(no mind; mechanism-complete)}$$

Lexical demarcation against folk usage:

$$\text{Folk}[\text{"purposive"}] = \text{Technical}[\text{"teleological"}] \implies \text{discipline re-binds the lexicon}$$

## 4. Relational Meta-Structure (EDN)

```clojure
{:genealogy/id "unit-gen-002-teleological-demarcation-northrop-wiener"
 :genealogy/target-claim "Teleology is given a necessary mechanism condition: continuous goal-seeking requires negative feed-back"
 :genealogy/epoch 1951
 :genealogy/epistemic-status :boundary-demarcation
 :genealogy/precursors ["unit-gen-000-dual-genesis-ashby-wiener"]
 :genealogy/successors ["unit-gen-003-pathological-hunting-ataxia-brazier-thompson"
                        "unit-gen-006-universals-calculus-craik-mcculloch-pitts"]
 :genealogy/references
 [{:author "F. S. C. Northrop" :year 1948
   :source "The Neurological and Behavioristic Psychological Basis of the Ordering of Society by Means of Ideas"
   :journal "Science" :volume 107 :pages "415"
   :role :purposive-teleological-distinction}
  {:author "Rosenblueth, Wiener, Bigelow" :year 1943
   :source "Behavior, Purpose, and Teleology"
   :journal "Philosophy of Science" :volume 10 :pages "18-24"
   :role :mechanism-condition-origin}]
 :genealogy/exemplar {:subject "anti-aircraft gun" :sensor "radar"
                      :mechanism "servo negative feed-back" :status "purposive AND teleological"}
 :genealogy/epistemic-delta "behavioral predicate admitted only with a stated mechanism condition"
 :genealogy/wisdom-synthesis :mechanism-conditioned-predicates}
```
