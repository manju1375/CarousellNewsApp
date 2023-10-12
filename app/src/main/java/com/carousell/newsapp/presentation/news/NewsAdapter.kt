package com.carousell.newsapp.presentation.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carousell.newsapp.carousellnewsapp.databinding.ItemNewsBinding
import com.carousell.newsapp.domain.model.NewsItem

class NewsAdapter(
    private val listener: NewsItemListener,
    private val newsList: ArrayList<NewsItem>
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    interface NewsItemListener {
        fun onClickedItem(newsId: String)
    }

    class NewsViewHolder(
        private val binding: ItemNewsBinding,
        private val listener: NewsItemListener
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var newsItem: NewsItem

        init {
            binding.newsBanner.setOnClickListener(this)
        }

        fun bind(newsItem: NewsItem) {
            this.newsItem = newsItem
            val bannerUrl = newsItem.bannerUrl
            Glide.with(binding.root).load(bannerUrl).into(binding.newsBanner)
        }

        override fun onClick(v: View?) {
            newsItem.id.let { listener.onClickedItem(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) =
        holder.bind(newsList[position])

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<NewsItem>) {
        newsList.addAll(list)
        notifyDataSetChanged()
    }
}