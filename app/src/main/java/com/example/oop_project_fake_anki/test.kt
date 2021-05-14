package com.example.oop_project_fake_anki

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test.*


class test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }

    fun routeBack(view: View) {
        println("moin")
        textView_do.text = "aha"
        val intent = Intent(baseContext, MainActivity::class.java)
        startActivity(intent)
    }
}