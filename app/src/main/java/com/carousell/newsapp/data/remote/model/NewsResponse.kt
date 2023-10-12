package com.carousell.newsapp.data.remote.model

import com.carousell.newsapp.domain.model.NewsItem

data class NewsResponse(
val newsList: List<NewsDto>
)
