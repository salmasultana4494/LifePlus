package com.example.test.models


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("average")
    var average: Double? = 0.0
)