package com.example.scratchgame.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BonusSymbol {
    @JsonProperty("reward_multiplier")
    private double rewardMultiplier;

    @JsonProperty("impact")
    private String impact;

    @JsonProperty("extra")
    private int extra;

    // Getters and Setters

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public void setRewardMultiplier(double rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public int getExtra() {
        return extra;
    }

    public void setExtra(int extra) {
        this.extra = extra;
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
