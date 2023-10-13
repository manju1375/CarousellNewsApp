package com.carousell.newsapp.carousellnewsapp

import com.carousell.newsapp.common.Resource
import com.carousell.newsapp.common.UiText
import com.carousell.newsapp.domain.repository.NewsRepository
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

class GetRecentsNewsUseCaseTest {

    @MockK
    lateinit var repository: NewsRepository

    private lateinit var getRecentNewsUseCase: GetRecentsNewsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getRecentNewsUseCase = GetRecentsNewsUseCase(repository)
    }

    @Test
    fun `when repo returns success then resource should be success`() {
        mockSuccess()
        runBlocking {
            val recentsNewsUseCase = getRecentNewsUseCase()

            val eventCount = recentsNewsUseCase.count()
            assert(eventCount == 2)

            var resource = recentsNewsUseCase.first()
            assert(resource is Resource.Loading)

            resource = recentsNewsUseCase.last()
            assert(resource is Resource.Success)
            assert(resource.data != null)
            println(resource)
        }
    }

    @Test
    fun `when repo returns error then resource should be error`() {
        mockError()
        runBlocking {
            val recentsNewsUseCase = getRecentNewsUseCase()

            val eventCount = recentsNewsUseCase.count()
            assert(eventCount == 2)

            var resource = recentsNewsUseCase.first()
            assert(resource is Resource.Loading)

            resource = recentsNewsUseCase.last()
            assert(resource is Resource.Error)
            println(resource)
        }
    }

    private fun mockSuccess() {
        coEvery { repository.getRecentNews() } returns flow {
            emit(Resource.Loading())
            emit(Resource.Success(listOf())) }
    }

    private fun mockError() {
        coEvery { repository.getRecentNews() } returns flow {
            emit(Resource.Loading())
            emit(Resource.Error(UiText.unknownError()))
        }
    }
}