package com.example.oop_project_fake_anki

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.oop_project_fake_anki.classes.DefaultCard
import com.example.oop_project_fake_anki.classes.Stack
import com.example.oop_project_fake_anki.utility.Storage
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_create_card.*
import kotlinx.android.synthetic.main.fragment_create_card.view.*
import kotlinx.android.synthetic.main.fragment_show_card.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [createCard.newInstance] factory method to
 * create an instance of this fragment.
 */
class createCard : Fragment(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var db: FirebaseFirestore
    private lateinit var stacks: MutableList<Stack>
    private lateinit var spinner: Spinner
    private lateinit var stacksList: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_card, container, false)
        view.button_Home.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_home)}

        val btn_home: Button = view.findViewById(R.id.button_Home)
        val btn_createCard: Button = view.findViewById(R.id.button_create_card)

        btn_home.setOnClickListener(this)
        btn_createCard.setOnClickListener(this)

        spinner = view.findViewById(R.id.spinner_select_stack)
        stacks = mutableListOf()
        stacksList = arrayListOf()
        adapter = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        EventChangeListener()

        return view
    }

    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        val storage = Storage(db)
        storage.getStacksForSpinner(stacks, stacksList, adapter)
    }

    override fun onClick(v: View?) {



        when (v?.id) {
            // TODO
            R.id.button_Home -> {

            }

            R.id.button_create_card -> {
                val card = DefaultCard("asdasd","b")

                card.description = edittext_add_front.text.toString()
                card.answer = edittext_add_back.text.toString()
                println(card.description)
                println(card.answer)
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
        //println(stacks[position].stackId)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}