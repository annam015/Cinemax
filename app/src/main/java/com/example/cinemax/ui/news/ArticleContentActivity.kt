package com.example.cinemax.ui.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cinemax.R
import com.example.cinemax.data.NewsArticle
import com.example.cinemax.databinding.ActivityArticleContentBinding
import com.bumptech.glide.Glide

class ArticleContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityArticleContentBinding = DataBindingUtil.setContentView(this, R.layout.activity_article_content)

        val article = intent.getParcelableExtra<NewsArticle>("article")
        binding.article = article

        article?.let {
            Glide.with(this).load(it.imageUrl).into(binding.articleImage)
            binding.articleTitle.text = it.title
            binding.articleContent.text = it.content
            binding.articleDate.text = it.date
        }
    }
}