package com.example.oop_project_fake_anki

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.oop_project_fake_anki.classes.Stack
import com.example.oop_project_fake_anki.utility.Storage
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [homeScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class homeScreen : Fragment(), View.OnClickListener {
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
        val view: View = inflater.inflate(R.layout.fragment_home_screen, container, false)
        val btn: Button = view.findViewById(R.id.homeButton)
        btn.setOnClickListener(this)
        return view
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.homeButton -> {
                var scope = runBlocking {
                    getData()
                }
            }
        }
    }

    suspend fun getData() = coroutineScope {
        val db = FirebaseFirestore.getInstance()
        val s = Storage(db);
        var stacks: MutableList<Stack> = mutableListOf()
        launch {
            // get data from firebase
            stacks = s.getStacksAndCards()
        }
        println(stacks)
    }
}
