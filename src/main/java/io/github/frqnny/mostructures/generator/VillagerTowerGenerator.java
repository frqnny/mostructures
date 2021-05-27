package io.github.frqnny.mostructures.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.frqnny.mostructures.ConfiguredFeatures;
import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.util.Identifier;

public class VillagerTowerGenerator {
    public static final StructurePool DEFAULT_STARTING_POOL;
    public static final StructurePool SAVANNA_STARTING_POOL;
    public static final Identifier SAVANNA_BASE_PLATES = MoStructures.id("villager/savanna_plates");
    private static final Identifier BASE_PLATES = MoStructures.id("villager/tower_plates");
    private static final Identifier TOWERS = MoStructures.id("villager/towers");
    private static final Identifier SAVANNA_TOWERS = MoStructures.id("villager/savanna_towers");
    private static final Identifier FEATURE_PLATES = MoStructures.id("villager/feature_plates");
    private static final Identifier FEATURES = MoStructures.id("villager/features");
    private static final Identifier ARMOR_STANDS = MoStructures.id("villager/armorstands");

    static {
        DEFAULT_STARTING_POOL = StructurePools.register(
                new StructurePool(
                        BASE_PLATES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":villager/tower_plate"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        SAVANNA_STARTING_POOL = StructurePools.register(
                new StructurePool(
                        SAVANNA_BASE_PLATES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":villager/savanna_tower_plate"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        TOWERS,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>((StructurePoolElement.method_30426(MoStructures.MODID + ":villager/tower_1", MoStructures.VILLAGER_TOWER_LIST)), 1),
                                new Pair<>((StructurePoolElement.method_30426(MoStructures.MODID + ":villager/tower_2", MoStructures.VILLAGER_TOWER_LIST)), 1),
                                new Pair<>((StructurePoolElement.method_30426(MoStructures.MODID + ":villager/tower_3", MoStructures.VILLAGER_TOWER_LIST)), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        SAVANNA_TOWERS,
                        new Identifier("empty"),
                        ImmutableList.of(
                                //new Pair<>((StructurePoolElement.method_30426(MoStructures.MODID + ":villager/savanna_tower_1", MoStructures.VILLAGER_TOWER_LIST)), 1),
                                new Pair<>((StructurePoolElement.method_30425(MoStructures.MODID + ":villager/savanna_tower_1")), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        FEATURE_PLATES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":villager/feature_plate"), 1)
                        ),
                        StructurePool.Projection.TERRAIN_MATCHING
                )
        );
        StructurePools.register(
                new StructurePool(
                        FEATURES,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30425(MoStructures.MODID + ":villager/iron_golem"), 1),
                                new Pair<>(StructurePoolElement.method_30421(ConfiguredFeatures.VILLAGER_SPAWN), 3),
                                new Pair<>(StructurePoolElement.method_30438(), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
        /*
        StructurePools.register(
                new StructurePool(
                        ARMOR_STANDS,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.method_30421(ConfiguredFeatures.ARMOR_STAND_SPAWN), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );

         */
    }


    public static void init() {

    }
}