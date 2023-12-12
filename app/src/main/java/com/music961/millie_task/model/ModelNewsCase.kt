package com.music961.millie_task.model

data class ModelNewsCase(
    val status : String,
    val totalResult : Int,
    val articles : List<ModelNews>
)
