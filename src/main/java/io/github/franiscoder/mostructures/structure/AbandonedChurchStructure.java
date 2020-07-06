package io.github.franiscoder.mostructures.structure;

import io.github.franiscoder.mostructures.generator.AbandonedChurchGenerator;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.VillageStructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class AbandonedChurchStructure extends StructureFeature<StructurePoolFeatureConfig> {
    public AbandonedChurchStructure() {
        super(StructurePoolFeatureConfig.CODEC);
    }

    @Override
    public StructureStartFactory<StructurePoolFeatureConfig> getStructureStartFactory() {
        return AbandonedChurchStructure.Start::new;
    }

    public static class Start extends VillageStructureStart<StructurePoolFeatureConfig> {
        public Start(StructureFeature<StructurePoolFeatureConfig> structureFeature, int i, int j, BlockBox blockBox, int k, long l) {
            super(structureFeature, i, j, blockBox, k, l);
        }

        @Override
        public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int x, int z, Biome biome, StructurePoolFeatureConfig config) {
            AbandonedChurchGenerator.addPieces(chunkGenerator, structureManager, new BlockPos(x << 4, 0, z << 4), children, random, config);
            this.setBoundingBoxFromChildren();
        }
    }
}
