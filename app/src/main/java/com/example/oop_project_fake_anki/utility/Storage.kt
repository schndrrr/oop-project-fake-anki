package com.example.oop_project_fake_anki.utility

import com.example.oop_project_fake_anki.classes.Card
import com.example.oop_project_fake_anki.classes.Stack
import java.io.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


const val USERIDdev = "uxlmFFX19O64PveyJc6l"

class Storage(db: FirebaseFirestore) {


    private var dataBase: FirebaseFirestore = db

    var stacks: MutableList<Stack> = mutableListOf<Stack>()

    //************ ALL ************\\
    fun getStacksAndCards() {
            getStacks()
            stacks.forEach {
                it.cards = getCardsForStackId(it.stackId)
            }
    }

    //************ STACKS ************\\
    fun getStacks() {
        val colRef = dataBase.collection("userID/${USERIDdev}/stacks")
        colRef.get().addOnSuccessListener { document ->
            if (document != null) {
                document.forEach {
                    stacks.add(it.toObject<Stack>())
                    println(it.toObject<Stack>().stackId)
                }
            } else {
                println("no doc")
            }
        }
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
    fun getCardsForStackId(stackId: String): MutableList<Card> {
        var cards: MutableList<Card> = mutableListOf<Card>()
        val colRef = dataBase.collection("userID/${USERIDdev}/cards")
        colRef
            .whereEqualTo("stackId", stackId)
            .get()
            .addOnSuccessListener { document ->
            if (document != null) {
                document.forEach {
                    cards.add(it.toObject<Card>())
                }
                println(cards)
            } else {
                println("no doc")
            }
        }
        return cards
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