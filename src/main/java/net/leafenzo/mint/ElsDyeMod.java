package net.leafenzo.mint;

import net.minecraft.util.Identifier;

public class ElsDyeMod {
    public static String MOD_ID = "mint";

    public static Identifier asResource(String path) {
        return new Identifier(MOD_ID, path.toLowerCase().replace(' ', '_')); // Silently make the path lowercase if it's not.
    }
}
//Sources:
//https://github.com/Alpha-s-Stuff/Nebula/blob/8fa67790b706a576bc6d687e1fc6f94a0f773d3c/src/main/java/me/alphamode/nebula/Nebula.java
