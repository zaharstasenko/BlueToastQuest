package com.bluetoastquest.core

import android.content.Context

class ResourceProviderImpl(private val context: Context) : ResourceProvider {
    override fun getString(id: Int) = context.getString(id)
}