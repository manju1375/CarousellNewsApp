package com.carousell.newsapp.data.remote.model

import com.carousell.newsapp.common.getDateElapsedString
import com.carousell.newsapp.domain.model.NewsItem
import com.google.gson.annotations.SerializedName


data class NewsDto(

    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("banner_url") val bannerUrl: String,
    @SerializedName("time_created") val timeCreated: Long,
    @SerializedName("rank") val rank: Int
)

fun NewsDto.toNewsItem(): NewsItem {
    return NewsItem(
        id = id,
        title = title,
        description = description,
        bannerUrl = bannerUrl,
        timeCreated = getDateElapsedString(timeCreated = timeCreated),
        rank = rank
    )
}

