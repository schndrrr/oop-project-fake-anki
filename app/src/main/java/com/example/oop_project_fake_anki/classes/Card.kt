package com.example.oop_project_fake_anki.classes

open class Card constructor(var question: String = "", var answer: String = "", var stackId: String = "", var index: String = "") {
    var learningState: String = ""

    fun updateLearningState(){

    }
}