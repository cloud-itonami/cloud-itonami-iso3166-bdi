(ns statute.facts
  "General-law compliance catalog for Burundi (BDI) -- extends this repo's
  existing `marketentry.facts` (public-procurement market-entry only,
  narrow scope) with a second, orthogonal catalog of statutes a company
  operating in this jurisdiction must generally track for compliance.
  Mirrors cloud-itonami-iso3166-jpn/-deu/-bgr/-aze/-alb/-arm/-atg/-ben/-and's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-
  federation).

  Every entry cites an OFFICIAL government-hosted URL -- never fabricated.
  Burundi's government web presence is genuinely fragmented across several
  hostnames (armp.bi has been fully DEACTIVATED -- 'This website has been
  deactivated' -- superseded by armp.gov.bi; several primary-source PDFs
  at droit-afrique.com and assemblee.bi returned HTTP 403 to both WebFetch
  and direct curl and had to be retrieved via the Wayback Machine archive
  instead; the amategeko.gov.bi official-gazette PDFs and the
  arct.gov.bi-hosted 2026 data-protection law PDF are themselves scanned
  images with NO text layer and had to be OCR'd with tesseract, not
  pdftotext) -- so this iteration's confidence notes are more granular
  than several prior siblings':

  - Companies/commercial-entity law: this iteration specifically
    investigated, rather than assumed by the task's own initial framing,
    whether Burundi is an OHADA member state (as Benin is, with its
    company law governed directly by the OHADA Acte Uniforme, no domestic
    transposition act). It is NOT: OHADA's own official 'State Members'
    page (https://www.ohada.org/en/state-members/, WebFetch-read
    2026-07-22) lists seventeen member states -- Benin, Burkina Faso,
    Cameroon, Central African Republic, Comoros, Congo, Cote d'Ivoire,
    Gabon, Guinea, Guinea Bissau, Equatorial Guinea, Mali, Niger, RDC,
    Senegal, Chad, Togo -- and Burundi is NOT among them. Independently,
    OHADA's own news page confirms Burundi is only in the ACCESSION
    PROCESS, not yet a member: 'Towards Burundi's Accession to OHADA: an
    international symposium organised by OHADA in partnership with the
    Burundi Legal Lab officially opened on Tuesday, 26 May 2026, at the
    Donatus Conference Centre in Bujumbura' (WebSearch-corroborated,
    ohada.org). This is a genuinely different finding-DIRECTION than
    Benin's: Burundi's company law is instead its OWN domestic statute,
    Loi n°1/09 du 30 mai 2011 portant Code des Sociétés Privées et à
    Participation Publique (curl/pdftotext-verified directly 2026-07-22
    against investburundi.bi's own hosting -- the Agence de Développement
    du Burundi's own site -- Article 1: 'La Société est créée par un
    contrat réunissant deux ou plusieurs personnes qui conviennent de
    mettre en commun une partie de leurs biens et de leur industrie...',
    and repeated references throughout to registration at the 'Registre
    du Commerce et des Sociétés', Burundi's OWN domestic company
    registry -- textually and institutionally distinct from OHADA's RCCM
    (Registre du Commerce et du Crédit Mobilier), confirming this is not
    merely a renamed OHADA instrument). HIGH confidence. (An older,
    superseded predecessor, Loi n°1/002 du 6 mars 1996 portant Code des
    Sociétés Privées et Publiques, is still circulated under a
    near-identical filename on some secondary hosts -- e.g. cejp.bi's own
    PDF is actually the 1996 text despite a cover page titled 'CODE DES
    SOCIETES PRIVEES ET PUBLIQUES' -- this catalog was careful to verify
    the LOI N° actually printed inside the document body, not just the
    filename/cover, and cites the CURRENT 2011 law, not the 1996
    predecessor it revised.)
  - Code du Travail (Labour Code): Loi n°1/11 du 24 novembre 2020 portant
    Révision du Décret-loi n°1/037 du 07 juillet 1993 portant Code du
    Travail du Burundi. This iteration confirmed the exact citation twice
    independently: via NATLEX (the ILO's own official legislation
    database) and via a direct 'Vu' recital quoting the identical law
    name/number/date, read directly (pdftotext) from an UNRELATED but
    genuinely official 2021 statute published in the Bulletin Officiel du
    Burundi (BOB N°11/2021, amategeko.gov.bi's own hosting: 'Vu la Loi
    n°1/11 du 24 novembre 2020 portant Révision du Code du Travail du
    Burundi'). This iteration could NOT, despite five distinct attempts
    across five different hosts (droit-afrique.com direct + Wayback
    Machine mirror, rohnproctor.com, cosybu.bi), retrieve a readable copy
    of the law's OWN full article text (each attempt returned either an
    HTTP 403/404 or a truncated/corrupted PDF) -- so confidence is
    MODERATE-HIGH: the citation itself (title/number/date) is corroborated
    by two independent official-tier sources, but this catalog has not
    read the law's own substantive articles directly, unlike the
    procurement code, company code and data-protection law below.
  - Loi portant Protection des Données à Caractère Personnel (data
    protection): Loi n°1/03 du 10 mars 2026 portant Protection des
    Données à Caractère Personnel -- a GENUINELY NEW finding this
    iteration specifically checked for rather than assuming absent (the
    task's own framing flagged that 'some African jurisdictions do not
    yet have one in force'; Burundi, as of 2026-07-22, now DOES): fetched
    directly from arct.gov.bi's own hosting (the Agence de Régulation et
    de Contrôle des Télécommunications, which is merely HOSTING the
    text, not the law's own enforcing authority -- see below), a
    scanned-image PDF with no text layer, OCR'd page-by-page with
    tesseract (English model -- no French tessdata was available in this
    environment, so accented characters are occasionally misrecognised,
    but the article numbers, structure and institutional name below read
    unambiguously). Article 1 (OCR'd): 'La présente loi a pour objet de
    mettre en place un dispositif permettant de prévenir et lutter contre
    les atteintes à la vie privée susceptibles d'être engendrées par la
    collecte, l'analyse, le traitement, la transmission, le stockage et
    l'usage, la réutilisation et la suppression des données à caractère
    personnel.' Article 42 (Chapitre V, 'DE L'ORGANE CHARGE DE LA
    PROTECTION DES DONNEES A CARACTERE PERSONNEL', OCR'd): 'Un organe de
    protection des données à caractère personnel, dénommé « Agence de
    protection des données à caractère personnel », veille à
    l'application des dispositions de la présente loi.' HIGH confidence
    on the law's own existence, number, date and the Article 42
    institution-creation text (read directly from the official PDF, not
    a secondary summary), though the OCR-not-native-text-layer method is
    itself a materially different (lower-fidelity) verification path
    than pdftotext -layout on a native-text PDF, which is why this catalog
    flags the method explicitly rather than silently treating OCR'd text
    as equivalent to native extraction.

  A law not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries. `:statute/url` + `:statute/law-number`
  are the citation the governor requires before any compliance-fact
  proposal referencing this law can commit."
  {"BDI"
   [{:statute/id "bdi.code-societes-privees-participation-publique"
     :statute/title "Loi n°1/09 du 30 mai 2011 portant Code des Sociétés Privées et à Participation Publique"
     :statute/jurisdiction "BDI"
     :statute/kind :law
     :statute/law-number "Loi n°1/09 du 30 mai 2011"
     :statute/url "https://investburundi.bi/wp-content/uploads/2023/02/CODE-DES-SOCIETES-PUBLIQUES-ET-PRIVES.pdf"
     :statute/url-provenance :official-investburundi-bi
     :statute/enacted-date "2011-05-30"
     :statute/retrieved-at "2026-07-22"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "bdi.code-du-travail"
     :statute/title "Code du Travail du Burundi (as revised)"
     :statute/jurisdiction "BDI"
     :statute/kind :law
     :statute/law-number "Loi n°1/11 du 24 novembre 2020 portant Révision du Décret-loi n°1/037 du 07 juillet 1993 portant Code du Travail du Burundi"
     :statute/url "https://natlex.ilo.org/dyn/natlex2/r/natlex/fe/details?p3_isn=111173"
     :statute/url-provenance :secondary-ilo-natlex
     :statute/enacted-date "2020-11-24"
     :statute/retrieved-at "2026-07-22"
     :statute/topic #{:labor :employment}}
    {:statute/id "bdi.loi-protection-donnees-caractere-personnel"
     :statute/title "Loi n°1/03 du 10 mars 2026 portant Protection des Données à Caractère Personnel"
     :statute/jurisdiction "BDI"
     :statute/kind :law
     :statute/law-number "Loi n°1/03 du 10 mars 2026"
     :statute/url "https://arct.gov.bi/wp-content/uploads/2026/03/Loi-n%C2%B01_03-2026-protection-des-donnees-a-caractere-personnel_compressed.pdf"
     :statute/url-provenance :official-arct-gov-bi
     :statute/enacted-date "2026-03-10"
     :statute/retrieved-at "2026-07-22"
     :statute/topic #{:data-protection :privacy}}]})

(defn spec-basis
  "The jurisdiction's statute vector, or nil -- nil means NO spec-basis
  for that jurisdiction yet."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report, same shape/discipline as `marketentry.facts/coverage`:
  never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-bdi statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "BDI")) " BDI statutes seeded with an "
                 "official citation. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic
  "Statutes for `iso3` tagged with `topic` (e.g. :labor, :data-protection)."
  [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
