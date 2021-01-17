package io.github.frqnny.mostructures.structure;

import com.google.common.collect.ImmutableList;
import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.generator.IceTowerGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.List;

public class IceTowerStructure extends SpacedStructure<StructurePoolFeatureConfig> {
    public static final Identifier ID = MoStructures.id("ice_tower");
    private static final List<SpawnSettings.SpawnEntry> MONSTER_SPAWNS = ImmutableList.of(new SpawnSettings.SpawnEntry(EntityType.STRAY, 1, 2, 2), new SpawnSettings.SpawnEntry(EntityType.CREEPER, 1, 1, 1));

    public IceTowerStructure() {
        super(StructurePoolFeatureConfig.CODEC);
    }

    @Override
    public List<SpawnSettings.SpawnEntry> getMonsterSpawns() {
        return MONSTER_SPAWNS;
    }

    @Override
    public StructureStartFactory<StructurePoolFeatureConfig> getStructureStartFactory() {
        return IceTowerStructure.Start::new;
    }

    public static class Start extends MarginedStructureStart<StructurePoolFeatureConfig> {
        public Start(StructureFeature<StructurePoolFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references, long seed) {
            super(feature, chunkX, chunkZ, box, references, seed);
        }

        @Override
        public void init(DynamicRegistryManager registryManager, ChunkGenerator chunkGenerator, StructureManager manager, int x, int z, Biome biome, StructurePoolFeatureConfig config) {
            IceTowerGenerator.addPieces(registryManager, config, chunkGenerator, manager, new BlockPos(x << 4, 0, z << 4), children, random);
            this.setBoundingBoxFromChildren();
        }
    }
}
