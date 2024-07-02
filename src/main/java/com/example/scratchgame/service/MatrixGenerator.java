package com.example.scratchgame.service;

import com.example.scratchgame.model.BonusSymbol;
import com.example.scratchgame.model.Config;
import com.example.scratchgame.model.Symbol;
import com.example.scratchgame.model.StandardSymbolProbability;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class MatrixGenerator {
    private final Config config;
    private final Random random = new Random();

    public MatrixGenerator(Config config) {
        this.config = config;
    }

    public Symbol[][] generateMatrix(int rows, int columns) {
        Symbol[][] matrix = new Symbol[rows][columns];
        List<StandardSymbolProbability> standardProbabilities = config.getProbabilities().getStandardSymbols();

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Symbol symbol = getRandomSymbol(standardProbabilities, row, column);
                if (symbol == null) {
                    System.err.println("Error: Null symbol generated at position (" + row + "," + column + ")");
                    throw new RuntimeException("Null symbol generated at position (" + row + "," + column + ")");
                }
                matrix[row][column] = symbol;
            }
        }
        System.out.println(random);
        // Add bonus symbols based on their probabilities
        Map<String, BonusSymbol> bonusSymbols = config.getProbabilities().getBonusSymbols();
        for (Map.Entry<String, BonusSymbol> entry : bonusSymbols.entrySet()) {
            if (random.nextInt(100) < entry.getValue().getRewardMultiplier()) {
                int randomRow = random.nextInt(rows);
                int randomColumn = random.nextInt(columns);
                Symbol symbol = config.getSymbols().get(entry.getKey());
                if (symbol == null) {
                    System.err.println("Error: Null bonus symbol for key " + entry.getKey());
                    throw new RuntimeException("Null bonus symbol for key " + entry.getKey());
                }
                matrix[randomRow][randomColumn] = symbol;
            }
        }

        return matrix;
    }

    private Symbol getRandomSymbol(List<StandardSymbolProbability> probabilities, int row, int column) {
        for (StandardSymbolProbability probability : probabilities) {
            if (probability.getRow() == row && probability.getColumn() == column) {
                return selectSymbol(probability.getSymbols());
            }
        }
        return null;
    }

    private Symbol selectSymbol(Map<String, Integer> symbols) {
        int sumOfProbabilities = symbols.values().stream().mapToInt(Integer::intValue).sum();
        int randomValue = random.nextInt(sumOfProbabilities);

        for (Map.Entry<String, Integer> entry : symbols.entrySet()) {
            randomValue -= entry.getValue();
            if (randomValue < 0) {
                Symbol symbol = config.getSymbols().get(entry.getKey());
                if (symbol == null) {
                    System.err.println("Error: Null symbol for key " + entry.getKey());
                    throw new RuntimeException("Null symbol for key " + entry.getKey());
                }
                return symbol;
            }
        }
        return null;
    }
}
