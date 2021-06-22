package com.example.oop_project_fake_anki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oop_project_fake_anki.classes.Stack
import com.example.oop_project_fake_anki.utility.Storage
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_show_card.view.*
import kotlinx.android.synthetic.main.fragment_show_stack.*


class showStack : Fragment() {

    private lateinit var db: FirebaseFirestore
    private lateinit var stacks: MutableList<Stack>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: showStackAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show_stack, container, false)
        view.button_home.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.action_home)}


        recyclerView = view.findViewById(R.id.rv_stacks)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)
        stacks = mutableListOf()

        adapter = showStackAdapter(stacks)

        recyclerView.adapter = adapter

        EventChangeListener()
        return view
    }

    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        val storage: Storage = Storage(db)
        storage.getStacksForAdapter(adapter, stacks)
        println(stacks.size)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}

