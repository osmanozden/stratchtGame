package com.example.scratchgame.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Config {
    @JsonProperty("columns")
    private int columns;

    @JsonProperty("rows")
    private int rows;

    @JsonProperty("symbols")
    private Map<String, Symbol> symbols;

    @JsonProperty("probabilities")
    private Probabilities probabilities;

    @JsonProperty("win_combinations")
    private Map<String, WinCombination> winCombinations;

    public int getColumns() {return columns;}
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
}
