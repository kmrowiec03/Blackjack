package com.example.blackjack;

public class ScoreDisplay implements Observer {
    private final String name;

    public ScoreDisplay(String name) {
        this.name = name;
    }

    @Override
    public void update(int points) {
        System.out.println("Aktualny wynik " + name + ": " + points);
    }
}