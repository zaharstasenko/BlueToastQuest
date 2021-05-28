package com.bluetoastquest.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }

    open fun onViewCreated() = Unit

    open fun onResume() = Unit
}