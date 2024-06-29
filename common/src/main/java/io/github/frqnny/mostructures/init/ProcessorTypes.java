package io.github.frqnny.mostructures.init;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.processor.SimpleCobblestoneProcessor;
import io.github.frqnny.mostructures.processor.SimpleStoneStructureProcessor;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.processor.StructureProcessorType;

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
