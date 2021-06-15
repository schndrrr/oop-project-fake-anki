package com.example.oop_project_fake_anki.classes

class Stack(var id: String = "", var name: String = "") {
    // TODO
    var cards: MutableList<Card> = mutableListOf<Card>()
}