package com.example.test.models


import com.google.gson.annotations.SerializedName

data class Self(
    @SerializedName("href")
    var href: String? = ""
)