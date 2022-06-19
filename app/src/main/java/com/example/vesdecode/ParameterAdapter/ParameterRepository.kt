package com.example.vesdecode.ParameterAdapter

import com.example.vesdecode.models.Parameter

class ParameterRepository {
    private val defaultItems = listOf(
        Parameter(name = "Вес", value = "0 г"),
        Parameter(name = "Энерг. ценность", value = "0 ккал"),
        Parameter(name = "Белки", value = "0 г"),
        Parameter(name = "Жиры", value = "0 г"),
        Parameter(name = "Углеводы", value = "0 г"),
    )

    fun getItems(): List<Parameter> = defaultItems
}