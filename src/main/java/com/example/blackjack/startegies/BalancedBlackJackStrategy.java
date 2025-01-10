package com.example.blackjack.startegies;

import com.example.blackjack.Card;
import com.example.blackjack.CardDeck;
import com.example.blackjack.ComputerOpponent;

import java.util.Random;

public class BalancedBlackJackStrategy implements IStrategy {
    private ComputerOpponent opponent; // Dodajemy pole do przechowywania komputera

    // Konstruktor przyjmujący obiekt komputera
    public BalancedBlackJackStrategy(ComputerOpponent opponent) {
        this.opponent = opponent;
    }
    @Override
    public boolean execute_strategy() {
        System.out.println("balancedBJstrategy");
        CardDeck deck = CardDeck.getInstance();
        int currentScore = opponent.getScore();
        Random random = new Random(); // Generator losowy

        if (deck.getDeck().isEmpty()) {
            System.out.println("Brak kart w talii!");
            return false;
        }

        boolean shouldDraw = false;

        // Logika prawdopodobieństwa
        if (currentScore < 12) {
            shouldDraw = true; // 100% dobiera kartę
        } else if (currentScore <= 16) {
            shouldDraw = random.nextInt(100) < 50; // 50% szans na dobór
        } else if (currentScore <= 18) {
            shouldDraw = random.nextInt(100) < 20; // 20% szans na dobór
        } else if (currentScore <= 20) {
            shouldDraw = random.nextInt(100) < 7; // 7% szans na dobór
        }

        if (shouldDraw) {
            Card card = deck.getDeck().removeFirst(); // Pobiera pierwszą kartę z talii
            opponent.addCardToHand(card);
            return true;
        }

        return false;
    }
}
