package com.example.oop_project_fake_anki.utility

import com.example.oop_project_fake_anki.classes.Stack
import com.google.firebase.firestore.FirebaseFirestore

class Storage(db: FirebaseFirestore) {

    private var dataBase: FirebaseFirestore = db

    fun getDataForId(id: String) {
        // TODO
        val colRef = dataBase.collection("stacks")
        colRef.get().addOnSuccessListener { document ->
            if (document != null) {
                println(document)
            } else {
                println("no doc")
            }
        }
    }

    fun postData(data: Stack) {
        // TODO

        println(data.id)


        val dataToPost = hashMapOf(
            "id" to data.id,
            "name" to data.name
        )
        dataBase.collection("stacks")
            .document("rand")
            .set(dataToPost).addOnSuccessListener { println("it worked") }
    }

}