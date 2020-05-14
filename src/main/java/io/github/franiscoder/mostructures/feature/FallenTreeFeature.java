package io.github.franiscoder.mostructures.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class FallenTreeFeature extends Feature<DefaultFeatureConfig> {
    public FallenTreeFeature() {
        super(DefaultFeatureConfig::deserialize);
    }

    public static BlockState getWoodToPlace(Biome biome) {
        Biome.Category category = biome.getCategory();
        if (biome == Biomes.BIRCH_FOREST || biome == Biomes.BIRCH_FOREST_HILLS || biome == Biomes.TALL_BIRCH_FOREST) {
            return Blocks.BIRCH_WOOD.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.X);
        } else if (category == Biome.Category.FOREST || biome == Biomes.PLAINS || category == Biome.Category.RIVER || category == Biome.Category.SWAMP) {
            return Blocks.OAK_WOOD.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.X);
        } else if (category == Biome.Category.SAVANNA) {
            return Blocks.ACACIA_WOOD.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.X);
        } else if (category == Biome.Category.TAIGA || category == Biome.Category.ICY) {
            return Blocks.SPRUCE_WOOD.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.X);
        } else if (category == Biome.Category.DESERT) {
            return Blocks.ACACIA_WOOD.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.X);
        } else {
            return Blocks.AIR.getDefaultState();
        }
    }

    public static boolean canPlaceWood(BlockState block) {
        return block == Blocks.AIR.getDefaultState() || block == Blocks.GRASS.getDefaultState() || block == Blocks.WATER.getDefaultState() || block == Blocks.VINE.getDefaultState();
    }

    @Override
    public boolean generate(ServerWorldAccess world, StructureAccessor accessor, ChunkGenerator generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        Biome.Category category = world.getBiome(pos).getCategory();
        if (world.getDimension().getType() != DimensionType.OVERWORLD || category == Biome.Category.OCEAN
                || category == Biome.Category.RIVER
                || category == Biome.Category.BEACH) {
            return false;
        }


        BlockPos newPos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos);

        if (world.getBlockState(newPos.down()) == Blocks.WATER.getDefaultState()) {
            newPos = newPos.down();
        }
        BlockState blockToPlace = getWoodToPlace(world.getBiome(newPos));

        //Makes Desert Fallen Trees rarer, as in Forest that is taken care whether it is to generate in an open space or not
        if (category == Biome.Category.DESERT || category == Biome.Category.PLAINS || category == Biome.Category.SWAMP) {
            if (random.nextInt(10) != 1) {
                return false;
            }
        }

        for (int i = 6; i > 0; i--) {
            if (canPlaceWood(world.getBlockState(newPos))) {
                world.setBlockState(newPos, blockToPlace, 3);
                newPos = newPos.east();
            } else {
                break;
            }
        }
        return true;
    }
}
