package com.bluetoastquest.questrepository

import com.bluetoastquest.questlist.Quest

interface QuestRepository {
    fun getQuests() : List<Quest>

    fun getQuestStep(id: String) : Int

    fun setQuestStep(id: String, step: Int)

    fun incrementQuestStep(id: String)

    fun getQuest(id: String): Quest?
}