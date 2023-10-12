package com.carousell.newsapp.domain.model

import com.carousell.newsapp.data.remote.model.NewsDto

data class NewsListModel(
    val newsListItem: List<NewsItem>
)


fun NewsListModel.toRecentNewsList():List<NewsItem>{
    return newsListItem.sortedByDescending {
        it.timeCreated
    }
}

fun NewsListModel.toPopularNewsList():List<NewsItem>{
    return newsListItem.sortedBy {
        it.rank
    }
}