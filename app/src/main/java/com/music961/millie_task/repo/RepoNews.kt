package com.music961.millie_task.repo

import com.music961.millie_task.core.model.EntityNews

interface RepoNews {
    fun getNews(
        country: String,
        apiKey: String,
        unit : (List<EntityNews>)->Unit
    )
}