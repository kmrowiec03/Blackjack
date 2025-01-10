package com.example.blackjack.startegies;

import com.example.blackjack.Card;
import com.example.blackjack.CardDeck;
import com.example.blackjack.ComputerOpponent;

import java.util.Random;

public class SafeBlackJackStrategy implements IStrategy {

    private ComputerOpponent opponent; // Dodajemy pole do przechowywania komputera
    private boolean pass=false;

    // Konstruktor przyjmujący obiekt komputera
    public SafeBlackJackStrategy(ComputerOpponent opponent) {
        this.opponent = opponent;
    }
    @Override
    public boolean execute_strategy() {
        System.out.println("safeBJstrategy");
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
                shouldDraw = random.nextInt(100) < 45; // 45% szans na dobór
            } else if (currentScore <= 18) {
                shouldDraw = random.nextInt(100) < 15; // 15% szans na dobór
            } else if (currentScore <= 20) {
                shouldDraw = random.nextInt(100) < 2; // 2% szans na dobór
            }

            if (shouldDraw) {
                Card card = deck.getDeck().removeFirst(); // Pobiera pierwszą kartę z talii
                opponent.addCardToHand(card);
                return true;
            }

            return false;




    }
}

