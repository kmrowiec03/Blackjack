package com.example.blackjack;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import java.util.Collections;

public class CardDeck implements ISetOfCards{

    private final ArrayList<Card> cards = new ArrayList<>();

    private static CardDeck instace = null;

    private CardDeck() {
        for (int i = 1; i <= 10; i++) {
            Card card1 = new Card(i,Color.GREEN);
            Card card2 = new Card(i,Color.YELLOW);
            Card card3 = new Card(i,Color.RED);
            Card card4 = new Card(i,Color.BLUE);
            addCard(card1);
            addCard(card2);
            addCard(card3);
            addCard(card4);
        }
        shuffleDeck();
    }

    public static CardDeck getInstance() {
        if (instace == null) {
            instace = new CardDeck();
        }
        return instace;
    }
    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public void deleteCard(Card card) {
        cards.remove(card);
    }

    @Override
    public void updateCard(Card card) {

    }

    @Override
    public ArrayList<Card> getDeck() {
        return cards;
    }

    @Override
    public Card getCard(Card card) {
        return cards.get(cards.indexOf(card));
    }
    public void shuffleDeck() {
        Collections.shuffle(cards);
    }
    public void deleteInstance(){
        instace = null;
        cards.clear();
    }
}
