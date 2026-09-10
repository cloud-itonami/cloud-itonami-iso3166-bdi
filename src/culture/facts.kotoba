(ns culture.facts
  "Country-level regional-culture catalog for Burundi (BDI) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"BDI"
   [{:culture/id "bdi.dish.ugali"
     :culture/name "Ugali"
     :culture/name-local "Bugali"
     :culture/country "BDI"
     :culture/kind :dish
     :culture/summary "Stiff maize- or cassava-flour porridge that is a staple across East and Central Africa, known in Burundi as bugali."
     :culture/url "https://en.wikipedia.org/wiki/Ugali"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "bdi.dish.ndagala"
     :culture/name "Ndagala"
     :culture/country "BDI"
     :culture/kind :dish
     :culture/summary "Small pelagic Clupeidae fish found only in Lake Tanganyika, called ndagala in Burundi, caught all year round and an important part of the local diet."
     :culture/url "https://en.wikipedia.org/wiki/Ndagala"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "bdi.dish.ibiharage"
     :culture/name "Ibiharage"
     :culture/country "BDI"
     :culture/kind :dish
     :culture/summary "Fried beans, a typical dish of Burundian cuisine, in which beans are the staple of Burundi cooking."
     :culture/url "https://en.wikipedia.org/wiki/Burundian_cuisine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "bdi.beverage.urwagwa"
     :culture/name "Banana beer"
     :culture/name-local "Urwagwa"
     :culture/country "BDI"
     :culture/kind :beverage
     :culture/summary "Alcoholic beverage fermented from mashed bananas with a sorghum, millet or maize flour yeast source, known in Rwanda and Burundi as urwagwa."
     :culture/url "https://en.wikipedia.org/wiki/Banana_beer"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "bdi.craft.karyenda"
     :culture/name "Karyenda"
     :culture/country "BDI"
     :culture/kind :craft
     :culture/summary "Traditional African drum that served as the main symbol of Burundi and its monarchy, holding semi-divine status."
     :culture/url "https://en.wikipedia.org/wiki/Karyenda"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "bdi.heritage.gishora"
     :culture/name "Gishora"
     :culture/country "BDI"
     :culture/kind :heritage
     :culture/summary "Early 19th-century royal palace site north of Gitega sheltering the sanctuary of the sacred drums, added to UNESCO's World Heritage Tentative List in 2007."
     :culture/url "https://en.wikipedia.org/wiki/Gishora"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-bdi culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "BDI"))
                 " BDI entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
