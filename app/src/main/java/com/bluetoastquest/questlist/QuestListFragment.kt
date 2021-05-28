package com.bluetoastquest.questlist

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluetoastquest.R
import com.bluetoastquest.base.BaseFragment
import com.bluetoastquest.utils.bind
import com.bluetoastquest.utils.bindNotNull
import kotlinx.android.synthetic.main.fragment_quest_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.bluetoastquest.utils.navigateToGreeting
import com.bluetoastquest.utils.navigateToQuest
import timber.log.Timber

class QuestListFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_quest_list
    override val viewModel: QuestListViewModel by viewModel()

    private val questAdapter = QuestsAdapter()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Timber.d("Navigating")
//
//    }

    override fun setupUI() {
        with(recyclerViewQuests) {
            adapter = questAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun bindViewModel() = with(viewModel) {
        bind(quests, questAdapter::update)
        bind(navigateToSelectedQuest, this@QuestListFragment::navigateToQuest)
        bind(navigateToGreeting) { navigateToGreeting() }

        questAdapter.clickListener = { onItemClicked(it) }
    }
}
