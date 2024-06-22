package io.github.frqnny.mostructures.init;

import com.mojang.serialization.MapCodec;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.processor.SimpleCobblestoneProcessor;
import io.github.frqnny.mostructures.processor.SimpleStoneStructureProcessor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;

public class ProcessorTypes {
    public static RegistrySupplier<StructureProcessorType<SimpleStoneStructureProcessor>> SIMPLE_STONE;
    public static RegistrySupplier<StructureProcessorType<SimpleCobblestoneProcessor>> SIMPLE_COBBLESTONE;

    public static void init() {
        var structureTypeRegistry = MoStructures.MANAGER.get().get(RegistryKeys.STRUCTURE_PROCESSOR);
        StructureProcessorType<SimpleStoneStructureProcessor> simpleStoneStructureProcessorStructureProcessorType = () -> SimpleStoneStructureProcessor.CODEC;
        SIMPLE_STONE = structureTypeRegistry.register(MoStructures.id("jungle_rot_processor"), () -> simpleStoneStructureProcessorStructureProcessorType);
        StructureProcessorType<SimpleCobblestoneProcessor> simpleCobblestoneProcessorStructureProcessorType = () -> SimpleCobblestoneProcessor.CODEC;
        SIMPLE_COBBLESTONE = structureTypeRegistry.register(MoStructures.id("simple_cobblestone"), () -> simpleCobblestoneProcessorStructureProcessorType);
    }

}
