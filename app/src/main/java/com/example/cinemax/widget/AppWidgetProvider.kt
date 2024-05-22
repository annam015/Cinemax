package com.example.cinemax.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.AppWidgetTarget
import com.example.cinemax.R
import com.example.cinemax.data.NewsArticle
import com.example.cinemax.data.NewsWebScraping
import com.example.cinemax.ui.news.ArticleContentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        CoroutineScope(Dispatchers.IO).launch {
            val articles = NewsWebScraping().getArticles()
            if (!articles.isNullOrEmpty()) {
                val article = articles[0] // Afișează primul articol
                for (appWidgetId in appWidgetIds) {
                    updateAppWidget(context, appWidgetManager, appWidgetId, article)
                }
            }
        }
    }

    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int, article: NewsArticle) {
        val views = RemoteViews(context.packageName, R.layout.widget_news)
        views.setTextViewText(R.id.news_title, article.title)

        // Adăugăm un AppWidgetTarget pentru a încărca imaginea în widget folosind Glide
        val appWidgetTarget = AppWidgetTarget(context, R.id.news_image, views, appWidgetId)
        Glide.with(context.applicationContext)
            .asBitmap()
            .load(article.imageUrl)
            .into(appWidgetTarget)

        // Adăugăm un intent pentru a deschide activitatea de știri când se apasă pe widget
        val intent = Intent(context, ArticleContentActivity::class.java).apply {
            putExtra("article", article)
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        views.setOnClickPendingIntent(R.id.news_image, pendingIntent)

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}