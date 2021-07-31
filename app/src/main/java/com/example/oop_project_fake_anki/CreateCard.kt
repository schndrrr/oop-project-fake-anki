package com.example.oop_project_fake_anki

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.oop_project_fake_anki.classes.Card
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

class createCard : Fragment(), View.OnClickListener {

    private lateinit var db: FirebaseFirestore
    private lateinit var stacks: MutableList<Stack>
    private lateinit var spinner: Spinner
    private lateinit var adapter: ArrayAdapter<String>
    var selectedStackId: String = ""
    private lateinit var storage: Storage
    private lateinit var edittext1: EditText
    private lateinit var edittext2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_card, container, false)
        view.button_home_editor.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_createCard_to_home)}

        val btnHome: ImageView = view.findViewById(R.id.button_home_editor)
        btnHome.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_createCard_to_home)
        }

        val btnCreateCard: Button = view.findViewById(R.id.button_create_card)
        btnCreateCard.setOnClickListener(this)

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
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedStackId = stacks[position].stackId
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        EventChangeListener()

        return view
    }

    private fun EventChangeListener() {
        storage.getStacksForSpinner(stacks, adapter)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            // TODO
            R.id.button_create_stack -> {
                addStack()
            }

            R.id.button_create_card -> {
                val card = DefaultCard("","")

                card.stackId = selectedStackId
                card.question = edittext_add_front.text.toString()
                card.answer = edittext_add_back.text.toString()

                if(edittext_add_front.text.toString().isEmpty() || edittext_add_back.text.toString().isEmpty()){
                    Toast.makeText(requireActivity(), "bitte Vorderseite und Rückseite eingeben", Toast.LENGTH_LONG).show()
                }else{
                    storage.postCard(card)
                    Toast.makeText(requireActivity(), "Deine Karte wurde gespeichert", Toast.LENGTH_LONG).show()
                    edittext1.text.clear()
                    edittext2.text.clear()
                }
            }
        }
    }
    fun addStack() {
        val inflter = LayoutInflater.from(this.context)
        val v = inflter.inflate(R.layout.add_stack_pop_up,null)
        val name = v.findViewById<EditText>(R.id.name)
        val addDialog = AlertDialog.Builder(this.context)

        addDialog.setView(v)
        addDialog.setPositiveButton("Hinzufügen"){
                dialog,_->
            val names = name.text.toString()
            storage.postStack((Stack("","$names")))
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
}