package com.music961.millie_task.compose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    val isLoading = remember { mutableStateOf(false) }

    LaunchedEffect(Unit){
        // 231213 Andy : Bringing in the news
        if (listNews.isEmpty()){
            isLoading.value = true
            vmNews.refreshListNews{
                isLoading.value = false
            }
        }
    }

    MillieScaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MillieDp.PaddingScaffold.dp),
        isLoading = isLoading,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "${listNews.size}건의 뉴스",
                        style = MillieTextStyle.AppBarLabel.style
                    )
                },
                actions = {
                    Button(onClick = {
                        vmNews.clearViewedHistory{
                            NavStage.Main.movePage(navCon)
                        }
                    }) {
                        Text("열람 기록 제거")
                    }
                }
            )
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns)
        ){
            items(listNews){entity->

                var isViewed by remember { mutableStateOf(false) }

                LaunchedEffect(listNews){
                    vmNews.checkNewsViewed(entity.title){ haveViewed->
                        isViewed = haveViewed
                    }
                }

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(MillieDp.PaddingItemAroundPadding.dp)
                        .preventDupClick {
                            if (entity.url.isNullOrBlank()) {
                                Toast
                                    .makeText(context, "링크가 없습니다.", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                isViewed = true
                                vmNews.selectNewsItem(entity.url, entity.title)
                                NavStage.WebView.movePage(navCon)
                            }
                        },
                    verticalArrangement = Arrangement.spacedBy(MillieDp.PaddingItemVerticalSpace.dp)
                ) {
                    Text(
                        text = entity.title,
                        color = if (isViewed) Color.Red else Color.Unspecified
                    )
                    AsyncImage(
                        model = entity.urlToImage,
                        contentDescription = "news photo",
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(text = entity.publishedAt ?:"")
                }
            }
        }
    }
}