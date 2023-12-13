package com.music961.millie_task.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityNews(
    @PrimaryKey
    val title : String,
    val url : String?,
    val urlToImage : String?,
    val publishedAt : String?
)
