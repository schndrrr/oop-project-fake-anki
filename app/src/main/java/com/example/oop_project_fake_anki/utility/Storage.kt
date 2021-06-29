package com.example.oop_project_fake_anki.utility

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.example.oop_project_fake_anki.cardAdapter
import com.example.oop_project_fake_anki.classes.Card
import com.example.oop_project_fake_anki.classes.Stack
import com.example.oop_project_fake_anki.showStackAdapter
import com.google.firebase.firestore.*
import java.io.*
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.Timestamp


var USERIDdev : String = "uxlmFFX19O64PveyJc6l"

class Storage(db: FirebaseFirestore) {

    private var dataBase: FirebaseFirestore = db

    var stacks: MutableList<Stack> = mutableListOf<Stack>()

    //************ ALL ************\\
    //fun getStacksAndCards() {
    //        stacks.forEach {
    //            it.cards = getCardsForStackId(it.stackId)
    //        }
    //}

    //************ STACKS ************\\
    fun getStacksForAdapter(adapter: showStackAdapter, stacksAdapter: MutableList<Stack>) {
        val colRef = dataBase.collection("userID/${USERIDdev}/stacks")
        colRef.addSnapshotListener(object: EventListener<QuerySnapshot> {
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

    fun postStack(data: Stack) {
        val id = generateUniqueIdFromTimestamp()
        // TODO create random id generator
        val dataToPost = hashMapOf(
            "id" to id,
            "name" to data.name
            // TODO add all properties of Stack
        )
        dataBase.collection("userID/${USERIDdev}/stacks")
            .document(id.toString())
            .set(dataToPost).addOnSuccessListener { println("it worked") }
    }

    //************ CARDS ************\\
    fun getCardsForStackId(stackId: String, adapter: cardAdapter, cards: MutableList<Card>) {
        val colRef = dataBase.collection("userID/${USERIDdev}/cards")
        colRef.addSnapshotListener(object: EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    Log.e("Firebase Error:", error.message.toString())
                }
                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc != null) {
                        cards.add(dc.document.toObject<Card>())
                    }
                }
                adapter.notifyDataSetChanged()
            }
        })
    }

    fun postCard(data: Card) {

        // TODO hashMapOf
        val dataToPost = hashMapOf(
            "question" to data.question,
            "answer" to data.answer,
            "index" to data.index,
            "stackId" to data.stackId

        )
        val randomId = System.currentTimeMillis()
        dataBase.collection("userID/${USERIDdev}/cards")
            .document(randomId.toString())
            .set(dataToPost).addOnSuccessListener { println("it worked") }
    }

    fun generateUniqueIdFromTimestamp(): String {
        val uniqueId = System.currentTimeMillis().toString()
        return uniqueId
    }

    // on function call use requireActivity()
    fun saveData(context : Context) {
        val id = "UID" + System.currentTimeMillis().toString()
        val sharedPreferences : SharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply{
            putString("KEY_ID", id)
        }.apply()
        //        println("$id")
    }

    fun loadData(context : Context): String {
        val sharedPreferences : SharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
        val savedId: String = sharedPreferences.getString("KEY_ID", null).toString()
        //        println("UserID = $savedId")
        return savedId
    }

    fun checkForUID(context: Context) {
        if (USERIDdev.isNullOrEmpty()) {
            saveData(context)
            USERIDdev = loadData(context)
        }
    }
}