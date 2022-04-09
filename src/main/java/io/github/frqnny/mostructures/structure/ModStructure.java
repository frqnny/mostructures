package io.github.frqnny.mostructures.structure;

import com.mojang.serialization.Codec;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.JigsawFeature;
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
        //TODO rewrite this section
        return true;
    }
}