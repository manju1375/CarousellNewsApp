package com.carousell.newsapp.presentation.news

import com.carousell.newsapp.common.Resource
import com.carousell.newsapp.core.viewmodel.BaseViewModel
import com.carousell.newsapp.domain.model.NewsItem
import com.carousell.newsapp.domain.use_case.get_popular_news.GetPopularNewsUseCase
import com.carousell.newsapp.domain.use_case.get_recent_news.GetRecentsNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getRecentsNewsUseCase: GetRecentsNewsUseCase,
    private val getPopularNewsUseCase: GetPopularNewsUseCase
) : BaseViewModel() {

    private val _recentNews = MutableStateFlow<Resource<List<NewsItem>>>(Resource.Loading())
    val recentNews = _recentNews.asStateFlow()

    private val _popularNews = MutableStateFlow<Resource<List<NewsItem>>>(Resource.Loading())
    val popularNews = _popularNews.asStateFlow()

    init {
        getRecentNews()
        getPopularNews()
    }

    private fun getRecentNews() {
        launchOnMain {
            getRecentsNewsUseCase().collect {
                _recentNews.value = it
            }
        }
    }

    private fun getPopularNews() {
        launchOnMain {
            getPopularNewsUseCase().collect {
                _popularNews.value = it
            }
        }
    }
}