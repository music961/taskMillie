package com.music961.millie_task.core.retrofit

import com.music961.millie_task.core.model.ModelNewsCase
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitNews {
    @GET("v2/top-headlines")
    fun getNews(
        @Query("country") country : String,
        @Query("apiKey") apiKey : String
    ) : Call<ModelNewsCase>
}