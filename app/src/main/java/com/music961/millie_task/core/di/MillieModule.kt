package com.music961.millie_task.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.music961.millie_task.BaseApplication
import com.music961.millie_task.core.enum.MillieUrl
import com.music961.millie_task.repo.RepoNews
import com.music961.millie_task.repo.impl.RepoNewsImpl
import com.music961.millie_task.core.retrofit.RetrofitNews
import com.music961.millie_task.core.room.NewsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideApplication(@ApplicationContext app: Context) = app as BaseApplication

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) : NewsDataBase =
        Room.databaseBuilder(
            context,
            NewsDataBase::class.java,
            "MillieNewsDB"
        ).build()
    
    @Singleton
    @Provides
    // 231213 Andy : dummy for test
    //fun provideRepoNews() : RepoNews = RepoNewsDummy()
    // 231213 Andy : release provider
    fun provideRepoNews(
        @ApplicationContext context: Context,
        retrofitNews: RetrofitNews,
        room : NewsDataBase
    ) : RepoNews = RepoNewsImpl(context, retrofitNews, room)

    @Singleton
    @Provides
    fun provideRetrofitNews() : RetrofitNews {
        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(MillieUrl.RetrofitBaseUrl.url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RetrofitNews::class.java)
    }
    
}