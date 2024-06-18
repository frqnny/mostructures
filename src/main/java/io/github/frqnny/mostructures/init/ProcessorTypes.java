package io.github.frqnny.mostructures.init;

import io.github.frqnny.mostructures.processor.SimpleCobblestoneProcessor;
import io.github.frqnny.mostructures.processor.SimpleStoneStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;

public class ProcessorTypes {
    public static final StructureProcessorType<SimpleStoneStructureProcessor> SIMPLE_STONE = StructureProcessorType.register("mostructures:jungle_rot_processor", SimpleStoneStructureProcessor.CODEC);
    public static final StructureProcessorType<SimpleCobblestoneProcessor> SIMPLE_COBBLESTONE = StructureProcessorType.register("mostructures:simple_cobblestone", SimpleCobblestoneProcessor.CODEC);

    public static void init() {

    }
}
