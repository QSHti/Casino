package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String playerDataPath = "C:\\Users\\dimas\\Desktop\\CasinoData\\player_data.txt";
        String matchDataPath = "C:\\Users\\dimas\\Desktop\\CasinoData\\match_data.txt";
        String resultPath = "C:\\Users\\dimas\\Desktop\\CasinoData\\result.txt";

        Map<UUID, Player> players = readPlayerData(playerDataPath);
        Map<UUID, Match> matches = readMatchData(matchDataPath);

        List<Operation> operations = new ArrayList<>();
        Casino casino = new Casino(players, matches);
        casino.processOperations(operations);

        writeResultsToFile(casino.getResults(), resultPath);
    }



    private static Map<UUID, Player> readPlayerData(String filePath) {
        Map<UUID, Player> players = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                UUID playerId = UUID.fromString(data[0]);
                String operation = data[1];
                int coinAmount = data.length > 3 ? Integer.parseInt(data[3]) : 0;
                String betSide = data.length > 4 ? data[4] : null;

                Player player = players.getOrDefault(playerId, new Player(playerId));
                // Update player based on the operation
                switch (operation) {
                    case "DEPOSIT":
                        player.deposit(coinAmount);
                        break;
                    case "BET":
                        player.placeBet(coinAmount, betSide);
                        break;
                    case "WITHDRAW":
                        player.withdraw(coinAmount);
                        break;
                }
                players.put(playerId, player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }


    private static Map<UUID, Match> readMatchData(String filePath) {
        Map<UUID, Match> matches = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    UUID matchId = UUID.fromString(data[0]);
                    double rateA = Double.parseDouble(data[1]);
                    double rateB = Double.parseDouble(data[2]);
                    String result = data[3];
                    matches.put(matchId, new Match(matchId, rateA, rateB, result));
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }


    private static void writeResultsToFile(List<String> results, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String result : results) {
                bw.write(result);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
