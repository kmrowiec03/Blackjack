package com.example.blackjack;

import com.example.blackjack.startegies.IStrategy;
import javafx.scene.control.Alert;

public class GameSimulation {

    private Player player;
    private ComputerOpponent computer;
    private CardDeck deck;
    private boolean isGameOver;
    private boolean playerPassed;  // Sprawdza, czy gracz spasował
    private boolean computerPassed;  // Sprawdza, czy komputer spasował
    private boolean playerTurn = true;
    SpecialCardDeck specialDeck;

    public GameSimulation() {
        this.player = new Player("Player");
        this.computer = new ComputerOpponent("Computer");
        this.deck = CardDeck.getInstance();
        this.isGameOver = false;
        this.playerPassed = false;
        this.computerPassed = false;

        this.specialDeck = new SpecialCardDeck(deck);
        specialDeck.addSpecialCardsPlusTwo();
    }

    public void setComputerStrategy(IStrategy strategy) {
        computer.setStrategy(strategy);
    }

    // Rozpoczyna grę i zarządza turami
    public void startGame() {
        System.out.println("Rozpoczynamy grę!");

        playerTurn = true;
    }


    public void playerDrawCard() {
        if (isGameOver) {
            System.out.println("Gra już się zakończyła!");
            return;
        }

        if (playerTurn && !playerPassed) {
            // Gracz dobiera kartę
            if (!specialDeck.getDeck().isEmpty()) {
                Card drawnCard = specialDeck.getDeck().remove(0);
                player.addCardToHand(drawnCard);
                System.out.println("Gracz dobrał kartę: " + drawnCard.getValue());
                checkGameStatus();
                specialDeck.processSpecialCard(player);
                playerTurn = false;

            } else {
                System.out.println("Brak kart w talii!");
            }
        } else {
            System.out.println("Teraz nie jest tura gracza. Czekaj na turę komputera.");
        }
    }

    public void playerPass() {
        if (isGameOver) {
            System.out.println("Gra już się zakończyła!");
            return;
        }

        if (playerTurn && !playerPassed) {
            playerPassed = true;
            System.out.println("Gracz spasował.");
            checkGameStatus();
            playerTurn = false;

        } else {
            System.out.println("Teraz nie jest tura gracza. Czekaj na turę komputera.");
        }
    }


    // Komputer dobiera karty, może spasować
    public void computerDrawCard() {
        if (isGameOver) {
            System.out.println("Gra już się zakończyła!");
            return;
        }

        if (!playerTurn && !computerPassed) {
            boolean passOrPlay = computer.executeStrategy(playerTurn); // Wywołanie strategii
            specialDeck.processSpecialCard(player);
            if(!passOrPlay) {
                computerPass();
            }
            checkGameStatus();

            if (!playerPassed) {
                playerTurn = true;
            }
        } else {
            System.out.println("Czekaj na swoją turę.");
        }
    }

    // Komputer postanawia spasować
    public void computerPass() {
        computerPassed = true;
        System.out.println("Komputer spasował.");
        checkGameStatus();
        if (!playerPassed ) {
            playerTurn = true;
        }
    }



    // Sprawdza stan gry po każdej turze
    public void checkGameStatus() {
        if (player.getScore() > 21) {
            showGameResult("Gracz przekroczył 21 punktów! Komputer wygrywa.");
            isGameOver = true;
        } else if (computer.getScore() > 21) {
            showGameResult("Komputer przekroczył 21 punktów! Gracz wygrywa.");
            isGameOver = true;
        } else if (playerPassed && computerPassed) {
            determineWinner();
        }
    }

    // Rozstrzyga, kto wygrał, jeśli obaj spasowali
    public void determineWinner() {
        String resultMessage;
        if (player.getScore() > computer.getScore()) {
            resultMessage = "Gracz wygrał!";
        } else if (computer.getScore() > player.getScore()) {
            resultMessage = "Komputer wygrał!";
        } else {
            resultMessage = "Remis!";
        }
        showGameResult(resultMessage);
        isGameOver = true;
    }
    public void showGameResult(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wynik gry");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public boolean isGameOver() {

        return isGameOver;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public Player getPlayer() {
        return player;
    }

    public ComputerOpponent getComputer() {
        return computer;
    }

    public void restartGame() {
        // Resetowanie stanu gry do początkowego
        this.deck.deleteInstance();
        this.player = new Player("Player");
        this.computer = new ComputerOpponent("Computer");
        this.deck = CardDeck.getInstance();
        this.isGameOver = false;
        this.playerPassed = false;
        this.computerPassed = false;
        this.playerTurn = true;

        this.specialDeck = new SpecialCardDeck(deck);
        specialDeck.addSpecialCardsPlusTwo();

        startGame();


    }
}