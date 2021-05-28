package com.bluetoastquest.preferences

import android.content.Context
import timber.log.Timber

private const val DEFAULT_STEP_VALUE = -1
private const val GREETINGS_SHOWN_KEY = "GREETINGS_SHOWN_KEY"

class StorageImpl(context: Context) : Storage {
    private val preferencesName = "BlueToastQuestStorage"

    private val sharedPreferences =
        context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    override fun setQuestStep(questId: String, step: Int) {
        saveInt(questId, step)
    }

    override fun getQuestStep(questId: String): Int = 10
    //sharedPreferences.getInt(questId, DEFAULT_STEP_VALUE)

    override fun shouldShowGreeting() =
        sharedPreferences.getBoolean(GREETINGS_SHOWN_KEY, true)

    override fun saveGreetingShown() {
        editor.putBoolean(GREETINGS_SHOWN_KEY, false).commit()
    }

    private fun saveInt(tag: String, value: Int) {
        editor.putInt(tag, value).commit()
    }
}
