package com.example.oop_project_fake_anki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.Group
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_show_card.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShowCard.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("UNREACHABLE_CODE")
class home : Fragment(), View.OnClickListener

    //private val listener: OnItemClickListener)
{

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
            //        view.ic_home_home.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.ac)}

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
            val addbtn = view.findViewById(R.id.ic_home_add) as ImageView
            addbtn.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_home_to_createCard)
                //navigate to createCard
            }
            val starbtn = view.findViewById(R.id.ic_home_favorites) as ImageView
            starbtn.setOnClickListener {

            }
            val headbtn = view.findViewById(R.id.ic_home_profile) as ImageView
            headbtn.setOnClickListener {

            }
            val optnbtn = view.findViewById(R.id.imageView) as ImageView
            optnbtn.setOnClickListener {

            }
            val wlcmbtn = view.findViewById(R.id.txt_home_frame_button) as ImageView
            wlcmbtn.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_home_to_showStack)
                //navigate to showStack action_home_to_showStack
            }

            return view
        }

        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }
    }
