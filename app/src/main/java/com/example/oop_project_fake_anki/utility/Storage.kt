/*
* Version: 1.0
* Developer: Johann Schneider, Johann-Georg Nitsche
* Date of creation: 14.05.21
* Date of last change: 04.08.2021
*
* Content: class Storage - manages the storage of data (e.g. stacks and their associated cards) in the Firebase Cloud storage tool
*
*/

package com.example.oop_project_fake_anki.utility

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.ArrayAdapter
import com.example.oop_project_fake_anki.CardAdapter
import com.example.oop_project_fake_anki.classes.Card
import com.example.oop_project_fake_anki.classes.Stack
import com.example.oop_project_fake_anki.ShowStackAdapter
import com.example.oop_project_fake_anki.classes.DefaultCard
import com.example.oop_project_fake_anki.classes.User
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.toObject

class Storage(db: FirebaseFirestore, context: Context) {

    private var dataBase: FirebaseFirestore = db
    private var scope: Context = context
    var productionDefId: String = ""
    var stacks: MutableList<Stack> = mutableListOf<Stack>()

    //  initialisation
    init {
        checkForUID()
    }

    //  add stack to a List stacksAdapter and notify datachange of ShowStackAdapter
    fun getStacksForAdapter(adapter: ShowStackAdapter, stacksAdapter: MutableList<Stack>) {
        val colRef = dataBase.collection("userID/${productionDefId}/stacks")
        colRef.addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    Log.e("Firebase Error:", error.message.toString())
                }
                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc != null) {
                        stacksAdapter.add(dc.document.toObject<Stack>())
                    }
                }
                adapter.notifyDataSetChanged()
            }
        })
    }

    //  add stack to list called stacks and its name to an array
    fun getStacksForSpinner(stacks: MutableList<Stack>, adapter: ArrayAdapter<String>) {
        val colRef = dataBase.collection("userID/${productionDefId}/stacks")
        colRef.addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    Log.e("Firebase Error:", error.message.toString())
                }
                for (dc: DocumentChange in value?.documentChanges!!) {
                    val doc = dc.document.toObject<Stack>();
                    stacks.add(doc)
                    adapter.add(doc.name)
                }
            }
        })
    }

    //  post data of a stack to firebase
    fun postStack(data: Stack) {
        val id = generateUniqueId()
        val dataToPost = hashMapOf(
            "stackId" to id,
            "name" to data.name
        )
        dataBase.collection("userID/${productionDefId}/stacks")
            .document(id.toString())
            .set(dataToPost).addOnSuccessListener { println("it worked") }
    }

    //  add all cards with same stackId to a list and update the CardAdapter
    fun getCardsForStackId(stackId: String, adapter: CardAdapter, cards: MutableList<DefaultCard>) {
        val colRef = dataBase.collection("userID/${productionDefId}/cards")
        colRef.addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    Log.e("Firebase Error:", error.message.toString())
                }
                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.document.toObject<Card>().stackId == stackId) {
                        cards.add(dc.document.toObject<DefaultCard>())
                    }
                }
                adapter.notifyDataSetChanged()
            }
        })
    }

    //  post data of a card to firebase
    fun postCard(data: DefaultCard) {
        val dataToPost = hashMapOf(
            "question" to data.question,
            "answer" to data.answer,
            "stackId" to data.stackId
        )
        val randomId = generateUniqueId()
        dataBase.collection("userID/${productionDefId}/cards")
            .document(randomId.toString())
            .set(dataToPost).addOnSuccessListener { println("it worked") }
    }

    //  get data of a User from firebase
    fun getUserData() {
        val colRef =
            dataBase.collection("userID/${productionDefId}/userData").document(productionDefId)
        colRef.get().addOnSuccessListener { document ->
            var userData = document.toObject<User>()
        }
    }

    //  generates a unique id from a given alphabet
    fun generateUniqueId(): String {
        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        var uniqueId: String = "";
        for (i in 0..20) {
            uniqueId += alphabet[(0..61).random()]
        }
        return uniqueId
    }

    //  save a userID to the device using SharedPreferences
    fun saveData(context: Context) {
        val id = "UID" + generateUniqueId()
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("shared", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply {
            putString("KEY_ID", id)
        }.apply()
    }

    //  load a userID from a device using SharedPreferences
    fun loadData(context: Context): String {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("shared", Context.MODE_PRIVATE)
        val savedId: String = sharedPreferences.getString("KEY_ID", null).toString()
        return savedId
    }

    //  check if userID already exists and if not create a new userID
    fun checkForUID() {
        if (productionDefId.isNullOrEmpty()) {
            productionDefId = loadData(scope)
            if (productionDefId.isNullOrEmpty() || productionDefId == "null") {
                saveData(scope)
            }
        }
    }
}