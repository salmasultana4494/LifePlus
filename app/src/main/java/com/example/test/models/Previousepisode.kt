package com.example.test.models


import com.google.gson.annotations.SerializedName

data class Previousepisode(
    @SerializedName("href")
    var href: String? = "",
    @SerializedName("name")
    var name: String? = ""
)