/*
*   Version: v1.0
*   Author: Florian Hager, Johannes Wilhelm
*   date of creation:   16.06.21
*   date of last change:    04.08.21
*   content: class CreateCard - Fragment to display and control the creation of cards and stacks
*/

package com.example.oop_project_fake_anki

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.oop_project_fake_anki.classes.DefaultCard
import com.example.oop_project_fake_anki.classes.Stack
import com.example.oop_project_fake_anki.utility.Storage
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_create_card.*
import kotlinx.android.synthetic.main.fragment_create_card.view.*

class CreateCard : Fragment(), View.OnClickListener {

    private lateinit var db: FirebaseFirestore
    private lateinit var stacks: MutableList<Stack>
    private lateinit var spinner: Spinner
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var storage: Storage
    private lateinit var edittext1: EditText
    private lateinit var edittext2: EditText
    var selectedStackId: String = ""

    //  initialise bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_card, container, false)
        view.button_home_editor.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_createCard_to_home)
        }

        // button btnHome
        val btnHome: ImageView = view.findViewById(R.id.button_home_editor)
        btnHome.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_createCard_to_home)
        }

        //  button btnCreateCard
        val btnCreateCard: Button = view.findViewById(R.id.button_create_card)
        btnCreateCard.setOnClickListener(this)

        //  button btnCreateStack
        val btnCreateStack: Button = view.findViewById(R.id.button_create_stack)
        btnCreateStack.setOnClickListener(this)

        edittext1 = view.findViewById(R.id.edittext_add_front)
        edittext2 = view.findViewById(R.id.edittext_add_back)

        db = FirebaseFirestore.getInstance()
        storage = Storage(db, requireContext())
        spinner = view.findViewById(R.id.spinner_select_stack)
        stacks = mutableListOf()
        adapter = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedStackId = stacks[position].stackId
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        eventChangeListener()
        return view
    }

    //  update stack and adapter changes from storage
    private fun eventChangeListener() {
        storage.getStacksForSpinner(stacks, adapter)
    }

    // onClick events of view
    override fun onClick(v: View?) {
        when (v?.id) {

            //  button button_create_stack click event
            R.id.button_create_stack -> {
                addStack()
            }

            //  button button_create_card click event
            R.id.button_create_card -> {
                val card = DefaultCard("", "")

                card.stackId = selectedStackId
                card.question = edittext_add_front.text.toString()
                card.answer = edittext_add_back.text.toString()

                if (edittext_add_front.text.toString()
                        .isEmpty() || edittext_add_back.text.toString().isEmpty()
                ) {
                    Toast.makeText(
                        requireActivity(),
                        "bitte Vorderseite und Rückseite eingeben",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    storage.postCard(card)
                    Toast.makeText(
                        requireActivity(),
                        "Deine Karte wurde gespeichert",
                        Toast.LENGTH_LONG
                    ).show()
                    edittext1.text.clear()
                    edittext2.text.clear()
                }
            }
        }
    }

    //    create a stack and store it to firebase
    fun addStack() {
        val inflater = LayoutInflater.from(this.context)
        val v = inflater.inflate(R.layout.add_stack_pop_up, null)
        val name = v.findViewById<EditText>(R.id.name)
        val addDialog = AlertDialog.Builder(this.context)

        addDialog.setView(v)
        addDialog.setPositiveButton("Hinzufügen") { dialog, _ ->
            val names = name.text.toString()
            storage.postStack((Stack("", "$names")))
            Toast.makeText(this.context, "Stapel hinzugefügt", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Abbrechen") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(this.context, "Abbrechen", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }
}