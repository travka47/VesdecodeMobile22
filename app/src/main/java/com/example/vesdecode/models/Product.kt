package com.example.vesdecode.models

data class Product (
    var id: Int? = null,
    var category_id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    var image: String? = null,
    var price_current: Int? = null,
    var price_old: Int? = null,
    var measure: Int? = null,
    var measure_unit: String? = null,
    var energy_per_100_grams: Double? = null,
    var proteins_per_100_grams: Double? = null,
    var fats_per_100_grams: Double? = null,
    var carbohydrates_per_100_grams: Double? = null,
    var tag_ids: MutableList<Int>? = mutableListOf<Int>()
)