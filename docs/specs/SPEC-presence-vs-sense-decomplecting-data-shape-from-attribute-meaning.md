# Presence vs. Sense: De-complecting Data Shape from Attribute Meaning in Clojure

**Document Coordinate:** `:arch.clojure.data/presence-vs-sense-v1`  
**Status:** Hermetically Self-Sufficient & Self-Specifying Reference Architecture  
**Core Axiom:** *Design is taking things apart. Attribute Sense belongs to the global open-world registry (`s/def` + `(s/keys)`); Key Presence belongs to local point-of-use destructuring.*

---

## 0. Self-Specification: This Document as Open-World Information

In accordance with the principles defined herein, this document specifies its own structural grammar and attribute senses using pure Clojure `spec.alpha` semantics before asserting its normative claims. Every structural element in this specification obeys the exact separation of **Sense** (what an attribute means globally), **Presence** (which attributes a consumer selects locally), and **Grammar** (how sequential sections compose).

```clojure
(ns arch.clojure.data.presence-vs-sense
  (:require [clojure.spec.alpha :as s]))

;; 1. SOVEREIGN ATTRIBUTE SENSES (Global, Context-Independent Meaning)
(s/def :doc.meta/coordinate qualified-keyword?)
(s/def :doc.meta/axiom      (s/and string? #(pos? (count %))))
(s/def :doc.section/id      qualified-keyword?)
(s/def :doc.section/title   string?)
(s/def :doc.section/claims  (s/coll-of string? :min-count 1))
(s/def :doc.code/form       any?)
(s/def :doc.code/blocks     (s/coll-of :doc.code/form))

;; 2. OPEN-WORLD MAP VALIDATION (Validates all present qualified attributes)
(s/def :doc/open-entity (s/keys))

;; 3. SEQUENTIAL GRAMMAR (Orders sections without owning attribute semantics)
(s/def :doc/section
  (s/and :doc/open-entity
         #(contains? % :doc.section/id)
         #(contains? % :doc.section/title)))

(s/def :doc/specification
  (s/cat :header   (s/and :doc/open-entity
                          #(contains? % :doc.meta/coordinate)
                          #(contains? % :doc.meta/axiom))
         :sections (s/+ :doc/section)))
```

Notice what `:doc/open-entity` does **not** do: it never enumerates `:req` or `:opt` keys. Any map flowing through this specification is validated against the global registry for every qualified attribute it carries, while individual readers destructure only the keys required at their point of use.

---

## 1. The Architectural Vertex: Why Static Aggregates Fail

To understand modern Clojure data design, we must confront the central flaw that plagued early `clojure.spec.alpha` usage and motivated the exploration—and eventual shelving—of `spec-alpha2`.

### The Two-Way Complection of Traditional Map Schemas

In static type systems (classes, structs, records, JSON Schema, Protobuf) and in idiomatic abuse of `s/keys` with hardcoded `:req` and `:opt` vectors, two fundamentally orthogonal questions are fused together:

1. **Attribute Value Semantics (Sense):** What is the invariant definition of `:user/email`, `:order/total-cents`, or `:ast/line-number`?
2. **Contextual Key Presence (Selection):** Which subset of keys must exist on a map for *this specific function call*, *this pipeline stage*, or *this API response*?

When you encode both concerns into a single `s/keys` definition:

```clojure
;; ANTIPATTERN: Complecting global Sense with local Presence
(s/def :user/create-payload
  (s/keys :req [:user/email :user/password]
          :opt [:user/display-name]))

(s/def :user/authenticated-session
  (s/keys :req [:user/id :user/email :user/roles]))

(s/def :user/audit-projection
  (s/keys :req [:user/id :user/email]
          :opt [:user/last-login-at]))
```

You trigger an **explosion of static aggregate specs**. Every workflow step, partial update (`PATCH`), database join, or UI view demands a brand-new named aggregate spec. Worse, when maps nest three or four levels deep, static `s/keys` aggregates fail to compose: you cannot tell an outer map spec to require a specific nested key path without defining bespoke wrapper specs at every intermediate level.

