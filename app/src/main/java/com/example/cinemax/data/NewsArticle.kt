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
) : Parcelable {
    constructor(title: String, imageUrl: String, articleUrl: String) : this(
        title = title,
        imageUrl = imageUrl,
        articleUrl = articleUrl,
        content = ""
    )
}