package io.github.frqnny.mostructures;

import draylar.omegaconfig.OmegaConfig;
import io.github.frqnny.mostructures.config.MoStructuresConfig;
import io.github.frqnny.mostructures.init.ProcessorTypes;
import io.github.frqnny.mostructures.init.Structures;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class MoStructures implements ModInitializer {
    public static final String MODID = "mostructures";

    public static Identifier id(String name) {
        return new Identifier(MODID, name);
    }

    @Override
    public void onInitialize() {
        OmegaConfig.register(MoStructuresConfig.class);
        Structures.init();
        ProcessorTypes.init();
    }
}
