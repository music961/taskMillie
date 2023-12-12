package com.music961.millie_task.other

import androidx.compose.runtime.snapshots.SnapshotStateList

fun <T> SnapshotStateList<T>.clearAndAddAll(list: List<T>) {
    clear()
    addAll(list)
}