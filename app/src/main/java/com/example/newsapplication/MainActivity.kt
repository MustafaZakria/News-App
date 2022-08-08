package com.example.newsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.News.News
import com.example.newsapplication.News.NewsAdapter
import com.maximeroussy.invitrode.WordGenerator
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.newsapplication.R
import com.example.newsapplication.Network.retrofit
import com.example.newsapplication.News.NewsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var news:List<News>? = null
    var adapter:RecyclerView.Adapter<NewsAdapter.ViewHolder>? = null
    var recyclerView:RecyclerView? = null
    var layoutManager:RecyclerView.LayoutManager? = null
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        layoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = layoutManager

        requestNews()
        Log.d("===", "onResponse ")
    }

    private fun randomWords(): Array<News> {
        val generator = WordGenerator()

        return Array(100) {
            val len = (10..50).random()
            val width = (100..150).random()
            val height = (100..150).random()

            News(generator.newWord(len), "https://picsum.photos/${width}/${height}", "https://picsum.photos/${width}/${height}")
        }
    }

    fun requestNews()  {

        retrofit.news().enqueue(object : Callback<NewsList> {
            override fun onResponse(call: Call<NewsList>, response: Response<NewsList>) {
                if (response.isSuccessful) {
                    news = response.body()?.articles
                    Log.d("****", "onResponse ${response.body()?.articles.toString()}")
                    adapter = NewsAdapter(news, this@MainActivity, recyclerView)
                    recyclerView?.adapter = adapter

                } else {
                    Log.d("***", "onResponse ${response.code()}")
                }
            }

            override fun onFailure(call: Call<NewsList>, t: Throwable) {
                Log.d("***", "onFailure ${t.localizedMessage} ")
            }
        })
        //return news
    }

}