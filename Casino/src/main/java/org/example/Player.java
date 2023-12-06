package org.example;

import java.util.UUID;

public class Player {
    private final UUID id;
    private long balance;
    private int totalBetsPlaced;
    private int totalBetsWon;
    private double winRate;

    public Player(UUID id) {
        this.id = id;
        this.balance = 0;
        this.totalBetsPlaced = 0;
        this.totalBetsWon = 0;
        this.winRate = 0.0;
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Invalid deposit amount attempted by player " + id + ". Attempted deposit amount: " + amount);
        }
    }

    public void placeBet(int amount, String betSide) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            totalBetsPlaced++;
        } else {
            System.out.println("Illegal bet attempted by player " + id + ". Bet amount: " + amount);
        }
    }

    public void withdraw(int amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
        } else {
            System.out.println("Illegal withdrawal attempted by player " + id + ". Attempted withdrawal amount: " + amount);
        }
    }

    public void updateBetOutcome(boolean isWin, int winnings) {
        if (isWin) {
            totalBetsWon++;
            balance += winnings;
        }
        updateWinRate();
    }


    private void updateWinRate() {
        if (totalBetsPlaced > 0) {
            winRate = (double) totalBetsWon / totalBetsPlaced;
        }
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public long getBalance() {
        return balance;
    }

    public double getWinRate() {
        return winRate;
    }
}
