package com.example.cinemax.data

class Movie {
    val title: String
    val overview: String
    val genres: String
    val releaseDate: String
    val posterPath: String
    val popularity: Double
    val avgRating: Double
    val noVotes: Int

    constructor(title: String, overview: String, genres: String, releaseDate: String, posterPath: String, popularity: Double, avgRating: Double, noVotes: Int) {
        this.title = title
        this.overview = overview
        this.genres = genres
        this.releaseDate = releaseDate
        this.posterPath = posterPath
        this.popularity = popularity
        this.avgRating = avgRating
        this.noVotes = noVotes
    }
}