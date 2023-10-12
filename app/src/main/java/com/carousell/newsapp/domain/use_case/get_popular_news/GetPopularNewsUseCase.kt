package com.carousell.newsapp.domain.use_case.get_popular_news

import com.carousell.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetPopularNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke() = repository.getPopularNews()
}