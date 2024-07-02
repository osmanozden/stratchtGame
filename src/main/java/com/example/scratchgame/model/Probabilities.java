package com.example.scratchgame.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class Probabilities {
    @JsonProperty("standard_symbols")
    private List<StandardSymbolProbability> standardSymbols;

    @JsonProperty("bonus_symbols")
    private Map<String, BonusSymbol> bonusSymbols;


    public List<StandardSymbolProbability> getStandardSymbols() {
        return standardSymbols;
    }

    public Map<String, BonusSymbol> getBonusSymbols() {
        return bonusSymbols;
    }

    @Override
    public String toString() {
        return "Probabilities{" +
                "standardSymbols=" + standardSymbols +
                ", bonusSymbols=" + bonusSymbols +
                '}';
    }
}
