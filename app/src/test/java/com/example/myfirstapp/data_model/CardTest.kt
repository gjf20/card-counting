package com.example.myfirstapp.data_model

import org.junit.Test

class CardTest {

    @Test
    fun getValueNumbers() {
        for(i in 1..10){
            val card = Card(i, Card.Suit.SPADES)
            assert(card.getValue() == i)
        }
    }

    @Test
    fun getValueFaceCards() {
        val jack = Card(11, Card.Suit.SPADES)
        val queen = Card(12, Card.Suit.SPADES)
        val king = Card(13, Card.Suit.SPADES)

        assertEquals(10, jack.getValue())
        assertEquals(10, queen.getValue())
        assertEquals(10, king.getValue())
    }

    @Test
    fun getNameNumbers() {
        for(i in 2..10){
            val card = Card(i, Card.Suit.SPADES)
            assertEquals(i.toString(), card.getName())
        }
    }

    @Test
    fun getNameFaceCards() {
        val ace = Card(1, Card.Suit.SPADES)
        val jack = Card(11, Card.Suit.SPADES)
        val queen = Card(12, Card.Suit.SPADES)
        val king = Card(13, Card.Suit.SPADES)

        assertEquals(ace.getName(), "ace")
        assertEquals(jack.getName(), "jack")
        assertEquals(queen.getName(), "queen")
        assertEquals(king.getName(), "king")
    }

    @Test
    fun getImageUriHasCorrectSuit() {
        val aceOfSpades = Card(1, Card.Suit.SPADES)
        assertEquals("ace_of_spades.png", aceOfSpades.imageUri)
    }

    @Test
    fun getImageUriNumbers() {
        enumValues<Card.Suit>().forEach { suit ->
            for(i in 2..10){
                val card = Card(i, suit)
                assertEquals("_"+card.number+"_of_"+suit.name.lowercase()+".png", card.imageUri)
            }
        }
    }

    @Test
    fun getImageUriFaceCards() {
        enumValues<Card.Suit>().forEach { suit ->
            val card = Card(1, suit)
            assertEquals("ace_of_${suit.name.lowercase()}.png", card.imageUri)

            for(i in 11..13){
                val card = Card(i, suit)
                assertEquals(card.getName()+"_of_"+suit.name.lowercase()+".png", card.imageUri)
            }
        }
    }
}