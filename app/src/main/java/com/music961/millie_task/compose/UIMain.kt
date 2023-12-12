package com.music961.millie_task.compose

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.music961.millie_task.compose.theme.MillieTheme
import com.music961.millie_task.compose.util.MillieScaffold
import com.music961.millie_task.core.enum.MillieDp
import com.music961.millie_task.viewModel.VmNews

@Composable
fun UIMain() {

    val context = LocalContext.current as ComponentActivity
    val vmNews = hiltViewModel<VmNews>(context)
    val listNews = vmNews.listNews

    // 231208 Andy : true if 600dp or higher
    val isWideScreen = LocalConfiguration.current.screenWidthDp >= 600
    val columns = if (isWideScreen) 3 else 1

    LaunchedEffect(Unit){
        // 231213 Andy : Bringing in the news
        vmNews.refreshListNews()
    }

    MillieScaffold(
        Modifier
            .fillMaxSize()
            .padding(MillieDp.ScaffoldPadding.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns)
        ){
            items(listNews){
                Column(
                    Modifier
                        .fillMaxWidth()
                        // 231213 Andy : random color
                        .background(Color((0..Int.MAX_VALUE).random()))
                        .padding(MillieDp.ItemPadding.dp)
                ) {
                    Text(text = it.title)
                    Text(text = it.urlToImage ?:"")
                    Text(text = it.publishedAt ?:"")
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