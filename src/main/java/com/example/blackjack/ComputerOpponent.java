package com.example.blackjack;


import com.example.blackjack.startegies.IStrategy;

public class ComputerOpponent extends Player {

    private IStrategy strategy;

    public ComputerOpponent(String name) {
        super(name);
    }
    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }
    public boolean executeStrategy(boolean isPLayerTurn) {
        if (strategy != null && !isPLayerTurn) {
            return strategy.execute_strategy();
        } else {
            System.out.println("Nie wybrano strategii!");
        }
        return false;
    }
}