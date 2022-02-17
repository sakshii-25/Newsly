package com.example.newsly

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.littlemango.stacklayoutmanager.StackLayoutManager




class MainActivity : AppCompatActivity() {

    lateinit var adapter: NewsAdapter
    lateinit var  newsList:RecyclerView
    private var articles= mutableListOf<Articles>() //*
    var pageNum=1           //*

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNews()
        newsList=findViewById<RecyclerView>(R.id.newsList)
        adapter= NewsAdapter(this@MainActivity, articles)
        newsList.adapter = adapter
        //newsList.layoutManager = LinearLayoutManager(this@MainActivity)

        val layoutManager=StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)   //*//
        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(3000)
        newsList.layoutManager=layoutManager

        getNews()
    }



    private fun getNews(){
        val news = NewsService.newsInstance.getHeadlines("in",pageNum)
        news.enqueue(object : Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
            val news =response.body()
                if(news!=null){

                    Log.d("TopNEWS",news.toString())
                    articles.addAll(news.articles)  //*
                    adapter.notifyDataSetChanged()  //*

                    /*adapter= NewsAdapter(this@MainActivity, news.articles)  {add this here if not adding mutablelist}
                    newsList.adapter = adapter
                    newsList.layoutManager = LinearLayoutManager(this@MainActivity)*/


                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("TopNEWS","Error in fetching News!",t)


            }

        })
    }
}