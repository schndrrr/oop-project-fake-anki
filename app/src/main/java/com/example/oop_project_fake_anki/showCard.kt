package com.example.oop_project_fake_anki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.example.oop_project_fake_anki.classes.Card
import com.example.oop_project_fake_anki.utility.Storage
import com.google.firebase.firestore.FirebaseFirestore
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.StackFrom
import kotlinx.android.synthetic.main.fragment_show_card.*
import kotlinx.android.synthetic.main.fragment_show_card.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_STACKID = "BL6i6JL4DAakxUxjnowB"

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
            stackId = it.getString(ARG_STACKID)
        }
    }

    private lateinit var db: FirebaseFirestore
    private lateinit var cards: MutableList<Card>
    private lateinit var svCardStack: CardStackView
    private lateinit var adapter: cardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO Navigation to other directions other than homescreen missing
        val view = inflater.inflate(R.layout.fragment_show_card, container, false)
        view.button_home.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_createCard_to_home)
        }
        //view.button_hard.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_front_of_card)}
        //view.button_normal.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_front_of_card)}
        //view.button_easy.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_front_of_card)}
        val btn: Button = view.findViewById(R.id.button_hard)
        val btnNormal: Button = view.findViewById(R.id.button_normal)
        val btnEasy: Button = view.findViewById(R.id.button_easy)
        val button_Home: Button = view.findViewById(R.id.button_home)
        btn.setOnClickListener(this)
        btnEasy.setOnClickListener(this)
        btnNormal.setOnClickListener(this)
        button_Home.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_showCard_to_home)
        }
        val btnTest: Button = view.findViewById(R.id.button_solution)


        btnTest.setOnClickListener(this)

        //svCardStack = view.findViewById(R.id.svCardStack)
        cards = mutableListOf()

        adapter = cardAdapter(requireActivity(), cards)
        //svCardStack.adapter = adapter
        val cardStackView = view.findViewById<CardStackView>(R.id.card_stack_view)
        val cardLayout = CardStackLayoutManager(requireActivity())
        cardLayout.setStackFrom(StackFrom.Top)
        cardStackView.layoutManager = cardLayout
        cardStackView.layoutManager
        cardStackView.adapter = adapter

        EventChangeListener()

        return view
    }

    override fun onClick(v: View?) {


        when (v?.id) {
            R.id.button_hard -> {
                button_normal.isVisible = false
                button_hard.isVisible = false
                button_easy.isVisible = false
                println("hallo button hard")
            }
            R.id.button_easy -> {
                button_normal.isVisible = false
                button_hard.isVisible = false
                button_easy.isVisible = false
                println("hallo button easy")
            }
            R.id.button_normal -> {
                button_normal.isVisible = false
                button_hard.isVisible = false
                button_easy.isVisible = false
                println("hallo button normal")
            }
            R.id.button_solution -> {
                // show buttons and textView
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
        println(cards.size)
    }
}



