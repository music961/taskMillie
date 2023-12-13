package com.music961.millie_task.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @param title use this as a primary key
 * @since 231213
 * @author Andy
*/
@Entity
data class EntityNewsViewed(
    @PrimaryKey
    val title : String
)
