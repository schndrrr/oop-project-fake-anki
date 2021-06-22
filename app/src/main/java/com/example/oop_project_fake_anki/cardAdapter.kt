package com.example.oop_project_fake_anki

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.oop_project_fake_anki.classes.Card

class cardAdapter internal constructor(context: Context?, private val cards: MutableList<Card>): BaseAdapter(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int {
        return cards.size
    }

    override fun getItem(position: Int): Any {
        return cards[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_card, parent, false)
            holder = ViewHolder()
            holder.cardQuestion = convertView!!.findViewById(R.id.tvCardQuestion)
        } else {
            holder = convertView.tag as ViewHolder
        }
        holder.cardQuestion!!.text = cards[position].question
        return convertView
    }

    inner class ViewHolder {
        internal var cardQuestion: TextView? = null
    }

}