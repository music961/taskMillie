package com.music961.millie_task.viewModel

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.music961.millie_task.model.ModelNews
import com.music961.millie_task.other.clearAndAddAll
import com.music961.millie_task.repo.RepoNews
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VmNews @Inject constructor(
    private val repoNews: RepoNews
) : ViewModel() {

    val listNews = SnapshotStateList<ModelNews>()

    fun refreshListNews(){
        repoNews.getNews("kr","6b0d757cec51491cb228cc84666f668d"){ news->
            listNews.clearAndAddAll(news)
        }
    }
}