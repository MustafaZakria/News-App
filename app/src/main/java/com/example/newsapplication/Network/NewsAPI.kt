package com.example.newsapplication.Network

import com.example.newsapplication.News.News
import com.example.newsapplication.News.NewsList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


//https://newsapi.org/v2/everything?q=Apple&from=2022-08-06&sortBy=popularity&apiKey=51f9199899f643f5a9cb4fb6a830bf22
interface NewsAPI {
    @GET("v2/everything?q=Apple&from=2022-08-06&sortBy=popularity&apiKey=51f9199899f643f5a9cb4fb6a830bf22")
    fun news(): Call<NewsList>
}

var retrofit = Retrofit.Builder()
    .baseUrl("https://newsapi.org")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(NewsAPI::class.java)