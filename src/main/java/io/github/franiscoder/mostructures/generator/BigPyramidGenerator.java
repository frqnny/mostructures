package io.github.franiscoder.mostructures.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.franiscoder.mostructures.MoStructures;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.TemplatePools;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.List;

public class BigPyramidGenerator {
    public static final Identifier SW_STARTING_PIECE = MoStructures.id("pyramid/sw_piece");
    public static final Identifier PYRAMID_PIECES = MoStructures.id("pyramid/pieces");
    public static final Identifier NE_FINAL_PIECE = MoStructures.id("pyramid/ne_piece");

    public static final StructurePool STARTING_POOL;

    static {
        STARTING_POOL = TemplatePools.register(
                new StructurePool(
                        SW_STARTING_PIECE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":pyramid/sw"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        TemplatePools.register(
                new StructurePool(
                        PYRAMID_PIECES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":pyramid/se"), 1),
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":pyramid/nw"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        TemplatePools.register(
                new StructurePool(
                        NE_FINAL_PIECE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":pyramid/ne"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
    }


    public static void addPieces(DynamicRegistryManager registry, StructurePoolFeatureConfig config, ChunkGenerator chunkGenerator, StructureManager structureManager, BlockPos pos, List<StructurePiece> pieces, ChunkRandom random) {
        StructurePoolBasedGenerator.method_30419(registry, config, PoolStructurePiece::new, chunkGenerator, structureManager, pos, pieces, random, true, true);
    }
}

