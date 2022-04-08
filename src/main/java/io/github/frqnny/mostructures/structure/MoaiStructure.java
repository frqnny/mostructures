package io.github.frqnny.mostructures.structure;

import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class MoaiStructure extends ModStructure {
    public MoaiStructure(int structureStartY) {
        super(StructurePoolFeatureConfig.CODEC, structureStartY, true, true, MoaiStructure::canGenerate);
    }

    public static boolean canGenerate(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {
        int y = context.chunkGenerator().getHeight(context.chunkPos().getStartX(), context.chunkPos().getStartZ(), Heightmap.Type.WORLD_SURFACE_WG, context.world());
        return y > 63 && checkStructureSpacing(context.chunkGenerator(), context.seed(), context.chunkPos(), context.config());
    }
}