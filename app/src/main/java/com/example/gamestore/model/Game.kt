package com.example.gamestore.model

import java.io.Serializable

data class Game(
    val id: Int,
    val title: String,
    val genre: String,
    val description: String,
    val imageResId: Int,
    val items: List<GameItem>
) : Serializable
