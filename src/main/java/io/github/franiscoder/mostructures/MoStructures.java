package io.github.franiscoder.mostructures;

import io.github.franiscoder.mostructures.common.init.ModStructures;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class MoStructures implements ModInitializer {
    public static final String MODID = "mostructures";

    public static Identifier id(String name) {
        return new Identifier(MODID, name);
    }

    @Override
    public void onInitialize() {
        ModStructures.init();
    }
}
