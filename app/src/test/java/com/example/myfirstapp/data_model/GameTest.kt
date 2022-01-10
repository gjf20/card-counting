package com.example.myfirstapp.data_model

import org.junit.Test

class GameTest {

    @Test
    fun startPlayerScore() {
        val deck = Deck(1)
        val game = Game(deck)
        val expectedScore = 0

        val score = game.getPlayerScore()
        assert(score == expectedScore, fun() : String {return "Player starting score was $score instead of $expectedScore"})
    }

    @Test
    fun playerScoreTotalsCards() {
        val numCardsToReorder = 2
        val expectedScore = 9
        val startingCards = Array<Card>(
            numCardsToReorder,
            fun(i: Int): Card { return Card(i + 4, Card.Suit.SPADES) })
        val deck = Deck(1, startingCards)
        val game = Game(deck)

        game.hitPlayer()
        game.hitPlayer()

        val score = game.getPlayerScore()
        assert(score == expectedScore, fun() : String {return "Player starting score was $score instead of $expectedScore"})
    }

    @Test
    fun playerScoreAceHigh() {
        val numCardsToReorder = 2
        val expectedScore = 13
        val startingCards = Array<Card>(
            numCardsToReorder,
            fun(i: Int): Card { return Card(i + 1, Card.Suit.SPADES) })
        val deck = Deck(1, startingCards)
        val game = Game(deck)

        game.hitPlayer()
        game.hitPlayer()

        val score = game.getPlayerScore()
        assert(score == expectedScore, fun() : String {return "Player starting score was $score instead of $expectedScore"})
    }

    @Test
    fun playerScoreAceLow() {
        val numCardsToReorder = 3
        val expectedScore = 16
        val startingCards = Array<Card>(
            numCardsToReorder,
            fun(i: Int): Card {
                return when (i) {
                    0 -> {
                        Card(5, Card.Suit.CLUBS)
                    }
                    1 -> {
                        Card(1, Card.Suit.CLUBS)
                    }
                    else -> {
                        Card(11, Card.Suit.CLUBS)
                    }
                }
            })
        val deck = Deck(1, startingCards)
        val game = Game(deck)

        game.hitPlayer()
        game.hitPlayer()
        game.hitPlayer()

        val score = game.getPlayerScore()
        assert(score == expectedScore, fun() : String {return "Player starting score was $score instead of $expectedScore"})
    }

    @Test
    fun playerScoreTwoAces() {
        val numCardsToReorder = 3
        val expectedScore = 14
        val startingCards = Array<Card>(
            numCardsToReorder,
            fun(i: Int): Card { return Card((i % 2) + 1, Card.Suit.SPADES) })
        val deck = Deck(2, startingCards) //use 2 decks bc we have 2 aces of spades in the starting order
        val game = Game(deck)

        game.hitPlayer()
        game.hitPlayer()
        game.hitPlayer()

        val score = game.getPlayerScore()
        assert(score == expectedScore, fun() : String {return "Player starting score was $score instead of $expectedScore"})
    }
}