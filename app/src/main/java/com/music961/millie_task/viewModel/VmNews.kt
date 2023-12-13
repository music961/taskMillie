package com.music961.millie_task.viewModel

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.music961.millie_task.core.model.EntityNews
import com.music961.millie_task.core.util.clearAndAddAll
import com.music961.millie_task.repo.RepoNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class VmNews @Inject constructor(
    private val repoNews: RepoNews
) : ViewModel() {

    val listNews = SnapshotStateList<EntityNews>()

    // 231213 Andy : url fo WebView
    private val _urlForWebView = MutableStateFlow("")
    val urlForWebView = _urlForWebView.asStateFlow()
    fun refreshListNews(){
        repoNews.getNews(
            country = "kr",
            apiKey = "6b0d757cec51491cb228cc84666f668d"
        ){ news->
            listNews.clearAndAddAll(news)
        }
    }

    fun setNewsUrl(url : String){
        _urlForWebView.update { url }
    }
}