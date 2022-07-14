package kz.jusan.tinderformentors.domain.entitites

import com.google.gson.annotations.SerializedName

data class Majors(
    // ("0", "Math"),
    // ("1", "Physics"),
    // ("2", "Chemistry"),
    // ("3", "Biology"),
    // ("4", "Informatics"),
    // ("5", "History")
    @SerializedName("majors")
    val majors: List<Int>
)