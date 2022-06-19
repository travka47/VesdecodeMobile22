package com.example.vesdecode.BinAdapter

import com.example.vesdecode.models.Bin
import com.example.vesdecode.models.Parameter

class BinRepository {
    private val defaultItems = listOf(
        Bin(name = "Том Ям", price_current = 720, counter = 1, price_old = 800, image = "ic_dish"),
        Bin(name = "Бургер с кучей овощей\n" +
                "и чеддером", price_current = 480, counter = 1, price_old = 560, image = "ic_dish"),
        Bin(name = "Кусок пиццы с соусом песто\n" +
                "и оливками", price_current = 480, counter = 1, price_old = 560, image = "ic_dish"),
        Bin(name = "Ролл Сяки Маки", price_current = 480, counter = 1, price_old = 560, image = "ic_dish"),
    )

    fun getItems(): List<Bin> = defaultItems
}