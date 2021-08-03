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
import com.example.oop_project_fake_anki.classes.DefaultCard
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
    private lateinit var svCardStack: CardStackView
    private lateinit var adapter: CardAdapter
    private lateinit var cardStackView: CardStackView
    private lateinit var cardLayout: CardStackLayoutManager
    private lateinit var helper: Helper
    private var cards: MutableList<DefaultCard> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /** initiate view */
        val view = inflater.inflate(R.layout.fragment_show_card, container, false)
        helper = Helper(view)
        /** Set on click listeners */
        view.button_hard.setOnClickListener(this)
        view.button_easy.setOnClickListener(this)
        view.button_normal.setOnClickListener(this)

        /** ROUTING */
        view.ic_showcard_add.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_showCard_to_createCard)
        }
        view.button_home_card.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_showCard_to_home)
        }


        adapter = CardAdapter(requireActivity(), cards, helper)
        cardStackView = view.findViewById(R.id.card_stack_view)
        cardLayout = CardStackLayoutManager(requireActivity())
        cardLayout.setStackFrom(StackFrom.Top)
        cardStackView.layoutManager = cardLayout
        cardStackView.layoutManager
        cardStackView.adapter = adapter

        // cardLayout.cardStackListener.onCardSwiped()

        EventChangeListener()

        return view
    }

    override fun onClick(v: View?) {
        if (cards.size <= 3) {
            val copy = cards[0]
            for (i in 0..(3 - cards.size)) {
                cards.add(copy)
            }
            adapter.notifyDataSetChanged()
        }
        when (v?.id) {
            R.id.button_hard -> {
                helper.deactivateButton()
                val firstCard = cards[0]
                cards.add((cards.size*0.2).roundToInt(), firstCard)
                cards.removeAt(0)
                adapter.notifyDataSetChanged()
                cardLayout.setSwipeAnimationSetting(helper.swipeLeft)
                cardStackView.swipe()
                println(cards[0].answer)
                helper.noCards()
            }

            R.id.button_normal -> {
                helper.deactivateButton()
                val firstCard = cards[0]
                cards.add((cards.size*0.6).roundToInt(), firstCard)
                cards.removeAt(0)
                adapter.notifyDataSetChanged()
                cardLayout.setSwipeAnimationSetting(helper.swipeUp)
                cardStackView.swipe()
                helper.noCards()
            }

            R.id.button_easy -> {
                helper.deactivateButton()
                cards.removeAt(0)
                adapter.notifyDataSetChanged()
                cardLayout.setSwipeAnimationSetting(helper.swipeRight)
                cardStackView.swipe()
                helper.noCards()
            }

        }
        adapter.notifyDataSetChanged()
    }

    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        val storage: Storage = Storage(db, requireActivity())
        storage.getCardsForStackId(stackId.toString(), adapter, cards)
        println(cards.size)
    }

    inner class Helper(v: View?) {
        val swipeLeft: SwipeAnimationSetting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
        val swipeUp: SwipeAnimationSetting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Top)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
        val swipeRight: SwipeAnimationSetting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
        fun activateButton() {
            button_easy.isActivated = true
            button_normal.isActivated = true
            button_hard.isActivated = true
        }
        fun deactivateButton() {
            button_normal.isActivated = false
            button_hard.isActivated = false
            button_easy.isActivated = false

        }
        fun noCards() {
            if (adapter.itemCount == 0) {
                deactivateButton()
            }
        }
    }
}



