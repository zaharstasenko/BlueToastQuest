package com.bluetoastquest.core

import android.content.Context

interface ResourceProvider {
    fun getString(id: Int) : String
}
