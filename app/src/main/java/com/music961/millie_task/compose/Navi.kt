package com.music961.millie_task.compose

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.music961.millie_task.core.enum.NavStage
import com.music961.millie_task.viewModel.VmNews

@Composable
fun Navi(){

    val context = LocalContext.current as ComponentActivity
    val navCon = rememberNavController()

    val vmNews = hiltViewModel<VmNews>(context)

    NavHost(
        navController = navCon,
        startDestination = NavStage.Main.name
    ){

        composable(NavStage.Main.name){
            UIMain(context, navCon, vmNews)
        }

        composable(NavStage.WebView.name){
            UIWebView(vmNews)
        }
    }
}