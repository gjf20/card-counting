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
}