package io.github.frqnny.mostructures.init;

import io.github.frqnny.mostructures.processor.AirStructureProcessor;
import io.github.frqnny.mostructures.processor.RemoveWaterloggedProcessor;
import io.github.frqnny.mostructures.processor.SimpleCobblestoneProcessor;
import io.github.frqnny.mostructures.processor.SimpleStoneStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;

public class ProcessorTypes {
    public static final StructureProcessorType<SimpleStoneStructureProcessor> SIMPLE_STONE = StructureProcessorType.register("mostructures:jungle_rot_processor", SimpleStoneStructureProcessor.CODEC);
    public static final StructureProcessorType<SimpleCobblestoneProcessor> SIMPLE_COBBLESTONE = StructureProcessorType.register("mostructures:simple_cobblestone", SimpleCobblestoneProcessor.CODEC);
    public static final StructureProcessorType<AirStructureProcessor> AIR_STRUCTURE_PROCESSOR = StructureProcessorType.register("mostructures:air_structure_processor", AirStructureProcessor.CODEC);
    public static final StructureProcessorType<RemoveWaterloggedProcessor> REMOVE_WATERLOGGED = StructureProcessorType.register("mostructures:remove_waterlog_processor", RemoveWaterloggedProcessor.CODEC);

    public static void init() {

    }
}
