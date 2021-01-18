package io.github.frqnny.mostructures.feature;

import io.github.frqnny.mostructures.ConfiguredFeatures;
import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.util.FeatureHelper;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SmallBeachFeatures extends Feature<DefaultFeatureConfig> {
    public static final Identifier VILLAGER_MOAI = MoStructures.id("beach/villager_moai");

    public static final Identifier ID = MoStructures.id("beach_features");


    public SmallBeachFeatures() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, DefaultFeatureConfig featureConfig) {
        List<Chunk> chunksToScan = new ArrayList<>(9);
        chunksToScan.add(world.getChunk(pos));
        chunksToScan.add(world.getChunk(pos.add(16, 0, 16)));
        chunksToScan.add(world.getChunk(pos.add(-16, 0, -16)));
        chunksToScan.add(world.getChunk(pos.add(0, 0, 16)));
        chunksToScan.add(world.getChunk(pos.add(16, 0, 0)));
        chunksToScan.add(world.getChunk(pos.add(-16, 0, 0)));
        chunksToScan.add(world.getChunk(pos.add(0, 0, -16)));
        chunksToScan.add(world.getChunk(pos.add(16, 0, -16)));
        chunksToScan.add(world.getChunk(pos.add(-16, 0, 16)));
        for (Chunk chunk : chunksToScan) {
            if (!chunk.getStructureReferences(ConfiguredFeatures.THE_CASTLE_IN_THE_SKY.feature).isEmpty()) {
                return false;
            }
        }

        BlockPos[] posToCheck = {pos.down().east(), pos.down().west(), pos.down().north(), pos.down().south(), pos};

        for (BlockPos waterPos : posToCheck) {
            if (!world.getBlockState(waterPos).getFluidState().isEmpty()) {
                return false;
            }
        }

        FeatureHelper.placeStructure(VILLAGER_MOAI, pos.add(0, -3, 0), world, random);
        return true;
    }
}
