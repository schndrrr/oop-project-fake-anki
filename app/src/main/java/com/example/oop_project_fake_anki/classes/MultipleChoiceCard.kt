package com.example.oop_project_fake_anki.classes

class MultipleChoiceCard(name: String, id: String) : Card(name, id) {
    var numberOfChoices: Int = 0
    var chosen: Boolean = false

}