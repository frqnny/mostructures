package io.github.frqnny.mostructures.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.util.Identifier;

public class TheCastleInTheSkyGenerator {
    public static final Identifier BOTTOM = MoStructures.id("castle/bottom");
    public static final Identifier TOP = MoStructures.id("castle/top");
    public static final Identifier A = MoStructures.id("castle/room_a");
    public static final Identifier B = MoStructures.id("castle/room_b");
    public static final Identifier C = MoStructures.id("castle/room_c");
    public static final Identifier D = MoStructures.id("castle/room_d");
    public static final Identifier E = MoStructures.id("castle/room_e");
    public static final Identifier F = MoStructures.id("castle/room_f");
    public static final Identifier G = MoStructures.id("castle/room_g");
    public static final Identifier H = MoStructures.id("castle/room_h");
    public static final Identifier I = MoStructures.id("castle/room_i");

    public static final StructurePool STARTING_POOL;


    static {
        STARTING_POOL = StructurePools.register(
                new StructurePool(
                        BOTTOM,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofSingle(MoStructures.MODID + ":castle/bottom"), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        TOP,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofSingle(MoStructures.MODID + ":castle/top"), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        A,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofSingle(MoStructures.MODID + ":castle/dungeon/template/a"), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        B,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofSingle(MoStructures.MODID + ":castle/dungeon/template/b"), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        C,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofSingle(MoStructures.MODID + ":castle/dungeon/template/c"), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        D,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofSingle(MoStructures.MODID + ":castle/dungeon/template/d"), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        E,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofSingle(MoStructures.MODID + ":castle/dungeon/template/e"), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        F,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofSingle(MoStructures.MODID + ":castle/dungeon/template/f"), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        G,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofSingle(MoStructures.MODID + ":castle/dungeon/template/g"), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        H,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofSingle(MoStructures.MODID + ":castle/dungeon/template/h"), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );
        StructurePools.register(
                new StructurePool(
                        I,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofSingle(MoStructures.MODID + ":castle/dungeon/template/i"), 1)

                        ),
                        StructurePool.Projection.RIGID
                )
        );


    }

    public static void init() {

    }
}
