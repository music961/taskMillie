package com.music961.millie_task.repo.impl

import android.content.Context
import com.music961.millie_task.core.util.retrofitEnqueue
import com.music961.millie_task.core.model.EntityNews
import com.music961.millie_task.core.model.EntityNewsViewed
import com.music961.millie_task.repo.RepoNews
import com.music961.millie_task.core.retrofit.RetrofitNews
import com.music961.millie_task.core.room.NewsDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepoNewsImpl @Inject constructor(
    private val context : Context,
    private val retrofitNews: RetrofitNews,
    private val room : NewsDataBase
) : RepoNews {
    override fun getNews(
        country: String,
        apiKey: String,
        unit : (List<EntityNews>)->Unit
    ) {
        retrofitNews.getNews( country, apiKey ).retrofitEnqueue(
            {
                unit(it.articles)
                // 231213 Andy : keep news to local by room
                CoroutineScope(Dispatchers.IO).launch {
                    room.newsDao().clearEntityNews()
                    room.newsDao().insertGetNews(it.articles)
                }
            },
            {
                CoroutineScope(Dispatchers.IO).launch {
                    unit(room.newsDao().getEntityNews())
                }
            }
        )
    }

    override suspend fun keepNewsViewed(title: String) {
        room.newsDao().insertViewedNewsTitle(EntityNewsViewed(title))
    }

    override suspend fun haveNewsViewed(title: String) =
        room.newsDao().haveViewedNews(title)

    override suspend fun clearViewedHistory() {
        room.newsDao().clearEntityNewsViewed()
    }

}