package com.example.scratchgame.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Config {
    private int columns;
    private int rows;
    private Map<String, Symbol> symbols;
    private Probabilities probabilities;

    @JsonProperty("win_combinations")
    private Map<String, WinCombination> winCombinations;

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }
    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    public Probabilities getProbabilities() {
        return probabilities;
    }

    public Map<String, WinCombination> getWinCombinations() {
        return winCombinations;
    }

    @Override
    public String toString() {
        return "Config{" +
                "columns=" + columns +
                ", rows=" + rows +
                ", symbols=" + symbols +
                ", probabilities=" + probabilities +
                ", winCombinations=" + winCombinations +
                '}';
    }
}
