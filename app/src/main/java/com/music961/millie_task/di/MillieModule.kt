package com.music961.millie_task.di

import com.music961.millie_task.repo.RepoNews
import com.music961.millie_task.repo.dummy.RepoNewsDummy
import com.music961.millie_task.retrofit.RetrofitNews
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MillieModule {

    @Singleton
    @Provides
    fun provideRepoNews() : RepoNews = RepoNewsDummy()
    
    @Singleton
    @Provides
    fun provideRetrofitNews() : RetrofitNews{
        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                //level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RetrofitNews::class.java)
    }
    
}