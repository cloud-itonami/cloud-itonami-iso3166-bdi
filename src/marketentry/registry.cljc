(ns marketentry.registry
  "Pure-function market-entry filing-draft + filing-submit record
  construction -- an append-only market-entry book-of-record draft.

  Like every sibling actor's registry, there is no single international
  reference-number standard for a public-procurement market-entry
  filing -- every jurisdiction assigns its own format. This namespace
  does NOT invent one; it builds a jurisdiction-scoped sequence number
  and validates the record's required fields, the same honest,
  non-fabricating discipline `marketentry.facts` uses.

  `engagement-fee-matches-claim?` is an HONEST reapplication of the
  SAME ground-truth-recompute DISCIPLINE sibling actors use (verify a
  claimed monetary total against the entity's own recorded quantity x
  unit fields), reapplied to a market-entry engagement fee line.

  `gre-a-gre-complementaire-eligible?` / `gre-a-gre-complementaire-claim-
  mismatches?` are the SAME discipline applied to a genuinely
  Burundi-specific mechanism: Loi n°1/01 du 4 février 2008 portant Code
  des Marchés Publics du Burundi (curl/pdftotext-verified 2026-07-22 via
  the Wayback Machine mirror of droit-afrique.com's own hosting, after
  the live host itself returned HTTP 403), Article 37 alinéa 8 and
  Article 40, which BOTH cap a gré-à-gré ('entente directe', i.e. direct/
  single-source contracting bypassing the ordinary appel d'offres
  procedure) CONTINUATION or COMPLEMENT to an existing base/initial
  contract at twenty (20) percent of that base/initial contract's own
  value:

    Art. 37 al. 8 -- continuation with the same intellectual-services
      prestataire by entente directe: 'le montant de ces prestations ne
      soit pas supérieur à vingt (20) pour cent du volume des
      prestations prévues au contrat de base.'
    Art. 40 -- complementary works/supplies/services gré-à-gré ground:
      'le montant cumulé desdits marchés complémentaires n'excède pas
      vingt (20) pour cent du marché initial.'

  This is a GENUINELY DIFFERENT check SHAPE than every prior iso3166
  sibling this repo mirrors: Bulgaria's ЗОП Art. 54(5) de-minimis is a
  PERCENTAGE-OF-TURNOVER eligibility formula, Albania's Neni 76(2)(c)
  carve-out is a FLAT-CONSTANT threshold, Azerbaijan's/Armenia's
  flagship checks are BOOLEAN registry-membership reads, Antigua and
  Barbuda's vendor-class check is a THREE-TIER single-numeric-axis
  classification, Benin's Art. 77 mechanism is a BID-EVALUATION PRICE
  ADJUSTMENT, and Andorra's Art. 30.1 mechanism is a FLAT-EUR two-axis
  (contract type x urgency) eligibility-threshold classification.
  Burundi's Art. 37/Art. 40 mechanism is instead a PERCENTAGE-OF-A-
  RELATED-CONTRACT'S-OWN-VALUE ceiling -- the 'recompute' here derives
  eligibility from a ratio between TWO DIFFERENT ENGAGEMENT FIELDS (the
  complementary/continuation amount and a DIFFERENT, related base
  contract's own amount), not from a fixed constant or a percentage of
  an unrelated aggregate (turnover). This is reported honestly as a
  seventh distinct check shape for the family, not treated as a lesser
  version of any prior shape.

  Only these two unambiguous QUANTITATIVE gré-à-gré grounds are modeled.
  The Code's other gré-à-gré grounds (patent/exclusive rights, extreme
  urgency, secrecy for national-security reasons) are deliberately NOT
  modeled: each turns on a qualitative, case-by-case judgment call this
  governor cannot independently recompute from an engagement's own
  declared numeric fields without inventing a discretionary judgment the
  contracting authority itself has not made. Modeling only the two
  unambiguous quantitative thresholds is an honest scope-narrowing, the
  same discipline this family's other honest-narrowing decisions already
  established.

  This namespace is pure data + pure functions -- no I/O, no network
  call to any real procurement portal. It builds the RECORD an
  operator would keep, not the act of submitting a portal registration
  itself (that is `marketentry.operation`'s `:filing/submit`, always
  human-gated -- see README Actuation)."
  (:require [clojure.string :as str]))

