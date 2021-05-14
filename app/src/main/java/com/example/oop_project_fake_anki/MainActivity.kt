package com.example.oop_project_fake_anki

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_backside_of_card.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun routeToNewCard(view: View) {
        val intent = Intent(this, CreateCard::class.java)
        startActivity(intent)
    }
    fun routeToNextCard(view: View) {
        val intent = Intent (this, GetNextCard::class.java)
        startActivity(intent)
    }
}