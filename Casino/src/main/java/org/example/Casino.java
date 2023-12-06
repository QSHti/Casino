package org.example;

import java.util.*;

public class Casino {
    private Map<UUID, Player> players;
    private Map<UUID, Match> matches;
    private List<String> results;

    public Casino(Map<UUID, Player> players, Map<UUID, Match> matches) {
        this.players = players;
        this.matches = matches;
        this.results = new ArrayList<>();
    }

    public void processOperations(List<Operation> operations) {
        for (Operation operation : operations) {
            Player player = players.get(operation.getPlayerId());
            if (player == null) {
                continue;
            }

            switch (operation.getType()) {
                case "DEPOSIT":
                    player.deposit(operation.getAmount());
                    break;
                case "WITHDRAW":
                    player.withdraw(operation.getAmount());
                    break;
                case "BET":
                    processBet(player, operation);
                    break;
            }
        }
        generateResults();
    }

    private void processBet(Player player, Operation operation) {
        Match match = matches.get(operation.getMatchId());
        if (match == null || player.getBalance() < operation.getAmount()) {
            return;
        }

        player.placeBet(operation.getAmount(), operation.getBetSide());
        boolean isWin = match.getResult().equals(operation.getBetSide());
        int winnings = calculateWinnings(operation.getAmount(), match, operation.getBetSide(), isWin);
        player.updateBetOutcome(isWin, winnings);
    }

    private int calculateWinnings(int betAmount, Match match, String betSide, boolean isWin) {
        if (!isWin) {
            return 0;
        }
        double rate = betSide.equals("A") ? match.getRateA() : match.getRateB();
        return (int) (betAmount * rate);
    }





    private void generateResults() {
        for (Player player : players.values()) {
            String result = player.getId() + " " + player.getBalance() + " " + String.format("%.2f", player.getWinRate());
            results.add(result);
        }
    }

    public List<String> getResults() {
        return results;
    }
}
