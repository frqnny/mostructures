package io.github.frqnny.mostructures.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.List;

public class BarnHouseGenerator {
    public static final StructurePool STARTING_POOL;
    private static final Identifier BASE_PLATES = MoStructures.id("barn_house/base_plates");
    private static final Identifier BARNHOUSE = MoStructures.id("barn_house/barnhouses");
    private static final Identifier FEATURE_PLATES = MoStructures.id("barn_house/feature_plates");
    private static final Identifier FEATURES = MoStructures.id("barn_house/features");

    static {
        STARTING_POOL = StructurePools.register(
                new StructurePool(
                        BASE_PLATES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":barn_house/base_plate"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        BARNHOUSE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":barn_house/barnhouse"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );

        StructurePools.register(
                new StructurePool(
                        FEATURE_PLATES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":barn_house/feature_plate"), 1)
                        ),
                        StructurePool.Projection.TERRAIN_MATCHING
                )
        );
        StructurePools.register(
                new StructurePool(
                        FEATURES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":barn_house/feature_cart"), 1),
                                new Pair<>(StructurePoolElement.method_30425("pillager_outpost/feature_logs"), 1),
                                new Pair<>(StructurePoolElement.method_30438(), 5)),
                        StructurePool.Projection.RIGID
                )
        );

    }


    public static void addPieces(DynamicRegistryManager registry, StructurePoolFeatureConfig config, ChunkGenerator chunkGenerator, StructureManager structureManager, BlockPos pos, List<StructurePiece> pieces, ChunkRandom random) {
        StructurePoolBasedGenerator.method_30419(registry, config, PoolStructurePiece::new, chunkGenerator, structureManager, pos, pieces, random, true, true);
    }
}