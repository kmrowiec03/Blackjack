package com.example.blackjack;

import java.util.ArrayList;

public abstract class CardDeckDecorator implements ISetOfCards{
    protected CardDeck decoratedDeck;

    public CardDeckDecorator(CardDeck decoratedDeck) {
        this.decoratedDeck = decoratedDeck;
    }

    @Override
    public void addCard(Card card) {
        decoratedDeck.addCard(card);  // Przekazuje działanie do oryginalnej talii
    }

    @Override
    public void deleteCard(Card card) {
        decoratedDeck.deleteCard(card);  // Przekazuje działanie do oryginalnej talii
    }

    @Override
    public void updateCard(Card card) {
        decoratedDeck.updateCard(card);  // Przekazuje działanie do oryginalnej talii
    }

    @Override
    public ArrayList<Card> getDeck() {
        return decoratedDeck.getDeck();  // Zwracamy deck z oryginalnej talii
    }

    @Override
    public Card getCard(Card card) {
        return decoratedDeck.getCard(card);  // Przekazuje działanie do oryginalnej talii
    }
}
