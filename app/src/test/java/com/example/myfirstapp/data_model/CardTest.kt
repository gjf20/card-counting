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

        assert(jack.getValue() == 10)
        assert(queen.getValue() == 10)
        assert(king.getValue() == 10)
    }

    @Test
    fun getNameNumbers() {
        for(i in 2..10){
            val card = Card(i, Card.Suit.SPADES)
            assert(card.getName() == i.toString())
        }
    }

    @Test
    fun getNameFaceCards() {
        val ace = Card(1, Card.Suit.SPADES)
        val jack = Card(11, Card.Suit.SPADES)
        val queen = Card(12, Card.Suit.SPADES)
        val king = Card(13, Card.Suit.SPADES)

        assert(ace.getName() == "ace")
        assert(jack.getName() == "jack")
        assert(queen.getName() == "queen")
        assert(king.getName() == "king")
    }

    @Test
    fun getImageUriNumbers() {
        enumValues<Card.Suit>().forEach { suit ->
            for(i in 2..10){
                val card = Card(i, suit)
                assert(card.imageUri == "_"+card.number+"_of_"+suit+".png")
            }
        }
    }

    @Test
    fun getImageUriFaceCards() {
        enumValues<Card.Suit>().forEach { suit ->
            val card = Card(1, suit)
            assert(card.imageUri == "ace_of_$suit.png")

            for(i in 11..13){
                val card = Card(i, suit)
                assert(card.imageUri == card.getName()+"_of_"+suit+".png")
            }
        }
    }
}