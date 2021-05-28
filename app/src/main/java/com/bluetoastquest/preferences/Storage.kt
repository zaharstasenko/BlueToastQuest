package com.bluetoastquest.preferences

interface Storage {
    fun setQuestStep(questId: String, step: Int)

    fun getQuestStep(questId: String) : Int

    fun shouldShowGreeting(): Boolean

    fun saveGreetingShown()
}
