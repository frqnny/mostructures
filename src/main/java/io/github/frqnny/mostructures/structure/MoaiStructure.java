package io.github.frqnny.mostructures.structure;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class MoaiStructure extends ModStructure {

    public MoaiStructure(int y) {
        super(y);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long worldSeed, ChunkRandom random, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, StructurePoolFeatureConfig config, HeightLimitView world) {
        BlockPos pos = chunkPos.getStartPos();

        BlockPos[] posToCheck = {pos.down().east(), pos.down().west(), pos.down().north(), pos.down().south(), pos};

        for (BlockPos waterPos : posToCheck) {
            if (!chunkGenerator.getBlockSource().get(waterPos).getFluidState().isEmpty()) {
                return false;
            }
        }
        return super.shouldStartAt(chunkGenerator, biomeSource, worldSeed, random, chunkPos1, biome, chunkPos, config, world);
    }
}

