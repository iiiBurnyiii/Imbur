package com.example.burny.imbur.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.burny.imbur.utils.LoadState

data class Listing<T> (
        val pagedList: LiveData<PagedList<T>>,
        val refreshState: LiveData<LoadState>,
        val loadState: LiveData<LoadState>
)