package com.example.youtube


class Model(val articles: List<Articles>)

class Articles(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String
)