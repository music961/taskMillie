package com.music961.millie_task.ui.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MillieScaffold(
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier
    ){
        Column {
            Spacer(
                modifier = Modifier.height(it.calculateTopPadding())
            )
            content(it)
        }
    }
}

@Composable
fun MillieColumn(
    modifier: Modifier = Modifier,
    spaceBy: Dp = 5.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(spaceBy)
    ) {
        content()
    }
}