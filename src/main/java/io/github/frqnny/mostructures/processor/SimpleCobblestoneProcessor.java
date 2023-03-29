package io.github.frqnny.mostructures.processor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.frqnny.mostructures.init.ProcessorTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class SimpleCobblestoneProcessor extends StructureProcessor {
    public static final Codec<SimpleCobblestoneProcessor> CODEC = RecordCodecBuilder.create((proc) -> proc.group(
            Codec.FLOAT.fieldOf("mossiness").forGetter(processor -> processor.mossiness)
    ).apply(proc, SimpleCobblestoneProcessor::new));
    private final float mossiness;

    public SimpleCobblestoneProcessor(float mossiness) {
        this.mossiness = mossiness;
    }

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfo, StructureTemplate.StructureBlockInfo structureBlockInfo2, StructurePlacementData structurePlacementData) {
        Random random = structurePlacementData.getRandom(structureBlockInfo2.pos);
        BlockState blockState = structureBlockInfo2.state;
        BlockPos blockPos2 = structureBlockInfo2.pos;
        BlockState blockState2 = null;

        if (blockState.isOf(Blocks.COBBLESTONE)) {
            blockState2 = this.processBlocks(random);
        }

        return blockState2 != null ? new StructureTemplate.StructureBlockInfo(blockPos2, blockState2, structureBlockInfo2.nbt) : structureBlockInfo2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return ProcessorTypes.SIMPLE_COBBLESTONE;
    }

    @Nullable
    private BlockState processBlocks(Random random) {
        if (random.nextFloat() >= 0.5F) {
            return null;
        } else {
            return random.nextFloat() < this.mossiness ? Blocks.COBBLESTONE.getDefaultState() : Blocks.MOSSY_COBBLESTONE.getDefaultState();

        }
    }
}
