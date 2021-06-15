package com.example.oop_project_fake_anki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.findNavController
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
        val view = inflater.inflate(R.layout.fragment_show_card, container, false)
        view.button_home.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_home)}
        //view.button_hard.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_front_of_card)}
        //view.button_normal.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_front_of_card)}
        //view.button_easy.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_front_of_card)}
        val btn: Button = view.findViewById(R.id.button_hard)
        val btnNormal: Button = view.findViewById(R.id.button_normal)
        val btnEasy: Button = view.findViewById(R.id.button_easy)
        btn.setOnClickListener(this)
        btnEasy.setOnClickListener(this)
        btnNormal.setOnClickListener(this)
        return view
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
            R.id.button_normal -> {
                // TODO
                println("hallo button normal")
            }
        }
    }

}