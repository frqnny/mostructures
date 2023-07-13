package io.github.frqnny.mostructures;

import draylar.omegaconfig.OmegaConfig;
import io.github.frqnny.mostructures.config.MoStructuresConfig;
import io.github.frqnny.mostructures.init.Events;
import io.github.frqnny.mostructures.init.ProcessorTypes;
import io.github.frqnny.mostructures.init.Structures;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class MoStructures implements ModInitializer {
    public static final MoStructuresConfig CONFIG = OmegaConfig.register(MoStructuresConfig.class);
    public static final String MODID = "mostructures";

    public static Identifier id(String name) {
        return new Identifier(MODID, name);
    }

    @Override
    public void onInitialize() {
        Structures.init();
        ProcessorTypes.init();
        Events.init();
    }
}
