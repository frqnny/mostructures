package io.github.frqnny.mostructures.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeatures;

public class TavernGenerator {
    public static final StructurePool STARTING_POOL;
    private static final Identifier BASE_PLATES = MoStructures.id("tavern/base_plates");
    private static final Identifier TAVERNS = MoStructures.id("tavern/taverns");
    private static final Identifier FEATURE_PLATES = MoStructures.id("tavern/feature_plates");
    private static final Identifier FEATURES = MoStructures.id("tavern/features");
    private static final Identifier TREE = MoStructures.id("tavern/trees");

    static {
        STARTING_POOL = StructurePools.register(
                new StructurePool(
                        BASE_PLATES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":tavern/base_plate"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        TAVERNS,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":tavern/tavern_1"), 1),
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":tavern/tavern_2"), 2),
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":tavern/tavern_3"), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );

        StructurePools.register(
                new StructurePool(
                        FEATURE_PLATES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":tavern/feature_plate"), 1)
                        ),
                        StructurePool.Projection.TERRAIN_MATCHING
                )
        );
        StructurePools.register(
                new StructurePool(
                        FEATURES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":tavern/campfire"), 1),
                                new Pair<>(StructurePoolElement.method_30438(), 5)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        TREE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30421(ConfiguredFeatures.OAK), 1),
                                new Pair<>(StructurePoolElement.method_30421(ConfiguredFeatures.BIRCH), 1)
                        ),
                        StructurePool.Projection.RIGID)
        );


    }


    public static void init() {

    }
}