package com.jea;

import net.minecraft.world.item.Item;

public class OIIAItem extends Item {
    public OIIAItem(Properties properties) {
        super(properties);
        properties.stacksTo(1);
        properties.durability(0);
        properties.useCooldown(20);

    }
}
