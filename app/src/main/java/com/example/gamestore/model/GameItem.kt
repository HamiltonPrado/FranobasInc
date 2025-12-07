package com.example.gamestore.model

import java.io.Serializable

data class GameItem(
    val id: Int,
    val name: String,
    val description: String,
    val priceUsd: Double,
    val imageResId: Int
) : Serializable
