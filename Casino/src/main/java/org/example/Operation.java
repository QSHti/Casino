package org.example;

import java.util.UUID;

public class Operation {
    private UUID playerId;
    private String type;
    private int amount;
    private UUID matchId;
    private String betSide;

    // Constructor
    public Operation(UUID playerId, String type, int amount, UUID matchId, String betSide) {
        this.playerId = playerId;
        this.type = type;
        this.amount = amount;
        this.matchId = matchId;
        this.betSide = betSide;
    }

    // Getters
    public UUID getPlayerId() {
        return playerId;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public UUID getMatchId() {
        return matchId;
    }

    public String getBetSide() {
        return betSide;
    }
}

