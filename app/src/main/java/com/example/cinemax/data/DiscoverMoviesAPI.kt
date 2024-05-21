package com.example.cinemax.data

import android.util.Log
import android.webkit.ConsoleMessage
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.Console
import java.net.HttpURLConnection
import java.net.URL

class DiscoverMoviesAPI(private val apiKey: String) {
    suspend fun getMovies(genreOptions: Map<String, Int>, releaseYear: String, sortBy: String, genreID: String): List<Movie>? {
        return withContext(Dispatchers.IO) {
            val url = URL(getURI(releaseYear, sortBy, genreID))
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("Authorization", "Bearer $apiKey")

            try {
                val response = connection.inputStream.bufferedReader().readText()
                val jsonResponse = JSONObject(response)
                val movieList = mutableListOf<Movie>()

                val results = jsonResponse.getJSONArray("results")
                for (i in 0 until results.length()) {
                    val item = results.getJSONObject(i)
                    val title = item.getString("title")
                    val overview = item.getString("overview")
                    val genres = item.getJSONArray("genre_ids").join(", ")
                    val releaseDate = item.getString("release_date")
                    val posterPath = "https://image.tmdb.org/t/p/w500" + item.getString("poster_path")
                    val popularity = item.getDouble("popularity")
                    val avgRating = item.getDouble("vote_average")
                    val noVotes = item.getInt("vote_count")

                    movieList.add(Movie(title, overview, genres, releaseDate, posterPath, popularity, avgRating, noVotes))
                }

                movieList
            } catch (e: Exception) {
                e.printStackTrace()
                null
            } finally {
                connection.disconnect()
            }
        }
    }

    private fun getURI(releaseYear: String, sortBy: String, genreID: String): String {
        return "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1" +
                "&primary_release_year=$releaseYear&sort_by=$sortBy&with_genres=$genreID"
    }
}