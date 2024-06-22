package io.github.frqnny.mostructures.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

import io.github.frqnny.mostructures.MoStructures;
import net.neoforged.neoforge.common.world.StructureModifiers;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.IRegistryExtension;

@Mod(MoStructures.MOD_ID)
public final class MoStructuresNeoForge {
    public MoStructuresNeoForge(IEventBus bus) {
        // Run our common setup.
        MoStructures.init();
    }
}
