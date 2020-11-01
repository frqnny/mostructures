package io.github.franiscoder.mostructures.feature;

import io.github.franiscoder.mostructures.MoStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class VolcanicVentFeature extends Feature<DefaultFeatureConfig> {
    public static final Identifier ID = MoStructures.id("volcanic_vent");

    public VolcanicVentFeature() {
        super(DefaultFeatureConfig.CODEC);
    }

    private static int getBaseHeight(ServerWorldAccess world, int x, int y) {
        return world.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, x, y) - 1;
    }

    public static BlockState getRandomBlock() {
        Random random = new Random();
        switch (random.nextInt(5)) {
            case 0:
                return Blocks.STONE.getDefaultState();
            case 1:
                return Blocks.MOSSY_COBBLESTONE.getDefaultState();
            case 2:
                return Blocks.COBBLESTONE.getDefaultState();
            case 3:
                return Blocks.GRAVEL.getDefaultState();
            case 4:
                return Blocks.ANDESITE.getDefaultState();
            default:
                return Blocks.COARSE_DIRT.getDefaultState();
        }
    }

    private static void placeBottom(ServerWorldAccess world, BlockPos pos, float percentage) {
        int max = ((int) (10 * percentage)) + new Random().nextInt(3);
        for (int i = 0; i < max; i++) {
            world.setBlockState(pos.add(0, i, 0), getRandomBlock(), 3);
        }

    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, DefaultFeatureConfig featureConfig) {
        boolean result = getBaseHeight(world, pos.getX(), pos.getZ()) < 50 && world.toServerWorld().getRegistryKey().equals(World.OVERWORLD);

        if (result) {
            BlockPos actualPos = new BlockPos(pos.getX(), getBaseHeight(world, pos.getX(), pos.getZ()), pos.getZ());

            int x = pos.getX();
            int z = pos.getZ();
            float[] percentageToRadius = new float[]{1.0F, .9F, 0.75F, 0.7F, 0.59F, 0.50F, .45F, 0.40F, 0.3F, 0.3F, 0.3F, 0.25F, 0.22F, 0.2F};
            int length = percentageToRadius.length;
            int l = 16;
            int m = random.nextInt(Math.max(1, 8 - l / 2));
            BlockPos.Mutable mutable = BlockPos.ORIGIN.mutableCopy();

            for (int o = x - length; o <= x + length; ++o) {
                for (int p = z - length; p <= z + length; ++p) {
                    int q = Math.abs(o - x) + Math.abs(p - z);
                    int r = Math.max(0, q + m);
                    if (r < length) {
                        float f = percentageToRadius[r];
                        if (random.nextDouble() < (double) f) {
                            int t = getBaseHeight(world, o, p);
                            mutable.set(o, t, p);
                            placeBottom(world, mutable, f);
                        }
                    }
                }
            }

            for (int d = 0; d < 12; d++) {
                world.setBlockState(actualPos.add(0, d, 0), Blocks.WATER.getDefaultState(), 3);
            }
            for (int f = 0; f < 6; f++) {
                world.setBlockState(actualPos.add(0, f, 0), Blocks.MAGMA_BLOCK.getDefaultState(), 3);
            }
        }
        return result;
    }
}
