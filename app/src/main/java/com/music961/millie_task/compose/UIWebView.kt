package com.music961.millie_task.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.music961.millie_task.compose.util.CircularLoading
import com.music961.millie_task.viewModel.VmNews

@Composable
fun UIWebView(vmNews: VmNews) {

    val url = vmNews.urlForWebView.collectAsState().value

    val state = rememberWebViewState(url = url)
    
    WebView(
        state = state,
        modifier = Modifier.fillMaxSize()
    )
    
    CircularLoading(isLoading = state.isLoading)
}