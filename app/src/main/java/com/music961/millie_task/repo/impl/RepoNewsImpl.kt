package com.music961.millie_task.repo.impl

import android.content.Context
import android.widget.Toast
import com.music961.millie_task.core.util.retrofitEnqueue
import com.music961.millie_task.model.ModelNews
import com.music961.millie_task.repo.RepoNews
import com.music961.millie_task.retrofit.RetrofitNews
import javax.inject.Inject

class RepoNewsImpl @Inject constructor(
    private val context : Context,
    private val retrofitNews: RetrofitNews
) : RepoNews {
    override fun getNews(
        country: String,
        apiKey: String,
        unit : (List<ModelNews>)->Unit
    ) {
        retrofitNews.getNews( country, apiKey ).retrofitEnqueue(
            {
                Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                unit(it.articles)
            },
            {
                Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show()
                unit(getNewsFromLocal())
            }
        )
    }

    private fun getNewsFromLocal() : List<ModelNews>{
        // TODO : room 을 통한 데이터 획득
        return emptyList()
    }
}