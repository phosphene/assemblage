---
uri: work/topic-102248-cybernetics/docs/deep-reads/wisdom-1951-hypothesis-of-cybernetics/L3-verbatim/sec04-cerebellar-tremor-hunting-ataxia
id: wisdom-1951-L3-sec04-cerebellar-hunting
title: "L3 Verbatim + Commentary — Section 4.1: Cerebellar Disease, Feedback Hunting, and Experimental De-afferentation"
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
  section: "4.1"
  page: "10-11"
  stable_url: "http://www.jstor.org/stable/685605"
basis_tier: "read-together"
tags:
  - verbatim
  - L3-commentary
  - cerebellar-tremor
  - hunting
  - rosenblueth
  - wiener
  - ataxia
  - motor-control
related:
  - work/topic-102248-cybernetics/docs/deep-reads/wisdom-1951-hypothesis-of-cybernetics/L2-formalization/sec04-adaptation-neural-nets-recognition
  - concepts/epistemic-levels
---

# L3 Verbatim: Cerebellar Intention Tremor & Servomechanism Hunting

*[read-together — Wisdom 1951 BJPS 2(5), pp. 10–11; exact PDF text witnesses from JSTOR stable:685605]*

---

## 1. Primary Textual Witnesses

### Block 4.1.1: Deductive Falsification and the Clinical Analog of Hunting (pp. 10–11)

> "4.1 Feed-Back in the Nervous System. Wiener handled the cybernetic hypothesis in the usual scientific way. He deduced a consequence from it and sought a test. When a thermostat corrects an error it makes a new one, but a smaller one, and this too it corrects by making a further, but smaller error : the negative feed-back leads to closer and closer focus on the target.
> 
> In certain conditions, however, it will lead to oscillation. This comes about if the self-correcting mechanism overshoots its mark too far, i.e. if the error it makes is commensurate with the one it is correcting. Wiener considered that if the organism has negative feed-back this deduction would hold, and that it should be possible to find an organic example of oscillation.
> 
> His collaborator, Rosenblueth, confirmed this:¹ a patient with cerebellar disease cannot bring a glass of water smoothly to his mouth; he swings it past on one side, tries to reverse the motion but succeeds in swinging it past on the other side.
> 
> 'This test is typical of the disorderly motor performance of patients with cerebellar disease. The analogy with the behavior of a machine with undamped feed-back is so vivid that we venture to suggest that the main function of the cerebellum is the control of the feed-back nervous mechanisms involved in purposeful motor activity.'²"

### Block 4.1.2: Normal Regulation and Experimental De-afferentation (p. 11)

> "Non-oscillatory motion, of course, characterises ordinary controlled action, such as picking up a pen--'our motion is regulated by some measure of the amount by which it has not yet been accomplished.'"

> "Wiener and Rosenblueth have here found highly important confirmation of the hypothesis.
> 
> Further support comes from experiments in which animals have been deprived of a part of the nervous system. Numerous examples are cited by Brazier⁴; she mentions, for instance, Young's work with cuttlefish in which removal of the higher centres led to unceasing swimming continuing till the fish died; she also mentions cauterisation of the interpeduncular nucleus in cats, carried out by Bailey, as a result of which they walk forward continuously till they die. Apparently the negative feed-back mechanisms were put out of action or removed."

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
  witness_pages: [10, 11]
  character_span_exact: true
  collation_status: "verified_against_pdf_text_stream"
  external_citations_verified:
    - author: "Norbert Wiener"
      year: 1948
      title: "Cybernetics"
      pages: ["5", "116"]
      status: "attested_in_footnotes_1_and_3"
    - authors: ["Arturo Rosenblueth", "Norbert Wiener", "Julian Bigelow"]
      year: 1943
      title: "Behavior, Purpose, and Teleology"
      journal: "Philosophy of Science"
      volume: 10
      page: 20
      status: "attested_in_footnote_2"
    - author: "Mary A. B. Brazier"
      year: 1950
      title: "Neural Nets and the Integration of Behaviour"
      book: "Perspectives in Neuropsychiatry"
      editor: "Derek Richter"
      pages: ["36"]
      status: "attested_in_footnote_4"
```

---

## 3. Analytical Commentary & Epistemic Decomposition

### A. The Hypothetico-Deductive Architecture of Wiener’s Move
Wisdom highlights the epistemic rigor of the Wiener-Rosenblueth discovery:
1. They did not simply notice a vague behavioral resemblance and declare an analogy.
2. They deduced a **pathological boundary condition** from the differential equations of negative feedback control: when loop gain is too high or phase lag equals $180^\circ$, negative feedback flips into positive runaway oscillation (**hunting**).
3. They asked: *Does neurology exhibit an exact clinical counterpart where target-directed movement produces violent overshooting while resting posture remains tranquil?*
4. Rosenblueth supplied the clinical answer: **intention tremor (ataxia) in cerebellar lesions**. At rest, the patient’s arm is steady. When reaching for a glass, the motor error signal is tapped but over-corrected; the hand swings violently past the glass in expanding sinusoidal oscillations.

### B. "The Measure of the Amount Not Yet Accomplished"
Wisdom highlights Wiener’s formulation: motor action is regulated by the instantaneous difference between current state and target state:

$$e(t) = x_{\text{target}} - x(t)$$

This operational definition grounds motor control in closed-loop error-nullification. Smooth voluntary movement is not ballistic execution; it is continuous deceleration as $e(t) \to 0$.

### C. Experimental Surgical De-afferentation
Wisdom cites Mary Brazier’s compilation of surgical ablation experiments:
- Cuttlefish (J. Z. Young): ablation of higher inhibitory ganglia causes continuous, unceasing swimming until physical exhaustion and death.
- Cats (Percival Bailey): bilateral lesioning of the interpeduncular nucleus produces continuous, unstoppable forward walking until death.
- **The Epistemic Finding:** The default state of motor effectors in these organisms is uninhibited driving energy. Negative feedback loops provide the indispensable brakes and checks that keep the organism viable. Removing the loop destroys the organism's capacity to arrest its own kinetic output.

---

## 4. Platform Wiki Coordinates

- **Epistemic Stack (`concepts/epistemic-levels`):** Level 1 Clinical Observation (intention tremor vs. resting tremor; walking cats) combined with Level 2 Formalization (control loop hunting criteria).
- **Constitutive vs. Causal:** The constitutive nature of purposeful reaching is closed error-nullification; the causal pathology is phase delay inducing divergence.
