package com.music961.millie_task.core.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.music961.millie_task.core.model.EntityNews
import com.music961.millie_task.core.model.EntityNewsViewed
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGetNews(list : List<EntityNews>)

    @Query("select * from EntityNews")
    fun getEntityNews() : List<EntityNews>

    @Query("delete from EntityNews")
    fun clearEntityNews()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertViewedNewsTitle(entity : EntityNewsViewed)

    @Query("select exists(select * from EntityNewsViewed where title=:title)")
    fun haveViewedNews(title : String) : Boolean

    @Query("delete from EntityNewsViewed")
    fun clearEntityNewsViewed()

    @Query("select * from EntityNewsViewed where title in (:titles)")
    fun getFlowNewsViewed(titles: List<String>) : Flow<List<EntityNewsViewed>>
}