> **Natural language gloss:** Bundling required key lists into static map specs forces you to invent a new named type for every partial view or workflow step, destroying composition across nested data structures.

---

## 2. Historical & Philosophical Lineage: RDF, `spec-alpha2`, and Destructuring

### The RDF Revelation: Attributes as First-Class Facts

Rich Hickey drew directly from the Resource Description Framework (RDF) and Datomic's entity-attribute-value (`EAV`) model when designing Clojure's namespace-qualified keyword ecosystem:

* In RDF, an attribute URI (like `foaf:mbox`) has a **universal, context-independent meaning**. It is not "owned" by a `Person` class or a `ContactForm` struct.
* An entity in Clojure is simply an **open-world map of `[qualified-attribute value]` assertions**.
* Because a namespace-qualified keyword (`:acct/balance-cents`) carries its own global identity, its specification `(s/def :acct/balance-cents int?)` is **timeless and universal**.

### The Rise and Demise of `spec-alpha2` (`s/schema` and `s/select`)

During the `spec-alpha2` design cycle, the Clojure team recognized that `s/keys` was being misused as a rigid container schema. They proposed splitting `s/keys` into two primitives:

* **`s/schema`:** A declaration of the universe of possible attributes associated with a domain concept, without asserting which ones are required.
* **`s/select`:** A point-of-use selection DSL (`(s/select ::user-schema [:user/id {:user/org [:org/name]}])`) specifying which keys and nested sub-keys must be present for a specific operation.

Ultimately, `spec-alpha2` was officially discontinued. Why? Because introducing a parallel selection DSL inside Spec duplicated a mechanism Clojure already possessed natively at the exact point of use: **map destructuring**.

### The Resolution: Destructuring Handles Presence; `(s/keys)` Handles Sense

By enriching Clojure's destructuring syntax and pairing it with bare `(s/keys)` validation, the architecture achieved a clean, zero-ceremony separation of concerns:

| Architectural Dimension | Mechanism | Scope & Lifecycle | Question Answered |
| :--- | :--- | :--- | :--- |
| **Sense (Meaning)** | `(s/def :ns/attr pred)` + `(s/keys)` | **Global & Timeless** (Registry) | *"If attribute `:ns/attr` is present, is its value valid?"* |
| **Presence (Shape)** | Point-of-Use Destructuring | **Local & Contextual** (Function / Let) | *"Which keys and nested paths does this consumer require right now?"* |
| **Order (Grammar)** | `s/cat`, `s/alt`, `s/*`, `s/+` | **Sequential** (Token / Arg Streams) | *"In what order do heterogeneous values appear?"* |

> **Natural language gloss:** Register what each qualified attribute means once globally with `s/def`, validate any map's values universally with `(s/keys)`, and declare which keys must be present locally at each function's destructuring site.

---

## 3. Mechanics of the Vertex: How Sense and Presence Interact

### 1. Universal Value Validation via Bare `(s/keys)`

A bare `(s/keys)` call with no `:req` or `:opt` arguments is one of the most powerful and underutilized primitives in `clojure.spec.alpha`. Its operational semantics are:

> *For every key `k` in map `m`, if `k` is a namespace-qualified keyword registered in `clojure.spec.alpha`, validate `(get m k)` against `(s/get-spec k)`. Ignore unregistered keys and unqualified keys.*

This gives you **open-world polymorphic validation**:

```clojure
(s/def :order/id uuid?)
(s/def :order/status #{:pending :paid :shipped :cancelled})
(s/def :shipping/tracking-code (s/and string? #(re-matches #"[A-Z0-9]{12}" %)))

;; A single universal validator for ANY map carrying any combination of registered attributes
(defn valid-domain-facts? [m]
  (s/valid? (s/keys) m))

(valid-domain-facts? {:order/id (random-uuid)
                      :order/status :paid
                      :extra/unregistered-annotation "ignored safely"})
;; => true

(valid-domain-facts? {:order/id (random-uuid)
                      :shipping/tracking-code "bad"})
;; => false (caught automatically without needing a shipping-specific aggregate spec!)
```

