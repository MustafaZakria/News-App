package com.example.newsapplication.News

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R

class NewsAdapter(private val news: Array<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val itemTitle: TextView
        private val itemImageURL: ImageView

        init {
            itemTitle = view.findViewById(R.id.itemText)
            itemImageURL = view.findViewById(R.id.itemImage)

            view.setOnClickListener {
                val pos: Int = adapterPosition
                ////////////////////////
            }
        }
    }
}

data class News(
    val title: String,
    val urlToImage: String,
    val url: String,
)