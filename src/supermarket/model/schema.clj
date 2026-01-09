(ns supermarket.model.schema)

(def CustomerSchema
  [:map
   [:document :string]
   [:status [:enum "ACTIVATED" "DISABLED"]]
   [:type [:enum "COMMON" "SPECIAL"]]
   [:registration-date :string]])