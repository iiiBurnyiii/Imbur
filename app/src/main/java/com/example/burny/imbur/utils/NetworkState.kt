package com.example.burny.imbur.utils


enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

@Suppress("DataClassPrivateConstructor")
data class LoadState private constructor(
        val status: Status
) {
    companion object {
        val LOADED = LoadState(Status.SUCCESS)
        val LOADING = LoadState(Status.RUNNING)
        val ERROR = LoadState(Status.FAILED)
    }
}