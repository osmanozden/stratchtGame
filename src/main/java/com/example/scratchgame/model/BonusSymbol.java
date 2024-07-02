package com.example.scratchgame.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BonusSymbol extends Symbol{
    @JsonProperty("reward_multiplier")
    private double rewardMultiplier;

    @JsonProperty("impact")
    private String impact;

    @JsonProperty("extra")
    private int extra;

    @JsonProperty("type")
    private String type;


    public String getType() {
        return type;
    }

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public String getImpact() {
        return impact;
    }

    public int getExtra() {
        return extra;
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
