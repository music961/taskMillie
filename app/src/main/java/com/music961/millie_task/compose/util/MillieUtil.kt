package com.music961.millie_task.compose.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MillieScaffold(
    modifier: Modifier = Modifier,
    isLoading : MutableState<Boolean> = mutableStateOf(false),
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

    CircularLoading(isLoading = isLoading.value)
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

@Composable
fun CircularLoading(isLoading : Boolean, modifier: Modifier = Modifier) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.size(100.dp)){
                CircularProgressIndicator(
                    modifier = modifier.fillMaxSize(),
                    color = Color(0xFF3F8C49)
                )
            }
        }
    }
}