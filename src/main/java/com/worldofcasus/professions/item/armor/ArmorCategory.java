package com.worldofcasus.professions.item.armor;

import com.worldofcasus.professions.item.ItemCategory;

import java.time.Duration;

public enum ArmorCategory implements ItemCategory {

    LIGHT_ARMOR(
            "Light Armor",
            Duration.ofMinutes(1L),
            Duration.ofMinutes(1L)
    ),
    MEDIUM_ARMOR(
            "Medium Armor",
            Duration.ofMinutes(5L),
            Duration.ofMinutes(1L)
    ),
    HEAVY_ARMOR(
            "Heavy Armor",
            Duration.ofMinutes(10L),
            Duration.ofMinutes(5L)
    ),
    SHIELD(
            "Shield",
            null,
            null
    );

    private final String name;
    private final Duration donTime;
    private final Duration doffTime;

    ArmorCategory(
            String name,
            Duration donTime,
            Duration doffTime
    ) {
        this.name = name;
        this.donTime = donTime;
        this.doffTime = doffTime;
    }

    @Override
    public String getName() {
        return name;
    }

    public Duration getDonTime() {
        return donTime;
    }

    public Duration getDoffTime() {
        return doffTime;
    }

}
