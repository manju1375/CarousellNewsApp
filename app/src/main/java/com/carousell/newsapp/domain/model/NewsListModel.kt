package com.carousell.newsapp.domain.model

import com.carousell.newsapp.data.remote.model.NewsDto

data class NewsListModel(
    val newsListItem: List<NewsDto>
)


fun NewsListModel.toRecentNewsList():List<NewsDto>{
    return newsListItem.sortedByDescending {
        it.timeCreated
    }
}

fun NewsListModel.toPopularNewsList():List<NewsDto>{
    /*return newsListItem.sortedBy {
        it.rank
    }*/
    return newsListItem.sortedWith(compareBy({ it.rank },{ -it.timeCreated } ))
}