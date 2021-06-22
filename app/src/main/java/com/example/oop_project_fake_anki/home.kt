package com.example.oop_project_fake_anki

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.Group
import androidx.navigation.Navigation
import io.grpc.util.TransmitStatusRuntimeExceptionInterceptor
import kotlinx.android.synthetic.main.fragment_home.view.*
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
@Suppress("UNREACHABLE_CODE")
class home : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        view.ic_home_home.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.action_home)}

        //NumberStacks gets information about existing stacks
        val NumberStacks = 1
        //Declaration of the two groups
        val Welcome: Group = view.findViewById(R.id.WelcomeHome)
        val Standard: Group = view.findViewById(R.id.StandardHome)

        //If the number of stacks is greater than or equal to one, the standard view becomes active
        if (NumberStacks >= 1) {
            Standard.visibility = View.VISIBLE
            Welcome.visibility = View.INVISIBLE
        //Otherwise the welcome view will be activated
        } else {
            Standard.visibility = View.INVISIBLE
            Welcome.visibility = View.VISIBLE
        }
        return view
    }
}