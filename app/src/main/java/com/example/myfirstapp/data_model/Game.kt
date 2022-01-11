package com.example.myfirstapp.data_model

class Game {
    private var playerHand = mutableListOf<Card>() //TODO extract into a player/hand class
    private var dealerHand = mutableListOf<Card>() //TODO extract into a class that extends the player/hand class
    private val deck : Deck
    val highAceModifier = 10

    constructor(deckForGame : Deck) {
        deck = deckForGame
    }

    fun getPlayerScore() : Int {
        var sum = 0
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

    fun getDealerScore() : Int {
        var sum = 0
        for (card : Card in dealerHand) {
            sum += card.getValue()
            if (card.number == 1) {
                sum += highAceModifier
            }
        }
        return sum
    }

    fun hitDealer() {
        val card = deck.drawCard()
        if (card == null) {
            println("Tried to draw a card from deck but card was null")
        } else {
            dealerHand.add(card)
        }
    }
}