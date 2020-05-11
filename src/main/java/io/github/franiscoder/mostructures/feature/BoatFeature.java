package io.github.franiscoder.mostructures.feature;

import io.github.franiscoder.mostructures.MoStructures;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class BoatFeature extends Feature<DefaultFeatureConfig> {
    public static final Identifier BOAT1 = MoStructures.id("boat/boat1");

    public BoatFeature() {
        super(DefaultFeatureConfig::deserialize);
    }

    @Override
    public boolean generate(IWorld world, StructureAccessor accessor, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        StructureManager manager = ((ServerWorld) world.getWorld()).getStructureManager();
        Structure structure = manager.getStructureOrBlank(BOAT1);
        BlockRotation blockRotation = BlockRotation.random(random);
        StructurePlacementData structurePlacementData = (new StructurePlacementData()).setMirror(BlockMirror.NONE).setRotation(blockRotation).setIgnoreEntities(false).setChunkPosition(null);
        structure.place(world, pos.add(0, -3, 0), structurePlacementData);
        return true;
    }
}
