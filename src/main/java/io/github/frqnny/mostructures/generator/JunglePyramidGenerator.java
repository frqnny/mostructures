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
import net.minecraft.structure.processor.BlockRotStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.List;

public class JunglePyramidGenerator {
    public static final Identifier SW_STARTING_PIECE = MoStructures.id("jungle_pyramid/sw_piece");
    public static final Identifier MIDDLE_PIECES = MoStructures.id("jungle_pyramid/middle_pieces");
    public static final Identifier NE_FINAL_PIECE = MoStructures.id("jungle_pyramid/ne_piece");

    public static final StructurePool STARTING_POOL;

    public static final StructureProcessorList JUNGLE_ROT = MoStructures.register("jungle_rot", ImmutableList.of(new BlockRotStructureProcessor(0.15F)));

    static {
        STARTING_POOL = StructurePools.register(
                new StructurePool(
                        SW_STARTING_PIECE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30429(ImmutableList.of(
                                        StructurePoolElement.method_30425(MoStructures.MODID + ":jungle_pyramid/sw"),
                                        StructurePoolElement.method_30426(MoStructures.MODID + ":jungle_pyramid/sw_overgrown",
                                                JUNGLE_ROT))),
                                        1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        MIDDLE_PIECES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30429(ImmutableList.of(
                                        StructurePoolElement.method_30425(MoStructures.MODID + ":jungle_pyramid/se"),
                                        StructurePoolElement.method_30426(MoStructures.MODID + ":jungle_pyramid/se_overgrown",
                                                JUNGLE_ROT))),
                                        1),
                                new Pair<>(StructurePoolElement.method_30429(ImmutableList.of(
                                        StructurePoolElement.method_30425(MoStructures.MODID + ":jungle_pyramid/nw"),
                                        StructurePoolElement.method_30426(MoStructures.MODID + ":jungle_pyramid/nw_overgrown",
                                                JUNGLE_ROT))),
                                        1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        NE_FINAL_PIECE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30429(ImmutableList.of(
                                        StructurePoolElement.method_30425(MoStructures.MODID + ":jungle_pyramid/ne"),
                                        StructurePoolElement.method_30426(MoStructures.MODID + ":jungle_pyramid/ne_overgrown",
                                                JUNGLE_ROT))),
                                        1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
    }

    public static void addPieces(DynamicRegistryManager registry, StructurePoolFeatureConfig config, ChunkGenerator chunkGenerator, StructureManager structureManager, BlockPos pos, List<StructurePiece> pieces, ChunkRandom random) {
        StructurePoolBasedGenerator.method_30419(registry, config, PoolStructurePiece::new, chunkGenerator, structureManager, pos, pieces, random, true, true);
    }
}
