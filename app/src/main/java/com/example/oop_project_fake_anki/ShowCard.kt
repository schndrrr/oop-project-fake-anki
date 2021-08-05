/*
*   Version: v1.0
*   Author: Niklas Dreger, Florian Hager, Johann-Georg Nitsche, Johannes Wilhelm
*   date of creation:   16.06.21
*   date of last change:    04.08.21
*   content: class ShowCard - Fragment to display and interact with cards
*/

package com.example.oop_project_fake_anki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.navigation.Navigation
import com.example.oop_project_fake_anki.classes.DefaultCard
import com.example.oop_project_fake_anki.utility.Storage
import com.google.firebase.firestore.FirebaseFirestore
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.fragment_show_card.*
import kotlinx.android.synthetic.main.fragment_show_card.view.*
import kotlin.math.roundToInt

class ShowCard : Fragment(), View.OnClickListener {

    private var stackId: String? = null
    private lateinit var db: FirebaseFirestore
    private lateinit var svCardStack: CardStackView
    private lateinit var adapter: CardAdapter
    private lateinit var cardStackView: CardStackView
    private lateinit var cardLayout: CardStackLayoutManager
    private lateinit var helper: Helper
    private var cards: MutableList<DefaultCard> = mutableListOf()

    //  initialise Bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            stackId = it.getString("stackIdValue")
            println("$stackId")
        }
    }

    //  initialise view
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_show_card, container, false)
        helper = Helper(view)
        //   onClick listeners for button_easy, button_normal, button_hard
        view.button_easy.setOnClickListener(this)
        view.button_normal.setOnClickListener(this)
        view.button_hard.setOnClickListener(this)

        //  routing
        view.ic_showcard_add.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_showCard_to_createCard)
        }
        view.button_home_card.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_showCard_to_home)
        }

        adapter = CardAdapter(requireActivity(), cards, helper)

        cardLayout = CardStackLayoutManager(requireActivity())
        cardLayout.setStackFrom(StackFrom.Top)
        cardLayout.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        cardLayout.setCanScrollHorizontal(false)
        cardLayout.setCanScrollVertical(false)
        cardLayout.setVisibleCount(200)

        cardStackView = view.findViewById(R.id.card_stack_view)
        cardStackView.layoutManager = cardLayout
        cardStackView.layoutManager
        cardStackView.adapter = adapter

        eventChangeListener()
        return view
    }

    //  onClick events of view
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_hard -> {
                helper.deactivateButton()
                cardLayout.setSwipeAnimationSetting(helper.swipeLeft)
                cardStackView.swipe()
                val firstCard = cards[0]
                cards.removeAt(0)
                adapter.notifyItemRemoved(0)
                cards.add((cards.size*0.2).roundToInt(), firstCard)
                adapter.notifyItemInserted((cards.size*0.2).roundToInt())
                helper.noCards()
            }

            R.id.button_normal -> {
                helper.deactivateButton()
                cardLayout.setSwipeAnimationSetting(helper.swipeUp)
                cardStackView.swipe()
                val firstCard = cards[0]
                cards.removeAt(0)
                adapter.notifyItemRemoved(0)
                cards.add((cards.size*0.6).roundToInt(), firstCard)
                adapter.notifyItemInserted((cards.size*0.2).roundToInt())
                helper.noCards()
            }

            R.id.button_easy -> {
                helper.deactivateButton()
                cards.removeAt(0)
                adapter.notifyItemRemoved(0)
                cardLayout.setSwipeAnimationSetting(helper.swipeRight)
                cardStackView.swipe()
                helper.noCards()
            }

        }
    }

    //  update card and adapter changes from storage
    private fun eventChangeListener() {
        db = FirebaseFirestore.getInstance()
        val storage: Storage = Storage(db, requireActivity())
        storage.getCardsForStackId(stackId.toString(), adapter, cards)
        println(cards.size)
    }

    //  class Helper - handles swipe animation and button behaviour
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

        //  activate button_easy, button_normal, button_hard
        fun activateButton() {
            button_easy.isActivated = true
            button_normal.isActivated = true
            button_hard.isActivated = true
        }

        //  deactivate button_easy, button_normal, button_hard
        fun deactivateButton() {
            button_normal.isActivated = false
            button_hard.isActivated = false
            button_easy.isActivated = false

        }

        //  deactivate buttons if no cards available
        fun noCards() {
            if (adapter.itemCount == 0) {
                deactivateButton()
            }
        }
    }
}



