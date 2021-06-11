package com.example.oop_project_fake_anki

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val db = FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun test(view: View) {
        view.findNavController().navigate(R.id.homeScreen)
    }

    fun homeScreen(view: View) {
        view.findNavController().navigate(R.id.homeScreen)
    }
}