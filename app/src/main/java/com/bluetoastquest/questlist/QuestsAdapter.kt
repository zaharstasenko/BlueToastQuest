package com.bluetoastquest.questlist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.*
import com.bluetoastquest.R
import com.bluetoastquest.utils.inflateView
import com.bluetoastquest.utils.onSingleClick
import kotlinx.android.synthetic.main.quest_card.view.*
import kotlinx.android.synthetic.main.quest_title.view.textViewStatus
import kotlinx.android.synthetic.main.quest_title.view.textViewTitle

class QuestsAdapter : Adapter<QuestsAdapter.QuestViewHolder>() {
    private val items = mutableListOf<Quest>()
    var clickListener: ((QuestData) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        QuestViewHolder(parent.inflateView(R.layout.quest_card))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: QuestViewHolder, position: Int) =
        holder.bind(items[position])

    fun update(questList: List<Quest>) = with(items) {
        clear()
        addAll(questList)
        notifyDataSetChanged()
    }

    inner class QuestViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bind(quest: Quest) = with(itemView) {
            imageViewQuest.setImageDrawable(context.getDrawable(quest.questData.imageId))
            textViewTitle.text = quest.questData.title
            textViewLocation.text = quest.questData.location
            textViewStatus.text = when {
                quest.isNotStarted() -> "Доступний"
                quest.isInProgress() -> "В процесі: Завершено " + quest.percentsDone() + "%"
                else -> "Завершено"
            }

            onSingleClick { clickListener?.invoke(quest.questData) }
        }
    }
}
