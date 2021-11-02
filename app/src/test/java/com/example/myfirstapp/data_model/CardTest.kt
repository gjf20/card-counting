package com.example.myfirstapp.data_model

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.lang.reflect.Method

class CardTest {

    @Test
    fun getSuitReturnsClubs() {
        val card = Card(1, Card.Suit.SPADES)//need this to invoke the private function
        val privateGetSuitMethod: Method =
            Card::class.java.getDeclaredMethod(
                "getSuit",
                Int::class.java
            )
        privateGetSuitMethod.isAccessible = true

        assertEquals(Card.Suit.CLUBS, privateGetSuitMethod.invoke(card, 1))
        assertEquals(Card.Suit.CLUBS, privateGetSuitMethod.invoke(card, 2))
        assertEquals(Card.Suit.CLUBS, privateGetSuitMethod.invoke(card, 13))
        assertEquals(Card.Suit.CLUBS, privateGetSuitMethod.invoke(card, 53))
    }

    @Test
    fun getSuitReturnsDiamonds() {
        val card = Card(1, Card.Suit.SPADES)//need this to invoke the private function
        val privateGetSuitMethod: Method =
            Card::class.java.getDeclaredMethod(
                "getSuit", Int::class.java
            )
        privateGetSuitMethod.isAccessible = true

        assertEquals(Card.Suit.DIAMONDS, privateGetSuitMethod.invoke(card, 14))
        assertEquals(Card.Suit.DIAMONDS, privateGetSuitMethod.invoke(card, 15))
        assertEquals(Card.Suit.DIAMONDS, privateGetSuitMethod.invoke(card, 26))
        assertEquals(Card.Suit.DIAMONDS, privateGetSuitMethod.invoke(card, 66))
    }

    @Test
    fun getSuitReturnsHearts() {
        val card = Card(1, Card.Suit.SPADES)//need this to invoke the private function
        val privateGetSuitMethod: Method =
            Card::class.java.getDeclaredMethod(
                "getSuit",
                Int::class.java
            )
        privateGetSuitMethod.isAccessible = true

        assertEquals(Card.Suit.HEARTS, privateGetSuitMethod.invoke(card, 27))
        assertEquals(Card.Suit.HEARTS, privateGetSuitMethod.invoke(card, 28))
        assertEquals(Card.Suit.HEARTS, privateGetSuitMethod.invoke(card, 39))
        assertEquals(Card.Suit.HEARTS, privateGetSuitMethod.invoke(card, 79))
    }

    @Test
    fun getSuitReturnsSpades() {
        val card = Card(1, Card.Suit.SPADES)//need this to invoke the private function
        val privateGetSuitMethod: Method =
            Card::class.java.getDeclaredMethod(
                "getSuit",
                Int::class.java
            )
        privateGetSuitMethod.isAccessible = true

        assertEquals(Card.Suit.SPADES, privateGetSuitMethod.invoke(card, 40))
        assertEquals(Card.Suit.SPADES, privateGetSuitMethod.invoke(card, 41))
        assertEquals(Card.Suit.SPADES, privateGetSuitMethod.invoke(card, 52))
        assertEquals(Card.Suit.SPADES, privateGetSuitMethod.invoke(card, 92))
    }

    @Test
    fun getFaceLoopsThroughAllFaces() {
        val card = Card(1, Card.Suit.SPADES) //need this to invoke the private function
        val privateGetSuitMethod: Method =
            Card::class.java.getDeclaredMethod(
                "getFace",
                Int::class.java
            )
        privateGetSuitMethod.isAccessible = true

        assertEquals(1, privateGetSuitMethod.invoke(card, 1))
        assertEquals(13, privateGetSuitMethod.invoke(card, 13))
        assertEquals(1, privateGetSuitMethod.invoke(card, 14))
        assertEquals(2, privateGetSuitMethod.invoke(card, 15))
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
        assertEquals("ace_of_spades", aceOfSpades.imageUri)
    }

    @Test
    fun getImageUriNumbers() {
        enumValues<Card.Suit>().forEach { suit ->
            for(i in 2..10){
                val card = Card(i, suit)
                assertEquals("_"+card.number+"_of_"+suit.name.lowercase(), card.imageUri)
            }
        }
    }

    @Test
    fun getImageUriFaceCards() {
        enumValues<Card.Suit>().forEach { suit ->
            val ace = Card(1, suit)
            assertEquals("ace_of_${suit.name.lowercase()}", ace.imageUri)

            for(i in 11..13){
                val card = Card(i, suit)
                assertEquals(card.getName()+"_of_"+suit.name.lowercase(), card.imageUri)
            }
        }
    }
}