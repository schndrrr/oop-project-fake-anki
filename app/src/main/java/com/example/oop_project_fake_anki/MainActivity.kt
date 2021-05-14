package com.example.oop_project_fake_anki

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.oop_project_fake_anki.CreateCard.CreateCard

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun routeToNewCard(view: View) {
        val intent = Intent(this, CreateCard::class.java)
        startActivity(intent)
    }


}