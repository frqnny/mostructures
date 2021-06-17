package io.github.frqnny.mostructures.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.util.Identifier;

public class PirateShipGenerator {
    public static final Identifier BOTTOM_LOWER = MoStructures.id("pirateship/bottomlower");
    public static final Identifier BOTTOM_UPPER = MoStructures.id("pirateship/bottomupper");
    public static final Identifier TOP_LOWER = MoStructures.id("pirateship/toplower");
    public static final Identifier TOP_UPPER = MoStructures.id("pirateship/topupper");
    public static final StructurePool STARTING_POOL;

    static {
        STARTING_POOL = StructurePools.register(
                new StructurePool(
                        BOTTOM_LOWER,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30426(MoStructures.MODID + ":ship/bottom_lower", MoStructures.PIRATE_SHIP_LIST), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        BOTTOM_UPPER,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30426(MoStructures.MODID + ":ship/bottom_upper", MoStructures.PIRATE_SHIP_LIST), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        TOP_LOWER,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30426(MoStructures.MODID + ":ship/top_lower", MoStructures.PIRATE_SHIP_LIST), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        TOP_UPPER,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30426(MoStructures.MODID + ":ship/top_upper", MoStructures.PIRATE_SHIP_LIST), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );
    }

    public static void init() {

    }

}
