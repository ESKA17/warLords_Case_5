package kz.jusan.tinderformentors.presentation.models

import androidx.annotation.DrawableRes

data class Card(
    val id: Int,
    val name: String,
    val age: Int,
    @DrawableRes val avatar: Int
)