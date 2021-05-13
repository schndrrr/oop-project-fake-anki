package com.example.oop_project_fake_anki

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout.*

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
        setContentView(R.layout.layout)
        textView_newSite.text = "Ja und das geht auch"
    }
}