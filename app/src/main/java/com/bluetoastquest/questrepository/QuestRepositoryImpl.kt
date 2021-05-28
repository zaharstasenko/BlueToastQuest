package com.bluetoastquest.questrepository

import com.bluetoastquest.preferences.Storage
import com.bluetoastquest.questlist.Quest
import timber.log.Timber

class QuestRepositoryImpl(private val storage: Storage) : QuestRepository {

    private val questsData = listOf(historyQuest, forestQuest, horrorQuest, altitudeQuest, memoQuest, hedgehogQuest)

    override fun getQuests() = questsData.map { Quest(it, storage.getQuestStep(it.id)) }

    override fun incrementQuestStep(id: String) {
        storage.setQuestStep(id, getQuestStep(id) + 1)
    }

    override fun getQuestStep(id: String): Int {
        return getQuests().firstOrNull { it.questData.id == id }?.stepNumber ?: -1
    }

    override fun setQuestStep(id: String, step: Int) {
        storage.setQuestStep(id, step)
    }

    override fun getQuest(id: String): Quest? {
        Timber.d("GetQuestUd " + id + " stepNum " + getQuests().firstOrNull { it.questData.id == id }?.stepNumber)
        return getQuests().firstOrNull { it.questData.id == id }
    }
}
