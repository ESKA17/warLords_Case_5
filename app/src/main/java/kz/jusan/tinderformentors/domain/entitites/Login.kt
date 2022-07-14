package kz.jusan.tinderformentors.domain.entitites

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)