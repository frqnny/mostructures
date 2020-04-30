package io.github.franiscoder.mostructures.common.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class RuinsFeature extends Feature<DefaultFeatureConfig> {
    public RuinsFeature() {
        super(DefaultFeatureConfig::deserialize);
    }

    public static BlockState getRandomBlock() {
        Random random = new Random();
        int blockToPlace = random.nextInt(2);
        switch (blockToPlace) {
            case 0:
                return Blocks.COBBLESTONE.getDefaultState();
            case 1:
                return Blocks.COARSE_DIRT.getDefaultState();
            default:
                return Blocks.MOSSY_COBBLESTONE.getDefaultState();
        }
    }

    private static int getBaseHeight(IWorld world, int x, int y) {
        return world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, x, y) - 1;
    }

    @Override
    public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        if (world.getBiome(pos).getCategory() == Biome.Category.EXTREME_HILLS || world.getBiome(pos).getCategory() == Biome.Category.OCEAN || world.getBiome(pos).getCategory() == Biome.Category.FOREST || world.getBiome(pos).getCategory() == Biome.Category.THEEND || world.getBlockState(pos.down()).getBlock() == Blocks.WATER)
            return false;

        this.placeBase(random, world, pos);
        //Generates
        BlockPos originalPos = pos;

        pos = pos.south(6);
        pos = new BlockPos(pos.getX(), getBaseHeight(world, pos.getX(), pos.getZ()), pos.getZ());
        placeSingleWall(world, pos, true);
        placeSingleWall(world, pos.east(), true);
        placeSingleWall(world, pos.west(), true);
        pos = originalPos;

        pos = pos.north(6);
        pos = new BlockPos(pos.getX(), getBaseHeight(world, pos.getX(), pos.getZ()), pos.getZ());
        placeSingleWall(world, pos, true);
        placeSingleWall(world, pos.east(), true);
        placeSingleWall(world, pos.west(), true);
        pos = originalPos;

        pos = pos.west(6);
        pos = new BlockPos(pos.getX(), getBaseHeight(world, pos.getX(), pos.getZ()), pos.getZ());
        placeSingleWall(world, pos, true);
        placeSingleWall(world, pos.north(), true);
        placeSingleWall(world, pos.south(), true);
        pos = originalPos;

        pos = pos.east(6);
        pos = new BlockPos(pos.getX(), getBaseHeight(world, pos.getX(), pos.getZ()), pos.getZ());
        placeSingleWall(world, pos, true);
        placeSingleWall(world, pos.north(), true);
        placeSingleWall(world, pos.south(), true);
        pos = originalPos;
        //Now, it will try to generate the angled walls

        //main south point
        BlockPos mainSouthPoint = pos.south(3);
        placeSingleWall(world, fixYForWall(world, mainSouthPoint.east(2)), false);
        placeSingleWall(world, fixYForWall(world, mainSouthPoint.east(3).north()), false);
        placeSingleWall(world, fixYForWall(world, mainSouthPoint.west(2)), false);
        placeSingleWall(world, fixYForWall(world, mainSouthPoint.west(3).north()), false);

        //main north point
        BlockPos mainNorthPoint = pos.north(3);
        placeSingleWall(world, fixYForWall(world, mainNorthPoint.east(2)), false);
        placeSingleWall(world, fixYForWall(world, mainNorthPoint.east(3).south()), false);
        placeSingleWall(world, fixYForWall(world, mainNorthPoint.west(2)), false);
        placeSingleWall(world, fixYForWall(world, mainNorthPoint.west(3).south()), false);

        //Add altar block which is just a chiseled thing
        world.setBlockState(originalPos, Blocks.CHISELED_STONE_BRICKS.getDefaultState(), 3);

        return true;
    }

    private void placeBase(Random random, IWorld world, BlockPos pos) {
        int i = pos.getX();
        int j = pos.getZ();
        float[] fs = new float[]{1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.9F, 0.9F, 0.8F, 0.7F, 0.6F, 0.4F, 0.2F};
        int k = fs.length;
        int l = 16;
        int m = random.nextInt(Math.max(1, 8 - l / 2));
        BlockPos.Mutable mutable = BlockPos.PooledMutable.get();

        for (int o = i - k; o <= i + k; ++o) {
            for (int p = j - k; p <= j + k; ++p) {
                int q = Math.abs(o - i) + Math.abs(p - j);
                int r = Math.max(0, q + m);
                if (r < k) {
                    float f = fs[r];
                    if (random.nextDouble() < (double) f) {
                        int t = getBaseHeight(world, o, p);
                        mutable.set(o, t, p);
                        this.placeBottom(world, mutable);
                    }
                }
            }
        }

    }

    private void placeBottom(IWorld world, BlockPos pos) {
        world.setBlockState(pos, getRandomBlock(), 3);

    }

    private void placeSingleWall(IWorld world, BlockPos pos, boolean isOutsideWall) {
        int height = 3;
        if (isOutsideWall) {
            Random random = new Random();
            int heightToAdd = random.nextInt(6);
            height += heightToAdd;
        } else {
            height = 2;
        }
        for (int i = 0; i < height; i++) {
            world.setBlockState(pos.up(i + 1), Blocks.STONE_BRICKS.getDefaultState(), 3);
        }
    }

    private BlockPos fixYForWall(IWorld world, BlockPos pos) {
        int y = getBaseHeight(world, pos.getX(), pos.getZ());
        return new BlockPos(pos.getX(), y, pos.getZ());
    }
}
