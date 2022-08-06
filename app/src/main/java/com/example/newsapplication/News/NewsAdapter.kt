package com.example.newsapplication.News

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.R


class NewsAdapter(private val news: Array<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = news[position]

        holder.itemTitle.text = news.title

        Glide.with(holder.itemView).load(news.urlToImage).into(holder.itemImageURL)

    }

    override fun getItemCount(): Int {
        return news.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         val itemTitle: TextView
         val itemImageURL: ImageView

        init {
            itemTitle = view.findViewById(R.id.itemText)
            itemImageURL = view.findViewById(R.id.itemImage)

            view.setOnClickListener {
                val pos: Int = adapterPosition
                val url = news[pos].url

                //val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                //startActivity(browserIntent)

            }
        }
    }
}

data class News(
    val title: String,
    val urlToImage: String,
    val url: String,
)