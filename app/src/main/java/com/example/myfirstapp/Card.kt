package com.example.myfirstapp

class Card(val number: Int, val suit: Suit) {

    enum class Suit(id : String) {
        CLUBS("clubs"),
        DIAMONDS("diamonds"),
        HEARTS("hearts"),
        SPADES("spades")
    }


    fun getValue() : Int {
        return if(this.number <= 10){
            this.number
        }
        else {
            10
        }
    }
}