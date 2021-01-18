package io.github.frqnny.mostructures.util;

import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

import java.util.Random;

public class FeatureHelper {

    public static void placeStructure(Identifier structureId, BlockPos pos, StructureWorldAccess world, Random random) {
        Structure structure = world.toServerWorld().getStructureManager().getStructureOrBlank(structureId);
        BlockRotation blockRotation = BlockRotation.random(random);
        StructurePlacementData structurePlacementData = (new StructurePlacementData()).setMirror(BlockMirror.NONE).setRotation(blockRotation).setIgnoreEntities(false).setChunkPosition(null);
        structure.place(world, pos, structurePlacementData, random);
    }
}
