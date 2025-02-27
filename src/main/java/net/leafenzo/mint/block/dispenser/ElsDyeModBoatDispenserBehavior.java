/*
 * SOURCES:
 * Team Hibiscus - https://github.com/Team-Hibiscus/NaturesSpirit/blob/1.20.1/remappedSrc/net/hibiscus/naturespirit/items/HibiscusBoatDispensorBehavior.java
 */

package net.leafenzo.mint.block.dispenser;

import net.leafenzo.mint.entity.ElsDyeModBoatEntity;
import net.minecraft.block.dispenser.BoatDispenserBehavior;
import net.minecraft.entity.vehicle.BoatEntity;

public final class ElsDyeModBoatDispenserBehavior extends BoatDispenserBehavior {
    private final ElsDyeModBoatEntity.ModBoat boatData;

    public ElsDyeModBoatDispenserBehavior(ElsDyeModBoatEntity.ModBoat boatData, boolean chest) {
        super(BoatEntity.Type.OAK, chest);
        this.boatData = boatData;
    }

    public ElsDyeModBoatEntity.ModBoat getBoatData() {
        return boatData;
    }
}