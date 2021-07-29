package com.example.oop_project_fake_anki

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oop_project_fake_anki.classes.Stack

class ShowStackAdapter (
    private val stacks: MutableList<Stack>,
    private val listener: OnItemClickListener
    ) : RecyclerView.Adapter<ShowStackAdapter.StackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StackViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_stack,
                parent,
                false
            )
        return StackViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StackViewHolder, position: Int) {
        val stack: Stack = stacks[position]
        holder.name.text = stack.name
        holder.numberOfCards.text = stack.numberOfCards
        holder.adapterPosition

    }

    override fun getItemCount(): Int {
        return stacks.size
    }

    inner class StackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val name: TextView = itemView.findViewById(R.id.tvtitle_stack)
        val numberOfCards: TextView = itemView.findViewById(R.id.tvnumber_of_cards)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onClickItem(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onClickItem(position: Int)
    }

}