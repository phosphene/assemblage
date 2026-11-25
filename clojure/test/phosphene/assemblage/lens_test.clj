(ns phosphene.assemblage.lens-test
  (:require [clojure.spec.alpha :as s]
            [clojure.test :refer [deftest is testing]]
            [phosphene.assemblage.ast :as ast]
            [phosphene.assemblage.ast-test :as ast-test]
            [phosphene.assemblage.lens :as lens]
            [phosphene.assemblage.spec :as spec]))

(deftest close-read-lens-test
  (testing "Projecting markdown AST into conformed Semiotic Close-Read Triad"
    (let [doc (ast/parse-markdown ast-test/sample-markdown)
          triad (lens/project-close-read doc)
          triad-meta (meta triad)]
      (is (map? triad))
      (is (= "CR-WISDOM-U013" (:close-read/id triad)))
      (is (= :resist-versus-diverge (get-in triad [:seme/firstness :quality/phenomenon])))
      (is (= :ashby-homeostat-1948 (get-in triad [:pheme/secondness :encounter])))
      (is (s/valid? ::spec/close-read-triad triad))

      ;; Metadata preservation across morphism
      (is (= "550e8400-e29b-41d4-a716-446655440000" (:registry.core/id triad-meta))))))

(deftest verbatim-witness-lens-test
  (testing "Extracting verbatim witness passages with zero-ellipses validation"
    (let [doc (ast/parse-markdown ast-test/sample-markdown)
          witnesses (lens/project-verbatim-witnesses doc)]
      (is (= 1 (count witnesses)))
      (let [w (first witnesses)]
        (is (pos? (:witness/char-count w)))
        (is (true? (:witness/zero-ellipses? w)))
        (is (re-find #"Four magnets are suspended" (:witness/text w)))
        (is (s/valid? ::spec/verbatim-witnesses witnesses))))))

(deftest topological-projection-lens-test
  (testing "Projecting semiotic close read into topological nodes and relations"
    (let [sample-with-anchors (str "---\n"
                                   "id: CR-WISDOM-U013\n"
                                   "---\n\n"
                                   "# Title\n\n"
                                   "```clojure\n"
                                   "{:close-read/id \"CR-WISDOM-U013\"\n"
                                   " :seme/firstness {:quality/phenomenon :stability}\n"
                                   " :pheme/secondness {:encounter :ashby\n"
                                   "                    :historical-anchors [:ashby-1948 :cannon-1932]}\n"
                                   " :term/definitions [{:term/name :homeostat :term/def \"four magnets\"}]}\n"
                                   "```")
          doc (ast/parse-markdown sample-with-anchors)
          elements (lens/project-assemblage-elements doc)]
      (is (map? elements))
      (is (= 3 (count (:nodes elements)))) ;; unit node + 2 anchors
      (is (= 2 (count (:edges elements))))
      (is (contains? (:nodes elements) :ashby-1948))
      (is (contains? (:nodes elements) :cannon-1932)))))
