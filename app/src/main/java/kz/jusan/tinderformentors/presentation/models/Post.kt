package kz.jusan.tinderformentors.presentation.models

import androidx.annotation.DrawableRes

data class Post(
    val id: Int,
    @DrawableRes val avatar: Int,
    val authorName: String,
    val university: String,
    val message: String
)
