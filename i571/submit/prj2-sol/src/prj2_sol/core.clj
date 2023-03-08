(ns prj2-sol.core)

(defrecord OrderItem
  [ sku        ;keyword ID identifying item
    category   ;keyword giving item category
    n-units    ;# of units of item
    unit-price ;price per unit of item
  ])

;; #1: 15-points
;;given a list items of OrderItem, return the total for
;;the order, i.e. sum n-units*unit-price over all items
;;must be recursive but cannot use loop or recur
(defn items-total1 [items]
  (loop [remaining items
         total 0]
    (if (empty? remaining)
      total
      (recur (rest remaining)
             (+ total (* (:n-units (first remaining)) (:unit-price (first remaining))))))))




;; #2: 15-points
;;given a list items of OrderItem and a category,
;;return list of elements of items having specified category.
;;must be implemented using recursion
(defn items-with-category1 [items category]
  (if (empty? items)
    []
    (let [rest (items-with-category1 (rest items) category)]
      (if (= category (:category (first items)))
        (cons (first items) rest)
        rest))))



;; #3: 15-points
;;same specs as items-total1 but must be implemented using
;;loop and recur
(defn items-total2 [items]
  (apply + (map (fn [items] (* (:n-units items) (:unit-price items))) items)))



;; #4: 10-points
;;given a list items of OrderItem return a list of all the :sku's
;;cannot use explicit recursion
(defn item-skus [items]
  (reduce #(conj %1 (:sku %2)) [] items))


;; #5: 10-points
;;given a list items of OrderItem, return a list of skus of those elements
;;in items having unit-price greater than price
;;cannot use explicit recursion
(defn expensive-item-skus [items price]
  (->> items
       (filter #(> (:unit-price %) price))
       (map :sku)))



;; #6: 10-points
;;same specs as items-total1 but cannot use explicit recursion
(defn items-total3 [items]
  (loop [total 0
         rest-items items]
    (if (empty? rest-items)
      total
      (recur (+ total (* (:n-units (first rest-items)) (:unit-price (first rest-items))))
             (rest rest-items)))))



;; #7: 10-points
;;same spec as items-with-category1, but cannot use explicit recursion
(defn items-with-category2 [items category]
  (remove #(not= (:category %) category) items))



;; #8: 15-points 
;;given a list items of OrderItem and an optional category
;;return total of n-units of category in items.  If the
;;optional category is not specified, return total of n-units
;;of all items.
;;cannot use explicit recursion
(defn item-category-n-units
  "Return sum of :n-units of items for category (all categories if unspecified)"
  ([items]
   (reduce + (map :n-units items)))
  ([items category]
   (->> items
        (filter #(or (nil? category) (= category (:category %))))
        (map :n-units)
        (reduce +))))



