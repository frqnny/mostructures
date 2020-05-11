package io.github.franiscoder.mostructures.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class BoulderFeature extends Feature<DefaultFeatureConfig> {
    public BoulderFeature() {
        super(DefaultFeatureConfig::deserialize);
    }


    public void generateSphere(IWorld world, BlockPos pos, int range) {
        int radius = range / 2;

        for (int x = -range; x <= range; x++) {
            for (int z = -range; z <= range; z++) {
                for (int y = -range; y <= range; y++) {

                    if ((x * x) + (y * y) + (z * z) <= (radius * radius)) {
                        world.setBlockState(pos.add(x, y, z), getRandomBlock(), 2);
                    }
                }
            }
        }
    }

    public BlockState getRandomBlock() {
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
    public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        if (world.getDimension().getType() != DimensionType.OVERWORLD) {
            return false;
        }

        pos = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, pos);

        generateSphere(world, pos, 4 + random.nextInt(4));
        if (new Random().nextBoolean()) {
            generateSphere(world, pos.south().down(), 4 + random.nextInt(4));
        } else {
            generateSphere(world, pos.north().up(), 4 + random.nextInt(4));
        }
        if (new Random().nextBoolean()) {
            generateSphere(world, pos.east().up(), 4 + random.nextInt(4));
        } else {
            generateSphere(world, pos.west().down(), 4 + random.nextInt(4));
        }
        return true;
    }
}
