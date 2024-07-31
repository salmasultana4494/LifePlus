package com.example.test.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.api.MovieRepository
import com.example.test.models.MovieSearchResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieSearchResponse>>()
    val movies: LiveData<List<MovieSearchResponse>> get() = _movies

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun searchShows(query: String) {
        viewModelScope.launch {
            repository.searchShows(query).enqueue(object : Callback<List<MovieSearchResponse>> {
                override fun onResponse(call: Call<List<MovieSearchResponse>>, response: Response<List<MovieSearchResponse>>) {
                    if (response.isSuccessful) {
                        _movies.value = response.body()
                    } else {
                        _error.value = "Failed to fetch data"
                    }
                }

                override fun onFailure(call: Call<List<MovieSearchResponse>>, t: Throwable) {
                    _error.value = t.message
                }
            })
        }
    }
}