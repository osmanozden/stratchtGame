package com.example.scratchgame.service;

import com.example.scratchgame.model.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class MatrixGenerator {
    private Config config;
    private Random random = new Random();

    public MatrixGenerator(Config config) {
        this.config = config;
    }

    public Symbol[][] generateMatrix(int rows, int columns) {
        Symbol[][] matrix = new Symbol[rows][columns];
        List<StandardSymbolProbability> standardProbabilities = config.getProbabilities().getStandardSymbols();

        // Matrix'i doldurma
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

        // Bonus sembollerini yerleştirme
        Map<String, Integer> bonusSymbolsProbabilities = config.getProbabilities().getBonusSymbolProbability();
        int totalProbability = bonusSymbolsProbabilities.values().stream().mapToInt(Integer::intValue).sum();

        for (Map.Entry<String, Integer> entry : bonusSymbolsProbabilities.entrySet()) {
            if (random.nextInt(totalProbability) < entry.getValue()) {
                int randomRow = random.nextInt(rows);
                int randomColumn = random.nextInt(columns);
                Symbol bonusSymbol = config.getSymbols().get(entry.getKey());
                if (bonusSymbol == null) {
                    System.err.println("Error: Null bonus symbol for key " + entry.getKey());
                    throw new RuntimeException("Null bonus symbol for key " + entry.getKey());
                }
                matrix[randomRow][randomColumn] = bonusSymbol;
            }
        }

        return matrix;
    }

    private Symbol getRandomSymbol(List<StandardSymbolProbability> probabilities, int row, int column) {
        for (StandardSymbolProbability probability : probabilities) {
            if (probability.getRow() == row && probability.getColumn() == column) {
                int totalWeight = probability.getSymbols().values().stream().mapToInt(Integer::intValue).sum();
                int randomValue = random.nextInt(totalWeight);
                int currentWeight = 0;
                for (Map.Entry<String, Integer> entry : probability.getSymbols().entrySet()) {
                    currentWeight += entry.getValue();
                    if (randomValue < currentWeight) {
                        Symbol symbol = config.getSymbols().get(entry.getKey());
                        if (symbol == null) {
                            System.err.println("Error: Null symbol for key " + entry.getKey() + " in getRandomSymbol method.");
                        }
                        return symbol;
                    }
                }
            }
        }
        System.err.println("Error: No matching probability for row " + row + " and column " + column + " in getRandomSymbol method.");
        return null;
    }
}
