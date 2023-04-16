package com.example.meebooapp.viewModel

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyObserver: LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun oncreate(){ // this can be customized.
        Log.i(LOG_INFO,"Main Activity is CREATED!")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onClose(){ // this can be customized.
        Log.i(LOG_INFO,"Main Activity is DESTROYED!")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun show(){ // this can be customized.
        Log.i(LOG_INFO,"Screen is VISIBLE!")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop(){ // this can be customized.
        Log.i(LOG_INFO,"Main Activity is HIDDEN!")
    }

}