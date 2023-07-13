package io.github.frqnny.mostructures.processor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.frqnny.mostructures.init.ProcessorTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class SimpleStoneStructureProcessor extends StructureProcessor {
    public static final Codec<SimpleStoneStructureProcessor> CODEC = RecordCodecBuilder.create((proc) -> proc.group(
            Codec.FLOAT.fieldOf("mossiness").forGetter(processor -> processor.mossiness)
    ).apply(proc, SimpleStoneStructureProcessor::new));
    private final float mossiness;

    public SimpleStoneStructureProcessor(float mossiness) {
        this.mossiness = mossiness;
    }

    private static BlockState randomStairProperties(Random random, Block stairs) {
        return stairs.getDefaultState().with(StairsBlock.FACING, Direction.Type.HORIZONTAL.random(random)).with(StairsBlock.HALF, BlockHalf.values()[random.nextInt(BlockHalf.values().length)]);
    }

    private static BlockState randomState(Random random, BlockState[] states) {
        return states[random.nextInt(states.length)];
    }

    @Nullable
    public StructureTemplate.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfo, StructureTemplate.StructureBlockInfo structureBlockInfo2, StructurePlacementData structurePlacementData) {
        Random random = structurePlacementData.getRandom(structureBlockInfo2.pos());
        BlockState blockState = structureBlockInfo2.state();
        BlockPos blockPos2 = structureBlockInfo2.pos();
        BlockState blockState2 = null;
        if (blockState.isOf(Blocks.STONE_BRICKS) || blockState.isOf(Blocks.STONE) || blockState.isOf(Blocks.CHISELED_STONE_BRICKS)) {
            blockState2 = this.processBlocks(random);
        }


        return blockState2 != null ? new StructureTemplate.StructureBlockInfo(blockPos2, blockState2, structureBlockInfo2.nbt()) : structureBlockInfo2;
    }

    @Nullable
    private BlockState processBlocks(Random random) {
        if (random.nextFloat() >= 0.5F) {
            return null;
        } else {
            BlockState[] blockStates = new BlockState[]{Blocks.CRACKED_STONE_BRICKS.getDefaultState(), randomStairProperties(random, Blocks.STONE_BRICK_STAIRS)};
            BlockState[] blockStates2 = new BlockState[]{Blocks.MOSSY_STONE_BRICKS.getDefaultState(), randomStairProperties(random, Blocks.MOSSY_STONE_BRICK_STAIRS)};
            return this.process(random, blockStates, blockStates2);
        }
    }

    private BlockState process(Random random, BlockState[] regularStates, BlockState[] mossyStates) {
        return random.nextFloat() < this.mossiness ? randomState(random, mossyStates) : randomState(random, regularStates);
    }

    protected StructureProcessorType<?> getType() {
        return ProcessorTypes.SIMPLE_STONE;
    }


}