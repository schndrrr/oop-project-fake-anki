package com.example.oop_project_fake_anki

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oop_project_fake_anki.classes.Card
import com.example.oop_project_fake_anki.classes.Stack

class cardAdapter internal constructor(context: Context?, private val cards: MutableList<Card>):
    RecyclerView.Adapter<cardAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

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
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): cardAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_card,
            parent,
            false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: cardAdapter.ViewHolder, position: Int) {
        val card: Card = cards[position]
        holder.question.text = card.question
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    interface OnItemClickListener {
        fun onClickItem(position: Int)
    }

}