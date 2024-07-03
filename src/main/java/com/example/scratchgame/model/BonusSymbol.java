package com.example.scratchgame.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonTypeName("bonus")
public class BonusSymbol extends Symbol {
    @JsonProperty("reward_multiplier")
    private double rewardMultiplier;

    @JsonProperty("impact")
    private String impact;

    @JsonProperty("extra")
    private int extra;


    @Override
    public double getRewardMultiplier() {
        return rewardMultiplier;
    }


    public String getImpact() {
        return impact;
    }


    public int getExtra() {
        return extra;
    }

}
