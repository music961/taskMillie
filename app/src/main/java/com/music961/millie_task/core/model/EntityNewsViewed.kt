package com.music961.millie_task.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityNewsViewed(
    @PrimaryKey
    val title : String
)
