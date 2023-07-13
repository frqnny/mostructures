package io.github.frqnny.mostructures.processor;

import com.mojang.serialization.Codec;
import io.github.frqnny.mostructures.init.ProcessorTypes;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class AirStructureProcessor extends StructureProcessor {
    public static final Codec<AirStructureProcessor> CODEC = Codec.unit(AirStructureProcessor::new);

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfo, StructureTemplate.StructureBlockInfo structureBlockInfo2, StructurePlacementData structurePlacementData) {
        if (structureBlockInfo2.state().isAir()) {
            worldView.getChunk(structureBlockInfo2.pos()).setBlockState(structureBlockInfo2.pos(), structureBlockInfo2.state(), false);
        }
        return structureBlockInfo2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return ProcessorTypes.AIR_STRUCTURE_PROCESSOR;
    }
}
