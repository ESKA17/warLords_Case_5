package kz.jusan.tinderformentors.domain.entitites

import com.google.gson.annotations.SerializedName

data class Register(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("role")
    val role: Int, // 0: admin, 1: mentor, 2: mentee
    @SerializedName("fullName")
    val fullName: String
)