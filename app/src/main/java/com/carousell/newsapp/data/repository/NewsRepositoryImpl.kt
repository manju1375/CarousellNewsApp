package com.carousell.newsapp.data.repository

import com.carousell.newsapp.carousellnewsapp.R
import com.carousell.newsapp.common.Resource
import com.carousell.newsapp.common.UiText
import com.carousell.newsapp.data.remote.NewsApi
import com.carousell.newsapp.data.remote.model.toNewsItem
import com.carousell.newsapp.domain.model.NewsItem

import com.carousell.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val api: NewsApi) : NewsRepository {

    override suspend fun getNews(): Flow<Resource<List<NewsItem>>> = flow {
        try {
            emit(Resource.Loading())
            val list = api.getNews().map { it.toNewsItem() }
            emit(Resource.Success(list))
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