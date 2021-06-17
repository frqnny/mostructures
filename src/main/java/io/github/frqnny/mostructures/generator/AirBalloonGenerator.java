package io.github.frqnny.mostructures.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.util.Identifier;

public class AirBalloonGenerator {
    public static final StructurePool STARTING_POOL;
    public static final Identifier AIR_BALLOONS = MoStructures.id("air_balloons");

    static {
        STARTING_POOL = StructurePools.register(
                new StructurePool(
                        AIR_BALLOONS,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":air/airballoon1"), 1),
                                new Pair<>(StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":air/airballoon2"), 1),
                                new Pair<>(StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":air/airballoon3"), 1),
                                new Pair<>(StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":air/airballoon4"), 1),
                                new Pair<>(StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":air/airballoon5"), 1),
                                new Pair<>(StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":air/airballoon6"), 1),
                                new Pair<>(StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":air/airballoon7"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
    }

    public static void init() {

    }
}
