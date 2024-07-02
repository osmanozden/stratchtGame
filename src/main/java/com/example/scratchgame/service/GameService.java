package com.example.scratchgame.service;

import com.example.scratchgame.model.BonusSymbol;
import com.example.scratchgame.model.Config;
import com.example.scratchgame.model.Symbol;
import com.example.scratchgame.model.WinCombination;

import java.util.*;

public class GameService {
    private final Config config;

    public GameService(Config config) {
        this.config = config;
    }

    public double calculateReward(Symbol[][] matrix, double betAmount) {
        Map<String, List<String>> appliedWinningCombinations = new HashMap<>();
        Symbol appliedBonusSymbol = null;
        double reward = 0;

        // Check for winning combinations
        for (Map.Entry<String, WinCombination> entry : config.getWinCombinations().entrySet()) {
            String combinationName = entry.getKey();
            WinCombination winCombination = entry.getValue();

            List<Symbol> matchedSymbols = checkCombination(matrix, winCombination);
            if (!matchedSymbols.isEmpty()) {
                for (Symbol symbol : matchedSymbols) {
                    if (symbol != null) {
                        double symbolReward = betAmount * symbol.getRewardMultiplier();
                        symbolReward *= winCombination.getRewardMultiplier();
                        reward += symbolReward;

                        appliedWinningCombinations.computeIfAbsent(symbol.getName(), k -> new ArrayList<>()).add(combinationName);
                    }
                }
            }
        }

        // Check for bonus symbols
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                Symbol symbol = matrix[row][column];
            }
        }

        // Output results
        System.out.println("Matrix: " + Arrays.deepToString(matrix));
        System.out.println("Reward: " + reward);
        System.out.println("Applied Winning Combinations: " + appliedWinningCombinations);
        System.out.println("Applied Bonus Symbol: " + (appliedBonusSymbol != null ? appliedBonusSymbol.getName() : "None"));

        return reward;
    }

    private List<Symbol> checkCombination(Symbol[][] matrix, WinCombination winCombination) {
        List<Symbol> matchedSymbols = new ArrayList<>();

        if ("same_symbols".equals(winCombination.getWhen())) {
            Map<String, Integer> symbolCounts = new HashMap<>();
            for (int row = 0; row < matrix.length; row++) {
                for (int column = 0; column < matrix[row].length; column++) {
                    Symbol symbol = matrix[row][column];
                    if (symbol != null) {
                        symbolCounts.put(symbol.getName(), symbolCounts.getOrDefault(symbol.getName(), 0) + 1);
                    }
                }
            }
            for (Map.Entry<String, Integer> entry : symbolCounts.entrySet()) {
                if (entry.getValue() >= winCombination.getCount()) {
                    matchedSymbols.add(config.getSymbols().get(entry.getKey()));
                }
            }
        } else if ("linear_symbols".equals(winCombination.getWhen())) {
            for (List<String> area : winCombination.getCoveredAreas()) {
                boolean match = true;
                String firstSymbolName = null;
                for (String position : area) {
                    String[] parts = position.split(":");
                    int row = Integer.parseInt(parts[0]);
                    int column = Integer.parseInt(parts[1]);
                    Symbol symbol = matrix[row][column];
                    String symbolName = symbol != null ? symbol.getName() : null;
                    if (symbolName == null) {
                        match = false;
                        break;
                    }
                    if (firstSymbolName == null) {
                        firstSymbolName = symbolName;
                    } else if (!firstSymbolName.equals(symbolName)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    matchedSymbols.add(config.getSymbols().get(firstSymbolName));
                }
            }
        }

        return matchedSymbols;
    }
}
