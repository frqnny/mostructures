package io.github.frqnny.mostructures.util;

import io.github.frqnny.mostructures.ConfiguredFeatures;
import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FeatureHelper {

    public static void placeStructure(Identifier structureId, FeatureContext<DefaultFeatureConfig> context) {
        placeStructure(structureId, context.getOrigin(), context.getWorld(), context.getRandom());
    }

    public static void placeStructureWithNewPos(Identifier structureId, FeatureContext<DefaultFeatureConfig> context, BlockPos pos) {
        placeStructure(structureId, pos, context.getWorld(), context.getRandom());

    }

    public static void placeStructure(Identifier structureId, BlockPos pos, StructureWorldAccess world, Random random) {
        Structure structure = world.toServerWorld().getStructureManager().getStructureOrBlank(structureId);
        BlockRotation blockRotation = BlockRotation.random(random);
        StructurePlacementData structurePlacementData = (new StructurePlacementData()).setMirror(BlockMirror.NONE).setRotation(blockRotation).setIgnoreEntities(false);
        structure.place(world, pos, pos, structurePlacementData, random, 2);
    }

    //Returns false if it can't generate, returns true if it can generate.
    public static boolean checkChunksForStructures(StructureWorldAccess world, BlockPos pos) {
        List<Chunk> chunksToScan = new ArrayList<>(9);
        chunksToScan.add(world.getChunk(pos));
        chunksToScan.add(world.getChunk(pos.add(16, 0, 16)));
        chunksToScan.add(world.getChunk(pos.add(-16, 0, -16)));
        chunksToScan.add(world.getChunk(pos.add(0, 0, 16)));
        chunksToScan.add(world.getChunk(pos.add(16, 0, 0)));
        chunksToScan.add(world.getChunk(pos.add(-16, 0, 0)));
        chunksToScan.add(world.getChunk(pos.add(0, 0, -16)));
        chunksToScan.add(world.getChunk(pos.add(16, 0, -16)));
        chunksToScan.add(world.getChunk(pos.add(-16, 0, 16)));
        for (Chunk chunk : chunksToScan) {
            if (
                    !chunk.getStructureReferences(ConfiguredFeatures.BARN_HOUSE.feature).isEmpty()
                            || !chunk.getStructureReferences(ConfiguredFeatures.JUNGLE_PYRAMID.feature).isEmpty()
                            || !chunk.getStructureReferences(ConfiguredFeatures.VILLAGER_TOWER.feature).isEmpty()
                            || !chunk.getStructureReferences(MoStructures.ABANDONED_CHURCH).isEmpty()
                            || !chunk.getStructureReferences(ConfiguredFeatures.VILLAGER_MARKET.feature).isEmpty()
                            || !chunk.getStructureReferences(ConfiguredFeatures.PILLAGER_FACTORY.feature).isEmpty()
                            || !chunk.getStructureReferences(ConfiguredFeatures.TAVERN.feature).isEmpty()
                            || !chunk.getStructureReferences(ConfiguredFeatures.KILLER_BUNNY_CASTLE.feature).isEmpty()
            ) {
                return false;
            }
        }

        return true;
    }
}
