package com.carousell.newsapp.domain.model

data class NewsItem(
    val id:String,
    val title:String,
    val description:String,
    val bannerUrl:String,
    val timeCreated:Long
)
