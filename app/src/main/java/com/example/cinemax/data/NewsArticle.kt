package com.example.cinemax.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
class NewsArticle(
    val title: String,
    val imageUrl: String,
    val articleUrl: String,
    var content: String = "",
    val date: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
) : Parcelable {
    constructor(title: String, imageUrl: String, articleUrl: String) : this(
        title = title,
        imageUrl = imageUrl,
        articleUrl = articleUrl,
        content = "",
        date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    )
}