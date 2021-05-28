package com.bluetoastquest.questlist

import androidx.lifecycle.MutableLiveData
import com.bluetoastquest.base.BaseViewModel
import com.bluetoastquest.preferences.Storage
import com.bluetoastquest.questrepository.QuestRepository
import com.bluetoastquest.utils.ActionLiveData
import com.bluetoastquest.utils.SingleLiveData

class QuestListViewModel(private val questRepository: QuestRepository, private val storage: Storage): BaseViewModel() {
    val quests = SingleLiveData<List<Quest>>()
    val navigateToSelectedQuest = SingleLiveData<String>()
    val navigateToGreeting = ActionLiveData()

    fun onItemClicked(questData: QuestData) {
        navigateToSelectedQuest.value = questData.id
    }

    override fun onResume() {
        quests.value = questRepository.getQuests()

        if (storage.shouldShowGreeting()) {
            navigateToGreeting.sendAction()
            storage.saveGreetingShown()
        }
    }
}
