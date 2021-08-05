/*
* Version: 1.0
* Developer: Johann Schneider
* Date of creation: 14.05.21
* Date of last change: 04.08.2021
*
* Content: class Stack - contains a stack of cards
*
*/

package com.example.oop_project_fake_anki.classes

data class Stack(var stackId: String = "", var name: String = "", var numberOfCards: String = "") {
    var cards: MutableList<Card> = mutableListOf<Card>()
}