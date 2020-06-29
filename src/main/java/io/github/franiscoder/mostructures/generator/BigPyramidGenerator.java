package io.github.franiscoder.mostructures.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.franiscoder.mostructures.MoStructures;
import io.github.franiscoder.mostructures.init.StructureInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.LegacySinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.List;

public class BigPyramidGenerator {
    public static final Identifier SW_STARTING_PIECE = MoStructures.id("pyramid/sw_piece");
    public static final Identifier PYRAMID_PIECES = MoStructures.id("pyramid/pieces");
    public static final Identifier NE_FINAL_PIECE = MoStructures.id("pyramid/ne_piece");

    static {
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        SW_STARTING_PIECE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":pyramid/sw"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        PYRAMID_PIECES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":pyramid/se"), 1),
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":pyramid/nw"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        NE_FINAL_PIECE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":pyramid/ne"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
    }


    public static void addPieces(ChunkGenerator chunkGenerator, StructureManager structureManager, BlockPos pos, List<StructurePiece> pieces, ChunkRandom random) {
        StructurePoolBasedGenerator.addPieces(SW_STARTING_PIECE, 7, BigPyramidGenerator.Piece::new, chunkGenerator, structureManager, pos, pieces, random, true, true);
    }

    public static class Piece extends PoolStructurePiece {
        public Piece(StructureManager manager, CompoundTag tag) {
            super(manager, tag, StructureInit.PYRAMID_PIECE);
        }

        public Piece(StructureManager structureManager, StructurePoolElement structurePoolElement, BlockPos blockPos, int i, BlockRotation blockRotation, BlockBox blockBox) {
            super(StructureInit.PYRAMID_PIECE, structureManager, structurePoolElement, blockPos, i, blockRotation, blockBox);
        }
    }
}

