/*
*   Version: v1.0
*   Author: Niklas Dreger, Johannes Wilhelm
*   date of creation:   11.06.21
*   date of last change:    04.08.21
*   content: class Home - Fragment displays home screen of the app and manages interactions
*/

package com.example.oop_project_fake_anki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.Group
import androidx.navigation.Navigation
import com.example.oop_project_fake_anki.classes.User
import com.example.oop_project_fake_anki.utility.Storage
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class Home : Fragment() {

    private lateinit var db: FirebaseFirestore
    private lateinit var storage: Storage

    // initialise bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //  initialise view
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        db = FirebaseFirestore.getInstance()
        storage = Storage(db, requireActivity())

        //  declaration of the two groups
        val welcome: Group = view.findViewById(R.id.WelcomeHome)
        val standard: Group = view.findViewById(R.id.StandardHome)

        val userData = storage.getUserData()
        val colRef = db.collection("userID/${storage.productionDefId}/userData").document(storage.productionDefId)
        colRef.get().addOnSuccessListener { document ->
            var userData = document.toObject<User>()
            view.txt_home_frame_cards_number.text = userData?.numberOfCards
            view.txt_home_frame_time_number.text = userData?.numberOfStacks

            println(userData?.numberOfStacks)
            if (userData?.numberOfStacks == "" || userData?.numberOfStacks.isNullOrEmpty() || userData?.numberOfStacks == "0") {
                ic_home_add.visibility = View.INVISIBLE
                standard.visibility = View.INVISIBLE
                welcome.visibility = View.VISIBLE
            } else {
                ic_home_add.visibility = View.VISIBLE
                standard.visibility = View.VISIBLE
                welcome.visibility = View.INVISIBLE
            }
        }

        //  button  addbtn navigates to card creation
        val addbtn = view.findViewById(R.id.ic_home_add) as ImageView
        addbtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_home_to_createCard)
        }

        //  button wlcmbtn navigates to stack creation
        val wlcmbtn = view.findViewById(R.id.txt_home_frame_button) as ImageView
        wlcmbtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_home_to_showStack)
            //navigate to showStack action_home_to_showStack
        }

        //  button wlcmadd navigates to card creation
        val wlcmadd = view.findViewById(R.id.ic_home_welcome_add) as ImageView
        wlcmadd.setOnClickListener {
        Navigation.findNavController(view).navigate(R.id.action_home_to_createCard)
    }

        return view
    }
}
