package com.example.oop_project_fake_anki

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oop_project_fake_anki.classes.Stack
import com.example.oop_project_fake_anki.utility.Storage
import kotlinx.android.synthetic.main.item_stack.view.*

class showStackAdapter (
    private val stacks: MutableList<Stack>
    ) : RecyclerView.Adapter<showStackAdapter.StackViewHolder>() {

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
    }

    override fun getItemCount(): Int {
        return stacks.size
    }

    class StackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvtitle_stack)
        val numberOfCards: TextView = itemView.findViewById(R.id.tvnumber_of_cards)
    }
}