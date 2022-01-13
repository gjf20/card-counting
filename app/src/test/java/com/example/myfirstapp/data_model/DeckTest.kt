package com.example.myfirstapp.data_model

import org.junit.Assert.*
import org.junit.Test

class DeckTest {

    @Test
    fun deckHasMultiplesOf52() {
        val numDecks = 2
        val deck = Deck(numDecks)
        assertEquals(numDecks * 52, deck.getLength())
    }

    @Test
    fun deckHasAllStandardCards() {
        val numDecks = 1
        val deck = Deck(numDecks)
        verifyAllCardsInDeck(deck, numDecks)
    }

    @Test
    fun deckCanStartWithSpecifiedOrder() {
        //specify the first 10 cards
        val expectedSuit = Card.Suit.SPADES
        val numDecks = 1
        val numCardsToReorder = 10 //keep under 14
        val desiredOrder = Array<Card>(numCardsToReorder, fun (i : Int) : Card{return Card(11-i, expectedSuit)})
        val deck = Deck(numDecks, desiredOrder)
        val deck2 = Deck(numDecks, desiredOrder)

        for (c : Card in desiredOrder) {
            val card = deck.drawCard()
            assert(card != null, fun(): String { return "Card was null" })
            assert(
                card?.suit == c.suit,
                fun(): String { return "Card suit ${card?.suit} was not ${c.suit}" })
            assert(card?.number == c.number,  fun(): String { return "Card number ${card?.number} was not ${c.number}" })
        }
        //check that s deck has all the cards
        verifyAllCardsInDeck(deck2, numDecks)
    }

    @Test
    fun deckCanStartWithSpecifiedOrderContainingDuplicates() {
        //specify the first 10 cards
        val expectedSuit = Card.Suit.SPADES
        val numDecks = 2
        val numCardsToReorder = 3
        val desiredOrder = Array<Card>(numCardsToReorder, fun (i : Int) : Card {return Card((i % 2) + 1, expectedSuit)})
        val deck = Deck(numDecks, desiredOrder)
        val deck2 = Deck(numDecks, desiredOrder)

        for (c : Card in desiredOrder) {
            val card = deck.drawCard()
            assert(card != null, fun(): String { return "Card was null" })
            assert(card?.number == c.number,  fun(): String { return "Card number ${card?.number} was not ${c.number}" })
            assert(
                card?.suit == c.suit,
                fun(): String { return "Card suit ${card?.suit} was not ${c.suit}" })
        }
        //check that s deck has all the cards
        verifyAllCardsInDeck(deck2, numDecks)
    }

    @Test
    fun deckForbidsStartingOrderWithTooManyCopiesOfCard() {
        //specify the first 10 cards
        val expectedSuit = Card.Suit.SPADES
        val numDecks = 1 //there will only be one copy of each card
        val numCardsToReorder = 3
        val desiredOrder = Array<Card>(numCardsToReorder, fun (i : Int) : Card {return Card((i % 2) + 1, expectedSuit)}) //desires 2 Aces of Spades

        try {
            val deck = Deck(numDecks, desiredOrder)
        } catch (e: IllegalArgumentException) {
            e.message?.let {
                assert(
                    it.contains("are not valid for $numDecks copies of a standard deck"),
                    fun(): String { return "Exception should reflect that the starting order argument was invalid" })
            }
        }
    }

    @Test
    fun deckForbidsOrderingWithTooManyCards() {
        //attempt to specify 53 cards
        val numDecks = 1
        val numCardsToReorder = 53 //keep under 14
        val desiredOrder = Array<Card>(
            numCardsToReorder,
            fun(i: Int): Card { return Card(i % 52, Card.Suit.SPADES) })
        try {
            val deck = Deck(numDecks, desiredOrder)
        } catch (e: IllegalArgumentException) {
            e.message?.let {
                assert(
                    it.contains("exceed the number of cards in the deck"),
                    fun(): String { return "Exception should reflect that the arguments were not correct" })
            }
        }
    }

