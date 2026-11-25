# JVM In-Process Markdown AST & Native EDN Synthesis Guide

This guide describes the pure Clojure/JVM implementation for parsing CommonMark documents into native ASTs, projecting them through late-bound morphic lenses, and streaming LLM EDN syntheses directly into Clojure data structures.

This fulfills **Initiative I-012**, **Ticket T-637**, and adheres strictly to the **Phosphene Global Fact Registry Architecture**.

---

## 1. Architecture Overview

```
                      +-----------------------------+
                      |   Markdown Close-Read Doc   |
                      |  - Frontmatter: Deduced ID  |
                      |  - Body: Unconstrained Text|
                      +--------------+--------------+
                                     |
                                     v
                       [phosphene.assemblage.ast]
                     nextjournal/markdown in-process
                   (attaches frontmatter via with-meta)
                                     |
                                     v
                      +-----------------------------+
                      |       Pure JVM AST Map      |
                      |  (= doc-a doc-b) checks tree|
                      |  (meta doc) has provenance  |
                      +--------------+--------------+
                                     |
                                     +-----------------------+
                                     |                       |
                                     v                       v
                         [phosphene.assemblage.lens]         [phosphene.assemblage.spec]
                         - project-close-read                - Sovereign attributes
                         - project-assemblage-elements       - Open maps (s/keys)
                         - project-verbatim-witnesses        - clj-spec v1.2 conformance
                                     |
                                     v
                        [phosphene.assemblage.core]
                       AssemblageGraph Combinatorics
```

---

## 2. Dependencies (`deps.edn`)

```clojure
{:paths ["src"]
 :deps {org.clojure/clojure {:mvn/version "1.12.0"}
        org.clojure/spec.alpha {:mvn/version "0.5.238"}
        io.github.nextjournal/markdown {:git/tag "v0.7.232"
                                        :git/sha "1580eeb11773902b943f4acf88d9f8e1a4ea9e67"}}
 :aliases
 {:test {:extra-paths ["test"]
         :extra-deps {org.clojure/test.check {:mvn/version "1.1.1"}}}}}
```

---

## 3. Module Index

### A. `phosphene.assemblage.ast`
- **`parse-markdown [raw-str]`**: Ingests markdown string into CommonMark AST using `nextjournal.markdown/parse`. Out-of-band YAML frontmatter is attached via `with-meta`.
- **`doc-metadata [ast-doc]`**: Accesses out-of-band document metadata (`(meta ast-doc)`).
- **`code-blocks [ast lang?]`**: Queries code blocks from the AST.
- **`headings [ast level?]`**: Queries headings from the AST.
- **`query-nodes [ast pred]`**: General recursive tree traversal.

### B. `phosphene.assemblage.lens`
- **`project-close-read [ast-doc]`**: Conforms an AST with an embedded EDN block into a Semiotic Close-Read Triad (`:seme`, `:pheme`, `:delome`).
- **`project-assemblage-elements [ast-doc]`**: Projects triad terms and historical anchors into discrete `:nodes` and `:edges` for `phosphene.assemblage.core`.
- **`project-verbatim-witnesses [ast-doc]`**: Extracts blockquotes and verifies zero-ellipses citation integrity.

### C. `phosphene.assemblage.spec`
- Conforms to **clj-spec v1.2-Peirce-Hume-Omega**.
- Validates Sovereign attributes (`:node/id`, `:node/traits`, `:node/level`).
- Structural schemas for `:assemblage/node`, `:assemblage/edge`, and `:phosphene.assemblage.spec/close-read-triad`.

### D. `phosphene.assemblage.client`
- Built on `java.net.http.HttpClient` (Java 11+ LTS).
- Zero external JSON/HTTP library dependencies.
- Sends chat completion prompts requesting native Clojure EDN directly.
- Parses output directly into Clojure maps using `clojure.edn/read-string`.

---

## 4. Quickstart Example

```clojure
(require '[phosphene.assemblage.ast :as ast]
         '[phosphene.assemblage.lens :as lens]
         '[phosphene.assemblage.core :as core]
         '[clojure.spec.alpha :as s]
         '[phosphene.assemblage.spec :as spec])

(def doc (ast/parse-markdown
  "---\nregistry.core/id: 550e8400-e29b-41d4-a716-446655440000\n---\n# Sample\n```clojure\n{:close-read/id \"CR-001\"\n :seme/firstness {:quality/phenomenon :stability}\n :pheme/secondness {:encounter :ashby}}\n```"))

;; 1. Check out-of-band metadata
(ast/doc-metadata doc)
;; => {:registry.core/id "550e8400-e29b-41d4-a716-446655440000"}

;; 2. Project conformed triad
(def triad (lens/project-close-read doc))
(s/valid? ::spec/close-read-triad triad)
;; => true

;; 3. Project topological graph elements
(def elements (lens/project-assemblage-elements doc))
(def g (-> (core/make-assemblage)
           (core/bulk-add (:nodes elements) (:edges elements))))
```