### 2. Deep Point-of-Use Presence via Destructuring

Instead of forcing upstream producers to conform to rigid aggregate specs, downstream consumers declare their exact structural expectations—including deep nested paths, defaults, and required presence—right at the point of consumption:

```clojure
(defn dispatch-shipment!
  "Requires :order/id, :order/status, and nested :customer/address keys at point of use,
   while remaining agnostic to any other attributes accreted onto the order map."
  [{:order/keys [id status]
    {:customer/keys [postal-code country] :as addr} :order/shipping-address
    :as order}]
  {:pre [(s/valid? (s/keys) order)
         (some? id)
         (= status :paid)
         (some? postal-code)
         (some? country)]}
  {:shipment/order-id id
   :shipment/destination addr})
```

Look at the architectural properties achieved here:
1. **Zero Static Aggregate Proliferation:** We never defined `::paid-order-with-shipping-address`.
2. **Deep Composability:** Nested presence (`:order/shipping-address` -> `:customer/postal-code`) is expressed directly in the binding form without intermediate wrapper specs.
3. **Open-World Accretion:** Upstream services can attach `:billing/invoice-id`, `:fraud/risk-score`, or `:telemetry/trace-id` to the `order` map without breaking `dispatch-shipment!`, and `(s/keys)` automatically validates those accreted attributes if they have registered specs.

---

## 4. Connecting the Vertex to Sequence Grammars (`s/cat`, `s/alt`, `s/*`)

When processing sequential streams—such as lexical tokens from a boundary parser, CLI arguments, or event logs—the **Presence vs. Sense** vertex prevents grammars from becoming brittle.

### Grammar Emits Open Maps of Qualified Senses

A well-designed `spec.alpha` sequence grammar (`s/cat`, `s/alt`, `s/*`) should never emit positional tuples or unqualified maps into your domain core. Instead, boundary conformers lift sequential tokens into **open maps of qualified attributes**:

```clojure
(s/def :ast.node/type #{:heading :paragraph :code-fence})
(s/def :ast.heading/level (s/int-in 1 7))
(s/def :ast.text/content string?)
(s/def :ast.code/language keyword?)

;; Sequence grammar parses raw token tuples into an open map of qualified senses
(s/def :ast/heading-grammar
  (s/and (s/cat :hashes #(= (first %) :tok/hashes)
                :text   #(= (first %) :tok/text))
         (s/conformer (fn [{:keys [hashes text]}]
                        {:ast.node/type     :heading
                         :ast.heading/level (second hashes)
                         :ast.text/content  (second text)}))
         ;; Validate all emitted qualified attributes against global Senses
         (s/keys)))
```

### Downstream Passes Destructure Only Their Required Slice

Once the sequence grammar hydrates the token stream into open maps of qualified attributes:
* **The Table-of-Contents Builder** destructures `{:ast.node/keys [type] :ast.heading/keys [level] :ast.text/keys [content]}`.
* **The Syntax Highlighter** destructures `{:ast.node/keys [type] :ast.code/keys [language] :ast.text/keys [content]}`.
* **The Word Counter** destructures `{:ast.text/keys [content]}`—working uniformly across headings, paragraphs, and code blocks without caring about node type!

---

## 5. The Unqualified Key Boundary & Quarantine Protocol

A critical asymmetry exists in Clojure: **unqualified keywords (`:id`, `:name`, `:type`) have no global identity.**

* Because `:id` is shared across users, orders, sessions, and AST nodes, `(s/def :id ...)` is a global namespace collision.
* Consequently, `(s/keys)` cannot validate unqualified keys automatically without explicit `:req-un` or `:opt-un` mappings.

### The Quarantine Rule (Mode-S to Mode-D Translation)

