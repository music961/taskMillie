package com.music961.millie_task.core.util

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.retrofitEnqueue(
    onSuccess: (T) -> Unit,
    onFailure: (Throwable) -> Unit
) {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful && response.body() != null) {
                onSuccess(response.body()!!)
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            onFailure(t)
        }
    })
}