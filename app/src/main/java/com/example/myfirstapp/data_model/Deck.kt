package com.example.myfirstapp.data_model

class Deck {
    private val standardDeckSize = 52
    private val cardArr : Array<Card>
    private var topCardIndex = 0

    constructor(numDecks : Int) {
        cardArr = Array<Card>(numDecks * standardDeckSize, fun(i : Int): Card {return Card(i+1)})
    }

    fun getLength() : Int {
        return cardArr.size
    }

    fun drawCard() : Card? {
        if (topCardIndex >= cardArr.size){
            return null
        }
        val card = cardArr[topCardIndex]
        topCardIndex++
        return card
    }

}