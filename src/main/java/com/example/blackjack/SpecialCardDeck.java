package com.example.blackjack;

import javafx.scene.paint.Color;

public class SpecialCardDeck extends CardDeckDecorator {

    public SpecialCardDeck(CardDeck decoratedDeck) {
        super(decoratedDeck);
    }

    public void addSpecialCardsPlusTwo() {

        Card drawTwoCard = new Card(0, Color.MAGENTA); // Karta specjalna

        decoratedDeck.addCard(drawTwoCard);
        System.out.println("Dodano specjalną kartę: Dobierz 2 karty");


        decoratedDeck.shuffleDeck();
    }

    // Logika przy wyciągnięciu specjalnej karty: dodanie dwóch kart z góry talii
    public void processSpecialCard(Player player) {
        CardDeck deck = CardDeck.getInstance();

        // Sprawdź, czy gracz ma specjalną kartę
        for (Card card : player.getHand()) {
            if (card.getValue() == 0) { // Karta specjalna o wartości 0
                System.out.println("Gracz " + player.getName() + " wylosował kartę specjalną! Dobieranie 2 kart...");

                // Usuwamy specjalną kartę z ręki gracza
                player.removeCardFromHand(card);

                // Dobieramy dwie karty z talii, jeśli są dostępne
                if (deck.getDeck().size() >= 2) {
                    Card firstCard = deck.getDeck().removeFirst();
                    Card secondCard = deck.getDeck().removeFirst();

                    // Dodajemy je do ręki gracza
                    player.addCardToHand(firstCard);
                    player.addCardToHand(secondCard);

                    System.out.println("Gracz " + player.getName() + " otrzymał karty: " +
                            firstCard.getValue() + " i " + secondCard.getValue());
                } else {
                    System.out.println("Brak wystarczającej liczby kart w talii, aby dobrać 2 karty.");
                }

                break; // Procesujemy tylko jedną kartę specjalną na raz
            }
        }
    }
}
