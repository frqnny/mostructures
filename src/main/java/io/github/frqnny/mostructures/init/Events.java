package io.github.frqnny.mostructures.init;

import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.config.StructureConfigEntry;
import io.github.frqnny.mostructures.mixin.RandomSpreadStructurePlacementAccessor;
import io.github.frqnny.mostructures.mixin.StructureSetAccessor;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;

import java.util.List;

public class Events {

    public static void init() {
        DynamicRegistrySetupCallback.EVENT.register((registries) -> {
            if (registries instanceof DynamicRegistryManager.Mutable mutableRegistries) {
                MutableRegistry<StructureSet> structureSets = mutableRegistries.getMutable(Registry.STRUCTURE_SET_KEY);

                RegistryEntryAddedCallback.event(structureSets).register((rawId, id, structureSet) -> {
                    StructureConfigEntry entry = MoStructures.CONFIG.get(id);

                    if (entry != null)  { // id matches some structure in the config
                        if (structureSet.placement() instanceof RandomSpreadStructurePlacement) {
                            ((RandomSpreadStructurePlacementAccessor) structureSet.placement()).setSpacing(entry.spacing);
                            ((RandomSpreadStructurePlacementAccessor) structureSet.placement()).setSeparation(entry.separation);
                            if (!entry.activated) {
                                ((StructureSetAccessor)(Object) structureSet).setStructures(List.of());
                            }
                        }
                    }
                });
            }
        });
    }
}
