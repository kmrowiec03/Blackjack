package com.example.blackjack;

import java.util.ArrayList;

public class Player {

    private final String name;
    private final PointCounter pointCounter;
    private final ScoreDisplay scoreDisplay;
    private final ArrayList<Card> hand; // Lista przechowująca karty gracza

    public Player(String name) {
        this.name = name;
        this.pointCounter = new PointCounter();
        this.scoreDisplay = new ScoreDisplay(name);
        this.hand = new ArrayList<>(); // Inicjalizacja listy kart

        // Rejestracja ScoreDisplay jako obserwatora w PointCounter
        pointCounter.addObserver(scoreDisplay);
    }

    // Metoda do dodawania punktów
    public void addPoints(int points) {
        pointCounter.addPoints(points);
    }

    // Metoda do uzyskiwania obecnego wyniku
    public int getScore() {
        return pointCounter.getPoints();
    }

    // Metoda do dodawania karty do ręki
    public void addCardToHand(Card card) {
        hand.add(card);
        if (card.getValue()<=10 && card.getValue()>0) {
            addPoints(card.getValue());
        }
    }

    // Metoda do usuwania karty z ręki
    public void removeCardFromHand(Card card) {
        hand.remove(card);
    }

    // Metoda, aby uzyskać karty w ręce gracza
    public ArrayList<Card> getHand() {
        return hand;
    }

    // Metoda, aby uzyskać nazwę gracza
    public String getName() {
        return name;
    }
}
