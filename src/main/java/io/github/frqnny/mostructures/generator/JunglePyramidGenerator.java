package io.github.frqnny.mostructures.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.processor.JungleTempleStructureProcessor;
import io.github.frqnny.mostructures.util.RegistrationHelper;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.List;

public class JunglePyramidGenerator {
    public static final Identifier BASE = MoStructures.id("jungle_pyramid/base");

    public static final StructurePool STARTING_POOL;

    public static StructureProcessorType<JungleTempleStructureProcessor> PROCESSOR;
    public static StructureProcessorList JUNGLE_ROT_LIST;

    static {
        STARTING_POOL = StructurePools.register(
                new StructurePool(
                        BASE,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>((StructurePoolElement.method_30426(MoStructures.MODID + ":jungle_pyramid/base", JUNGLE_ROT_LIST)), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
    }

    public static void addPieces(DynamicRegistryManager registry, StructurePoolFeatureConfig config, ChunkGenerator chunkGenerator, StructureManager structureManager, BlockPos pos, List<StructurePiece> pieces, ChunkRandom random) {
        StructurePoolBasedGenerator.method_30419(registry, config, PoolStructurePiece::new, chunkGenerator, structureManager, pos, pieces, random, true, true);
    }

    public static void registerJungleRotProcessor() {
        PROCESSOR = StructureProcessorType.register("jungle_rot_processor", JungleTempleStructureProcessor.CODEC);
        JUNGLE_ROT_LIST = RegistrationHelper.registerStructureProcessor("jungle_rot", ImmutableList.of(new JungleTempleStructureProcessor(0.15F)));
    }
}
