package com.example.newsapplication.News

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.R
import com.google.gson.annotations.SerializedName


class NewsAdapter(private val news: List<News>?, val mContext: Context, val recyclerView: RecyclerView?) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = news?.get(position)

        holder.itemTitle.text = news?.title

        Glide.with(holder.itemView).load(news?.urlToImage).into(holder.itemImageURL)

    }

    override fun getItemCount(): Int {
        return news?.size ?: 0
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         val itemTitle: TextView
         val itemImageURL: ImageView

        init {
            itemTitle = view.findViewById(R.id.itemText)
            itemImageURL = view.findViewById(R.id.itemImage)

            view.setOnClickListener {
                val pos: Int = adapterPosition
                val url = news?.get(pos)?.url

                val text:String? = news?.get(pos)?.title


                mContext.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

                matches(recyclerView, text)
                //startActivity(browserIntent)

            }
        }
    }

    public fun matches(recyclerView: RecyclerView?, text:String?) {
        val itemCount = recyclerView?.adapter?.itemCount
        for (i in 0 until itemCount!!) {
            val holder = recyclerView?.findViewHolderForAdapterPosition(i)
            if (holder != null) {
                val tvHolder = holder.itemView.findViewById<View>(R.id.itemText) as TextView
                if (tvHolder.text == text) {
                    tvHolder.setTextColor(Color.parseColor("#CC33CC"))
                }
            }
        }
    }
}

data class News(
    val title: String,
    val urlToImage: String? = null,
    val url: String,
)

data class NewsList (
    @SerializedName("articles")
    val articles: List<News>? = null
)