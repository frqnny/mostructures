package io.github.frqnny.mostructures.structure;

import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.world.Heightmap;

public class MoaiStructure extends ModStructure {
    public MoaiStructure(int structureStartY) {
        super(ConfigMS.CODEC, structureStartY, true, true, MoaiStructure::canGenerate);
    }

    public static boolean canGenerate(StructureGeneratorFactory.Context<ConfigMS> context) {
        int y = context.chunkGenerator().getHeight(context.chunkPos().getStartX(), context.chunkPos().getStartZ(), Heightmap.Type.WORLD_SURFACE_WG, context.world());
        return y > 63 && checks(context.chunkGenerator(), context.seed(), context.chunkPos(), context.config(), context.world());
    }
}