Treat unqualified keys strictly as **external serialization artifacts** (JSON payloads, YAML frontmatter, SQL result sets, CLI flags):

1. **Quarantine at the Boundary:** Never allow maps with unqualified keys to circulate through internal domain functions.
2. **Lift to Qualified Senses Immediately:** At the ingestion boundary, transform unqualified keys into namespace-qualified attributes (e.g., via `set/rename-keys` or a boundary `s/conformer`), immediately validate with `(s/keys)`, and emit clean qualified maps into the system core.
3. **Project Outward Only at Egress:** Strip namespaces (`(update-keys m (comp keyword name))`) only at the final serialization step when writing JSON or external wire formats.

```clojure
(require '[clojure.set :as set])

;; Boundary ingestion: lift external JSON map into sovereign qualified attributes
(defn ingest-external-user [raw-json-map]
  (let [qualified (-> raw-json-map
                      (select-keys [:id :email :role])
                      (set/rename-keys {:id    :user/id
                                        :email :user/email
                                        :role  :user/role}))]
    (if (s/valid? (s/keys) qualified)
      qualified
      (throw (ex-info "Boundary Sense violation"
                      {:explain (s/explain-data (s/keys) qualified)})))))
```

---

## 6. Design Patterns and Antipatterns

### Pattern 1: Attribute-First Registry Design
Define atomic, single-attribute specs (`(s/def :entity/attr pred)`) before defining any composite structures. Every spec name should describe **what the value is**, never which container holds it (e.g., `:person/birth-date`, not `:person-form-input/birth-date-field`).

### Pattern 2: Universal `(s/keys)` Instrumentation Gates
Use bare `(s/keys)` as a lightweight invariant check at module boundaries and inside `:pre`/`:post` assertions. Because `(s/keys)` validates every registered qualified key present in a map, adding a new `s/def` for an attribute automatically enforces its invariant across every map in your system.

### Pattern 3: Consumer-Driven Presence Contracts
Let consumer functions define what keys they require via destructuring rather than asking producer functions to conform to consumer-specific output specs. Producers guarantee **Sense** (all emitted attributes satisfy their global specs); consumers assert **Presence** (the keys they read exist).

### Antipattern 1: The `:req` / `:opt` Aggregate Explosion
* **Symptom:** Creating dozens of `s/keys` specs (`::order-init`, `::order-priced`, `::order-shipped`) that differ only in which subset of keys is required at each step.
* **Cure:** Collapse them into a single open `(s/keys)` check for value validation and express stage-specific key presence via destructuring or targeted predicates (`#(every? % [:order/id :order/total])`).

### Antipattern 2: Encoding Container Context in Attribute Names
* **Symptom:** Naming specs `:api.v1.response/user-email` and `:db.row/user-email` for the exact same semantic attribute.
* **Cure:** Give the attribute one sovereign Sense (`:user/email`) so maps can flow seamlessly from database to domain logic to API response without key translation.

### Antipattern 3: Letting Unqualified Maps Leak Past the Boundary
* **Symptom:** Passing maps with `{:type :heading :text "Intro"}` through ten internal namespaces, forcing every function to guess the map's schema or rely on fragile `:req-un` specs.
* **Cure:** Lift to qualified keys (`{:ast.node/type :heading :ast.text/content "Intro"}`) once at the lexer/parser boundary.

---

## 7. Summary Synthesis: The Three Laws of Clojure Data Architecture

1. **Law of Sovereign Sense:** Every domain attribute deserves a globally unique, namespace-qualified keyword whose value semantics are registered once via `s/def` and enforced everywhere via `(s/keys)`.
2. **Law of Local Presence:** Key presence is a contextual requirement of the consumer, not an intrinsic property of the data; express it at the point of use through destructuring.
3. **Law of Sequential Grammar:** When order and cardinality matter across streams of values or tokens, compose `s/cat`, `s/alt`, and `s/*` to parse sequences into open maps of qualified attributes.