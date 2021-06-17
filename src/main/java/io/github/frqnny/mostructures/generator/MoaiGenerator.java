package io.github.frqnny.mostructures.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.util.Identifier;

public class MoaiGenerator {
    public static final StructurePool STARTING_POOL;
    public static final Identifier MOAI = MoStructures.id("moai");

    static {
        STARTING_POOL = StructurePools.register(
                new StructurePool(
                        MOAI,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":moai/villager_moai"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );


    }

    public static void init() {

    }
}
