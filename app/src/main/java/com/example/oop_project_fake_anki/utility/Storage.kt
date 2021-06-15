package com.example.oop_project_fake_anki.utility

import com.example.oop_project_fake_anki.classes.Card
import com.example.oop_project_fake_anki.classes.Stack
import java.io.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class Storage(db: FirebaseFirestore) {

    private var dataBase: FirebaseFirestore = db


    //************ ALL ************\\
    fun getStacksAndCards(): MutableList<Stack> {
        var stacks = getStacks()
        stacks.forEach {
            it.cards = getCardsForStackId(it.id)
        }
        return stacks
    }

    //************ STACKS ************\\
    fun getStacks(): MutableList<Stack> {
        var stacks: MutableList<Stack> = mutableListOf<Stack>()
        val colRef = dataBase.collection("stacks")
        colRef.get().addOnSuccessListener { document ->
            if (document != null) {
                document.forEach {
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
            "id" to data.id,
            "name" to data.name
            // TODO add all properties of Stack
        )
        val random = "123"
        dataBase.collection("stacks")
            .document(random)
            .set(dataToPost).addOnSuccessListener { println("it worked") }
    }

    //************ CARDS ************\\
    private fun getCardsForStackId(stackId: String): MutableList<Card> {
        var cards: MutableList<Card> = mutableListOf<Card>()
        val colRef = dataBase.collection("cards")
        colRef
            .whereEqualTo("stackId", stackId)
            .get()
            .addOnSuccessListener { document ->
            if (document != null) {
                document.forEach {
                    cards.add(it.toObject<Card>())
                }
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