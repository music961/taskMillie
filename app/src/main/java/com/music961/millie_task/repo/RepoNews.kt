package com.music961.millie_task.repo

import com.music961.millie_task.model.ModelNews

interface RepoNews {
    fun getNews(
        country: String,
        apiKey: String,
        unit : (List<ModelNews>)->Unit
    )
}