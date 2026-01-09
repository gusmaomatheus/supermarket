(ns supermarket.model.errors)

;; errors code:
;; E001 -> Without field
;; E002 -> Invalid type
;; E003 -> Invalid value format

(defn ->without-field-error [field]
  (format "E001: The '%s' field is required." (name field)))

(defn ->invalid-type-error [field type]
  (format "E002: The '%s' field must be of type '%s'." (name field) (name type)))

(defn ->invalid-value-error [field custom-message]
  (format "E003: The '%s' field must be %s." (name field)  custom-message))