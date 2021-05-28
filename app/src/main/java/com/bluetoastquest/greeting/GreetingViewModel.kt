package com.bluetoastquest.greeting

import com.bluetoastquest.base.BaseViewModel
import com.bluetoastquest.utils.ActionLiveData

class GreetingViewModel : BaseViewModel() {
    val navigateBack = ActionLiveData()

    fun onButtonStartClicked() {
        navigateBack.sendAction()
    }
}