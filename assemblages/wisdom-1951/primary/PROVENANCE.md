---
uri: assemblages/wisdom-1951/primary/provenance
id: wisdom-1951-primary-provenance
title: "Primary Corpus Provenance — J. O. Wisdom, 'The Hypothesis of Cybernetics' (BJPS 1951)"
type: primary-provenance
status: living
owner: edphos
author: feelingflowingbot
date: 2026-11-26
source:
  author: "J. O. Wisdom"
  title: "The Hypothesis of Cybernetics"
  journal: "The British Journal for the Philosophy of Science"
  year: 1951
  medium: "JSTOR digitized scan (PDF, 25 pages)"
  sha256-source-text: "66c589afc1c477263a8cf13661657b66206dce0dcd27be103167e9ebb1c21ae3"
tags:
  - verbatim-register
  - primary-corpus
  - hermetic
related:
  - assemblages/wisdom-1951/primary/source-text.txt
---

# Primary Corpus Provenance

## The Verbatim Register Rule

The `verbatim` register admits **selection only**:

1. **Exact contiguous token sequences.** A verbatim witness must be an exact,
   contiguous run of tokens from the primary corpus. No token inside the
   selected sequence may be invented, altered, or removed.
2. **Truncation forbidden.** Ellipses (`...`), bracketed insertions, paraphrase,
   and "summary" tokens are all inventions. None may appear inside a witness.
3. **Boundaries are arbitrary.** Where the selected sequence begins and ends is
   a free choice — the selection operator is unconstrained at its edges. Two
   witnesses that overlap or nest are both valid.
4. **Empirical verification.** A witness is valid iff, after whitespace/hyphen
   normalization (below), it is an exact substring of the primary corpus at
   some integer offset. This is a decidable predicate — `witness/exact-contiguous?`
   in `phosphene.assemblage.witness`.

Other registers (canonical formulations, glosses, EDN projections, formalisms)
are **projection registers**: free generation is permitted there, but a
projection must never be marked verbatim.

## Normalization Rules (token-preserving)

The corpus is stored as a single normalized token stream
(`source-text.txt`, 64,623 chars). Normalization operates at the
whitespace/hyphenation level only:

1. **De-hyphenation of line breaks.** The OCR scan breaks words across lines
   ("feed- back", "chain- reaction", "in- flationary"). A hyphen immediately
   followed by a line break is removed and the halves joined. Hyphens that are
   genuine parts of terms ("feed-back", "anti-aircraft") are preserved.
2. **Whitespace collapse.** All runs of whitespace (newlines, page breaks,
   multiple spaces) collapse to a single space.

No token is added, removed, substituted, or reordered. A witness deposited with
OCR hyphenation artifacts ("feed- back") and the same witness normalized
("feed-back") denote the same token sequence.

## Extraction

Extracted from `/home/node/.openclaw/media/inbound/The_Hypothesis_of_Cybernetics_J_O_Wisdom_z_library_sk_1lib_s---bfccd0e1-086a-4ca4-a8a9-33eba3dfae13.pdf`
via PyMuPDF page-text concatenation, then normalized as above. The PDF itself
is not committed (licensing); the normalized token stream is the hermetic
primary ground against which all verbatim witnesses are verified.

## Register Boundary

| Register | Contents | Constraint |
|---|---|---|
| **verbatim** | `L3-verbatim/` witnesses, unit verbatim blocks | exact contiguous substring of `source-text.txt`; boundaries arbitrary |
| **projection** | L2 formalization, canonical formulations, glosses, EDN triads, commentary | free generation; never marked verbatim |

## Corpus Repair Log — 2026-11-26 (witness gate shakedown)

The first full run of the hermetic witness gate (`clojure -M:test`, repo root)
exposed transcription defects in `source-text.txt`. Each repair is grounded in
the JSTOR scan (`pdftotext -layout`, PDF pp. 3–20) and preserves the printed
token stream:

| Corpus defect | Repair | Ground |
|---|---|---|
| `Any chainreaction mechanism` | `chain-reaction` | scan p.4 line-break hyphen dropped by builder |
| `running awayby a mechanism` | `running away-by` | scan p.3 line-break hyphen dropped |
| `or as errorcompensating` | `error-compensating` | scan p.6 line-break hyphen dropped |
| `simple feed-backand mainly` | `feed-back-and mainly` | scan p.4 line-end dash dropped (em dash rendered as hyphen) |
| `alter the feedbacks until` | `alter the feed-backs until` | scan p.13 line-break hyphen dropped |
| `hypotheticodeductive` | `hypothetico-deductive` | scan p.20 line-break hyphen dropped |
| `1948, 0o7, 415` | `1948, 107, 415` | OCR of printed `107` (Northrop footnote) |
| `4.' Feed-Back in the Nervous System` | `4.1 Feed-Back in the Nervous System` | scan p.10 artifact; printed heading is `4.1` |

Left as-is (scan-faithful artifacts, witnesses must transcribe them):
`mechanism increases the tflow of the gas` (scan's own gluing),
spacing before `: ; ?`, footnote markers rendered as `'` (e.g.
`designer.'`, `dynamical system.'`), `telelogical` (Wisdom's own typo, p.5).

Normalization semantics (phosphene.assemblage.witness/normalize, applied
identically to corpus and witness — token-preserving): em/en dash → hyphen;
superscript footnote digits → plain digits; hyphen + whitespace → hyphen
(line-break join); whitespace adjacent to quote glyphs stripped; whitespace
runs collapsed. No token is added, removed, substituted, or reordered.

Gate result: 16 tests, 94 assertions, 0 failures, 0 errors
(2026-11-26, `clojure -M:test` from repo root via root deps.edn +
cognitect test-runner).