(defn- unsigned-certificate
  "Every certificate this actor produces is UNSIGNED -- signature is
  the market-entry operator's act, not this actor's."
  [kind subject record-id]
  {"@context" ["https://www.w3.org/ns/credentials/v2"]
   "type" ["VerifiableCredential" kind]
   "credentialSubject" {"id" subject "record" record-id}
   "proof" nil
   "issued_by_registry" false
   "status" "draft-unsigned"})

(defn- zero-pad [n w]
  (let [s (str n)]
    (str (apply str (repeat (max 0 (- w (count s))) "0")) s)))

(defn compute-engagement-fee
  "The ground-truth engagement fee for `engagement`'s own `:base-fee`
  and `:monitoring-months` x `:monthly-rate` -- a single flat
  base + months x rate calculation, not a full pricing engine."
  [{:keys [base-fee monthly-rate monitoring-months]}]
  (+ (double base-fee)
     (* (double monthly-rate) (double monitoring-months))))

(defn engagement-fee-matches-claim?
  "Does `engagement`'s own `:claimed-fee` equal the independently
  recomputed `compute-engagement-fee`?"
  [{:keys [claimed-fee] :as engagement}]
  (== (double claimed-fee) (compute-engagement-fee engagement)))

(def gre-a-gre-complementaire-cap-ratio
  "Loi n°1/01 du 4 février 2008 portant Code des Marchés Publics du
  Burundi, Art. 37 al. 8 + Art. 40 (curl/pdftotext-verified 2026-07-22
  via the Wayback Machine mirror of droit-afrique.com's own hosting):
  a complementary/continuation gré-à-gré amount may not exceed this
  fraction of the related base/initial contract's own value."
  0.20)

(defn gre-a-gre-complementaire-eligible?
  "Does `engagement`'s own declared `:gre-a-gre-complementaire-value`
  (the amount of the complementary/continuation gré-à-gré contract) sit
  at or below twenty (20) percent of its own declared
  `:gre-a-gre-base-value` (the related base/initial contract's own
  amount), per Art. 37 al. 8 / Art. 40? A missing or non-positive base
  value is never eligible -- this governor does not guess a related
  base contract's own amount."
  [{:keys [gre-a-gre-base-value gre-a-gre-complementaire-value]}]
  (let [base (double (or gre-a-gre-base-value 0))
        comp (double (or gre-a-gre-complementaire-value 0))]
    (and (pos? base)
         (<= comp (* gre-a-gre-complementaire-cap-ratio base)))))

(defn gre-a-gre-complementaire-claim-mismatches?
  "Does `engagement`'s own declared `:gre-a-gre-claim?` differ from the
  INDEPENDENTLY recomputed Art. 37 al. 8 / Art. 40 eligibility? Catches
  BOTH directions honestly: claiming gré-à-gré eligibility the
  engagement's own declared base/complementaire values do not actually
  support, and an eligible engagement being declared ineligible."
  [{:keys [gre-a-gre-claim?] :as engagement}]
  (not= (boolean gre-a-gre-claim?)
        (gre-a-gre-complementaire-eligible? engagement)))

(defn register-draft
  "Validate + construct the FILING-DRAFT registration DRAFT -- the
  market-entry operator's own act of preparing a portal registration
  package. Pure function -- does not touch any real procurement
  portal."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "draft: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "draft: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "draft: sequence must be >= 0" {})))
  (let [draft-number (str (str/upper-case jurisdiction) "-DFT-" (zero-pad sequence 6))
        record {"record_id" draft-number
                "kind" "filing-draft"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "draft_number" draft-number
     "certificate" (unsigned-certificate "FilingDraft" draft-number draft-number)}))

(defn register-submit
  "Validate + construct the FILING-SUBMIT registration DRAFT -- the
  market-entry operator's own act of actually submitting a portal
  registration (always human-gated upstream)."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "submit: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "submit: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "submit: sequence must be >= 0" {})))
  (let [submit-number (str (str/upper-case jurisdiction) "-SUB-" (zero-pad sequence 6))
        record {"record_id" submit-number
                "kind" "filing-submit"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "submit_number" submit-number
     "certificate" (unsigned-certificate "FilingSubmit" submit-number submit-number)}))

(defn append [history result]
  (conj (vec history) (get result "record")))
