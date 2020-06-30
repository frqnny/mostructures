package io.github.franiscoder.mostructures.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.franiscoder.mostructures.MoStructures;
import io.github.franiscoder.mostructures.init.StructureInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.*;
import net.minecraft.structure.processor.BlockRotStructureProcessor;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.List;

public class JunglePyramidGenerator {
    public static final Identifier SW_STARTING_PIECE = MoStructures.id("jungle_pyramid/sw_piece");
    public static final Identifier MIDDLE_PIECES = MoStructures.id("jungle_pyramid/middle_pieces");
    public static final Identifier NE_FINAL_PIECE = MoStructures.id("jungle_pyramid/ne_piece");

    static {
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        SW_STARTING_PIECE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new ListPoolElement(ImmutableList.of(
                                        new LegacySinglePoolElement(MoStructures.MODID + ":jungle_pyramid/sw"),
                                        new LegacySinglePoolElement(MoStructures.MODID + ":jungle_pyramid/sw_overgrown",
                                                ImmutableList.of(new BlockRotStructureProcessor(0.15F))))),
                                        1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        MIDDLE_PIECES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new ListPoolElement(ImmutableList.of(
                                        new LegacySinglePoolElement(MoStructures.MODID + ":jungle_pyramid/se"),
                                        new LegacySinglePoolElement(MoStructures.MODID + ":jungle_pyramid/se_overgrown",
                                                ImmutableList.of(new BlockRotStructureProcessor(0.15F))))),
                                        1),
                                new Pair<>(new ListPoolElement(ImmutableList.of(
                                        new LegacySinglePoolElement(MoStructures.MODID + ":jungle_pyramid/nw"),
                                        new LegacySinglePoolElement(MoStructures.MODID + ":jungle_pyramid/nw_overgrown",
                                                ImmutableList.of(new BlockRotStructureProcessor(0.15F))))),
                                        1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        NE_FINAL_PIECE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new ListPoolElement(ImmutableList.of(
                                        new LegacySinglePoolElement(MoStructures.MODID + ":jungle_pyramid/ne"),
                                        new LegacySinglePoolElement(MoStructures.MODID + ":jungle_pyramid/ne_overgrown",
                                                ImmutableList.of(new BlockRotStructureProcessor(0.15F))))),
                                        1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
    }

    public static void addPieces(ChunkGenerator chunkGenerator, StructureManager structureManager, BlockPos pos, List<StructurePiece> pieces, ChunkRandom random) {
        StructurePoolBasedGenerator.addPieces(SW_STARTING_PIECE, 7, JunglePyramidGenerator.Piece::new, chunkGenerator, structureManager, pos, pieces, random, true, true);
    }

    public static class Piece extends PoolStructurePiece {
        public Piece(StructureManager manager, CompoundTag tag) {
            super(manager, tag, StructureInit.JUNGLE_PYRAMID_PIECE);
        }

        public Piece(StructureManager structureManager, StructurePoolElement structurePoolElement, BlockPos blockPos, int i, BlockRotation blockRotation, BlockBox blockBox) {
            super(StructureInit.JUNGLE_PYRAMID_PIECE, structureManager, structurePoolElement, blockPos, i, blockRotation, blockBox);
        }
    }
}
