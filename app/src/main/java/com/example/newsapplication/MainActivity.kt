package com.example.newsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.News.News
import com.example.newsapplication.News.NewsAdapter
import com.maximeroussy.invitrode.WordGenerator
import android.util.Log
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.newsapplication.R
import com.example.newsapplication.Network.retrofit
import com.example.newsapplication.News.NewsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        var news:Array<News>? = requestNews()
        val adapter = NewsAdapter(news)
        recyclerView.adapter = adapter
    }

    private fun requestNews() : Array<News>? {
        var news:Array<News>? = null
        retrofit.news().enqueue(object : Callback<NewsList> {
            override fun onResponse(call: Call<NewsList>, response: Response<NewsList>) {
                if (response.isSuccessful) {
                    news = response.body()?.articles?.toTypedArray()
                    Log.d("*****++", "onResponse ${news?.get(0).toString()}") ///
                } else {
                    Log.d("***", "onResponse ${response.code()}")
                }
            }

            override fun onFailure(call: Call<NewsList>, t: Throwable) {
                Log.d("***", "onFailure ${t.localizedMessage} ")
            }
        })
        Log.d("*****..", "onResponse ${news?.get(0).toString()}")  ///
        return news
    }

}