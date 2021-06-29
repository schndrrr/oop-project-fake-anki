package com.example.oop_project_fake_anki

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oop_project_fake_anki.classes.Stack
import com.example.oop_project_fake_anki.utility.Storage
import com.example.oop_project_fake_anki.utility.StorageService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_show_card.view.*
import kotlinx.android.synthetic.main.fragment_show_stack.*

class showStack : Fragment(), showStackAdapter.OnItemClickListener  {

    private lateinit var db: FirebaseFirestore
    private lateinit var stacks: MutableList<Stack>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: showStackAdapter
    private lateinit var addbtn: FloatingActionButton
    private lateinit var storageService: StorageService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show_stack, container, false)
        view.button_home.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.action_showStack_to_home)}

        addbtn = view.findViewById(R.id.button_add_stack)
        addbtn.setOnClickListener{addStack()}
        recyclerView = view.findViewById(R.id.rv_stacks)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)
        stacks = mutableListOf()
        storageService = StorageService()

        adapter = showStackAdapter(stacks, this)

        recyclerView.adapter = adapter

        println(storageService.test)

        EventChangeListener()
        return view
    }

    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        val storage: Storage = Storage(db)
        storage.getStacksForAdapter(adapter, stacks)
        println(stacks.size)
    }

    private fun addStack() {
        val inflter = LayoutInflater.from(this.context)
        val v = inflter.inflate(R.layout.add_stack_pop_up,null)
        val name = v.findViewById<EditText>(R.id.name)
        val addDialog = AlertDialog.Builder(this.context)
        var storage: Storage = Storage(db)

        addDialog.setView(v)
        addDialog.setPositiveButton("Hinzufügen"){
            dialog,_->
            val names = name.text.toString()
            //stacks.add((Stack("","$names")))
            storage.postStack((Stack("","$names")))
            adapter.notifyDataSetChanged()
            Toast.makeText(this.context, "Stapel hinzugefügt",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Abbrechen"){
            dialog,_->
            dialog.dismiss()
            Toast.makeText(this.context, "Abbrechen",Toast.LENGTH_SHORT).show()

        }
        addDialog.create()
        addDialog.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClickItem(position: Int) {
        println(stacks[position].stackId)
        findNavController().navigate(R.id.action_showStack_to_showCard)
    }
}

