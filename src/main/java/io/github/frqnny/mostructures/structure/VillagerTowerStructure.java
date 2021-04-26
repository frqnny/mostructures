package io.github.frqnny.mostructures.structure;

import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.generator.VillagerTowerGenerator;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class VillagerTowerStructure extends SpacedStructure<StructurePoolFeatureConfig> {
    public static final Identifier ID = MoStructures.id("villager_tower");

    public VillagerTowerStructure() {
        super(StructurePoolFeatureConfig.CODEC);
    }


    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long worldSeed, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, StructurePoolFeatureConfig featureConfig) {
        //desert is ugly, we make it rarer :)
        if (biome.getCategory() == Biome.Category.DESERT) {
            return MoStructures.random.nextBoolean();
        }
        return super.shouldStartAt(chunkGenerator, biomeSource, worldSeed, chunkRandom, chunkX, chunkZ, biome, chunkPos, featureConfig);
    }

    @Override
    public StructureStartFactory<StructurePoolFeatureConfig> getStructureStartFactory() {
        return VillagerTowerStructure.Start::new;
    }

    public static class Start extends MarginedStructureStart<StructurePoolFeatureConfig> {
        public Start(StructureFeature<StructurePoolFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references, long seed) {
            super(feature, chunkX, chunkZ, box, references, seed);
        }

        @Override
        public void init(DynamicRegistryManager registryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int x, int z, Biome biome, StructurePoolFeatureConfig config) {
            VillagerTowerGenerator.addPieces(registryManager, config, chunkGenerator, structureManager, new BlockPos(x << 4, 0, z << 4), children, random);
            this.setBoundingBoxFromChildren();
        }
    }
}