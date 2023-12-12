package com.music961.millie_task.repo.dummy

import com.music961.millie_task.model.ModelNews
import com.music961.millie_task.repo.RepoNews

class RepoNewsDummy : RepoNews {
    override fun getNews() = listOf(
        ModelNews(
            "title 1",
            "urlToImage 1",
            "publishedAt 1"
        ),
        ModelNews(
            "title 2",
            "urlToImage 2",
            "publishedAt 2"
        ),
        ModelNews(
            "title 3",
            "urlToImage 3",
            "publishedAt 3"
        ),
        ModelNews(
            "title 4",
            "urlToImage 4",
            "publishedAt 4"
        )
    )

}