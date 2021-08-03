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
class home : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private lateinit var db: FirebaseFirestore
    private lateinit var storage: Storage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        //        view.ic_home_home.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.ac)}

        db = FirebaseFirestore.getInstance()
        storage = Storage(db, requireActivity())
        val userData = storage.getUserData()

        val colRef = db.collection("userID/${storage.productionDefId}/userData").document(storage.productionDefId)
        colRef.get().addOnSuccessListener { document ->
            var userData = document.toObject<User>()
            view.txt_home_frame_cards_number.text = userData?.numberOfCards
            view.txt_home_frame_time_number.text = userData?.numberOfStacks
        }

        //Declaration of the two groups
        val welcome: Group = view.findViewById(R.id.WelcomeHome)
        val standard: Group = view.findViewById(R.id.StandardHome)

        standard.visibility = View.VISIBLE
        welcome.visibility = View.INVISIBLE

        val addbtn = view.findViewById(R.id.ic_home_add) as ImageView
        addbtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_home_to_createCard)
        }
        val wlcmbtn = view.findViewById(R.id.txt_home_frame_button) as ImageView
        wlcmbtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_home_to_showStack)
            //navigate to showStack action_home_to_showStack
        }

        return view
    }
}
