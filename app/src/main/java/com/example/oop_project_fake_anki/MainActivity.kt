package com.example.oop_project_fake_anki

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun test(view: View) {
        // print line to terminal
        println("hallo")
        val buttonText: String = "Karten"
        // change text of textview
        textView_test.text = "Moin Moin du landratte"
        // Route to new site
        val intent = Intent(this, test::class.java)
        startActivity(intent)
    }
    fun sendMessage(view: View) {
        println ("Hat es funktioniert?")
    }
}