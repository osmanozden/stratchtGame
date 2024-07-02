package com.example.scratchgame.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BonusSymbol {
    @JsonProperty("reward_multiplier")
    private double rewardMultiplier;

    @JsonProperty("impact")
    private String impact;

    @JsonProperty("extra")
    private int extra;

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    @Override
    public String toString() {
        return "BonusSymbol{" +
                "rewardMultiplier=" + rewardMultiplier +
                ", impact='" + impact + '\'' +
                ", extra=" + extra +
                '}';
    }
}
