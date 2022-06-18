package com.example.vesdecode.CategoryAdapter

class CategoryRepository {
    private val defaultItems = listOf(
        Category (name = "Роллы"),
        Category (name = "Суши"),
        Category (name = "Наборы"),
        Category (name = "Горячие блюда"),
    )

    fun getItems(): List<Category> = defaultItems
}