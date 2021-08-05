/*
*   Version: v1.0
*   Author: Florian Hager
*   date of creation:   16.06.21
*   date of last change:    04.08.21
*   content: class ShowStackAdapter - fills and manages the data of the RecyclerView
*/

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

    //  initialise StackViewHolder layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StackViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_stack,
            parent,
            false
        )
        return StackViewHolder(itemView)
    }

    //  fills the StackViewHolder with data
    override fun onBindViewHolder(holder: StackViewHolder, position: Int) {
        val stack: Stack = stacks[position]
        holder.name.text = stack.name
        holder.numberOfCards.text = stack.numberOfCards
        holder.adapterPosition
    }

    //  return number of cards in a stack
    override fun getItemCount(): Int {
        return stacks.size
    }

    // inner class StackViewHolder - reference TextView to its values
    inner class StackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val name: TextView = itemView.findViewById(R.id.tvtitle_stack)
        val numberOfCards: TextView = itemView.findViewById(R.id.tvNumberOfCards)
        init {
            itemView.setOnClickListener(this)
        }

        // handle onClick Events of View
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                if (stacks[position].numberOfCards != "0") {
                    listener.onClickItem(position)
                }
            }
        }
    }

    // identify the clicked element with position
    interface OnItemClickListener {
        fun onClickItem(position: Int)
    }

}