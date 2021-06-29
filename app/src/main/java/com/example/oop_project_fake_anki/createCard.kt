package com.example.oop_project_fake_anki

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.oop_project_fake_anki.classes.DefaultCard
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
class createCard : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_card, container, false)
        view.button_Home.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_createCard_to_home)}

        val btn_home: Button = view.findViewById(R.id.button_Home)
        btn_home.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_createCard_to_home)
        }
        val btn_createCard: Button = view.findViewById(R.id.button_create_card)

      //  btn_home.setOnClickListener(this)
        btn_createCard.setOnClickListener(this)

        return view
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

}