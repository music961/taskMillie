package com.music961.millie_task.viewModel

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.music961.millie_task.core.model.EntityNews
import com.music961.millie_task.core.util.clearAndAddAll
import com.music961.millie_task.repo.RepoNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VmNews @Inject constructor(
    private val repoNews: RepoNews
) : ViewModel() {

    val listNews = SnapshotStateList<EntityNews>()

    // 231213 Andy : url fo WebView
    private val _urlForWebView = MutableStateFlow("")
    val urlForWebView = _urlForWebView.asStateFlow()

    fun refreshListNews(unit: () -> Unit){
        repoNews.getNews(
            country = "us",
            apiKey = "6b0d757cec51491cb228cc84666f668d"
        ){ news->
            listNews.clearAndAddAll(news)
            unit()
        }
    }

    fun selectNewsItem(url : String, title : String){
        _urlForWebView.update { url }
        viewModelScope.launch(Dispatchers.IO){
            repoNews.keepNewsViewed(title)
        }
    }

    fun checkNewsViewed(title: String, unit : (Boolean)->Unit){
        viewModelScope.launch(Dispatchers.IO){
            val isViewed = repoNews.haveNewsViewed(title)
            unit(isViewed)
        }
    }

    fun clearViewedHistory(callback : ()->Unit){
        viewModelScope.launch(Dispatchers.IO) {
            repoNews.clearViewedHistory()
            callback()
        }
    }
}