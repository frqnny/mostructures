package io.github.frqnny.mostructures.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.util.Identifier;

public class BarnHouseGenerator {
    public static final StructurePool STARTING_POOL;
    private static final Identifier BASE_PLATES = MoStructures.id("barn_house/base_plates");
    private static final Identifier BARNHOUSE = MoStructures.id("barn_house/barnhouses");
    private static final Identifier FEATURE_PLATES = MoStructures.id("barn_house/feature_plates");
    private static final Identifier FEATURES = MoStructures.id("barn_house/features");

    static {
        STARTING_POOL = StructurePools.register(
                new StructurePool(
                        BASE_PLATES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":barn_house/base_plate"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        BARNHOUSE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":barn_house/barnhouse_1"), 1),
                                new Pair<>(StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":barn_house/barnhouse_2"), 1),
                                new Pair<>(StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":barn_house/barnhouse_3"), 1),
                                new Pair<>(StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":barn_house/barnhouse_4"), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );

        StructurePools.register(
                new StructurePool(
                        FEATURE_PLATES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":barn_house/feature_plate"), 1)
                        ),
                        StructurePool.Projection.TERRAIN_MATCHING
                )
        );
        StructurePools.register(
                new StructurePool(
                        FEATURES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofLegacySingle(MoStructures.MODID + ":barn_house/feature_cart"), 1),
                                new Pair<>(StructurePoolElement.ofLegacySingle("pillager_outpost/feature_logs"), 1),
                                new Pair<>(StructurePoolElement.ofEmpty(), 5)),
                        StructurePool.Projection.RIGID
                )
        );

    }


    public static void init() {

    }
}