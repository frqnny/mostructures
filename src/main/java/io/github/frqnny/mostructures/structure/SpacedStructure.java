package io.github.frqnny.mostructures.structure;

import com.mojang.serialization.Codec;
import io.github.frqnny.mostructures.ConfiguredFeatures;
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
        int BIOME_RANGE = 2;

        for (int curChunkX = chunkX - BIOME_RANGE; curChunkX <= chunkX + BIOME_RANGE; curChunkX++) {
            for (int curChunkZ = chunkZ - BIOME_RANGE; curChunkZ <= chunkZ + BIOME_RANGE; curChunkZ++) {
                if (!biomeSource.getBiomeForNoiseGen(curChunkX << 2, 60, curChunkZ << 2).getGenerationSettings().hasStructureFeature(this)) {
                    return false;
                }
            }
        }

        //cannot be near other specified structure
        StructureConfig structureConfigVillage = chunkGenerator.getStructuresConfig().getForType(StructureFeature.VILLAGE);
        StructureConfig configBarnHouse = chunkGenerator.getStructuresConfig().getForType(ConfiguredFeatures.BARN_HOUSE.feature);
        StructureConfig configBigPyramid = chunkGenerator.getStructuresConfig().getForType(ConfiguredFeatures.BIG_PYRAMID.feature);
        StructureConfig configJunglePyramid = chunkGenerator.getStructuresConfig().getForType(ConfiguredFeatures.JUNGLE_PYRAMID.feature);
        StructureConfig configPillagerFactory = chunkGenerator.getStructuresConfig().getForType(ConfiguredFeatures.PILLAGER_FACTORY.feature);
        StructureConfig configVillagerMarket = chunkGenerator.getStructuresConfig().getForType(ConfiguredFeatures.VILLAGER_MARKET.feature);
        StructureConfig configVillagerTower = chunkGenerator.getStructuresConfig().getForType(ConfiguredFeatures.VILLAGER_TOWER.feature);
        for (int k = chunkX - 2; k <= chunkX + 2; ++k) {
            for (int m = chunkZ - 2; m <= chunkZ + 2; ++m) {
                if (structureConfigVillage != null) {
                    ChunkPos possibleVillagePos = StructureFeature.VILLAGE.getStartChunk(structureConfigVillage, worldSeed, chunkRandom, k, m);
                    if (k == possibleVillagePos.x && m == possibleVillagePos.z) {
                        return false;
                    }
                }
                if (configBarnHouse != null) {
                    ChunkPos possibleBarnhousePos = ConfiguredFeatures.BARN_HOUSE.feature.getStartChunk(configBarnHouse, worldSeed, chunkRandom, k, m);
                    if (k == possibleBarnhousePos.x && m == possibleBarnhousePos.z && !(this instanceof BarnHouseStructure)) {
                        return false;
                    }
                }
                if (configBigPyramid != null) {
                    ChunkPos possibleBigPyramidPos = ConfiguredFeatures.BIG_PYRAMID.feature.getStartChunk(configBigPyramid, worldSeed, chunkRandom, k, m);
                    if (k == possibleBigPyramidPos.x && m == possibleBigPyramidPos.z && !(this instanceof BigPyramidStructure)) {
                        return false;
                    }
                }
                if (configJunglePyramid != null) {
                    ChunkPos possibleJunglePyramidPos = ConfiguredFeatures.JUNGLE_PYRAMID.feature.getStartChunk(configJunglePyramid, worldSeed, chunkRandom, k, m);
                    if (k == possibleJunglePyramidPos.x && m == possibleJunglePyramidPos.z && !(this instanceof JunglePyramidStructure)) {
                        return false;
                    }
                }
                if (configPillagerFactory != null) {
                    ChunkPos possiblePillagerFactoryPos = ConfiguredFeatures.PILLAGER_FACTORY.feature.getStartChunk(configPillagerFactory, worldSeed, chunkRandom, k, m);
                    if (k == possiblePillagerFactoryPos.x && m == possiblePillagerFactoryPos.z && !(this instanceof PillagerFactoryStructure)) {
                        return false;
                    }
                }
                if (configVillagerMarket != null) {
                    ChunkPos possibleVillagerMarketPos = ConfiguredFeatures.VILLAGER_MARKET.feature.getStartChunk(configVillagerMarket, worldSeed, chunkRandom, k, m);
                    if (k == possibleVillagerMarketPos.x && m == possibleVillagerMarketPos.z && !(this instanceof VillagerMarketStructure)) {
                        return false;
                    }
                }
                if (configVillagerTower != null) {
                    ChunkPos possibleVillagerTowerPos = ConfiguredFeatures.VILLAGER_TOWER.feature.getStartChunk(configVillagerTower, worldSeed, chunkRandom, k, m);
                    if (k == possibleVillagerTowerPos.x && m == possibleVillagerTowerPos.z && !(this instanceof VillagerTowerStructure)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

}
