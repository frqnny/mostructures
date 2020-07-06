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

public class VillagerTowerGenerator {
    private static final Identifier BASE_PLATES = MoStructures.id("villager/tower_plates");
    private static final Identifier TOWERS = MoStructures.id("villager/towers");
    private static final Identifier FEATURE_PLATES = MoStructures.id("villager/feature_plates");
    private static final Identifier FEATURES = MoStructures.id("villager/features");

    static {
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        BASE_PLATES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":villager/tower_plate"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        TOWERS,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new ListPoolElement(ImmutableList.of(
                                        new LegacySinglePoolElement(MoStructures.MODID + ":villager/tower"),
                                        new LegacySinglePoolElement(MoStructures.MODID + ":villager/tower_overgrown",
                                                ImmutableList.of(new BlockRotStructureProcessor(0.45F))))),
                                        1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );

        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        FEATURE_PLATES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":villager/feature_plate"), 1)
                        ),
                        StructurePool.Projection.TERRAIN_MATCHING
                )
        );
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        FEATURES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":villager/iron_golem"), 1),
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + "villager/villager"), 3),
                                new Pair<>(EmptyPoolElement.INSTANCE, 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
    }


    public static void addPieces(ChunkGenerator chunkGenerator, StructureManager structureManager, BlockPos pos, List<StructurePiece> pieces, ChunkRandom random) {
        StructurePoolBasedGenerator.addPieces(BASE_PLATES, 7, VillagerTowerGenerator.Piece::new, chunkGenerator, structureManager, pos, pieces, random, true, true);
    }

    public static class Piece extends PoolStructurePiece {
        public Piece(StructureManager manager, CompoundTag tag) {
            super(manager, tag, StructureInit.VILLAGER_TOWER_PIECE);
        }

        public Piece(StructureManager structureManager, StructurePoolElement structurePoolElement, BlockPos blockPos, int i, BlockRotation blockRotation, BlockBox blockBox) {
            super(StructureInit.VILLAGER_TOWER_PIECE, structureManager, structurePoolElement, blockPos, i, blockRotation, blockBox);
        }

    }
}