package com.music961.millie_task.core.enum

import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

enum class NavStage {
    Main,
    WebView

    ;

    fun movePage(navCon : NavController) {
        CoroutineScope(Dispatchers.Main).launch {
            navCon.navigate(name) {
                popUpTo(name) { saveState = true; inclusive = true }
            }
        }
    }
}