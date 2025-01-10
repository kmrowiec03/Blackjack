package com.example.blackjack;

import java.util.ArrayList;
import java.util.List;

public class PointCounter implements Subject {
    private int points;
    private final List<Observer> observers;
    public PointCounter() {
        points = 0;
        observers = new ArrayList<Observer>();
    }
    public void increment() {
        points++;
        notifyObservers();
    }
    public void decrement() {
        points--;
        notifyObservers();
    }
    public void addPoints(int value) {
        this.points += value;
    }
    public int getPoints() {
        return points;
    }
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(points);
        }
    }
}
