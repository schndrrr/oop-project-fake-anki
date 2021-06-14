package com.example.oop_project_fake_anki

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.oop_project_fake_anki.classes.Stack
import com.example.oop_project_fake_anki.utility.Storage
import com.google.firebase.firestore.FirebaseFirestore

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
        val view: View = inflater.inflate(R.layout.fragment_create_card, container, false)
        val btn: Button = view.findViewById(R.id.button)
        btn.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View) {
        when(v?.id) {
            R.id.button -> {
                // TODO
                println("hallo")
                val db = FirebaseFirestore.getInstance()
                val s = Storage(db);
                val testStack: Stack = Stack("testTest", "EinName")
                s.postData(testStack)
            }
        }
    }

}