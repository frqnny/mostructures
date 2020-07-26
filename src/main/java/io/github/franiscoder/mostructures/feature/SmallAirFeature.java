package io.github.franiscoder.mostructures.feature;

import io.github.franiscoder.mostructures.MoStructures;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class SmallAirFeature extends Feature<DefaultFeatureConfig> {
    private static final Identifier AIR_BALLOON_1 = MoStructures.id("air/airballoon1");
    private static final Identifier AIR_BALLOON_2 = MoStructures.id("air/airballoon2");
    private static final Identifier AIR_BALLOON_3 = MoStructures.id("air/airballoon3");
    public static final Identifier[] AIR_FEATURES = new Identifier[]{AIR_BALLOON_1, AIR_BALLOON_2, AIR_BALLOON_3};


    public SmallAirFeature() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(ServerWorldAccess world, ChunkGenerator generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        boolean result = world.getDimension() == DimensionType.getOverworldDimensionType();

        if (result) {
            int i = random.nextInt(AIR_FEATURES.length);
            StructureManager manager = world.getWorld().getStructureManager();
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
