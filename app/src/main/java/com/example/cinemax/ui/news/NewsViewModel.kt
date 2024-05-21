package com.example.cinemax.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemax.data.NewsArticle
import com.example.cinemax.data.NewsWebScraping
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val _articles = MutableLiveData<List<NewsArticle>>()
    val articles: LiveData<List<NewsArticle>> = _articles

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            val articlesList = NewsWebScraping().getArticles()
            _articles.postValue(articlesList)
        }
    }
}


//package com.example.cinemax.ui.news
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//
//class NewsViewModel : ViewModel() {
//
//    private val _text = MutableLiveData<String>().apply {
//        value = "This is News Fragment"
//    }
//    val text: LiveData<String> = _text
//}
//
//
