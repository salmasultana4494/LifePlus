package com.example.test.models


import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("days")
    var days: List<String?>? = listOf(),
    @SerializedName("time")
    var time: String? = ""
)