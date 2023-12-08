package com.music961.millie_task.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.music961.millie_task.ui.theme.MillieTheme
import com.music961.millie_task.ui.util.MillieScaffold

@Composable
fun UIMain() {

    // 231208 Andy : true if 600dp or higher
    val isWideScreen = LocalConfiguration.current.screenWidthDp >= 600
    val columns = if (isWideScreen) 3 else 1

    val list = listOf("34","44","55","66","777")

    MillieScaffold(
        Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns)
        ){
            items(list){
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(Color((0..Int.MAX_VALUE).random()))
                ) {
                    Text(text = it)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UIMainPreview() {
    MillieTheme {
        UIMain()
    }
}