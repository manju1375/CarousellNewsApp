package com.carousell.newsapp.domain.use_case.get_popular_movies

import com.carousell.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke() = repository.getNews()
}