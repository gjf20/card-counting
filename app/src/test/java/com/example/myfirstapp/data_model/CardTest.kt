package com.example.myfirstapp.data_model

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.lang.reflect.Method

class CardTest {

    @Test
    fun getSuitReturnsClubs() {
        val card = Card(1, Card.Suit.SPADES)
        val privateGetSuitMethod: Method =
            Card::class.java.getDeclaredMethod(
                "getSuit",
                Int::class.java
            )
        privateGetSuitMethod.isAccessible = true

        assertEquals(Card.Suit.CLUBS, privateGetSuitMethod.invoke(card, 0))
        assertEquals(Card.Suit.CLUBS, privateGetSuitMethod.invoke(card, 4))
        assertEquals(Card.Suit.CLUBS, privateGetSuitMethod.invoke(card, 8))
        assertEquals(Card.Suit.CLUBS, privateGetSuitMethod.invoke(card, 24))
    }

    @Test
    fun getSuitReturnsDiamonds() {
        val card = Card(1, Card.Suit.SPADES)
        val privateGetSuitMethod: Method =
            Card::class.java.getDeclaredMethod(
                "getSuit", Int::class.java
            )
        privateGetSuitMethod.isAccessible = true

        assertEquals(Card.Suit.DIAMONDS, privateGetSuitMethod.invoke(card,1))
        assertEquals(Card.Suit.DIAMONDS, privateGetSuitMethod.invoke(card,5))
        assertEquals(Card.Suit.DIAMONDS, privateGetSuitMethod.invoke(card,9))
        assertEquals(Card.Suit.DIAMONDS, privateGetSuitMethod.invoke(card,25))
    }

    @Test
    fun getSuitReturnsHearts() {
        val card = Card(1, Card.Suit.SPADES)
        val privateGetSuitMethod: Method =
            Card::class.java.getDeclaredMethod(
                "getSuit",
                Int::class.java
            )
        privateGetSuitMethod.isAccessible = true

        assertEquals(Card.Suit.HEARTS, privateGetSuitMethod.invoke(card, 2))
        assertEquals(Card.Suit.HEARTS, privateGetSuitMethod.invoke(card, 6))
        assertEquals(Card.Suit.HEARTS, privateGetSuitMethod.invoke(card, 10))
        assertEquals(Card.Suit.HEARTS, privateGetSuitMethod.invoke(card, 26))
    }

    @Test
    fun getSuitReturnsSpades() {
        val card = Card(1, Card.Suit.SPADES)
        val privateGetSuitMethod: Method =
            Card::class.java.getDeclaredMethod(
                "getSuit",
                Int::class.java
            )
        privateGetSuitMethod.isAccessible = true

        assertEquals(Card.Suit.SPADES, privateGetSuitMethod.invoke(card, 3))
        assertEquals(Card.Suit.SPADES, privateGetSuitMethod.invoke(card, 7))
        assertEquals(Card.Suit.SPADES, privateGetSuitMethod.invoke(card, 11))
        assertEquals(Card.Suit.SPADES, privateGetSuitMethod.invoke(card, 27))
    }

    @Test
    fun getValueNumbers() {
        for(i in 1..10){
            val card = Card(i, Card.Suit.SPADES)
            assertEquals(card.getValue(), i)
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
            val ace = Card(1, suit)
            assertEquals("ace_of_${suit.name.lowercase()}.png", ace.imageUri)

            for(i in 11..13){
                val card = Card(i, suit)
                assertEquals(card.getName()+"_of_"+suit.name.lowercase()+".png", card.imageUri)
            }
        }
    }
}