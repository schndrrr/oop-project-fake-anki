package com.example.oop_project_fake_anki.classes

data class Stack(var stackId: String = "", var name: String = "", var numberOfCards: String = "") {
    // TODO
    var cards: MutableList<Card> = mutableListOf<Card>()
}