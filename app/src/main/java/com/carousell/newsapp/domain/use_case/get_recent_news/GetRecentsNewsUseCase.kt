package com.carousell.newsapp.domain.use_case.get_recent_news

import com.carousell.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetRecentsNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke() = repository.getRecentNews()
}