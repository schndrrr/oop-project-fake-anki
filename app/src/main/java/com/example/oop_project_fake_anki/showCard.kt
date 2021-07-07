package com.example.oop_project_fake_anki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import android.widget.StackView
import com.example.oop_project_fake_anki.classes.Card
import com.example.oop_project_fake_anki.utility.Storage
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_show_card.*
import kotlinx.android.synthetic.main.fragment_show_card.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_STACKID = "BL6i6JL4DAakxUxjnowB"

/**
 * A simple [Fragment] subclass.
 * Use the [showCard.newInstance] factory method to
 * create an instance of this fragment.
 */
class showCard : Fragment(), View.OnClickListener {
    private var stackId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            stackId = it.getString("stackIdValue")
            println("$stackId")
        }
    }

    private lateinit var db: FirebaseFirestore
    private lateinit var cards: MutableList<Card>
    private lateinit var svCardStack: StackView
    private lateinit var adapter: cardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO Navigation to other directions other than homescreen missing
        val view = inflater.inflate(R.layout.fragment_show_card, container, false)
        view.button_home_card.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_createCard_to_home)
        }
        //view.button_hard.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_front_of_card)}
        //view.button_normal.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_front_of_card)}
        //view.button_easy.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_front_of_card)}
        val btn: Button = view.findViewById(R.id.button_hard)
        val btnNormal: Button = view.findViewById(R.id.button_normal)
        val btnEasy: Button = view.findViewById(R.id.button_easy)
        val btnhomecard: Button = view.findViewById(R.id.button_home_card)
        btn.setOnClickListener(this)
        btnEasy.setOnClickListener(this)
        btnNormal.setOnClickListener(this)
        btnhomecard.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_showCard_to_home)
        }
        val btnTest: Button = view.findViewById(R.id.button_solution)


        btnTest.setOnClickListener(this)

        svCardStack = view.findViewById(R.id.svCardStack)
        cards = mutableListOf()

        adapter = cardAdapter(this.context, cards)
        svCardStack.adapter = adapter

        EventChangeListener()

        return view
    }

    override fun onClick(v: View?) {


        when (v?.id) {
            R.id.button_hard -> {
                button_normal.isVisible = false
                button_hard.isVisible = false
                button_easy.isVisible = false
                answer.text = ""
            }
            R.id.button_easy -> {
                button_normal.isVisible = false
                button_hard.isVisible = false
                button_easy.isVisible = false
                answer.text = ""
            }
            R.id.button_normal -> {
                button_normal.isVisible = false
                button_hard.isVisible = false
                button_easy.isVisible = false
                answer.text = ""
            }
            R.id.button_solution -> {
                // show buttons and textView
                answer.text = "Seine Hose ist natürlich blau"
                button_easy.isVisible = true
                button_normal.isVisible = true
                button_hard.isVisible = true
            }
        }
    }

    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        val storage: Storage = Storage(db)
        storage.getCardsForStackId(stackId.toString(), adapter, cards)
    }
}



