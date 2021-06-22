package com.example.oop_project_fake_anki.utility

import android.util.Log
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


const val USERIDdev = "uxlmFFX19O64PveyJc6l"

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

        val dataToPost = hashMapOf(
            "id" to data.stackId,
            "name" to data.name
            // TODO add all properties of Stack
        )
        val random = "123"
        dataBase.collection("stacks")
            .document(random)
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

}