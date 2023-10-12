package com.carousell.newsapp.domain.repository

import com.carousell.newsapp.common.Resource
import com.carousell.newsapp.domain.model.NewsItem
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(): Flow<Resource<List<NewsItem>>>
}