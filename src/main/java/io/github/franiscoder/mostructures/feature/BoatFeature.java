package io.github.franiscoder.mostructures.feature;

import io.github.franiscoder.mostructures.MoStructures;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class BoatFeature extends Feature<DefaultFeatureConfig> {
    public static final Identifier BOAT1 = MoStructures.id("boat/boat1");

    public static final Identifier ID = MoStructures.id("boat");

    public BoatFeature() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, DefaultFeatureConfig featureConfig) {
        boolean result = world.toServerWorld().getRegistryKey().equals(World.OVERWORLD);
        if (result) {
            BlockPos newPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, pos);
            Structure structure = world.toServerWorld().getStructureManager().getStructureOrBlank(BOAT1);
            BlockRotation blockRotation = BlockRotation.random(random);
            StructurePlacementData structurePlacementData = (new StructurePlacementData()).setMirror(BlockMirror.NONE).setRotation(blockRotation).setIgnoreEntities(false).setChunkPosition(null);
            structure.place(world, newPos.add(0, -3, 0), structurePlacementData, random);
        }
        return result;
    }
}
