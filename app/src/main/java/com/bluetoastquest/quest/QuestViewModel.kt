package com.bluetoastquest.quest

import com.bluetoastquest.R
import com.bluetoastquest.base.BaseViewModel
import com.bluetoastquest.core.ResourceProvider
import com.bluetoastquest.questlist.*
import com.bluetoastquest.questrepository.QuestRepository
import com.bluetoastquest.utils.ActionLiveData
import com.bluetoastquest.utils.SingleLiveData

class QuestViewModel(
    private val repository: QuestRepository,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {
    private var questId = ""
    private val quest get() = repository.getQuest(questId)

    val screenTitle = SingleLiveData<String>()
    val mainText = SingleLiveData<String>()
    val bottomButtonText = SingleLiveData<String>()
    val scanError = ActionLiveData()
    val imageId = SingleLiveData<Int>()
    val navigateToScanner = ActionLiveData()
    val navigateBack = ActionLiveData()

    fun onCreate(id: String) {
        questId = id

        quest?.let { screenTitle.value = it.questData.title }

        handleQuestStatus()
    }

    fun onResult(contents: String?) {
        if (contents == quest?.currentTaskAnswer()) {
            repository.incrementQuestStep(questId)
            handleQuestStatus()
        } else {
            scanError.sendAction()
        }
    }

    fun onBottomButtonClicked() {
        quest?.let {
            when {
                it.isNotStarted() -> {
                    repository.incrementQuestStep(questId)
                    handleQuestStatus()
                }
                it.isInProgress() -> navigateToScanner.sendAction()
                it.isFinished() -> navigateBack.sendAction()
            }
        }
    }

    private fun handleQuestStatus() {
        quest?.let {
            when {
                it.isNotStarted() -> showGreetings()
                it.isInProgress() -> showNextStep()
                it.isFinished() -> showSuccess()
            }
        }
    }

    private fun showGreetings() {
        quest?.let {
            imageId.value = it.questData.imageId
            mainText.value = it.questData.greeting
            bottomButtonText.value = resourceProvider.getString(R.string.fragment_quest_bottom_button_state_not_started)
        }
    }

    private fun showNextStep() {
        quest?.let {
            imageId.value = it.currentTask().imageId
            mainText.value = it.currentTask().description
            bottomButtonText.value = resourceProvider.getString(R.string.fragment_quest_bottom_button_state_in_progress)
        }
    }

    private fun showSuccess() {
        quest?.let {
            imageId.value = it.questData.imageId
            mainText.value = it.questData.successMessage
            bottomButtonText.value = resourceProvider.getString(R.string.fragment_quest_bottom_button_state_finished)
        }
    }
}
