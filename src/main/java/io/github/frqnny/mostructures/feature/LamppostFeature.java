package io.github.frqnny.mostructures.feature;

import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.util.FeatureHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;


public class LamppostFeature extends Feature<DefaultFeatureConfig> {
    public static final Identifier LAMPPOST = MoStructures.id("miscellaneous/lamppost");
    public static final Identifier NETHER_LAMPPOST = MoStructures.id("miscellaneous/netherlamppost");

    public static final Identifier ID = MoStructures.id("lamppost");

    public LamppostFeature() {
        super(DefaultFeatureConfig.CODEC);
    }

    public static BlockPos getCorrectNetherHeight(BlockPos pos, ServerWorldAccess world) {

        //It'll check for lava at lava ocean level (32) now
        BlockPos posToWorkOn = new BlockPos(pos.getX(), 30, pos.getZ());
        BlockState block = world.getBlockState(posToWorkOn);
        BlockState state = Blocks.AIR.getDefaultState();
        while (block != state) {
            posToWorkOn = posToWorkOn.up();
            block = world.getBlockState(posToWorkOn);

            if (!world.getBlockState(posToWorkOn).getFluidState().isEmpty()) return null;
        }

        return posToWorkOn;
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();
        Random random = context.getRandom();

        Identifier lamppost = world.getBiome(pos).getCategory() == Biome.Category.NETHER ? NETHER_LAMPPOST : LAMPPOST;
        boolean inWater = false;
        BlockPos newPos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos);

        if (world.getBlockState(newPos.down()) == Blocks.WATER.getDefaultState()) {
            inWater = true;
        }

        Biome.Category category = world.getBiome(pos).getCategory();
        if (category == Biome.Category.FOREST && !inWater && random.nextBoolean()) {
            FeatureHelper.placeStructure(lamppost, newPos, world, random);
            return true;

        } else if (category == Biome.Category.NETHER) {
            BlockPos correctPos = getCorrectNetherHeight(pos, world);
            if (correctPos == null) {
                return false;

            } else {
                FeatureHelper.placeStructure(lamppost, correctPos, world, random);
                return true;

            }
        }
        return false;
    }
}
