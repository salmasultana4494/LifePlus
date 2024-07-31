package com.example.test.api

import com.example.test.models.MovieSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/search/shows")
    fun searchShows(@Query("q") query: String): Call<List<MovieSearchResponse>>
}