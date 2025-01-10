package com.example.blackjack;

import javafx.scene.paint.Color;  // Importujemy Color z JavaFX

public class Card {
    private int value;
    private Color color;  // Zmieniamy typ koloru na javafx.scene.paint.Color

    public Card(int value, Color color) {
        this.value = value;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }
}
