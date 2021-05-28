package com.bluetoastquest.utils

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bluetoastquest.questlist.QuestListFragmentDirections

fun Fragment.navigateBack() = findNavController().popBackStack()

fun Fragment.navigateToQuest(questId: String) =
    findNavController().navigate(QuestListFragmentDirections.actionQuestListFragmentToQuestFragment(questId))

fun Fragment.navigateToGreeting() = findNavController().navigate(QuestListFragmentDirections.actionQuestListFragmentToGreetingFragment())