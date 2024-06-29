package io.github.frqnny.mostructures.fabric;

import io.github.frqnny.mostructures.MoStructures;
import net.fabricmc.api.ModInitializer;

public final class MoStructuresFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MoStructures.init();
    }
}
