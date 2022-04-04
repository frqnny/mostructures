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
    public static final Identifier BOTTOM = MoStructures.id("castle/bottom");
    public static final Identifier TOP = MoStructures.id("castle/top");

    public static final StructurePool STARTING_POOL;


    static {
        STARTING_POOL = StructurePools.register(
                new StructurePool(
                        BOTTOM,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":castle/bottom"), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        TOP,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":castle/top"), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );

    }

    public static void init() {

    }
}