    @Test
    fun multipleDecksHaveMultipleCardsCopies() {
        val numDecks = 2
        val deck = Deck(numDecks)
        val expectedFaces = 8
        val expectedSuits = 26

        val faces = Array<Int>(13, fun(_ : Int): Int {return 0})
        val suits = Array<Int>(4, fun(_ : Int): Int {return 0})

        for (i in 1..numDecks*52) {
            val card = deck.drawCard()
            if (card != null) {
                faces[(card.number) -1]++
                suits[card.suit.ordinal]++
            }
        }

        faces.forEachIndexed { index, count ->
            assertEquals("Did not find $expectedFaces #${index+1}'s",expectedFaces, count)
        }
        suits.forEachIndexed{ index, count ->
            assertEquals("Did not find $expectedSuits #${Card.Suit.values()[index].name}",expectedSuits, count)
        }
    }

    @Test(expected = OutOfCardsException::class)
    fun drawCardEmptyDeck() {
        val deck = Deck(0)
        deck.drawCard()
    }

    @Test
    fun runningCountLowMinus() {
        val deck = Deck(1)
        drawCards(deck,1) //draw past the ace
        val startingCount = deck.getRunningCount()
        var card = deck.drawCard()
        assertEquals("expected 2 card, got ${card?.number}", 2, card?.number)
        assertEquals("expected count to increment", startingCount + 1, deck.getRunningCount())
        deck.drawCard()
        deck.drawCard()
        deck.drawCard()
        deck.drawCard()
        val endingCount = deck.getRunningCount()
        assertEquals("expected count to increment for any +1 count card",startingCount+5, endingCount)
    }

    @Test
    fun runningCountMiddle0() {
        val deck = Deck(1)
        drawCards(deck,6) //draw until 7 is next
        val startingCount = deck.getRunningCount()
        var card = deck.drawCard()
        assertEquals("expected 7 card, got ${card?.number}", 7, card?.number)
        assertEquals("expected count not to change", startingCount, deck.getRunningCount())
        deck.drawCard()
        deck.drawCard()
        val endingCount = deck.getRunningCount()
        assertEquals("expected count not to change for any 0 count card",startingCount, endingCount)
    }

    @Test
    fun runningCountHighPlus() {
        val deck = Deck(1)
        drawCards(deck,9) //draw past the low cards
        val startingCount = deck.getRunningCount()
        var card = deck.drawCard()
        assertEquals("expected 2 card, got ${card?.number}", 10, card?.number)
        assertEquals("expected count to decrement", startingCount - 1, deck.getRunningCount())
        deck.drawCard()
        deck.drawCard()
        deck.drawCard()
        val endingCount = deck.getRunningCount()
        assertEquals("expected count to decrement for any -1 count card",startingCount-4, endingCount)
    }

    @Test
    fun runningCountHighAce() {
        val deck = Deck(1)
        val startingCount = deck.getRunningCount()
        var card = deck.drawCard()
        assertEquals("expected ace card, got ${card?.number}", 1, card?.number)
        assertEquals("expected count to decrement", startingCount - 1, deck.getRunningCount())
    }

    @Test
    fun trueCount() {
        //true count varies by how many decks are left
        val deck = Deck(1)
        val deck2 = Deck(2)
        assertEquals("Decks should have the same true count at the beginning", deck.getTrueCount(), deck2.getTrueCount())

        deck.drawCard()
        deck2.drawCard()

        assertEquals("Decks should have different true count by factor of 2", deck.getTrueCount() / 2, deck2.getTrueCount())
    }

    //helper
    private fun drawCards(deck : Deck, num : Int) {
        for (i in 1..num) {
            deck.drawCard()
        }
    }

    private fun verifyAllCardsInDeck(deck: Deck, numDecks: Int) {
        val expectedFaces = 4 * numDecks
        val expectedSuits = 13 * numDecks

        val faces = Array<Int>(13, fun(_ : Int): Int {return 0})
        val suits = Array<Int>(4, fun(_ : Int): Int {return 0})

        for (i in 1..(52 * numDecks)) {
            val card = deck.drawCard()
            if (card != null) {
                faces[card.number - 1]++
                suits[card.suit.ordinal]++
            }
        }

        faces.forEachIndexed { index, count ->
            assertEquals("Did not find $expectedFaces #${index+1}'s",expectedFaces, count)
        }
        suits.forEachIndexed{ index, count ->
            assertEquals("Did not find $expectedSuits #${Card.Suit.values()[index].name}",expectedSuits, count)
        }
    }
}