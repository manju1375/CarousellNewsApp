package com.carousell.newsapp.presentation.news

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.carousell.newsapp.carousellnewsapp.R
import com.carousell.newsapp.carousellnewsapp.databinding.NewsFragmentBinding
import com.carousell.newsapp.common.Resource
import com.carousell.newsapp.domain.model.NewsItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : Fragment(), NewsAdapter.NewsItemListener {

    private lateinit var binding: NewsFragmentBinding
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = NewsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.news.collect {
                    when (it) {
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            it.data?.let { newsList -> renderList(newsList) }
                            binding.newsRv.visibility = View.VISIBLE
                        }
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.newsRv.visibility = View.GONE
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.errorTxt.visibility = View.VISIBLE
                            binding.errorTxt.text = it.uiText?.asString(requireContext()) ?: getString(R.string.error_unknown)
                        }
                    }
                }
            }
        }
    }

    private fun renderList(movies: List<NewsItem>) {
        newsAdapter.addData(movies)
    }

    private fun setupUI() {
        binding.newsRv.apply {
            layoutManager = LinearLayoutManager(
                context
            )
            setHasFixedSize(true)
        }
        newsAdapter = NewsAdapter(this, arrayListOf())
        binding.newsRv.adapter = newsAdapter
    }

    override fun onClickedItem(newsId: String) {
        Toast.makeText(activity, R.string.news_item_click_string,Toast.LENGTH_SHORT).show()
    }
}