package io.github.frqnny.mostructures.feature;

import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.util.FeatureHelper;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class BoatFeature extends Feature<DefaultFeatureConfig> {
    public static final Identifier BOAT1 = MoStructures.id("boat/boat1");

    public static final Identifier ID = MoStructures.id("boat");

    public BoatFeature() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, DefaultFeatureConfig featureConfig) {
        BlockPos newPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, pos);
        FeatureHelper.placeStructure(BOAT1, newPos.add(0, -3, 0), world, random);
        return true;
    }
}
