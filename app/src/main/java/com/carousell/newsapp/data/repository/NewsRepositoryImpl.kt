package com.carousell.newsapp.data.repository

import com.carousell.newsapp.carousellnewsapp.R
import com.carousell.newsapp.common.Resource
import com.carousell.newsapp.common.UiText
import com.carousell.newsapp.data.remote.NewsApi
import com.carousell.newsapp.data.remote.model.NewsDto
import com.carousell.newsapp.data.remote.model.toNewsItem
import com.carousell.newsapp.domain.model.NewsItem
import com.carousell.newsapp.domain.model.NewsListModel
import com.carousell.newsapp.domain.model.toPopularNewsList
import com.carousell.newsapp.domain.model.toRecentNewsList

import com.carousell.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val api: NewsApi) : NewsRepository {

    suspend fun getNews(): List<NewsDto> {
        return api.getNews()
    }

    override suspend fun getRecentNews(): Flow<Resource<List<NewsItem>>> = flow {
        try {
            emit(Resource.Loading())
            val list = getNews()
            val recentLists = NewsListModel(list).toRecentNewsList().map { it.toNewsItem() }
            emit(Resource.Success(recentLists))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    UiText.StringResource(R.string.error_couldnt_reach_server)
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error(UiText.StringResource(R.string.oops_something_went_wrong)))
        }
    }

    override suspend fun getPopularNews(): Flow<Resource<List<NewsItem>>> = flow {
        try {
            emit(Resource.Loading())
            val list = getNews()
            val popularNewsList = NewsListModel(list).toPopularNewsList().map { it.toNewsItem() }
            emit(Resource.Success(popularNewsList))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    UiText.StringResource(R.string.error_couldnt_reach_server)
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error(UiText.StringResource(R.string.oops_something_went_wrong)))
        }
    }


}