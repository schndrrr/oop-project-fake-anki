package com.example.oop_project_fake_anki

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oop_project_fake_anki.classes.Stack

/**
 * Adapter class that handles the dynamically loaded Stacks
 * called from ShowStack.kt
 * inputs:
 *  stacks - list of all stacks of user
 *  listener - class to call function onClickItem
 *
 * Written by:
 *  Florian Hager, Johann Georg Nitsche, Johann Schneider
 */
class ShowStackAdapter (
    private val stacks: MutableList<Stack>,
    private val listener: OnItemClickListener
    ) : RecyclerView.Adapter<ShowStackAdapter.StackViewHolder>() {

    /** Creates View Holder  **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StackViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_stack,
                parent,
                false
            )
        return StackViewHolder(itemView)
    }
    /** Sets TextView in item to value of stack[at current position]**/
    override fun onBindViewHolder(holder: StackViewHolder, position: Int) {
        val stack: Stack = stacks[position]
        holder.name.text = stack.name
        holder.numberOfCards.text = stack.numberOfCards
        holder.adapterPosition

    }
    /** returns the size of the Stack **/
    override fun getItemCount(): Int {
        return stacks.size
    }
    /** inner Class which holds one instance of item_stack.xml **/
    inner class StackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        /** variables (TextView) that are in the item_stack.xml **/
        val name: TextView = itemView.findViewById(R.id.tvtitle_stack)
        val numberOfCards: TextView = itemView.findViewById(R.id.tvNumberOfCards)

        /** on click listener is set with init of class **/
        init {
            itemView.setOnClickListener(this)
        }
        /** function is triggered on Click of item**/
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                if (stacks[position].numberOfCards != "0") {
                    /** calls onClickItem in ShowStack.kt **/
                    listener.onClickItem(position)
                }
            }
        }
    }
    /** interface of on Click listener**/
    interface OnItemClickListener {
        /** prototypes function, can be overwritten from outside of this class **/
        fun onClickItem(position: Int)
    }

}