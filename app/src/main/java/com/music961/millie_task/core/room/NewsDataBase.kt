package com.music961.millie_task.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.music961.millie_task.core.model.EntityNews
import com.music961.millie_task.core.model.EntityNewsViewed

@Database(
    entities = [
        EntityNews::class,
        EntityNewsViewed::class
    ],
    version = 1
)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}