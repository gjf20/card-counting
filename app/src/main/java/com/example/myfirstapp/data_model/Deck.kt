package com.example.myfirstapp.data_model

import java.lang.Exception
import kotlin.math.roundToInt

class Deck {
    private val standardDeckSize = 52
    private val cardArr : Array<Card>
    private var topCardIndex = 0
    private var runningCount = 0

    constructor(numDecks : Int) {
        cardArr = Array<Card>(numDecks * standardDeckSize, fun(i : Int): Card {return Card(i+1)})
    }

    constructor(numDecks : Int, startingCards : Array<Card>) {
        val numCards = numDecks * standardDeckSize
        require (startingCards.size < numCards) {
            "The number of cards in the starting order should not exceed the number of cards in the deck"
        }

        cardArr = Array<Card>(numCards, fun(i : Int): Card {return Card(i+1)})
        val isValidReorder = reorderTop(startingCards)
        require(isValidReorder) {
            "The cards specified in the starting order are not valid for $numDecks copies of a standard deck"
        }
    }

    fun getLength() : Int {
        return cardArr.size
    }

    fun drawCard() : Card {
        if (topCardIndex >= cardArr.size){
            throw OutOfCardsException()
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

    private fun reorderTop(desiredOrder : Array<Card>) : Boolean {
        for((i, card) in desiredOrder.withIndex()) {
            val desiredCardIndex = getIndexOf(card, i)
            if (desiredCardIndex == -1) {
                return false
            }

            cardArr[desiredCardIndex] = cardArr[i]
            cardArr[i] = card
        }
        return true
    }

    private fun getIndexOf(card : Card, startingIndex : Int) : Int {
        for (i in startingIndex..(cardArr.size-1)) {
            val c = cardArr[i]
            if(c.getName() == card.getName() && c.suit == card.suit){
                return i
            }
        }
        return -1
    }

}

class OutOfCardsException : Exception() {}