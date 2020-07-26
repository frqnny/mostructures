package io.github.franiscoder.mostructures.structure;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public abstract class SpacedStructure<C extends FeatureConfig> extends StructureFeature<C> {
    public SpacedStructure(Codec<C> codec) {
        super(codec);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long worldSeed, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, C featureConfig) {
        StructureConfig structureConfig = chunkGenerator.getConfig().getForType(StructureFeature.VILLAGE);
        if (structureConfig != null) {
            for (int k = chunkX - 10; k <= chunkX + 10; ++k) {
                for (int m = chunkZ - 10; m <= chunkZ + 10; ++m) {
                    ChunkPos possibleVillagePos = StructureFeature.VILLAGE.getStartChunk(structureConfig, worldSeed, chunkRandom, k, m);
                    if (k == possibleVillagePos.x && m == possibleVillagePos.z) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
