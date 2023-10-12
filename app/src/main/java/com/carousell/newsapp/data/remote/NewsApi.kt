package com.carousell.newsapp.data.remote

import com.carousell.newsapp.data.remote.model.NewsDto
import com.carousell.newsapp.data.remote.model.NewsResponse
import retrofit2.http.GET

interface NewsApi {

    @GET("android/carousell_news.json")
    suspend fun getNews(): List<NewsDto>
}