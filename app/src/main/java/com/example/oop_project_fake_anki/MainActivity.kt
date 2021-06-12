package com.example.oop_project_fake_anki

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.oop_project_fake_anki.classes.Card
import com.example.oop_project_fake_anki.classes.DefaultCard
import com.example.oop_project_fake_anki.classes.Stack
import com.example.oop_project_fake_anki.utility.Storage

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val s = Storage()
//        s.getData(applicationContext, "test.json")
//        s.saveData("json")
        var data: String = s.getData(applicationContext, "storage.json")
        var stack: List<Stack> = s.parseData(data)
//        stack.forEach {
//            println(it.name.toString())
//        }


//        hier mal ein teil der default card mit Wertezuweisung

        val defCard1 = DefaultCard("testCard", "1")
        defCard1.description=="testcard description or answer"
        defCard1.answer=="testcard answer"
    }
}