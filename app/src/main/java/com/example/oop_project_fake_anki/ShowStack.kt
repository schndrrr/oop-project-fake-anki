/*
*   Version: v1.0
*   Author: Florian Hager, Johannes Wilhelm
*   date of creation:   17.06.21
*   date of last change:    04.08.21
*   content: class ShowStackAdapter - Fragment to show stacks with their cards
*/

package com.example.oop_project_fake_anki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oop_project_fake_anki.classes.Stack
import com.example.oop_project_fake_anki.utility.Storage
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_show_stack.view.*

class ShowStack : Fragment(), ShowStackAdapter.OnItemClickListener  {

    private lateinit var db: FirebaseFirestore
    private lateinit var stacks: MutableList<Stack>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ShowStackAdapter
    private lateinit var addbtn: Button
    private lateinit var storage: Storage

    //  initialise View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show_stack, container, false)
        view.button_home_showstack.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.action_showStack_to_home)}
        view.ic_showstack_add.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.action_showStack_to_CreateCard)}

        db = FirebaseFirestore.getInstance()
        storage = Storage(db, requireActivity())

        recyclerView = view.findViewById(R.id.rv_stacks)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)
        stacks = mutableListOf()
        adapter = ShowStackAdapter(stacks, this)
        recyclerView.adapter = adapter

        eventChangeListener()
        return view
    }

    //  initialise View
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    //  update stack and adapter changes from storage
    private fun eventChangeListener() {
        storage.getStacksForAdapter(adapter, stacks)
    }

    //  onClick listener navigates from showStack to showCard
    override fun onClickItem(position: Int) {
        val stacksPostitionValue = stacks[position].stackId
        val bundle = bundleOf("stackIdValue" to stacksPostitionValue)

        findNavController().navigate(R.id.action_showStack_to_showCard, bundle)
    }
}

