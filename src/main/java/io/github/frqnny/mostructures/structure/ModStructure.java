package io.github.frqnny.mostructures.structure;

import io.github.frqnny.mostructures.ConfiguredFeatures;
import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.util.StructureHelper;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class ModStructure extends StructureFeature<StructurePoolFeatureConfig> {
    private final int structureStartY;

    public ModStructure() {
        this(0);
    }

    public ModStructure(int structureStartY) {
        super(StructurePoolFeatureConfig.CODEC);
        this.structureStartY = structureStartY;
    }


    @Override
    public StructureStartFactory<StructurePoolFeatureConfig> getStructureStartFactory() {
        return ModStructure.Start::new;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long worldSeed, ChunkRandom random, ChunkPos pos, Biome biome, ChunkPos chunkPos, StructurePoolFeatureConfig config, HeightLimitView world) {

        //cannot be near other specified structure
        StructureConfig structureConfigVillage = chunkGenerator.getStructuresConfig().getForType(StructureFeature.VILLAGE);
        StructureConfig configBarnHouse = chunkGenerator.getStructuresConfig().getForType(ConfiguredFeatures.BARN_HOUSE.feature);
        StructureConfig configBigPyramid = chunkGenerator.getStructuresConfig().getForType(ConfiguredFeatures.BIG_PYRAMID.feature);
        StructureConfig configJunglePyramid = chunkGenerator.getStructuresConfig().getForType(ConfiguredFeatures.JUNGLE_PYRAMID.feature);
        StructureConfig configPillagerFactory = chunkGenerator.getStructuresConfig().getForType(ConfiguredFeatures.PILLAGER_FACTORY.feature);
        StructureConfig configVillagerMarket = chunkGenerator.getStructuresConfig().getForType(ConfiguredFeatures.VILLAGER_MARKET.feature);
        StructureConfig configVillagerTower = chunkGenerator.getStructuresConfig().getForType(ConfiguredFeatures.VILLAGER_TOWER.feature);


        for (int k = pos.x - 6; k <= pos.x + 6; ++k) {
            for (int m = pos.z - 6; m <= pos.z + 6; ++m) {
                if (structureConfigVillage != null) {
                    ChunkPos possibleVillagePos = StructureFeature.VILLAGE.getStartChunk(structureConfigVillage, worldSeed, random, k, m);
                    if (k == possibleVillagePos.x && m == possibleVillagePos.z) {
                        return false;
                    }
                }
                if (configBarnHouse != null) {
                    ChunkPos possibleBarnhousePos = ConfiguredFeatures.BARN_HOUSE.feature.getStartChunk(configBarnHouse, worldSeed, random, k, m);
                    if (k == possibleBarnhousePos.x && m == possibleBarnhousePos.z && this != MoStructures.BARN_HOUSE) {
                        return false;
                    }
                }
                if (configBigPyramid != null) {
                    ChunkPos possibleBigPyramidPos = ConfiguredFeatures.BIG_PYRAMID.feature.getStartChunk(configBigPyramid, worldSeed, random, k, m);
                    if (k == possibleBigPyramidPos.x && m == possibleBigPyramidPos.z && this != MoStructures.BIG_PYRAMID) {
                        return false;
                    }
                }
                if (configJunglePyramid != null) {
                    ChunkPos possibleJunglePyramidPos = ConfiguredFeatures.JUNGLE_PYRAMID.feature.getStartChunk(configJunglePyramid, worldSeed, random, k, m);
                    if (k == possibleJunglePyramidPos.x && m == possibleJunglePyramidPos.z && this != MoStructures.JUNGLE_PYRAMID) {
                        return false;
                    }
                }
                if (configPillagerFactory != null) {
                    ChunkPos possiblePillagerFactoryPos = ConfiguredFeatures.PILLAGER_FACTORY.feature.getStartChunk(configPillagerFactory, worldSeed, random, k, m);
                    if (k == possiblePillagerFactoryPos.x && m == possiblePillagerFactoryPos.z && this != MoStructures.PILLAGER_FACTORY) {
                        return false;
                    }
                }
                if (configVillagerMarket != null) {
                    ChunkPos possibleVillagerMarketPos = ConfiguredFeatures.VILLAGER_MARKET.feature.getStartChunk(configVillagerMarket, worldSeed, random, k, m);
                    if (k == possibleVillagerMarketPos.x && m == possibleVillagerMarketPos.z && this != MoStructures.VILLAGER_MARKET) {
                        return false;
                    }
                }
                if (configVillagerTower != null) {
                    ChunkPos possibleVillagerTowerPos = ConfiguredFeatures.VILLAGER_TOWER.feature.getStartChunk(configVillagerTower, worldSeed, random, k, m);
                    if (k == possibleVillagerTowerPos.x && m == possibleVillagerTowerPos.z && this != MoStructures.VILLAGER_TOWER) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static class Start extends MarginedStructureStart<StructurePoolFeatureConfig> {
        public Start(StructureFeature<StructurePoolFeatureConfig> s, ChunkPos c, int i, long l) {
            super(s, c, i, l);
        }

        @Override
        public void init(DynamicRegistryManager registryManager, ChunkGenerator chunkGenerator, StructureManager manager, ChunkPos pos, Biome biome, StructurePoolFeatureConfig config, HeightLimitView world) {
            StructureHelper.initPools();
            StructurePoolBasedGenerator.method_30419(registryManager, config, PoolStructurePiece::new, chunkGenerator, manager, new BlockPos(pos.x << 4, ((ModStructure) this.getFeature()).structureStartY, pos.z << 4), this, this.random, true, true, world);
            this.setBoundingBoxFromChildren();
        }
    }

}
