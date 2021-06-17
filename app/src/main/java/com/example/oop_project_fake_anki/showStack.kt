package com.example.oop_project_fake_anki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_show_card.view.*
import kotlinx.android.synthetic.main.fragment_show_stack.*


class showStack : Fragment() {

    private lateinit var showStackAdapter: showStackAdapter
    private lateinit var recv:RecyclerView
    private lateinit var addbtn:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showStackAdapter = showStackAdapter(mutableListOf())




        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show_stack, container, false)
        view.button_home.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.action_home)}
        return view
    }

