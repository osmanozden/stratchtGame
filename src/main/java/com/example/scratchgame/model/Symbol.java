package com.example.scratchgame.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = StandardSymbol.class, name = "standard"),
        @JsonSubTypes.Type(value = BonusSymbol.class, name = "bonus")

})
public abstract class Symbol {
    private String name;
    private String type;

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public abstract double getRewardMultiplier();

    @Override
    public String toString() {
        return "Symbol{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
