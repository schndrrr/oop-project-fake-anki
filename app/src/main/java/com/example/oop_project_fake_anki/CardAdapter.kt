/*
*   Version: v1.0
*   Author: Florian Hager, Niklas Dreger
*   date of creation:   22.06.21
*   date of last change:    04.08.21
*   content: class CardAdapter - handles cards
*/

package com.example.oop_project_fake_anki

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oop_project_fake_anki.classes.DefaultCard

class CardAdapter internal constructor(context: Context?, private val cards: MutableList<DefaultCard>, helper: ShowCard.Helper):
    RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val help: ShowCard.Helper = helper

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener  {
        internal var cardQuestion: TextView? = null
        val question: TextView = itemView.findViewById(R.id.tvCardQuestion)
        val answer: TextView = itemView.findViewById(R.id.tvCardAnswer)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            answer.text = cards[position].answer
            help.activateButton()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardAdapter.ViewHolder {
        val itemView = inflater.inflate(
            R.layout.item_card,
            parent,
            false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CardAdapter.ViewHolder, position: Int) {
        val card: DefaultCard = cards[position]
        holder.question.text = card.question
        holder.answer.text = ""
    }

    override fun getItemCount(): Int {
        return cards.size
    }

}