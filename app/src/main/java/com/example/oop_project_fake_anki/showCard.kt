package com.example.oop_project_fake_anki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.StackView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oop_project_fake_anki.classes.Card
import com.example.oop_project_fake_anki.classes.Stack
import com.example.oop_project_fake_anki.utility.Storage
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_show_card.*
import kotlinx.android.synthetic.main.fragment_show_card.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [showCard.newInstance] factory method to
 * create an instance of this fragment.
 */
class showCard : Fragment(), View.OnClickListener {

    private lateinit var db: FirebaseFirestore
    private lateinit var cards: MutableList<Card>
    private lateinit var svCardStack: StackView
    private lateinit var adapter: cardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show_card, container, false)
        view.button_home.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_home)}
        //view.button_hard.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_front_of_card)}
        //view.button_normal.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_front_of_card)}
        //view.button_easy.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_front_of_card)}
        val btn: Button = view.findViewById(R.id.button_hard)
        val btnEasy: Button = view.findViewById(R.id.button_easy)
        btn.setOnClickListener(this)
        btnEasy.setOnClickListener(this)

        svCardStack = view.findViewById(R.id.svCardStack)
        cards = mutableListOf()

        adapter = cardAdapter(this.context, cards)
        svCardStack.adapter = adapter

        EventChangeListener()

        return view
    }

    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        val storage: Storage = Storage(db)
        storage.getCardsForStackId("BL6i6JL4DAakxUxjnowB", adapter, cards)
        println(cards.size)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.button_hard -> {
                // TODO
                println("hallo button hard")
            }
            R.id.button_easy -> {
                // TODO
                println("hallo button easy")
            }
        }
    }

}