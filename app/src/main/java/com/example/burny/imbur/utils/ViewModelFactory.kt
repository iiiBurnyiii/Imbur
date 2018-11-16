package com.example.burny.imbur.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor (val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            viewModels[modelClass]?.get() as T

}