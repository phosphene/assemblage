(ns phosphene.assemblage.genealogy-lens-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.spec.alpha :as s]
            [clojure.java.io :as io]
            [clojure.string :as str]
            [phosphene.assemblage.ast :as ast]
            [phosphene.assemblage.lens :as lens]
            [phosphene.assemblage.spec :as spec]))

(deftest test-genealogy-unit-spec
  (testing "Conforming a valid genealogy unit metadata block"
    (let [sample {:genealogy/id "unit-gen-000-dual-genesis-ashby-wiener"
                  :genealogy/epoch 1951
                  :genealogy/target-claim "The Ashby-Wiener Hypothesis of Cybernetics"
                  :genealogy/epistemic-status :dual-genesis
                  :genealogy/precursors []
                  :genealogy/successors ["unit-gen-001-thermodynamic-steady-state"]
                  :genealogy/references
                  [{:genealogy.ref/author "W. R. Ashby"
                    :genealogy.ref/year 1940
                    :genealogy.ref/source "Adaptiveness and Equilibrium"
                    :genealogy.ref/discipline :biology
                    :genealogy.ref/role :biological-origin}
                   {:genealogy.ref/author "Rosenblueth, Wiener, Bigelow"
                    :genealogy.ref/year 1943
                    :genealogy.ref/source "Behavior, Purpose, and Teleology"
                    :genealogy.ref/discipline :mathematics
                    :genealogy.ref/role :teleological-origin}]}]
      (is (s/valid? ::spec/genealogy-unit sample)))))

(deftest test-project-genealogy-metadata
  (testing "Extracting genealogical metadata from Markdown AST"
    (let [doc-md "---\nregistry.core/id: test-doc\n---\n# Test\n\n```clojure\n{:genealogy/id \"unit-gen-test\"\n :genealogy/epoch 1951\n :genealogy/target-claim \"Claim\"\n :genealogy/epistemic-status :dual-genesis}\n```\n"
          doc-ast (ast/parse-markdown doc-md)
          meta-block (lens/project-genealogy-metadata doc-ast)]
      (is (= "unit-gen-test" (:genealogy/id meta-block)))
      (is (= :dual-genesis (:genealogy/epistemic-status meta-block))))))

(deftest test-corpus-genealogy-projection
  (testing "Projecting all synthesized genealogy units into an interconnected network"
    (let [dir (io/file "assemblages/wisdom-1951/genealogy")
          files (filter #(str/starts-with? (.getName %) "unit-gen-") (.listFiles dir))
          ast-docs (map #(ast/parse-markdown (slurp %)) files)
          network (lens/project-genealogy-network ast-docs)]
      (is (= 9 (:network/count network)))
      (is (contains? (:network/units network) "unit-gen-000-dual-genesis-ashby-wiener"))
      (is (contains? (:network/units network) "unit-gen-008-genealogical-synthesis-model-ceiling"))
      ;; Verify root exists
      (is (some #(= "unit-gen-000-dual-genesis-ashby-wiener" (:genealogy/id %))
                (:network/roots network))))))
