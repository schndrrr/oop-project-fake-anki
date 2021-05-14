package com.example.oop_project_fake_anki

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val s = Storage()

        val json = s.getData(applicationContext, "test.json")
        s.saveData(json)
        s.getData(applicationContext, "storage.json")
    }
}