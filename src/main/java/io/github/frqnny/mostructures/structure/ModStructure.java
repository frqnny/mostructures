package io.github.frqnny.mostructures.structure;

import com.mojang.serialization.Codec;
import io.github.frqnny.mostructures.ConfiguredFeatures;
import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.JigsawFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.function.Predicate;

public class ModStructure extends JigsawFeature {
    public ModStructure() {
        this(0);
    }

    public ModStructure(int structureStartY) {
        this(structureStartY, true, true);
    }

    public ModStructure(int structureStartY, boolean modifyBoundingBox, boolean surface) {
        this(StructurePoolFeatureConfig.CODEC, structureStartY, modifyBoundingBox, surface, ModStructure::canGenerate);
    }

    public ModStructure(Codec<StructurePoolFeatureConfig> codec, int structureStartY, boolean modifyBoundingBox, boolean surface, Predicate<StructureGeneratorFactory.Context<StructurePoolFeatureConfig>> predicate) {
        super(codec, structureStartY, modifyBoundingBox, surface, predicate);
    }

    public static boolean canGenerate(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {
        return checkStructureSpacing(context.chunkGenerator(), context.seed(), context.chunkPos(), context.config());
    }

    public static boolean checkStructureSpacing(ChunkGenerator chunkGenerator, long worldSeed, ChunkPos pos, StructurePoolFeatureConfig structureConfig) {
        // Cannot be near other specified structure
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
                    ChunkPos possibleVillagePos = StructureFeature.VILLAGE.getStartChunk(structureConfigVillage, worldSeed, k, m);
                    if (k == possibleVillagePos.x && m == possibleVillagePos.z) {
                        return false;
                    }
                }
                if (configBarnHouse != null) {
                    ChunkPos possibleBarnhousePos = MoStructures.BARN_HOUSE.getStartChunk(configBarnHouse, worldSeed, k, m);
                    if (k == possibleBarnhousePos.x && m == possibleBarnhousePos.z && structureConfig != ConfiguredFeatures.BARN_HOUSE.config) {
                        return false;
                    }
                }
                if (configBigPyramid != null) {
                    ChunkPos possibleBigPyramidPos = MoStructures.BIG_PYRAMID.getStartChunk(configBigPyramid, worldSeed, k, m);
                    if (k == possibleBigPyramidPos.x && m == possibleBigPyramidPos.z && structureConfig != ConfiguredFeatures.BIG_PYRAMID.config) {
                        return false;
                    }
                }
                if (configJunglePyramid != null) {
                    ChunkPos possibleJunglePyramidPos = MoStructures.JUNGLE_PYRAMID.getStartChunk(configJunglePyramid, worldSeed, k, m);
                    if (k == possibleJunglePyramidPos.x && m == possibleJunglePyramidPos.z && structureConfig != ConfiguredFeatures.JUNGLE_PYRAMID.config) {
                        return false;
                    }
                }
                if (configPillagerFactory != null) {
                    ChunkPos possiblePillagerFactoryPos = MoStructures.PILLAGER_FACTORY.getStartChunk(configPillagerFactory, worldSeed, k, m);
                    if (k == possiblePillagerFactoryPos.x && m == possiblePillagerFactoryPos.z && structureConfig != ConfiguredFeatures.PILLAGER_FACTORY.config) {
                        return false;
                    }
                }
                if (configVillagerMarket != null) {
                    ChunkPos possibleVillagerMarketPos = MoStructures.VILLAGER_MARKET.getStartChunk(configVillagerMarket, worldSeed, k, m);
                    if (k == possibleVillagerMarketPos.x && m == possibleVillagerMarketPos.z && structureConfig != ConfiguredFeatures.VILLAGER_MARKET.config) {
                        return false;
                    }
                }
                if (configVillagerTower != null) {
                    ChunkPos possibleVillagerTowerPos = MoStructures.VILLAGER_TOWER.getStartChunk(configVillagerTower, worldSeed, k, m);
                    if (k == possibleVillagerTowerPos.x && m == possibleVillagerTowerPos.z && structureConfig != ConfiguredFeatures.VILLAGER_TOWER.config) {
                        return false;
                    }
                }
                if (configTavern != null) {
                    ChunkPos possibleVillagerTowerPos = MoStructures.TAVERN.getStartChunk(configTavern, worldSeed, k, m);
                    if (k == possibleVillagerTowerPos.x && m == possibleVillagerTowerPos.z && structureConfig != ConfiguredFeatures.TAVERN.config) {
                        return false;
                    }
                }
                if (configKillerBunnyCastle != null) {
                    ChunkPos possibleVillagerTowerPos = MoStructures.KILLER_BUNNY_CASTLE.getStartChunk(configKillerBunnyCastle, worldSeed, k, m);
                    if (k == possibleVillagerTowerPos.x && m == possibleVillagerTowerPos.z && structureConfig != ConfiguredFeatures.KILLER_BUNNY_CASTLE.config) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}