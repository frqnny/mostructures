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
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class SmallBeachFeatures extends Feature<DefaultFeatureConfig> {
    public static final Identifier VILLAGER_MOAI = MoStructures.id("beach/villager_moai");
    //public static final Identifier[] BEACHFEATURES = {VILLAGER_MOAI};

    public SmallBeachFeatures() {
        super(DefaultFeatureConfig::deserialize);
    }


    @Override
    public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {

        BlockPos[] posToCheck = {pos.down().east(), pos.down().west(), pos.down().north(), pos.down().south(), pos};

        for (BlockPos waterPos : posToCheck) {
            if (!world.getBlockState(waterPos).getFluidState().isEmpty()) {
                return false;
            }
        }

        StructureManager manager = ((ServerWorld) world.getWorld()).getStructureManager();
        Structure structure = manager.getStructureOrBlank(VILLAGER_MOAI);
        BlockRotation blockRotation = BlockRotation.random(random);
        StructurePlacementData structurePlacementData = (new StructurePlacementData()).setMirrored(BlockMirror.NONE).setRotation(blockRotation).setIgnoreEntities(false).setChunkPosition(null);
        structure.place(world, pos.add(0, -3, 0), structurePlacementData);
        return true;
    }
}
