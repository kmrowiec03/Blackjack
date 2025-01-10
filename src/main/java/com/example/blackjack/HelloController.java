package com.example.blackjack;

import com.example.blackjack.startegies.BalancedBlackJackStrategy;
import com.example.blackjack.startegies.RiskyBlackJackStrategy;
import com.example.blackjack.startegies.SafeBlackJackStrategy;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;

public class HelloController {

    @FXML
    private VBox strategyButtons; // Kontener przycisków wyboru strategii
    @FXML
    private VBox gameButtons;
    @FXML
    private Label playerScoreLabel;
    @FXML
    private Label computerScoreLabel;

    @FXML
    private Button drawCardButton;
    @FXML
    private Button passButton;
    @FXML
    private Button restartButton;
    @FXML
    private Button balancedButton;
    @FXML
    private Button riskyButton;
    @FXML
    private Button safeButton;

    @FXML
    private HBox playerCardsBox;
    @FXML
    private HBox computerCardsBox;



    private GameSimulation gameSimulation;

    @FXML
    private void initialize() {
        gameSimulation = new GameSimulation();

        // Przypisanie akcji do przycisków strategii
        balancedButton.setOnAction(event -> selectBalancedStrategy());
        riskyButton.setOnAction(event -> selectRiskyStrategy());
        safeButton.setOnAction(event -> selectSafeStrategy());

        restartButton.setOnAction(event -> restartGame());

        // Akcje dla przycisków w grze
        drawCardButton.setOnAction(event -> playerDrawCard());
        passButton.setOnAction(event -> playerPass());

        gameSimulation.startGame();
        displayPlayerCards();
        displayComputerCards(true);
    }

    // Metody wyboru strategii
    @FXML
    private void selectBalancedStrategy() {
        gameSimulation.setComputerStrategy(new BalancedBlackJackStrategy(gameSimulation.getComputer()));
        System.out.println("Komputer wybrał Balanced Strategy.");
        strategyButtons.setVisible(false);
        gameButtons.setVisible(true);
        playerCardsBox.setVisible(true);
        computerCardsBox.setVisible(true);
    }

    @FXML
    private void selectRiskyStrategy() {
        gameSimulation.setComputerStrategy(new RiskyBlackJackStrategy(gameSimulation.getComputer()));
        System.out.println("Komputer wybrał Risky Strategy.");
        strategyButtons.setVisible(false);
        gameButtons.setVisible(true);
        playerCardsBox.setVisible(true);
        computerCardsBox.setVisible(true);
    }

    @FXML
    private void selectSafeStrategy() {
        gameSimulation.setComputerStrategy(new SafeBlackJackStrategy(gameSimulation.getComputer()));
        System.out.println("Komputer wybrał Safe Strategy.");
        strategyButtons.setVisible(false);
        gameButtons.setVisible(true);
        playerCardsBox.setVisible(true);
        computerCardsBox.setVisible(true);
    }

    // Rozpoczęcie gry, ukrycie przycisków strategii
    private void startGame() {
        strategyButtons.setVisible(false);
        gameButtons.setVisible(true);

        // Rozpoczęcie gry i rozdanie początkowych kart
        gameSimulation.startGame();
        updateScores();
    }

    // Gracz dobiera kartę
    @FXML
    private void playerDrawCard() {
        if (gameSimulation.isGameOver()) {
            System.out.println("Gra zakończona. Zrestartuj grę.");
            return;
        }

        if (gameSimulation.isPlayerTurn()) {
            gameSimulation.playerDrawCard();
            displayPlayerCards();
            updateScores();
            if (!gameSimulation.isGameOver()) {
                computerTurn();  // Po turze gracza komputer wykonuje swój ruch
            }
        } else {
            System.out.println("Teraz nie jest tura gracza. Czekaj na turę komputera.");
        }
    }

    // Gracz spasowuje
    @FXML
    private void playerPass() {
        showComputerCards();
        if (gameSimulation.isGameOver()) {
            System.out.println("Gra zakończona. Zrestartuj grę.");
            return;
        }

        if (gameSimulation.isPlayerTurn()) {
            gameSimulation.playerPass();
            displayPlayerCards();
            updateScores();
            if (!gameSimulation.isGameOver()) {
                while (!gameSimulation.isGameOver()){
                    computerTurn();
                }
            }
        } else {
            System.out.println("Teraz nie jest tura gracza. Czekaj na turę komputera.");
        }
    }

    // Komputer wykonuje turę
    private void computerTurn() {
        if (gameSimulation.isGameOver()) {
            System.out.println("Gra zakończona. Zrestartuj grę.");
            return;
        }

        if (!gameSimulation.isGameOver()) {
            gameSimulation.computerDrawCard();
            displayComputerCards(true);
            updateScores();
        }
    }
    @FXML
    private void restartGame() {
        showComputerCards();
        gameSimulation.restartGame();
        strategyButtons.setVisible(true);
        gameButtons.setVisible(false);
        displayPlayerCards();
        displayComputerCards(true);
        updateScores();
    }

    // Aktualizacja wyników gracza i komputera
    private void updateScores() {
        playerScoreLabel.setText("Player Score: " + gameSimulation.getPlayer().getScore());
        //computerScoreLabel.setText("Computer Score: " + gameSimulation.getComputer().getScore());
    }

    private void displayPlayerCards() {
        playerCardsBox.getChildren().clear();

        for (Card card : gameSimulation.getPlayer().getHand()) {
            StackPane cardRectangle = createCardRectangle(card, false);
            playerCardsBox.getChildren().add(cardRectangle);
        }
    }

    private void displayComputerCards(boolean isReversed) {
        computerCardsBox.getChildren().clear(); // Wyczyść poprzednie karty

        for (Card card : gameSimulation.getComputer().getHand()) {
            StackPane cardRectangle = createCardRectangle(card, isReversed);
            computerCardsBox.getChildren().add(cardRectangle);
        }
    }
    private void showComputerCards() {
        displayComputerCards(false);
    }

    private StackPane createCardRectangle(Card card, boolean isReversed) {
        StackPane stackPane = new StackPane();

        if (isReversed) {
            // Karta obrócona - czarny rewers
            Rectangle rectangle = new Rectangle(60, 90);  // Wymiary prostokąta (karty)
            rectangle.setFill(Color.BLACK);  // Czarny rewers


            stackPane.getChildren().add(rectangle);
        } else {
            // Karta widoczna - prawdziwa karta
            Rectangle rectangle = new Rectangle(60, 90);  // Wymiary prostokąta (karty)
            rectangle.setFill(card.getColor());  // Kolor wypełnienia prostokąta (np. kolor karty)

            // Numer lub figura karty
            Text cardText = new Text(String.valueOf(card.getValue()));
            cardText.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            cardText.setFill(Color.WHITE);  // Kolor tekstu (np. biały, aby kontrastował z kartą)
            cardText.setTranslateY(30);  // Przesuwamy tekst w dół w prostokącie
            cardText.setTranslateX(20);  // Przesuwamy tekst w prawo

            // Dodajemy tekst na prostokąt
            stackPane.getChildren().addAll(rectangle, cardText);  // Dodajemy prostokąt i tekst do StackPane
        }

        return stackPane;
    }


}
