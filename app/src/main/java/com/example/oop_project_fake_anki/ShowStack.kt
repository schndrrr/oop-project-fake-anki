package com.example.oop_project_fake_anki

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oop_project_fake_anki.classes.Stack
import com.example.oop_project_fake_anki.utility.Storage
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_show_card.view.*
import kotlinx.android.synthetic.main.fragment_show_stack.*
import kotlinx.android.synthetic.main.fragment_show_stack.view.*

class ShowStack : Fragment(), ShowStackAdapter.OnItemClickListener  {

    private lateinit var db: FirebaseFirestore
    private lateinit var stacks: MutableList<Stack>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ShowStackAdapter
    private lateinit var addbtn: Button
    private lateinit var storage: Storage


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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

        EventChangeListener()
        return view
    }
    private fun EventChangeListener() {
        storage.getStacksForAdapter(adapter, stacks)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClickItem(position: Int) {
        val stackspostitionvalue = stacks[position].stackId
        val bundle = bundleOf("stackIdValue" to stackspostitionvalue)

        findNavController().navigate(R.id.action_showStack_to_showCard, bundle)
    }
}

