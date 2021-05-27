package com.example.oop_project_fake_anki

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun test(view: View) {
        view.findNavController().navigate(R.id.homeScreen)
    }

    fun test2(view: View) {
        view.findNavController().navigate(R.id.createCard)
    }

    fun homeScreen(view: View) {
        view.findNavController().navigate(R.id.homeScreen)
    }
}