package com.example.oop_project_fake_anki

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.oop_project_fake_anki.classes.Stack
import kotlinx.android.synthetic.main.item_stack.view.*

class showStackAdapter (
    private val stacks: MutableList<Stack>
    ) : RecyclerView.Adapter<showStackAdapter.StackViewHolder>() {

        class StackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StackViewHolder {
        return StackViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_stack,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StackViewHolder, position: Int) {
        val curStack = stacks[position]
        holder.itemView.apply {
            tvtitle_stack.text = curStack.name
            tvnumber_of_cards.text = curStack.numberOfCards
        }
    }

    override fun getItemCount(): Int {
        return stacks.size
    }
}