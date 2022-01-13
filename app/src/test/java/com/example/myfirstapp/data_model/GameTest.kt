package com.example.myfirstapp.data_model

import org.junit.Test

class GameTest {

    @Test
    fun hitPlayer() {
        //mock first card
        val startingCard = Card(7, Card.Suit.CLUBS)
        val startingCardsMutable = mutableListOf<Card>()
        startingCardsMutable.add(startingCard)

        val deck = Deck(1, startingCardsMutable.toTypedArray())
        val game = Game(deck)

        val cardFromHit = game.hitPlayer()

        assert(startingCard.equals(cardFromHit), fun() : String {return "Player's card after hit was $cardFromHit instead of ${startingCard.getName()}"})
    }

    @Test
    fun hitDealer() {
        //mock first card
        val startingCard = Card(7, Card.Suit.CLUBS)
        val startingCardsMutable = mutableListOf<Card>()
        startingCardsMutable.add(startingCard)

        val deck = Deck(1, startingCardsMutable.toTypedArray())
        val game = Game(deck)

        val cardFromHit = game.hitDealer()

        assert(startingCard.equals(cardFromHit), fun() : String {return "Dealer's card after hit was ${cardFromHit.getName()} instead of ${startingCard.getName()}"})
    }

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
        val startingCards = Array(
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
        val startingCards = Array(
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
        val startingCards = Array(
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


    @Test
    fun startDealerScore() {
        val deck = Deck(1)
        val game = Game(deck)
        val expectedScore = 0

        val score = game.getDealerScore()
        assert(score == expectedScore, fun() : String {return "Player starting score was $score instead of $expectedScore"})
    }

    @Test
    fun dealerScoreTotalsCards() {
        val numCardsToReorder = 2
        val expectedScore = 9
        val startingCards = Array(
            numCardsToReorder,
            fun(i: Int): Card { return Card(i + 4, Card.Suit.SPADES) })
        val deck = Deck(1, startingCards)
        val game = Game(deck)

        game.hitDealer()
        game.hitDealer()

        val score = game.getDealerScore()
        assert(score == expectedScore, fun() : String {return "Dealer starting score was $score instead of $expectedScore"})
    }

    @Test
    fun dealerScoreAceStand() {
        val numCardsToReorder = 2
        val expectedScore = 13
        val startingCards = Array(
            numCardsToReorder,
            fun(i: Int): Card { return Card(i + 1, Card.Suit.SPADES) })
        val deck = Deck(1, startingCards)
        val game = Game(deck)

        game.hitDealer()
        game.hitDealer()

        val score = game.getDealerScore()
        assert(score == expectedScore, fun() : String {return "Dealer starting score was $score instead of $expectedScore"})
    }

    @Test
    fun playerScoreAceBust() {
        val numCardsToReorder = 3
        val expectedScore = 26
        val startingCards = Array(
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

        game.hitDealer()
        game.hitDealer()
        game.hitDealer()

        val score = game.getDealerScore()
        assert(score == expectedScore, fun() : String {return "Dealer starting score was $score instead of $expectedScore"})
    }

    @Test
    fun getPlayerHandReturnsPlayerHand() {
        //deck with known first 4 cards
        val expectedCard = Card(8, Card.Suit.CLUBS)
        var startingCardsMutable = mutableListOf<Card>()
        startingCardsMutable.add(expectedCard)
        var startingCards = startingCardsMutable.toTypedArray()
        val deck = Deck(1, startingCards)
        val game = Game(deck)

        game.hitPlayer()
        val playerCard = game.getPlayerHand()[0]

        assert(expectedCard.equals(playerCard))
    }

    @Test
    fun getDealerHandReturnsEmptyDealerHand() {
        val deck = Deck(1)
        val game = Game(deck)

        val hand = game.getDealerHand()

        assert(hand.isEmpty())
    }

    @Test
    fun getDealerHandReturnsDealerHand() {
        //deck with known first 4 cards
        val expectedCard = Card(8, Card.Suit.CLUBS)
        var startingCardsMutable = mutableListOf<Card>()
        startingCardsMutable.add(expectedCard)
        var startingCards = startingCardsMutable.toTypedArray()
        val deck = Deck(1, startingCards)
        val game = Game(deck)

        game.hitDealer()
        val card = game.getDealerHand()[0]

        assert(expectedCard.equals(card))
    }

    @Test
    fun getPlayerHandReturnsEmptyPlayerHand() {
        val deck = Deck(1)
        val game = Game(deck)

        val playerHand = game.getPlayerHand()

        assert(playerHand.isEmpty())
    }

    @Test
    fun gameSetupDeals() {
        //deck with known first 4 cards
        var startingCardsMutable = mutableListOf<Card>()
        startingCardsMutable.add(Card(7, Card.Suit.CLUBS))
        startingCardsMutable.add(Card(8, Card.Suit.CLUBS))
        startingCardsMutable.add(Card(11, Card.Suit.CLUBS))
        startingCardsMutable.add(Card(13, Card.Suit.CLUBS))
        var startingCards = startingCardsMutable.toTypedArray()
        val deck = Deck(1, startingCards)
        val game = Game(deck)

        game.setup()
        val playerHand = game.getPlayerHand()
        val dealerHand = game.getDealerHand()

        assert(playerHand[0].equals(startingCards[0]))
        assert(dealerHand[0].equals(startingCards[1]))
        assert(playerHand[1].equals(startingCards[2]))
        assert(dealerHand[1].equals(startingCards[3]))
    }
}