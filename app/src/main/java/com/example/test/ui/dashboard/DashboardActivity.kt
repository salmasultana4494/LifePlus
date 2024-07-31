package com.example.test.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.api.MovieRepository
import com.example.test.api.RetrofitInstance
import com.example.test.databinding.ActivityDashboardBinding
import com.example.test.models.RegistrationData
import com.example.test.ui.ProfileActivity
import com.example.test.ui.adapter.ShowAdapter

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var adapter: ShowAdapter
    private val movieRepository = MovieRepository(RetrofitInstance.api)
    private lateinit var viewModel: DashboardViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieRepository = MovieRepository(RetrofitInstance.api)
        val viewModelFactory = DashboardViewModelFactory(movieRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DashboardViewModel::class.java)

        val registrationData = intent.getParcelableExtra<RegistrationData>("USER_DATA")
        binding.movieListRv.layoutManager = LinearLayoutManager(this)
        val searchView = binding.searchView
        searchView.queryHint = "Search shows"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.progressBar.visibility = View.VISIBLE
                    viewModel.searchShows(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        binding.profileLogo.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("USER_DATA", registrationData)
            startActivity(intent)
        }
        viewModel.movies.observe(this, Observer { movies ->
            binding.emptyView.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
            adapter = ShowAdapter(movies, this)
            binding.movieListRv.adapter = adapter
        })

        viewModel.error.observe(this, Observer { errorMessage ->
            binding.progressBar.visibility = View.GONE
            binding.movieListRv.visibility = View.GONE
            binding.emptyView.visibility = View.VISIBLE
            Log.e("DashboardActivity", errorMessage)
        })
    }
}