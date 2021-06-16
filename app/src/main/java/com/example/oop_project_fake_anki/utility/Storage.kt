package com.example.oop_project_fake_anki.utility

import com.example.oop_project_fake_anki.classes.Card
import com.example.oop_project_fake_anki.classes.Stack
import java.io.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

const val USERIDdev = "uxlmFFX19O64PveyJc6l"

class Storage(db: FirebaseFirestore) {


    private var dataBase: FirebaseFirestore = db


    //************ ALL ************\\
    suspend fun getStacksAndCards(): MutableList<Stack> {
        var stacks = getStacks()
        stacks.forEach {
            it.cards = getCardsForStackId(it.stackId)
        }
        return stacks
    }

    //************ STACKS ************\\
    suspend fun getStacks(): MutableList<Stack> {
        var stacks: MutableList<Stack> = mutableListOf<Stack>()
        val colRef = dataBase.collection("userID/${USERIDdev}/stacks")
        colRef.get().addOnSuccessListener { document ->
            if (document != null) {
                document.forEach {
                    val temp = it.toObject<Stack>()
                    stacks.add(it.toObject<Stack>())
                }
            } else {
                println("no doc")
            }
        }
        return stacks
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
    suspend fun getCardsForStackId(stackId: String): MutableList<Card> {
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
        val dataToPost = ""//hashMapOf()
        val random = "123"
        dataBase.collection("cards")
            .document(random)
            .set(dataToPost).addOnSuccessListener { println("it worked") }
    }

}