package com.music961.millie_task.repo.dummy

import android.util.Log
import com.music961.millie_task.core.model.EntityNews
import com.music961.millie_task.repo.RepoNews

class RepoNewsDummy : RepoNews {
    override fun getNews(
        country: String,
        apiKey: String,
        unit : (List<EntityNews>)->Unit
    )  {
        unit(
            listOf(
                EntityNews(
                    "title 1",
                    "https://www.millie.co.kr",
                    "https://i.namu.wiki/i/V0CEkJQUpSkS0yF576k8NpS1EvYRlCWtlnXqXG4RwOTq-sDxFBiS61_GbbC24I_1ClDgVFHHBAgLYzaFg84kfSWgBPHZplE_hzJStOUr8fll7sdIA6T1JvSIu42VyPNoLHKPQNAO5OvOBXyOtDtmcQ.webp",
                    "publishedAt 1"
                ),
                EntityNews(
                    "title 2",
                    "https//www.naver.com",
                    "https://i.namu.wiki/i/364PN4pSx2JvCljaHwjEbb4f_qfzEvVwa_tjlUPrjkUiPY29xMKYFGOXscwLSa550o2BSnn3AWKrWyPXh8t2Ds4euhwLAZsnosRT5JAHkUxGMODAfgDAoDN0pFWXZwY5iDfm6afe8vY44K_xqa9oVA.webp",
                    "publishedAt 2"
                ),
                EntityNews(
                    "title 3",
                    "https://www.google.com",
                    "https://i.namu.wiki/i/eTqDgIDO1nmbOiJhDeXoMi2ljNtsOS3LvabQ-t8NxuKhIBuiaAUuA_nWq0bZVV7KgYoivKntahLQ2c9auiQhCykQA05MSOGAOTbc8GDcX2B8c46haDL87DUfSQyuiQ9NjMtY1_XtRu5dnqyv-gFQrg.webp",
                    "publishedAt 3"
                ),
                EntityNews(
                    "title 4",
                    "https://www.youtube.com",
                    "https://s3pinto.s3.ap-northeast-2.amazonaws.com/%EA%B2%8C%EC%9E%84_%ED%91%9C%EC%A7%80/33017_face.webp",
                    "publishedAt 4"
                )
            )
        )
    }

    override suspend fun keepNewsViewed(title: String) {
        Log.d("Millie", "keep news viewed")
    }

    override suspend fun haveNewsViewed(title: String): Boolean {
        Log.d("Millie", "check have news viewed")
        return false
    }

    override suspend fun clearViewedHistory() {
        Log.d("Millie", "clear news viewed history")
    }
}