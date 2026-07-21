# cloud-itonami-iso3166-bdi

**BDI**: Burundi.

- ARMP / DNCMP / CGMP (three-way passation/contrôle/régulation split)
- Registre du Commerce et des Sociétés (RCS, domestic -- Burundi is NOT
  an OHADA member) + NIF (OBR)

AGPL-3.0-or-later.

## Market-entry / statute catalogs

Governed public-sector market-entry compliance actor, same architecture
as `cloud-itonami-iso3166-jpn`/`-deu`/`-ben`/`-atg`/`-and`:

- `src/marketentry/{facts,governor,phase,sim,operation,registry,store,
  marketentryllm}.cljc` -- the actor. `facts.cljc` cites the Code des
  Marchés Publics (Loi n°1/01 du 4 février 2008, as amended by Loi
  n°1/04 du 29 janvier 2018), whose own Article 6 splits procurement
  into THREE organs, not two: the Cellule de Gestion des Marchés Publics
  (CGMP, passation, at each Autorité Contractante), the Direction
  Nationale de Contrôle des Marchés Publics (DNCMP, contrôle a priori/a
  posteriori) and the Autorité de Régulation des Marchés Publics (ARMP,
  policy-level régulation, dispute resolution, sanctions) -- a richer
  shape than Benin's two-body ARMP-regulates/DNCMP-operates split, and
  no separate e-procurement portal-operator body was found (unlike
  Benin's SIGMAP/DNCMP pairing). Business registration is legally two
  separate acts (RCS company registration under Loi n°1/09 du 30 mai
  2011 portant Code des Sociétés Privées et à Participation Publique --
  a DOMESTIC statute, since Burundi is confirmed NOT an OHADA member
  state, unlike Benin -- + NIF issuance by the Office Burundais des
  Recettes), bundled since 2021 into ONE single-window intake operated
  by the Agence de Développement du Burundi (ADB, easybusiness.bi) that
  issues both numbers on a single combined Certificat d'immatriculation.
  `governor.cljc`'s flagship check independently recomputes Code des
  Marchés Publics Art. 37 al. 8 / Art. 40's twenty-percent-of-base-
  contract ceiling for complementary/continuation gré-à-gré (direct/
  single-source) contracting.
- `src/statute/facts.cljc` -- general-law catalog: the domestic Code des
  Sociétés Privées et à Participation Publique (Loi n°1/09/2011), the
  Code du Travail (as revised by Loi n°1/11 du 24 novembre 2020), and
  the brand-new Loi n°1/03 du 10 mars 2026 portant Protection des
  Données à Caractère Personnel (creates the Agence de protection des
  données à caractère personnel, Art. 42).

Every citation is curl/pdftotext (or, for two scanned-image-only
official PDFs, tesseract-OCR) verified against an official or
official-tier source (droit-afrique.com via the Wayback Machine,
investburundi.bi, arct.gov.bi, ohada.org, amategeko.gov.bi); see each
namespace's docstring for the full research trail, method caveats and
any honestly-narrowed scope.

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Burundi:

- `src/culture/facts.cljc` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
