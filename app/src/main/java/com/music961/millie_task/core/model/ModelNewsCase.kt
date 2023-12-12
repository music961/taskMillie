package com.music961.millie_task.core.model

data class ModelNewsCase(
    val status : String,
    val totalResult : Int,
    val articles : List<EntityNews>
)
