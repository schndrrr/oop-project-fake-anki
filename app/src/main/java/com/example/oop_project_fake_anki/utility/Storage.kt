package com.example.oop_project_fake_anki.utility

import com.example.oop_project_fake_anki.classes.Stack
import java.io.*
import com.google.firebase.firestore.FirebaseFirestore

class Storage(db: FirebaseFirestore) {

    private var dataBase: FirebaseFirestore = db

    fun getDataForId(id: String) {
        val colRef = dataBase.collection("stacks")
        colRef.get().addOnSuccessListener { document ->
            if (document != null) {
                var stacks: MutableList<Stack> = mutableListOf<Stack>()
                document.forEach {
                    println(it.data)
                    // TODO
                }
            } else {
                println("no doc")
            }
        }
    }

    fun postData(data: Stack) {

        val dataToPost = hashMapOf(
            "id" to data.id,
            "name" to data.name
        )
        val random = "123"
        dataBase.collection("stacks")
            .document(random)
            .set(dataToPost).addOnSuccessListener { println("it worked") }
    }
}