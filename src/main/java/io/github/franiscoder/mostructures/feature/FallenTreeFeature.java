package io.github.franiscoder.mostructures.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class FallenTreeFeature extends Feature<DefaultFeatureConfig> {
    public FallenTreeFeature() {
        super(DefaultFeatureConfig::deserialize);
    }

    public static BlockState getWoodToPlace(Biome biome) {
        if (biome == Biomes.BIRCH_FOREST || biome == Biomes.BIRCH_FOREST_HILLS || biome == Biomes.TALL_BIRCH_FOREST) {
            return Blocks.BIRCH_WOOD.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.X);
        } else if (biome.getCategory() == Biome.Category.FOREST || biome == Biomes.PLAINS || biome.getCategory() == Biome.Category.RIVER) {
            return Blocks.OAK_WOOD.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.X);
        } else if (biome.getCategory() == Biome.Category.TAIGA) {
            return Blocks.SPRUCE_WOOD.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.X);
        } else if (biome.getCategory() == Biome.Category.DESERT) {
            return Blocks.ACACIA_WOOD.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.X);
        } else {
            return Blocks.AIR.getDefaultState();
        }
    }

    public static boolean canPlaceWood(BlockState block) {
        return block == Blocks.AIR.getDefaultState() || block == Blocks.GRASS.getDefaultState() || block == Blocks.WATER.getDefaultState();
    }

    @Override
    public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        Biome.Category category = world.getBiome(pos).getCategory();
        if (world.getDimension().getType() != DimensionType.OVERWORLD
                || category == Biome.Category.OCEAN
                || category == Biome.Category.RIVER
                || category == Biome.Category.BEACH
                || Registry.BIOME.getId(world.getBiome(pos)).getNamespace().equals("minecraft")
        ) {
            return false;
        }


        BlockPos newPos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos);

        if (world.getBlockState(newPos.down()) == Blocks.WATER.getDefaultState()) {
            newPos = newPos.down();
        }
        BlockState blockToPlace = getWoodToPlace(world.getBiome(newPos));

        //Makes Desert Fallen Trees rarer, as in Forest that is taken care whether it is to generate in an open space or not
        if (world.getBiome(newPos).getCategory() == Biome.Category.DESERT || world.getBiome(newPos).getCategory() == Biome.Category.PLAINS) {
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
