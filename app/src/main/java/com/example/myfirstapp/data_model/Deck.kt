package com.example.myfirstapp.data_model

import kotlin.math.roundToInt

class Deck {
    private val standardDeckSize = 52
    private val cardArr : Array<Card>
    private var topCardIndex = 0
    private var runningCount = 0

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

    fun getRunningCount() : Int {
        return runningCount
    }

    fun getTrueCount() : Float {
        val decksLeft = ((cardArr.size - topCardIndex) / standardDeckSize.toFloat()).roundToInt() //estimate the number of decks left
        return runningCount / decksLeft.toFloat()
    }

    fun getCountString(count : Int) : String { //TODO move to common
        var prefix = ""
        if (count > 0) {
            prefix = "+"
        }
        return prefix + count
    }

    fun getCountString(count : Float) : String { //TODO move to common
        var prefix = ""
        if (count > 0) {
            prefix = "+"
        }
        return prefix + ((count * 100).roundToInt() / 100)
    }

    private fun adjustCount(c : Card) {
        val value = c.getValue()
        if (value in 2..6){
            runningCount += 1
        } else if (value == 10 || value == 1) {
            runningCount -= 1
        }
    }

}