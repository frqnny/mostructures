package io.github.franiscoder.mostructures.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class BoulderFeature extends Feature<DefaultFeatureConfig> {
    public BoulderFeature() {
        super(DefaultFeatureConfig.CODEC);
    }

    public static void generateSphere(ServerWorldAccess world, BlockPos pos, int range) {
        BlockPos.Mutable pos1 = new BlockPos.Mutable().set(pos);
        int radius = range / 2;

        for (int x = -range; x <= range; x++) {
            for (int z = -range; z <= range; z++) {
                for (int y = -range; y <= range; y++) {
                    if (MathHelper.square(z) + MathHelper.square(x) + MathHelper.square(y) <= MathHelper.square(radius)) {
                        world.setBlockState(pos1.add(x, y, z), getRandomBlock(), 3);
                    }
                }
            }
        }
    }

    public static BlockState getRandomBlock() {
        Random random = new Random();
        switch (random.nextInt(4)) {
            case 0:
                return Blocks.STONE.getDefaultState();
            case 1:
                return Blocks.MOSSY_COBBLESTONE.getDefaultState();
            case 2:
                return Blocks.COBBLESTONE.getDefaultState();
            case 3:
                return Blocks.GRAVEL.getDefaultState();
            default:
                return Blocks.COARSE_DIRT.getDefaultState();
        }
    }

    @Override
    public boolean generate(ServerWorldAccess world, ChunkGenerator generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        if (world.getBiome(pos).getCategory() == Biome.Category.NETHER && world.getBiome(pos).getCategory() == Biome.Category.THEEND) {
            return false;
        }
        Random randomFixed = world.getRandom();
        pos = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, pos);

        generateSphere(world, pos, 4 + randomFixed.nextInt(5));
        if (new Random().nextBoolean()) {
            generateSphere(world, pos.south().down(), 4 + randomFixed.nextInt(5));
        } else {
            generateSphere(world, pos.north().up(), 4 + randomFixed.nextInt(5));
        }
        if (new Random().nextBoolean()) {
            generateSphere(world, pos.east().up(), 4 + randomFixed.nextInt(5));
        } else {
            generateSphere(world, pos.west().down(), 4 + randomFixed.nextInt(5));
        }
        return true;
    }
}
