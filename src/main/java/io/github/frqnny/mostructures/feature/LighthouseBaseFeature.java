package io.github.frqnny.mostructures.feature;

import com.mojang.serialization.Codec;
import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class LighthouseBaseFeature extends Feature<DefaultFeatureConfig> {
    public static final Identifier ID = MoStructures.id("lighthouse_base");
    private static final int yToNotGoOver = 64;

    public LighthouseBaseFeature() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();
        generateSphere(world, pos.add(0, -5, 0), 10);
        generateSphere(world, pos.add(-1, -5, -10), 6);
        generateSphere(world, pos.add(-10, -6, -5), 8);
        generateSphere(world, pos.add(-7, -7, -3), 8);
        generateSphere(world, pos.add(-3, -6, -1), 6);
        generateSphere(world, pos.add(1, -5, -10), 8);
        generateSphere(world, pos.add(10, -5, -5), 8);
        generateSphere(world, pos.add(7, -6, -3), 6);
        generateSphere(world, pos.add(3, -7, -1), 8);
        generateSphere(world, pos.add(-1, -6, 10), 8);
        generateSphere(world, pos.add(-10, -7, 5), 8);
        generateSphere(world, pos.add(-7, -5, 3), 6);
        generateSphere(world, pos.add(-3, -5, 1), 8);
        generateSphere(world, pos.add(1, -7, 10), 8);
        generateSphere(world, pos.add(10, -6, 5), 6);
        generateSphere(world, pos.add(7, -6, 3), 8);
        generateSphere(world, pos.add(3, -6, 1), 8);




        return true;
    }


    public static void generateSphere(StructureWorldAccess world, BlockPos pos, int radius) {
        BlockPos.Mutable mutable = pos.mutableCopy();
        int range = radius << 1; // multiplies it by two

        int originalX = mutable.getX();
        int originalY = mutable.getY();
        int originalZ = mutable.getZ();
        Random random = new Random();

        for (int x = -range; x <= range; x++) {
            for (int z = -range; z <= range; z++) {
                for (int y = -range; y <= range; y++) {
                    if (MathHelper.square(z) + MathHelper.square(x) + MathHelper.square(y) <= MathHelper.square(radius)) {
                        int yToPlace = originalY + y;
                        if (yToPlace < yToNotGoOver) {
                            mutable.set(originalX + x, originalY + y, originalZ + z);
                            BlockState state = Blocks.BEDROCK.getDefaultState();
                            if (world.method_37368(mutable)) {
                                world.setBlockState(mutable, state, 3);

                            }
                        }
                    }
                }
            }
        }
    }

    public static BlockState getRandomBlock(Random random) {
        return switch (random.nextInt(4)) {
            case 0 -> Blocks.STONE.getDefaultState();
            case 1 -> Blocks.ANDESITE.getDefaultState();
            case 2 -> Blocks.COBBLESTONE.getDefaultState();
            case 3 -> Blocks.GRAVEL.getDefaultState();
            case 4 -> Blocks.TUFF.getDefaultState();
            default -> Blocks.COARSE_DIRT.getDefaultState();
        };
    }
}
