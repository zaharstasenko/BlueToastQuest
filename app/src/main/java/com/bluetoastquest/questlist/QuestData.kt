package com.bluetoastquest.questlist

import java.io.Serializable

enum class Status {
    NOT_STARTED, IN_PROGRESS, FINISHED
}

class Quest(val questData: QuestData, val stepNumber: Int) : Serializable

class QuestData(
    val id: String,
    val title: String,
    val imageId: Int,
    val location: String,
    val greeting: String,
    val successMessage: String,
    val tasks: List<QuestTask>
) : Serializable

class QuestTask(val stepNumber: Int, val description: String, val imageId: Int, val password: String) : Serializable

fun Quest.currentTask() = this.questData.tasks[stepNumber]

fun Quest.currentTaskAnswer() = currentTask().password

fun Quest.currentStatus() =
    when {
        stepNumber < 0 -> Status.NOT_STARTED
        stepNumber < questData.tasks.size -> Status.IN_PROGRESS
        else -> Status.FINISHED
    }

fun Quest.isNotStarted() = currentStatus() == Status.NOT_STARTED

fun Quest.isInProgress() = currentStatus() == Status.IN_PROGRESS

fun Quest.isFinished() = currentStatus() == Status.FINISHED

fun Quest.taskAmount() = questData.tasks.size

fun Quest.percentsDone() = stepNumber * 100 / taskAmount()