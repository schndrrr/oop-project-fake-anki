package com.example.oop_project_fake_anki

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.oop_project_fake_anki.classes.DefaultCard
import com.google.firebase.FirebaseApp

//new layout branch
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContentView(R.layout.activity_main)
    }

    fun test(view: View) {
        view.findNavController().navigate(R.id.homeScreen)
//        hier mal ein teil der default card mit Wertezuweisung

        val defCard1 = DefaultCard("testCard", "1")
        defCard1.description=="testcard description or answer"
        defCard1.answer=="testcard answer"
    }
}