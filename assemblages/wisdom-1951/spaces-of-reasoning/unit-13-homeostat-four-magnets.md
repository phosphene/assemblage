---
uri: work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-13-homeostat-four-magnets
id: CR-WISDOM-U013
title: "Unit 13 — The Homeostat Machine: Four Magnets, Stable/Unstable Arrangements (§4.2, pp. 11–12)"
source: J. O. Wisdom, "The Hypothesis of Cybernetics", BJPS 2(5), 1951, pp. 11–12
basis_tier: read-together
standard: work/topic-98577-public-evolutionary-science/SPEC-close-reading-architecture.md (clj-spec v1.2)
related:
  - ../deep-reads/wisdom-1951-hypothesis-of-cybernetics/L2-formalization/sec04-adaptation-neural-nets-recognition.md
  - ../deep-reads/wisdom-1951-hypothesis-of-cybernetics/L3-verbatim/sec04-descartes-ashby-ultrastability.md
  - ../deep-reads/wisdom-1951-hypothesis-of-cybernetics/derivations.md
---

# Unit 13 — The Homeostat Machine: Four Magnets, Stable/Unstable Arrangements

## Unit of Reason

Wisdom's compression of Ashby's homeostat into its minimal dynamical vocabulary: a
four-magnet coupled system whose *behaviour class* (stable vs. unstable arrangement) is
observable, and whose *parameters* (the feed-back settings) are about to become variables
themselves. This unit fixes the first-order layer; Unit 14 adds the second-order
step-function.

## Informal Concept

Standard negative feedback regulates a variable within fixed parameters. Wisdom isolates
the crucial precursor move: before parameters can change, the system must have two
qualitatively distinct behaviour classes — resistance-to-displacement and maximal
divergence — so that "instability" becomes a detectable state that can *trigger* something.

## Verbatim Text (zero ellipses)

> He points out that negative feed-back is not sufficient to describe all facets of human
> behaviour. It does not by itself cover learning by experience-the feed-backs have to
> improve with the passage of time. The corresponding problem for the brain is that as a
> machine it has to work out an essential part of its own wiring.³
> (§4.2, p. 11)

> Ashby describes the homeostat in some detail, but all that is needed here is to mention
> some of its properties. Four magnets are suspended in a field due to electric current, so
> that they are free to move when the current is turned on; but feed-back mechanisms are
> present which are operated by the movements of the magnets, and these feed-backs alter
> the current. This causes fresh movements of the magnets and so on. The magnets show a
> definite pattern of movement. In a stable or steady arrangement the four magnets move to
> a position where they resist attempts to displace them; if they are displaced, they
> return. In an unstable arrangement they diverge as far as possible.
> (§4.2, p. 11)

> Now instead of setting and altering the feed-backs by hand, uniselectors are introduced
> to do this. They are mechanisms working off a relay current, their function being to
> alter the way in which a feed-back is set (for instance they might alter the distribution
> of weight in the pendulum of a clock); this can be arranged to happen only when the
> current from the mains increases to a certain limit at which the
> (p. 11 → p. 12: sentence continues) relay current is cut off.
> (§4.2, pp. 11–12)

## Triadic Semiotic Specification

```clojure
{:close-read/id "CR-WISDOM-U013"
 :close-read/uri "work/topic-102248-cybernetics/docs/spaces-of-reasoning/unit-13-homeostat-four-magnets.md"
 :close-read/unit-of-reason "The Homeostat Machine: behaviour classes before parameter change"

 ;; Seme (Firstness): the qualitative contrast of stability as active resistance
 :seme/firstness
 {:quality/phenomenon :resist-versus-diverge
  :perceptual/invariant :displacement-response-as-behaviour-class
  :perceptual/metaphor "A spinning top that steadies when nudged, versus one that walks itself off the table"}

 ;; Pheme (Secondness): the actual machine and its engineering lineage
 :pheme/secondness
 {:encounter :ashby-homeostat-1948
  :data {:machines [:four-magnets :electric-field :feed-back-mechanisms :uniselectors]
         :behaviour-classes [:stable-arrangement :unstable-arrangement]}
  :historical-anchors [:ashby-1948-electronic-engineering-20-379-83
                       :ashby-1945-physical-origin-adaptation
                       :thompson-1951-in-the-press]}

 ;; Delome (Thirdness): the inferential rule
 :delome/thirdness
 [(taxonomy :system-order
    {:first-order  {:variables [:magnet-positions]
                    :parameters :fixed}
     :second-order {:variables [:magnet-positions :feed-back-settings]
                    :parameters :discrete-steps-via-uniselector}})
  (rule :homeostat-arrangement
    (branch :magnet-response
            :resist-and-return :stable-arrangement
            :diverge-maximally :unstable-arrangement))
  (rule :instability-trigger
    (implies (> :mains-current :instability-limit)
             (and (cut-off :relay-current)
                  (step-uniselectors :feed-backs))))]
}
```

## Expression & Term Definitions

| Term | Kind | Definition | Nominatum |
|---|---|---|---|
| `:term/stable-arrangement` | behaviour class | Configuration in which displaced magnets resist and return | Four-magnet equilibrium basin, p. 11 |
| `:term/unstable-arrangement` | behaviour class | Configuration in which magnets diverge as far as possible | Repeller region of phase space, p. 11 |
| `:term/uniselector` | mechanism | Relay-operated device altering how a feed-back is set | Telephone stepping switch, p. 11 |
| `:term/instability-limit` | threshold | Mains-current value at which the relay cuts off | Step-function trigger, pp. 11–12 |
| `:term/second-order-system` | structural type | System whose parameters are themselves variables | Feed-back settings as variables, p. 11 |

## Verify Notes

- **Basis tier:** `read-together` — Wisdom 1951 BJPS 2(5), pp. 11–12; PDF p. = text p. + 1.
- **Second-order witnesses:** Ashby 1948 *Electronic Engineering* 20, 379–83 (fn 2, p. 11);
  Ashby 1945 *J. Gen. Psych.* 32, 24 (fn 2, p. 12); both route through Wisdom 1951 until
  independently verified.
- **Publication-status flag:** Thompson, "Cybernetics and Animal Integration", Irish J. Med.
  Sci. (fn 1, p. 11) was *in the press* in 1951 — no independent verification possible from
  this witness alone.
- **Anachronism flag:** Wisdom 1951 never uses the word "ultrastability" (Ashby's later
  coinage, *Design for a Brain* book, 1952). The L3 file
  `sec04-descartes-ashby-ultrastability.md` carries our gloss in its title; the behaviour
  described here is proto-ultrastable. Do not back-project the term into Wisdom's text.
