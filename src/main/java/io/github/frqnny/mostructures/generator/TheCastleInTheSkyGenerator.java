package io.github.frqnny.mostructures.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.util.RegUtils;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.structure.processor.BlockRotStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;

public class TheCastleInTheSkyGenerator {
    public static final Identifier SW_STARTING_PIECE = MoStructures.id("castle/sw_piece");
    public static final Identifier MIDDLE_PIECES = MoStructures.id("castle/middle_pieces");
    public static final Identifier NE_FINAL_PIECE = MoStructures.id("castle/ne_piece");

    public static final StructurePool STARTING_POOL;

    public static final StructureProcessorList TCINS_ROT = RegUtils.registerStructureProcessorList("tcins_rot", ImmutableList.of(new BlockRotStructureProcessor(0.25F)));

    static {
        STARTING_POOL = StructurePools.register(
                new StructurePool(
                        SW_STARTING_PIECE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofList(ImmutableList.of(
                                        StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":castle/sw"),
                                        StructurePoolElement.ofProcessedLegacySingle(MoStructures.MODID + ":castle/sw_overgrown",
                                                TCINS_ROT))),
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
                                new Pair<>(StructurePoolElement.ofList(ImmutableList.of(
                                        StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":castle/se"),
                                        StructurePoolElement.ofProcessedLegacySingle(MoStructures.MODID + ":castle/se_overgrown",
                                                TCINS_ROT))),
                                        1),
                                new Pair<>(StructurePoolElement.ofList(ImmutableList.of(
                                        StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":castle/nw"),
                                        StructurePoolElement.ofProcessedLegacySingle(MoStructures.MODID + ":castle/nw_overgrown",
                                                TCINS_ROT))),
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
                                new Pair<>(StructurePoolElement.ofList(ImmutableList.of(
                                        StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":castle/ne"),
                                        StructurePoolElement.ofProcessedLegacySingle(MoStructures.MODID + ":castle/ne_overgrown",
                                                TCINS_ROT))),
                                        1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
    }

    public static void init() {

    }
}
