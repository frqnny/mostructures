package io.github.franiscoder.mostructures.feature;

import io.github.franiscoder.mostructures.MoStructures;
import net.minecraft.class_5281;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class SmallAirFeature extends Feature<DefaultFeatureConfig> {
    private static final Identifier AIR_BALLOON_1 = MoStructures.id("air/airballoon1");
    private static final Identifier AIR_BALLOON_2 = MoStructures.id("air/airballoon2");
    private static final Identifier AIR_BALLOON_3 = MoStructures.id("air/airballoon3");
    private static final Identifier AIRPLANE = MoStructures.id("air/airplane");
    public static final Identifier[] AIR_FEATURES = new Identifier[]{AIR_BALLOON_1, AIR_BALLOON_2, AIR_BALLOON_3, AIRPLANE};


    public SmallAirFeature() {
        super(DefaultFeatureConfig::deserialize);
    }

    @Override
    public boolean generate(class_5281 world, StructureAccessor accessor, ChunkGenerator generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        Random random2 = world.getRandom();
        int i = random2.nextInt(AIR_FEATURES.length);
        StructureManager manager = ((ServerWorld) world.getWorld()).getStructureManager();
        Structure structure = manager.getStructureOrBlank(AIR_FEATURES[i]);
        int yToAdd = Math.max(random2.nextInt(100), 40);
        pos = pos.add(0, yToAdd, 0);
        BlockRotation blockRotation = BlockRotation.random(random);
        StructurePlacementData structurePlacementData = (new StructurePlacementData()).setMirror(BlockMirror.NONE).setRotation(blockRotation).setIgnoreEntities(false).setChunkPosition(null);
        structure.place(world, pos, structurePlacementData);
        return true;
    }
}
