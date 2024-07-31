package com.example.test.api

import com.example.test.models.MovieSearchResponse
import retrofit2.Call

class MovieRepository(private val apiService: ApiService) {
    fun searchShows(query: String): Call<List<MovieSearchResponse>> {
        return apiService.searchShows(query)
    }
}