package com.example.burny.imbur.utils

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConnectivityObserver @Inject constructor (
        val context: Context
) : LiveData<Boolean>() {

    private var networkDisposable: Disposable? = null

    override fun onActive() {
        networkDisposable =
                ReactiveNetwork.observeNetworkConnectivity(context.applicationContext)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { connectivity ->
                            Log.d("NetworkObserverLogger", "state: $connectivity")
                            value = connectivity.available()
                        }
    }

    override fun onInactive() {
        safelyDispose(networkDisposable)
    }

    private fun safelyDispose(disposable: Disposable?) {
        if (disposable != null && !disposable.isDisposed)
            disposable.dispose()
    }

}