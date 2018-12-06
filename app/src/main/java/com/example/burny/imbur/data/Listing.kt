package com.example.burny.imbur.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.burny.imbur.utils.LoadStatus

data class Listing<T> (
        val galleryLiveData: LiveData<PagedList<T>>,
        val refreshStatus: LiveData<LoadStatus>,
        val loadStatus: LiveData<LoadStatus>
)