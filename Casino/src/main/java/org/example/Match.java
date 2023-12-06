package org.example;

import java.util.UUID;

public class Match {
    private UUID id;
    private double rateA;
    private double rateB;
    private String result;

    // Constructor
    public Match(UUID id, double rateA, double rateB, String result) {
        this.id = id;
        this.rateA = rateA;
        this.rateB = rateB;
        this.result = result;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public double getRateA() {
        return rateA;
    }

    public double getRateB() {
        return rateB;
    }

    public String getResult() {
        return result;
    }
}
