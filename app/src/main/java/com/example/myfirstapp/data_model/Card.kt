package com.example.myfirstapp.data_model

class Card {
    val imageUri: String
    val number : Int
    private val suit : Suit

    constructor(num: Int, cardSuit: Suit) {
        number = getFace(num)
        suit = cardSuit
        imageUri = makeImageUri()
    }

    constructor(num: Int) {
        number = getFace(num)
        suit = getSuit(num)
        imageUri = makeImageUri()
    }

    enum class Suit(id : Int) {
        CLUBS(0),
        DIAMONDS(1),
        HEARTS(2),
        SPADES(3)
    }

    private fun getSuit(index: Int) : Suit {
        return Suit.values().first {it.ordinal == index % 4}
    }

    private fun getFace(index: Int) : Int {
        return ((index - 1) % 13) + 1
    }

    fun getValue() : Int {
        return if(this.number <= 10){
            this.number
        }
        else {
            10
        }
    }

    fun getName() : String {
        return when (this.number){
            1 -> "ace"
            11 -> "jack"
            12 -> "queen"
            13 -> "king"
            else -> this.number.toString()
        }
    }

    private fun makeImageUri() : String {
        val prefix = if(this.getName().all { Character.isDigit(it) }) {
            "_"
        }
        else {
            ""
        }

        return prefix + this.getName() + "_of_" + this.suit.name.lowercase() + ".png"
    }
}