package io.github.frqnny.mostructures.processor;

import com.mojang.serialization.Codec;
import io.github.frqnny.mostructures.generator.JunglePyramidGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class JungleTempleStructureProcessor extends StructureProcessor {
    public static final Codec<JungleTempleStructureProcessor> CODEC;
    private final float mossiness;

    public JungleTempleStructureProcessor(float mossiness) {
        this.mossiness = mossiness;
    }

    @Nullable
    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfo, Structure.StructureBlockInfo structureBlockInfo2, StructurePlacementData structurePlacementData) {
        Random random = structurePlacementData.getRandom(structureBlockInfo2.pos);
        BlockState blockState = structureBlockInfo2.state;
        BlockPos blockPos2 = structureBlockInfo2.pos;
        BlockState blockState2 = null;
        if (blockState.isOf(Blocks.STONE_BRICKS) || blockState.isOf(Blocks.STONE) || blockState.isOf(Blocks.CHISELED_STONE_BRICKS)) {
            blockState2 = this.processBlocks(random);
        }


        return blockState2 != null ? new Structure.StructureBlockInfo(blockPos2, blockState2, structureBlockInfo2.tag) : structureBlockInfo2;
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

    private static BlockState randomStairProperties(Random random, Block stairs) {
        return stairs.getDefaultState().with(StairsBlock.FACING, Direction.Type.HORIZONTAL.random(random)).with(StairsBlock.HALF, BlockHalf.values()[random.nextInt(BlockHalf.values().length)]);
    }

    private BlockState process(Random random, BlockState[] regularStates, BlockState[] mossyStates) {
        return random.nextFloat() < this.mossiness ? randomState(random, mossyStates) : randomState(random, regularStates);
    }

    private static BlockState randomState(Random random, BlockState[] states) {
        return states[random.nextInt(states.length)];
    }

    protected StructureProcessorType<?> getType() {
        return JunglePyramidGenerator.PROCESSOR;
    }

    static {
        CODEC = Codec.FLOAT.fieldOf("mossiness").xmap(JungleTempleStructureProcessor::new, (processor) -> processor.mossiness).codec();
    }
}