package com.example.scratchgame.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Map;

@JsonTypeName("standard_symbols")
public class StandardSymbolProbability {
    private int column;
    private int row;
    private Map<String, Integer> symbols;

    @Override
    public String toString() {
        return "StandardSymbolProbability{" +
                "column=" + column +
                ", row=" + row +
                ", symbols=" + symbols +
                '}';
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Map<String, Integer> getSymbols() {
        return symbols;
    }
}
