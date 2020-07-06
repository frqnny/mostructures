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
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.List;

public class AbandonedChurchGenerator {
    public static final Identifier PLAINS_PLATE = MoStructures.id("abandoned/village/plates_plains");
    public static final Identifier SAVANNA_PLATE = MoStructures.id("abandoned/village/plates_savanna");
    public static final Identifier DESERT_PLATE = MoStructures.id("abandoned/village/plates_desert");
    public static final Identifier SNOWY_PLATE = MoStructures.id("abandoned/village/plates_snowy");
    public static final Identifier TAIGA_PLATE = MoStructures.id("abandoned/village/plates_taiga");

    public static final Identifier CHURCHES_PLAINS = MoStructures.id("abandoned/village/churches_plains");
    public static final Identifier CHURCHES_SAVANNA = MoStructures.id("abandoned/village/churches_savanna");
    public static final Identifier CHURCHES_DESERT = MoStructures.id("abandoned/village/churches_desert");
    public static final Identifier CHURCHES_SNOWY = MoStructures.id("abandoned/village/churches_snowy");
    public static final Identifier CHURCHES_TAIGA = MoStructures.id("abandoned/village/churches_taiga");


    static {
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        PLAINS_PLATE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":abandoned/village/plate/plains"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        SAVANNA_PLATE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":abandoned/village/plate/savanna"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        DESERT_PLATE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":abandoned/village/plate/desert"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        SNOWY_PLATE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":abandoned/village/plate/snowy"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        TAIGA_PLATE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":abandoned/village/plate/taiga"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        CHURCHES_PLAINS,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":abandoned/village/plains_temple1"), 1),
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":abandoned/village/plains_temple2"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        CHURCHES_SAVANNA,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":abandoned/village/savanna_temple1"), 1),
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":abandoned/village/savanna_temple2"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        CHURCHES_DESERT,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":abandoned/village/desert_temple1"), 1),
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":abandoned/village/desert_temple2"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        CHURCHES_SNOWY,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":abandoned/village/snowy_temple1"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        CHURCHES_TAIGA,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(MoStructures.MODID + ":abandoned/village/taiga_temple1"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
    }

    public static void addPieces(ChunkGenerator chunkGenerator, StructureManager structureManager, BlockPos pos, List<StructurePiece> pieces, ChunkRandom random, StructurePoolFeatureConfig config) {
        StructurePoolBasedGenerator.addPieces(config.startPool, 7, AbandonedChurchGenerator.Piece::new, chunkGenerator, structureManager, pos, pieces, random, true, true);
    }

    public static class Piece extends PoolStructurePiece {
        public Piece(StructureManager manager, CompoundTag tag) {
            super(manager, tag, StructureInit.ABANDONED_CHURCH_PIECE);
        }

        public Piece(StructureManager structureManager, StructurePoolElement structurePoolElement, BlockPos blockPos, int i, BlockRotation blockRotation, BlockBox blockBox) {
            super(StructureInit.ABANDONED_CHURCH_PIECE, structureManager, structurePoolElement, blockPos, i, blockRotation, blockBox);

        }
    }
}