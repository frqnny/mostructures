package io.github.frqnny.mostructures.processor;

import com.mojang.serialization.Codec;
import io.github.frqnny.mostructures.init.ProcessorTypes;
import net.minecraft.block.Waterloggable;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.Chunk;
import org.jetbrains.annotations.Nullable;


//A more aggresive form of removal waterlloging
public class RemoveWaterloggedProcessor extends StructureProcessor {
    public static final Codec<RemoveWaterloggedProcessor> CODEC = Codec.unit(RemoveWaterloggedProcessor::new);


    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfo, StructureTemplate.StructureBlockInfo structureBlockInfo2, StructurePlacementData structurePlacementData) {

        ChunkPos currentChunkPos = new ChunkPos(structureBlockInfo2.pos);
        if (structureBlockInfo2.state.getBlock() instanceof Waterloggable) {
            Chunk currentChunk = worldView.getChunk(currentChunkPos.x, currentChunkPos.z);
            if (worldView.getFluidState(structureBlockInfo2.pos).isIn(FluidTags.WATER)) {
                currentChunk.setBlockState(structureBlockInfo2.pos, structureBlockInfo2.state, false);
            }

        }

        return structureBlockInfo2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return ProcessorTypes.REMOVE_WATERLOGGED;
    }
}
