package com.example.myfirstapp

import org.junit.Assert.*
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

        assert(jack.getValue() == 10)
        assert(queen.getValue() == 10)
        assert(king.getValue() == 10)
    }

}