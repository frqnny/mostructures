package io.github.franiscoder.mostructures.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.franiscoder.mostructures.MoStructures;
import io.github.franiscoder.mostructures.init.ModStructures;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.*;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.List;

public class BarnHouseGenerator {
    private static final Identifier BASE_PLATES = MoStructures.id("barn_house/base_plates");
    private static final Identifier BARNHOUSE = MoStructures.id("barn_house/barnhouses");
    private static final Identifier FEATURE_PLATES = MoStructures.id("barn_house/feature_plates");
    private static final Identifier FEATURES = MoStructures.id("barn_house/features");

    static {
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        BASE_PLATES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":barn_house/base_plate"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        BARNHOUSE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":barn_house/barnhouse"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        FEATURE_PLATES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":barn_house/feature_plate"), 1)
                        ),
                        StructurePool.Projection.TERRAIN_MATCHING
                )
        );
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        FEATURES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":barn_house/feature_cart"), 1),
                                new Pair<>(new LegacySinglePoolElement("pillager_outpost/feature_logs"), 1),
                                new Pair<>(EmptyPoolElement.INSTANCE, 6)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
    }


    public static void addPieces(ChunkGenerator chunkGenerator, StructureManager structureManager, BlockPos pos, List<StructurePiece> pieces, ChunkRandom random) {
        StructurePoolBasedGenerator.addPieces(BASE_PLATES, 7, BarnHouseGenerator.Piece::new, chunkGenerator, structureManager, pos, pieces, random, true, true);
    }

    public static class Piece extends PoolStructurePiece {
        public Piece(StructureManager manager, CompoundTag tag) {
            super(manager, tag, ModStructures.BARN_HOUSE_PIECE);
        }

        public Piece(StructureManager structureManager, StructurePoolElement structurePoolElement, BlockPos blockPos, int i, BlockRotation blockRotation, BlockBox blockBox) {
            super(ModStructures.BARN_HOUSE_PIECE, structureManager, structurePoolElement, blockPos, i, blockRotation, blockBox);
        }

    }
}
