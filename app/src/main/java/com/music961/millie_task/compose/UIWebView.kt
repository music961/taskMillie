package com.music961.millie_task.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.music961.millie_task.viewModel.VmNews

@Composable
fun UIWebView(vmNews: VmNews) {

    val url = vmNews.urlForWebView.collectAsState().value

    WebView(
        state = rememberWebViewState(url = url),
        modifier = Modifier.fillMaxSize()
    )
}