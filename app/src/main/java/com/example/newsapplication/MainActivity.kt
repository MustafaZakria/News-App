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

        var news:Array<News> = getNews()
        val adapter = NewsAdapter(news)
        recyclerView.adapter = adapter

        requestNews()
    }

    private fun getNews(): Array<News> {
        val generator = WordGenerator()

        return Array(100) {
            val len = (10..50).random()
            val width = (100..150).random()
            val height = (100..150).random()

            News(generator.newWord(len), "https://picsum.photos/${width}/${height}", "https://picsum.photos/")
        }
    }

    private fun requestNews() {
        retrofit.news().enqueue(object : Callback<List<News>> {
            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                if (response.isSuccessful) {
                    Log.d("***", "onResponse ${response.body().toString()}")
                } else {
                    Log.d("***", "onResponse ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                Log.d("***", "onFailure ${t.localizedMessage} ")
            }
        })
    }

}