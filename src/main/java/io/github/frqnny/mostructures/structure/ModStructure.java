package io.github.frqnny.mostructures.structure;

import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.util.StrucUtils;
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
    final boolean modifyBoundingBox;
    final boolean surface;
    private final int structureStartY;

    public ModStructure() {
        this(0);
    }

    public ModStructure(int structureStartY) {
        this(structureStartY, true, true);
    }

    public ModStructure(int structureStartY, boolean modifyBoundingBox, boolean surface) {
        super(StructurePoolFeatureConfig.CODEC);
        this.structureStartY = structureStartY;
        this.modifyBoundingBox = modifyBoundingBox;
        this.surface = surface;
    }

    @Override
    public StructureStartFactory<StructurePoolFeatureConfig> getStructureStartFactory() {
        return ModStructure.Start::new;
    }

    @SuppressWarnings("ObjectAllocationInLoop")
    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long worldSeed, ChunkRandom random, ChunkPos pos, Biome biome, ChunkPos chunkPos, StructurePoolFeatureConfig config, HeightLimitView world) {
        //cannot be near other specified structure
        StructureConfig structureConfigVillage = chunkGenerator.getStructuresConfig().getForType(StructureFeature.VILLAGE);
        StructureConfig configBarnHouse = chunkGenerator.getStructuresConfig().getForType(MoStructures.BARN_HOUSE);
        StructureConfig configBigPyramid = chunkGenerator.getStructuresConfig().getForType(MoStructures.BIG_PYRAMID);
        StructureConfig configJunglePyramid = chunkGenerator.getStructuresConfig().getForType(MoStructures.JUNGLE_PYRAMID);
        StructureConfig configPillagerFactory = chunkGenerator.getStructuresConfig().getForType(MoStructures.PILLAGER_FACTORY);
        StructureConfig configVillagerMarket = chunkGenerator.getStructuresConfig().getForType(MoStructures.VILLAGER_MARKET);
        StructureConfig configVillagerTower = chunkGenerator.getStructuresConfig().getForType(MoStructures.VILLAGER_TOWER);
        StructureConfig configKillerBunnyCastle = chunkGenerator.getStructuresConfig().getForType(MoStructures.KILLER_BUNNY_CASTLE);
        StructureConfig configTavern = chunkGenerator.getStructuresConfig().getForType(MoStructures.TAVERN);


        for (int k = pos.x - 6; k <= pos.x + 6; ++k) {
            for (int m = pos.z - 6; m <= pos.z + 6; ++m) {
                if (structureConfigVillage != null) {
                    ChunkPos possibleVillagePos = StructureFeature.VILLAGE.getStartChunk(structureConfigVillage, worldSeed, random, k, m);
                    if (k == possibleVillagePos.x && m == possibleVillagePos.z) {
                        return false;
                    }
                }
                if (configBarnHouse != null) {
                    ChunkPos possibleBarnhousePos = MoStructures.BARN_HOUSE.getStartChunk(configBarnHouse, worldSeed, random, k, m);
                    if (k == possibleBarnhousePos.x && m == possibleBarnhousePos.z && this != MoStructures.BARN_HOUSE) {
                        return false;
                    }
                }
                if (configBigPyramid != null) {
                    ChunkPos possibleBigPyramidPos = MoStructures.BIG_PYRAMID.getStartChunk(configBigPyramid, worldSeed, random, k, m);
                    if (k == possibleBigPyramidPos.x && m == possibleBigPyramidPos.z && this != MoStructures.BIG_PYRAMID) {
                        return false;
                    }
                }
                if (configJunglePyramid != null) {
                    ChunkPos possibleJunglePyramidPos = MoStructures.JUNGLE_PYRAMID.getStartChunk(configJunglePyramid, worldSeed, random, k, m);
                    if (k == possibleJunglePyramidPos.x && m == possibleJunglePyramidPos.z && this != MoStructures.JUNGLE_PYRAMID) {
                        return false;
                    }
                }
                if (configPillagerFactory != null) {
                    ChunkPos possiblePillagerFactoryPos = MoStructures.PILLAGER_FACTORY.getStartChunk(configPillagerFactory, worldSeed, random, k, m);
                    if (k == possiblePillagerFactoryPos.x && m == possiblePillagerFactoryPos.z && this != MoStructures.PILLAGER_FACTORY) {
                        return false;
                    }
                }
                if (configVillagerMarket != null) {
                    ChunkPos possibleVillagerMarketPos = MoStructures.VILLAGER_MARKET.getStartChunk(configVillagerMarket, worldSeed, random, k, m);
                    if (k == possibleVillagerMarketPos.x && m == possibleVillagerMarketPos.z && this != MoStructures.VILLAGER_MARKET) {
                        return false;
                    }
                }
                if (configVillagerTower != null) {
                    ChunkPos possibleVillagerTowerPos = MoStructures.VILLAGER_TOWER.getStartChunk(configVillagerTower, worldSeed, random, k, m);
                    if (k == possibleVillagerTowerPos.x && m == possibleVillagerTowerPos.z && this != MoStructures.VILLAGER_TOWER) {
                        return false;
                    }
                }
                if (configTavern != null) {
                    ChunkPos possibleVillagerTowerPos = MoStructures.TAVERN.getStartChunk(configTavern, worldSeed, random, k, m);
                    if (k == possibleVillagerTowerPos.x && m == possibleVillagerTowerPos.z && this != MoStructures.TAVERN) {
                        return false;
                    }
                }
                if (configKillerBunnyCastle != null) {
                    ChunkPos possibleVillagerTowerPos = MoStructures.KILLER_BUNNY_CASTLE.getStartChunk(configKillerBunnyCastle, worldSeed, random, k, m);
                    if (k == possibleVillagerTowerPos.x && m == possibleVillagerTowerPos.z && this != MoStructures.KILLER_BUNNY_CASTLE) {
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
            ModStructure structure = (ModStructure) this.getFeature();
            StrucUtils.initPools();
            StructurePoolBasedGenerator.generate(registryManager, config, PoolStructurePiece::new, chunkGenerator, manager, new BlockPos(pos.x << 4, structure.structureStartY, pos.z << 4), this, this.random, structure.modifyBoundingBox, structure.surface, world);
            this.setBoundingBoxFromChildren();
        }
    }
}
