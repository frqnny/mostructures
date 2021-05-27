package io.github.frqnny.mostructures.processor;

import com.mojang.serialization.Codec;
import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class DataBlockStructureProcessor extends StructureProcessor {
    public static final Codec<DataBlockStructureProcessor> CODEC = Codec.unit(DataBlockStructureProcessor::new);


    @Nullable
    @Override
    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfoLocal, Structure.StructureBlockInfo structureBlockInfoWorld, StructurePlacementData structurePlacementData) {
        BlockState state = structureBlockInfoWorld.state;

        if (state.isOf(Blocks.STRUCTURE_BLOCK)) {
            String mode = structureBlockInfoWorld.nbt.getString("mode");


            if (mode.contains("DATA")) {
                String metadata = structureBlockInfoWorld.nbt.getString("metadata");
                BlockPos worldPos = structureBlockInfoWorld.pos;

            }
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return MoStructures.DATA_BLOCK_STRUCTURE_PROCESSOR;
    }
}
