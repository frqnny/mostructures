package io.github.frqnny.mostructures.processor;

import com.mojang.serialization.Codec;
import io.github.frqnny.mostructures.init.ProcessorTypes;
import net.minecraft.block.Waterloggable;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
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
    public Structure.StructureBlockInfo process(WorldView worldReader, BlockPos pos, BlockPos pos2, Structure.StructureBlockInfo infoIn1, Structure.StructureBlockInfo infoIn2, StructurePlacementData settings) {

        ChunkPos currentChunkPos = new ChunkPos(infoIn2.pos);
        if (infoIn2.state.getBlock() instanceof Waterloggable) {
            Chunk currentChunk = worldReader.getChunk(currentChunkPos.x, currentChunkPos.z);
            if (worldReader.getFluidState(infoIn2.pos).isIn(FluidTags.WATER)) {
                currentChunk.setBlockState(infoIn2.pos, infoIn2.state, false);
            }

        }

        return infoIn2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return ProcessorTypes.REMOVE_WATERLOGGED;
    }
}
