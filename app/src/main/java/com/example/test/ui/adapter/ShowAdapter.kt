package com.example.test.ui.adapter

import android.app.AlertDialog
import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.databinding.ItemMovieBinding
import com.example.test.databinding.MovieDetailsDialogBinding
import com.example.test.models.MovieSearchResponse
import com.example.test.models.Show

class ShowAdapter(private val shows: List<MovieSearchResponse>, private val context: Context) : RecyclerView.Adapter<ShowAdapter.ShowViewHolder>() {

    inner class ShowViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val show = shows[position].show
        holder.binding.movieName.text = show?.name
        holder.binding.rating.text = show?.rating?.average.toString()
        Glide.with(holder.itemView.context)
            .load(show?.image?.medium)
            .into(holder.binding.banner)
        holder.itemView.setOnClickListener {
            showMovieDetailsDialog(show)
        }
    }

    override fun getItemCount() = shows.size
    private fun showMovieDetailsDialog(show: Show?) {
        val dialogBinding = MovieDetailsDialogBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .create()

        dialogBinding.movieName.text = show?.name
        dialogBinding.rating.text = show?.rating?.average.toString()
        dialogBinding.summary.text = Html.fromHtml(show?.summary, Html.FROM_HTML_MODE_LEGACY)
        Glide.with(context)
            .load(show?.image?.medium)
            .into(dialogBinding.banner)

        dialog.show()
    }
}
