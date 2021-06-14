package com.example.oop_project_fake_anki.classes

class DefaultCard(name: String, id: String) : Card(name, id) {
    val description: String = "defaultValue"
    val answer: String = "defaultValue"
}
