package kz.jusan.tinderformentors.domain.entitites

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("city")
    val city: String,
    @SerializedName("school")
    val school: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("university")
    val university: String,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,
    @SerializedName("iin")
    val iin: String
)