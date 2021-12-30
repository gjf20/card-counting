package com.example.myfirstapp.data_model

class Deck {
    private val standardDeckSize = 52
    private val cardArr : Array<Card>
    private var topCardIndex = 0
    private var count = 0

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
        adjustCount(card)
        return card
    }

    fun getCount() : Int {
        return count
    }

    fun getCountString() : String {
        val count = getCount()
        var prefix = ""
        if (count > 0) {
            prefix = "+"
        }
        return prefix + count
    }

    private fun adjustCount(c : Card) {
        val value = c.getValue()
        if (value in 2..6){
            count += 1
        } else if (value == 10 || value == 1) {
            count -= 1
        }
    }

}