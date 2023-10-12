package com.carousell.newsapp.presentation.news

import com.carousell.newsapp.common.Resource
import com.carousell.newsapp.core.dispatchers.Dispatcher
import com.carousell.newsapp.core.viewmodel.BaseViewModel
import com.carousell.newsapp.domain.model.NewsItem
import com.carousell.newsapp.domain.use_case.get_popular_movies.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : BaseViewModel() {

    private val _news = MutableStateFlow<Resource<List<NewsItem>>>(Resource.Loading())
    val news = _news.asStateFlow()

    init {
        getNews()
    }

    private fun getNews() {
        launchOnMain {
            getNewsUseCase().collect {
                _news.value = it
            }
        }
    }
}