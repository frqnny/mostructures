package io.github.frqnny.mostructures.structure;

import com.mojang.serialization.Codec;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Optional;
import java.util.function.Predicate;

public class ModStructure extends Structure<ConfigMS> {
    public ModStructure() {
        this(0);
    }

    public ModStructure(int structureStartY) {
        this(structureStartY, true, true);
    }

    public ModStructure(int structureStartY, boolean modifyBoundingBox, boolean surface) {
        this(ConfigMS.CODEC, structureStartY, modifyBoundingBox, surface, ModStructure::canGenerate);
    }

    public ModStructure(Codec<ConfigMS> codec, int structureStartY, boolean modifyBoundingBox, boolean surface, Predicate<StructureGeneratorFactory.Context<ConfigMS>> predicate) {
        super(codec, (context) -> {
            if (!predicate.test(context)) {
                return Optional.empty();
            } else {
                BlockPos blockPos = new BlockPos(context.chunkPos().getStartX(), structureStartY, context.chunkPos().getStartZ());
                return StructurePoolBasedGenerator.generate(context, PoolStructurePiece::new, blockPos, modifyBoundingBox, surface);
            }
        });
    }

    public static boolean canGenerate(StructureGeneratorFactory.Context<ConfigMS> context) {
        return checks(context.chunkGenerator(), context.seed(), context.chunkPos(), context.config(), context.world()) ;
    }

    public static boolean checks(ChunkGenerator chunkGenerator, long worldSeed, ChunkPos pos, ConfigMS config, HeightLimitView world) {
        for (RegistryKey<StructureSet> key : config.structureSetToAvoid) {
            if (chunkGenerator.method_41053(key, worldSeed, pos.x, pos.z, 3)) {
                return false;
            }
        }

        int heightRange = config.heightRange;
        if (heightRange != -1) {
            int maxTerrainHeight = Integer.MIN_VALUE;
            int minTerrainHeight = Integer.MAX_VALUE;

            for (int curChunkX = pos.x - 2; curChunkX <= pos.x + 2; curChunkX++) {
                for (int curChunkZ = pos.z - 2; curChunkZ <= pos.z + 2; curChunkZ++) {
                    int height = chunkGenerator.getHeight((curChunkX << 4) + 7, (curChunkZ << 4) + 7, Heightmap.Type.WORLD_SURFACE_WG, world);
                    maxTerrainHeight = Math.max(maxTerrainHeight, height);
                    minTerrainHeight = Math.min(minTerrainHeight, height);
                }
            }

            return maxTerrainHeight - minTerrainHeight <= heightRange;
        }
        return true;
    }
}