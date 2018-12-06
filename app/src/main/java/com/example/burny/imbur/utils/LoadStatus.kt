package com.example.burny.imbur.utils


enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

@Suppress("DataClassPrivateConstructor")
data class LoadStatus private constructor(
        val status: Status
) {
    companion object {
        val LOADED = LoadStatus(Status.SUCCESS)
        val LOADING = LoadStatus(Status.RUNNING)
        val ERROR = LoadStatus(Status.FAILED)
    }
}