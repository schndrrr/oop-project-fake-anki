package com.example.oop_project_fake_anki

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.oop_project_fake_anki.classes.Stack
//import kotlinx.android.synthetic.main.fragment_create_Stack.view.*
//import kotlinx.android.synthetic.main.fragment_create_Stack.*

import com.example.oop_project_fake_anki.utility.Storage
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_show_stack.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [createCard.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateStack : Fragment(), View.OnClickListener {
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
        val view = inflater.inflate(R.layout.fragment_show_stack, container, false)
        view.button_create_stack.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_home)}

        val btn_home: Button = view.findViewById(R.id.button_Home)
        val btn_createStack: Button = view.findViewById(R.id.button_create_stack)

//        btn_home.setOnClickListener(this)
        btn_createStack.setOnClickListener(this)


        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            // TODO
            R.id.button_Home -> {

            }

            R.id.button_create_stack -> {
                val stack = Stack("stack_nr_1", "")

                stack.name= "halllooo"

                println(stack.name)
                println("stack.namestack.namestack.namestack.namestack.namestack.namestack.namestack.name")

            }
        }
    }

}