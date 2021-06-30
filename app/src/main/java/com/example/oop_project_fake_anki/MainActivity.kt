package com.example.oop_project_fake_anki
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oop_project_fake_anki.classes.DefaultCard
import com.example.oop_project_fake_anki.utility.Storage
import com.example.oop_project_fake_anki.utility.StorageService
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

//new layout branch
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val storageService = StorageService()
//        startService(intent)
//        storageService.test = "Moin moin"
    }
}