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
        val expectedFaces = 4
        val expectedSuits = 13

        val faces = Array<Int>(13, fun(_ : Int): Int {return 0})
        val suits = Array<Int>(4, fun(_ : Int): Int {return 0})

        for (i in 1..52) {
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

    @Test
    fun drawCardEmptyDeck() {
        val deck = Deck(0)
        val card = deck.drawCard()

        assertNull(card)
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
}