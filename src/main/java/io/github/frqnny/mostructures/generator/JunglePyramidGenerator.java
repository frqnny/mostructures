package io.github.frqnny.mostructures.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.util.Identifier;

public class JunglePyramidGenerator {
    public static final Identifier BASE = MoStructures.id("jungle_pyramid/base");

    public static final StructurePool STARTING_POOL;

    static {
        STARTING_POOL = StructurePools.register(
                new StructurePool(
                        BASE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>((StructurePoolElement.method_30426(MoStructures.MODID + ":jungle_pyramid/base", MoStructures.JUNGLE_ROT_LIST)), 1)
                                //new Pair<>((StructurePoolElement.method_30425(MoStructures.MODID + ":jungle_pyramid/base")), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );
    }

    public static void init() {

    }

}
