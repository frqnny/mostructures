package io.github.frqnny.mostructures.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.util.Identifier;

public class VillageBazaarGenerator {
    public static final StructurePool STARTING_POOL;
    public static final Identifier START = MoStructures.id("bazaar/ring");
    public static final Identifier CENTER_PIECE = MoStructures.id("bazaar/center_piece");
    public static final Identifier HOUSE_9X = MoStructures.id("bazaar/house_nine");
    public static final Identifier PATH = MoStructures.id("bazaar/path");
    public static final Identifier VILLAGER = MoStructures.id("bazaar/villager");


    static {
        StructurePools.register(
                new StructurePool(
                        START,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":bazaar/ring_1"), 1)
                        ),
                        StructurePool.Projection.TERRAIN_MATCHING
                )
        );
        STARTING_POOL = StructurePools.register(
                new StructurePool(
                        CENTER_PIECE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":bazaar/center_piece_1"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        HOUSE_9X,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":bazaar/house9x_1"), 2),
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":bazaar/house9x_2"), 3),
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":bazaar/house9x_3"), 2),
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":bazaar/house9x_4"), 2),
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":bazaar/house9x_5"), 1),
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":bazaar/house9x_6"), 1),
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":bazaar/house9x_7"), 1),
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":bazaar/house9x_8"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        PATH,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":bazaar/path"), 1)
                        ),
                        StructurePool.Projection.TERRAIN_MATCHING
                )
        );

        StructurePools.register(
                new StructurePool(
                        VILLAGER,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":bazaar/villager"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
    }

    public static void init() {

    }
}