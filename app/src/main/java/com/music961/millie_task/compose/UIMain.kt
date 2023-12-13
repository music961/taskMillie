package com.music961.millie_task.compose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.music961.millie_task.compose.util.MillieScaffold
import com.music961.millie_task.compose.util.preventDupClick
import com.music961.millie_task.core.enum.MillieDp
import com.music961.millie_task.core.enum.MillieTextStyle
import com.music961.millie_task.core.enum.NavStage
import com.music961.millie_task.viewModel.VmNews

@ExperimentalMaterial3Api
@Composable
fun UIMain(
    context : Context,
    navCon : NavController,
    vmNews: VmNews
) {

    val listNews = vmNews.listNews

    // 231208 Andy : true if 600dp or higher
    val isWideScreen = LocalConfiguration.current.screenWidthDp >= 600
    val columns = if (isWideScreen) 3 else 1

    LaunchedEffect(Unit){
        // 231213 Andy : Bringing in the news
        vmNews.refreshListNews()
    }

    MillieScaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MillieDp.PaddingScaffold.dp),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "${listNews.size}건의 뉴스",
                        style = MillieTextStyle.AppBarLabel.style
                    )
                }
            )
//            Row(
//                modifier = Modifier.height(MillieDp.TopBarHeight.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = "${listNews.size}건의 뉴스",
//                    style = MillieTextStyle.AppBarLabel.style
//                )
//            }
        }
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
                        .padding(MillieDp.PaddingItemAroundPadding.dp)
                        .preventDupClick {
                            if (it.url.isNullOrBlank()) {
                                Toast
                                    .makeText(context, "링크가 없습니다.", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                vmNews.setNewsUrl(it.url)
                                NavStage.WebView.movePage(navCon)
                            }
                        },
                    verticalArrangement = Arrangement.spacedBy(MillieDp.PaddingItemVerticalSpace.dp)
                ) {
                    Text(text = it.title)
                    AsyncImage(
                        model = it.urlToImage,
                        contentDescription = "news photo",
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(text = it.publishedAt ?:"")
                }
            }
        }
    }
}