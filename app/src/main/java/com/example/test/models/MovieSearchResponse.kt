package com.example.test.models


import com.google.gson.annotations.SerializedName

data class MovieSearchResponse(
    @SerializedName("score")
    var score: Double? = 0.0,
    @SerializedName("show")
    var show: Show? = Show()
)