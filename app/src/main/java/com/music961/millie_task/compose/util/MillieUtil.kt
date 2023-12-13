package com.music961.millie_task.compose.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@Composable
fun MillieScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        floatingActionButton = floatingActionButton
    ){
        Column {
            Spacer(
                modifier = Modifier.height(it.calculateTopPadding())
            )
            content(it)
        }
    }
}

var lastClickTime = System.currentTimeMillis()

fun Modifier.preventDupClick(duplicateTime: Long = 200L,onClick: () -> Unit) =
    composed {
        clickable {
            System.currentTimeMillis().also { currentTime->
                if (currentTime - lastClickTime > duplicateTime) {
                    lastClickTime = currentTime
                    onClick()
                }
            }
        }
    }