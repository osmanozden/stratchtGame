package com.example.scratchgame.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StandardSymbol extends Symbol {
    @JsonProperty("reward_multiplier")
    private double rewardMultiplier;
    @JsonProperty("type")
    private String type;


    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    @Override
    public String toString() {
        return "StandardSymbol{" +
                "rewardMultiplier=" + rewardMultiplier +
                '}';
    }

    @Override
    public String getType() {
        return type;
    }
}
