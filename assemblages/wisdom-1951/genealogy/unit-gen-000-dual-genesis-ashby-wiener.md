---
registry.core/id: unit-gen-000-dual-genesis-ashby-wiener
corpus: wisdom-1951
line: genealogy
section: "§3 The Basic Cybernetic Hypothesis (pp. 5–6)"
epistemic-status: :dual-genesis
---

# GEN-000 — The Dual Genealogical Root: Ashby (1940) and Rosenblueth–Wiener–Bigelow (1943)

## 1. Verbatim Textual Witness (zero ellipses)

> "The hypothesis seems to be due mainly to Ashby and Wiener, though others had written on some of the resemblances."

> "Other writers, who, as we shall see, contributed to the detail are sometimes credited somewhat vaguely with the idea, and they may have had it but the literature affords no confirmation of this."

> "It would seem to be fair therefore to regard the general approach as the Ashby-Wiener hypothesis (though possibly Wiener's associate, Bigelow, should also be included)."

> "It is interesting to find thatWilbur had part of the hypothesis but did not put it to explanatory use."

> "Why well known ?"

*Source: Wisdom (1951), pp. 5–6. Anchored to `primary/source-text.txt`; footnote register `primary/footnotes.edn` :page/6. Each span is an exact contiguous substring of the canonical corpus (verified by `witness/exact-contiguous?`).*

## 2. Natural Language Gloss (Wisdom's epistemic move)

Wisdom refuses the singular founding myth that Wiener's 1948 book encourages. He performs three distinct acts of attribution in a single passage:

1. **Priority assignment:** the *hypothesis* (that the chief mechanism of the CNS is negative feed-back) originates with **Ashby, 1940** ("Adaptiveness and Equilibrium", *Journal of Mental Science*, 86, 478–483) and independently with **Rosenblueth, Wiener & Bigelow, 1943** ("Behavior, Purpose, and Teleology", *Philosophy of Science*, 10, 18–24). Wiener owns only the *word* — coined from the Greek for helmsman in 1948.
2. **Vague-credit screening:** contemporaries sometimes credited with the idea "the literature affords no confirmation of this" are excluded from the root. Genealogical edges require documentary witness, not reputation.
3. **Explanatory-use test:** G. B. Wilbur (1941, *American Imago*, 2, 224 n) invoked a radio "self-regenerating system" metaphor for Freud's reflex arc, but Wisdom's acid marginal query — "Why well known ?" — marks him as having the phrase without the hypothesis. A metaphor not put to *explanatory use* generates no lineage edge.

The compound label **Ashby-Wiener hypothesis** encodes the empirical finding: two independent roots, one clinical/psychiatric (Ashby, mental-homeostasis problem), one mathematical/engineering (Wiener & Bigelow, servomechanical fire-control problem), converging on the same mechanism claim.

## 3. Formal Symbolic Logic

Let $\mathcal{H}_{\text{cyb}}$ denote the basic cybernetic hypothesis; $R_A$ the Ashby 1940 lineage; $R_W$ the Rosenblueth–Wiener–Bigelow 1943 lineage.

$$\text{Genesis}(\mathcal{H}_{\text{cyb}}) = R_A(1940) \,\cap\, R_W(1943), \qquad R_A \perp R_W \ (\text{independent convergence})$$

Naming is decoupled from genesis:

$$\text{Coinage}(\text{Wiener}, 1948) \not\Rightarrow \text{Genesis}(\text{Wiener}, 1948), \qquad \text{Hypothesis} = \text{Ashby-Wiener}$$

Citation-edge admission predicate (the Wilbur filter):

$$\text{Edge}(x \to \mathcal{H}_{\text{cyb}}) \iff \text{Documented}(x) \,\land\, \text{ExplanatoryUse}(x)$$

$$\text{Wilbur}_{1941}: \text{Documented} \,\land\, \neg\text{ExplanatoryUse} \implies \neg\,\text{Edge}$$

## 4. Relational Meta-Structure (EDN)

```clojure
{:genealogy/id "unit-gen-000-dual-genesis-ashby-wiener"
 :genealogy/target-claim "The basic cybernetic hypothesis has two independent roots, not one founding myth"
 :genealogy/epoch 1951
 :genealogy/epistemic-status :dual-genesis
 :genealogy/precursors []
 :genealogy/successors ["unit-gen-001-thermodynamic-steady-state-cannon-hill"
                        "unit-gen-002-teleological-demarcation-northrop-wiener"
                        "unit-gen-004-ultrastability-cartesian-ceiling-ashby-cannon"]
 :genealogy/roots
 [{:author "W. R. Ashby" :year 1940
   :source "Adaptiveness and Equilibrium"
   :journal "Journal of Mental Science" :volume 86 :pages "478-483"
   :discipline :psychiatry-biology :vector :equilibrium-adaptation}
  {:author "Rosenblueth, Wiener, Bigelow" :year 1943
   :source "Behavior, Purpose, and Teleology"
   :journal "Philosophy of Science" :volume 10 :pages "18-24"
   :discipline :mathematics-engineering :vector :negative-feedback-teleology}]
 :genealogy/coined-by {:author "Norbert Wiener" :year 1948 :term "cybernetics"}
 :genealogy/screened-out
 [{:author "G. B. Wilbur" :year 1941 :reason "metaphor without explanatory use"
   :wisdom-query "Why well known ?"}]
 :genealogy/wisdom-synthesis :dual-origin-convergence}
```
