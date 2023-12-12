package com.music961.millie_task.repo.dummy

import com.music961.millie_task.model.ModelNews
import com.music961.millie_task.repo.RepoNews

class RepoNewsDummy : RepoNews {
    override fun getNews(
        country: String,
        apiKey: String,
        unit : (List<ModelNews>)->Unit
    )  {
        unit(
            listOf(
                ModelNews(
                    "title 1",
                    "https://i.namu.wiki/i/V0CEkJQUpSkS0yF576k8NpS1EvYRlCWtlnXqXG4RwOTq-sDxFBiS61_GbbC24I_1ClDgVFHHBAgLYzaFg84kfSWgBPHZplE_hzJStOUr8fll7sdIA6T1JvSIu42VyPNoLHKPQNAO5OvOBXyOtDtmcQ.webp",
                    "publishedAt 1"
                ),
                ModelNews(
                    "title 2",
                    "https://i.namu.wiki/i/364PN4pSx2JvCljaHwjEbb4f_qfzEvVwa_tjlUPrjkUiPY29xMKYFGOXscwLSa550o2BSnn3AWKrWyPXh8t2Ds4euhwLAZsnosRT5JAHkUxGMODAfgDAoDN0pFWXZwY5iDfm6afe8vY44K_xqa9oVA.webp",
                    "publishedAt 2"
                ),
                ModelNews(
                    "title 3",
                    "https://i.namu.wiki/i/eTqDgIDO1nmbOiJhDeXoMi2ljNtsOS3LvabQ-t8NxuKhIBuiaAUuA_nWq0bZVV7KgYoivKntahLQ2c9auiQhCykQA05MSOGAOTbc8GDcX2B8c46haDL87DUfSQyuiQ9NjMtY1_XtRu5dnqyv-gFQrg.webp",
                    "publishedAt 3"
                ),
                ModelNews(
                    "title 4",
                    "https://s3pinto.s3.ap-northeast-2.amazonaws.com/%EA%B2%8C%EC%9E%84_%ED%91%9C%EC%A7%80/33017_face.webp",
                    "publishedAt 4"
                )
            )
        )
    }
}