package com.example.oop_project_fake_anki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.Navigation
import com.example.oop_project_fake_anki.classes.Card
import com.example.oop_project_fake_anki.utility.Storage
import com.google.firebase.firestore.FirebaseFirestore
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.fragment_show_card.*
import kotlinx.android.synthetic.main.fragment_show_card.view.*
import kotlin.math.roundToInt

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_STACKID = "BL6i6JL4DAakxUxjnowB"

/**
 * A simple [Fragment] subclass.
 * Use the [ShowCard.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShowCard : Fragment(), View.OnClickListener {
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
    private lateinit var svCardStack: CardStackView
    private lateinit var adapter: CardAdapter
    private lateinit var cardStackView: CardStackView
    private lateinit var cardLayout: CardStackLayoutManager

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
        val btnHard: Button = view.findViewById(R.id.button_hard)
        val btnNormal: Button = view.findViewById(R.id.button_normal)
        val btnEasy: Button = view.findViewById(R.id.button_easy)
        val btnhomecard: ImageView = view.findViewById(R.id.button_home_card)
        btnHard.setOnClickListener(this)
        btnEasy.setOnClickListener(this)
        btnNormal.setOnClickListener(this)
        btnhomecard.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_showCard_to_home)
        }


        val helper = Helper()

        cards = mutableListOf()
        adapter = CardAdapter(requireActivity(), cards, helper)
        cardStackView = view.findViewById(R.id.card_stack_view)
        cardLayout = CardStackLayoutManager(requireActivity())
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
                button_normal.isActivated = false
                button_hard.isActivated = false
                button_easy.isActivated = false
                cards.add((cards.size*0.2).roundToInt(), cards[0])
                val setting = SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Left)
                    .setDuration(Duration.Normal.duration)
                    .setInterpolator(AccelerateInterpolator())
                    .build()
                cardLayout.setSwipeAnimationSetting(setting)
                cardStackView.swipe()
//                card stays at pos 1
                cards.remove(cards[0])
                print("hello")
            }

            R.id.button_normal -> {
                button_normal.isActivated = false
                button_hard.isActivated = false
                button_easy.isActivated = false
                cards.add((cards.size*0.6).roundToInt(), cards[0])
                val setting = SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Top)
                    .setDuration(Duration.Normal.duration)
                    .setInterpolator(AccelerateInterpolator())
                    .build()
                cardLayout.setSwipeAnimationSetting(setting)
                cardStackView.swipe()
//                card to middle pos
                cards.remove(cards[0])
                print("hello")
            }

            R.id.button_easy -> {
                button_normal.isActivated = false
                button_hard.isActivated = false
                button_easy.isActivated = false
                cards.add((cards.size*0.9).roundToInt(), cards[0])
                cards.add((cards.size*0.2).roundToInt(), cards[0])
                val setting = SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Right)
                    .setDuration(Duration.Normal.duration)
                    .setInterpolator(AccelerateInterpolator())
                    .build()
                cardLayout.setSwipeAnimationSetting(setting)
                cardStackView.swipe()
//                card to last pos
                cards.remove(cards[0])
                print("hello")
            }

        }
        adapter.notifyDataSetChanged()
    }

    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        val storage: Storage = Storage(db, requireContext())
        storage.getCardsForStackId(stackId.toString(), adapter, cards)
    }

    inner class Helper {
        fun activateButton() {
            button_easy.isActivated = true
            button_normal.isActivated = true
            button_hard.isActivated = true
        }
    }
}



