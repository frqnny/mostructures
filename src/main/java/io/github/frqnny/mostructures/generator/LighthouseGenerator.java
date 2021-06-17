package io.github.frqnny.mostructures.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.util.Identifier;

public class LighthouseGenerator {
    public static final StructurePool STARTING_POOL;
    public static final Identifier LIGHTHOUSES = MoStructures.id("lighthouse/towers");
    public static final Identifier BASE = MoStructures.id("lighthouse/base");

    static {
        STARTING_POOL = StructurePools.register(
                new StructurePool(
                        LIGHTHOUSES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30434(MoStructures.MODID + ":lighthouse/lighthouse_1"), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        BASE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30426(MoStructures.MODID + ":lighthouse/base", MoStructures.PIRATE_SHIP_LIST), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );


    }

    public static void init() {

    }
}