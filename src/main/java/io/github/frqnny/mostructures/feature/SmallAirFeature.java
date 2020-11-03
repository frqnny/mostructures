package io.github.frqnny.mostructures.feature;

import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class SmallAirFeature extends Feature<DefaultFeatureConfig> {
    public static final Identifier ID = MoStructures.id("airballoon");
    private static final Identifier AIR_BALLOON_1 = MoStructures.id("air/airballoon1");
    private static final Identifier AIR_BALLOON_2 = MoStructures.id("air/airballoon2");
    private static final Identifier AIR_BALLOON_3 = MoStructures.id("air/airballoon3");
    public static final Identifier[] AIR_FEATURES = new Identifier[]{AIR_BALLOON_1, AIR_BALLOON_2, AIR_BALLOON_3};


    public SmallAirFeature() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, DefaultFeatureConfig featureConfig) {
        boolean result = world.toServerWorld().getRegistryKey().equals(World.OVERWORLD);

        if (result) {
            int i = random.nextInt(AIR_FEATURES.length);
            StructureManager manager = world.toServerWorld().getStructureManager();
            Structure structure = manager.getStructureOrBlank(AIR_FEATURES[i]);
            int yToAdd = Math.max(random.nextInt(100), 45);
            BlockPos finalPos = pos.add(0, yToAdd, 0);
            BlockRotation blockRotation = BlockRotation.random(random);
            StructurePlacementData structurePlacementData = (new StructurePlacementData()).setMirror(BlockMirror.NONE).setRotation(blockRotation).setIgnoreEntities(false).setChunkPosition(null);
            structure.place(world, finalPos, structurePlacementData, random);
        }
        return result;
    }


}
