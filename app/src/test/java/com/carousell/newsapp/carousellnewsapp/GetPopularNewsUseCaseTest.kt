package com.carousell.newsapp.carousellnewsapp

import com.carousell.newsapp.common.Resource
import com.carousell.newsapp.common.UiText
import com.carousell.newsapp.domain.repository.NewsRepository
import com.carousell.newsapp.domain.use_case.get_popular_news.GetPopularNewsUseCase
import com.carousell.newsapp.domain.use_case.get_recent_news.GetRecentsNewsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPopularNewsUseCaseTest {

    @MockK
    lateinit var repository: NewsRepository

    private lateinit var getPopularNewsUseCase: GetPopularNewsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getPopularNewsUseCase = GetPopularNewsUseCase(repository)
    }

    @Test
    fun `when repo returns success then resource should be success`() {
        mockSuccess()
        runBlocking {
            val popularNewsUseCase = getPopularNewsUseCase()

            val eventCount = popularNewsUseCase.count()
            assert(eventCount == 2)

            var resource = popularNewsUseCase.first()
            assert(resource is Resource.Loading)

            resource = popularNewsUseCase.last()
            assert(resource is Resource.Success)
            assert(resource.data != null)
            println(resource)
        }
    }

    @Test
    fun `when repo returns error then resource should be error`() {
        mockError()
        runBlocking {
            val popularNewsUseCase = getPopularNewsUseCase()

            val eventCount = popularNewsUseCase.count()
            assert(eventCount == 2)

            var resource = popularNewsUseCase.first()
            assert(resource is Resource.Loading)

            resource = popularNewsUseCase.last()
            assert(resource is Resource.Error)
            println(resource)
        }
    }

    private fun mockSuccess() {
        coEvery { repository.getPopularNews() } returns flow {
            emit(Resource.Loading())
            emit(Resource.Success(listOf())) }
    }

    private fun mockError() {
        coEvery { repository.getPopularNews() } returns flow {
            emit(Resource.Loading())
            emit(Resource.Error(UiText.unknownError()))
        }
    }
}