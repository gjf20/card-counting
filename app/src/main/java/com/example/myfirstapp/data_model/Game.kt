package com.example.myfirstapp.data_model

class Game {
    private var playerHand = mutableListOf<Card>()
    private val deck : Deck

    constructor(deckForGame : Deck) {
        deck = deckForGame
    }

    fun getPlayerScore() : Int {
        var sum = 0
        val highAceModifier = 10
        var aceCount = 0
        for (card : Card in playerHand) {
            sum += card.getValue()
            if (card.number == 1 && sum + highAceModifier < 21) {
                sum += highAceModifier
                aceCount++
            }
        }

        while (sum > 21) {
            if (aceCount > 0) {
                sum -= highAceModifier
                aceCount--
            }else {
                break
            }
        }

        return sum
    }

    fun hitPlayer() {
        val card = deck.drawCard()
        if (card == null) {
            println("Tried to draw a card from deck but card was null")
        } else {
            playerHand.add(card)
        }
    }
}