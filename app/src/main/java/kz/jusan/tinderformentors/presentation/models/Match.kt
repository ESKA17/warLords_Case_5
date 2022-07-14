package kz.jusan.tinderformentors.presentation.models

import androidx.annotation.DrawableRes

data class Match(
    val id: Int,
    @DrawableRes val avatar: Int,
    val authorName: String,
    val authorEmail: String,
    val university: String,
    val subjects: String
)
