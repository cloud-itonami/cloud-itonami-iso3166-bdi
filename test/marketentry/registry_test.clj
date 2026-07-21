(ns marketentry.registry-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.registry :as registry]))

(deftest engagement-fee-recompute
  (let [e {:base-fee 15000000 :monthly-rate 800000 :monitoring-months 12 :claimed-fee 24600000.0}]
    (is (== 24600000.0 (registry/compute-engagement-fee e)))
    (is (true? (registry/engagement-fee-matches-claim? e))))
  (let [bad {:base-fee 15000000 :monthly-rate 800000 :monitoring-months 12 :claimed-fee 29900000.0}]
    (is (false? (registry/engagement-fee-matches-claim? bad)))))

(deftest register-draft-and-submit
  (let [d (registry/register-draft "eng-1" "BDI" 0)
        s (registry/register-submit "eng-1" "BDI" 0)]
    (is (= "BDI-DFT-000000" (get d "draft_number")))
    (is (= "BDI-SUB-000000" (get s "submit_number")))
    (is (nil? (get-in d ["certificate" "proof"])))
    (is (= "draft-unsigned" (get-in s ["certificate" "status"])))))

(deftest register-requires-ids
  (is (thrown? Exception (registry/register-draft "" "BDI" 0)))
  (is (thrown? Exception (registry/register-submit "eng-1" "" 0))))

(deftest gre-a-gre-complementaire-eligible-below-cap
  (testing "complementaire value at or below 20% of base value -> eligible, regardless of exact base magnitude"
    (is (true? (registry/gre-a-gre-complementaire-eligible?
                {:gre-a-gre-base-value 50000000 :gre-a-gre-complementaire-value 8000000})))
    (is (true? (registry/gre-a-gre-complementaire-eligible?
                {:gre-a-gre-base-value 50000000 :gre-a-gre-complementaire-value 10000000}))
        "exactly 20% is still eligible (<=, not <)")))

(deftest gre-a-gre-complementaire-ineligible-above-cap
  (testing "complementaire value above 20% of base value -> not eligible"
    (is (false? (registry/gre-a-gre-complementaire-eligible?
                 {:gre-a-gre-base-value 50000000 :gre-a-gre-complementaire-value 15000000})))))

(deftest gre-a-gre-complementaire-missing-base-is-never-eligible
  (testing "a missing or non-positive base value is never eligible -- this governor does not guess a related base contract's own amount"
    (is (false? (registry/gre-a-gre-complementaire-eligible?
                 {:gre-a-gre-base-value 0 :gre-a-gre-complementaire-value 0})))
    (is (false? (registry/gre-a-gre-complementaire-eligible?
                 {:gre-a-gre-complementaire-value 1000})))))

(deftest gre-a-gre-complementaire-claim-mismatch
  (testing "claimed eligibility matches the recompute -> no mismatch"
    (is (false? (registry/gre-a-gre-complementaire-claim-mismatches?
                 {:gre-a-gre-base-value 50000000 :gre-a-gre-complementaire-value 8000000
                  :gre-a-gre-claim? true}))))
  (testing "claiming eligibility an above-cap complementaire value does not actually support -> mismatch"
    (is (true? (registry/gre-a-gre-complementaire-claim-mismatches?
                {:gre-a-gre-base-value 50000000 :gre-a-gre-complementaire-value 15000000
                 :gre-a-gre-claim? true}))))
  (testing "an eligible engagement declared ineligible -> mismatch (catches both directions)"
    (is (true? (registry/gre-a-gre-complementaire-claim-mismatches?
                {:gre-a-gre-base-value 50000000 :gre-a-gre-complementaire-value 8000000
                 :gre-a-gre-claim? false})))))
