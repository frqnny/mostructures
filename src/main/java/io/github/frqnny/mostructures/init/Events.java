package io.github.frqnny.mostructures.init;

import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.config.StructureConfigEntry;
import io.github.frqnny.mostructures.structure.ModStructurePlacement;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.util.registry.Registry;

public class Events {

    public static void init() {
        DynamicRegistrySetupCallback.EVENT.register(view -> RegistryEntryAddedCallback.event(view.get(Registry.STRUCTURE_SET_KEY)).register((rawId, id, structureSet) -> {
            StructureConfigEntry entry = MoStructures.CONFIG.get(id);

            if (entry != null) { // id matches some structure in the config
                if (structureSet.placement() instanceof ModStructurePlacement p) {
                    p.setSpacing(entry.spacing);
                    p.setSeparation(entry.separation);
                    p.setActivated(entry.activated);
                }
            }
        }));
    }
}
