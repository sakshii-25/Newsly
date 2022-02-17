package com.example.newsly

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val Base_URL="https://newsapi.org/"
const val API_KEY="7ec5193482104df3b3bd33db90a334c2"


interface NewsInterface{

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country")country: String,@Query("page") page:Int) : Call<News>

    // https://newsapi.org/v2/top-headlines?apiKey=7ec5193482104df3b3bd33db90a334c2&country=in&page=1
}

object NewsService{
    val newsInstance:NewsInterface
    init {
        val retrofit  = Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}