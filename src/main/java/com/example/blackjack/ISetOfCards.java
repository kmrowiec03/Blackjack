package com.example.blackjack;

import java.util.ArrayList;

public interface ISetOfCards {
    void addCard(Card card);
    void deleteCard(Card card);
    void updateCard(Card card);
    ArrayList<Card> getDeck();
    Card getCard(Card card);



}
