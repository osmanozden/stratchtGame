package com.example.scratchgame.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;
import java.util.Map;

@JsonTypeName("probabilities")
public class Probabilities {
    @JsonProperty("standard_symbols")
    private List<StandardSymbolProbability> standardSymbols;

    public Map<String, Integer> getBonusSymbolProbability() {
        return bonusSymbolProbability;
    }

    @JsonProperty("bonus_symbols")
    private Map<String, Integer> bonusSymbolProbability;


    public List<StandardSymbolProbability> getStandardSymbols() {
        return standardSymbols;
    }



    @Override
    public String toString() {
        return "Probabilities{" +
                "standardSymbols=" + standardSymbols +
                ", bonusSymbols=" + bonusSymbolProbability +
                '}';
    }
}
