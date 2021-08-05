/*
*   Version: v1.0
*   Author: Johann Schneider
*   date of creation:   07.05.21
*   date of last change:    07.07.21
*   content: class MainActivity - Main Class and entry point of the app
*/

package com.example.oop_project_fake_anki
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}