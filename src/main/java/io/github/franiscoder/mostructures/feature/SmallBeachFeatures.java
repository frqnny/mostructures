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
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Objects;
import java.util.Random;

public class SmallBeachFeatures extends Feature<DefaultFeatureConfig> {
    public static final Identifier VILLAGER_MOAI = MoStructures.id("beach/villager_moai");
    //public static final Identifier[] BEACHFEATURES = {VILLAGER_MOAI};

    public SmallBeachFeatures() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(ServerWorldAccess world, ChunkGenerator generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        //Random random2 = world.getRandom();
        //int i = random2.nextInt(BEACHFEATURES.length);
        if (!Objects.requireNonNull(Registry.BIOME.getId(world.getBiome(pos))).getNamespace().equals("minecraft")) {
            return false;
        }

        BlockPos[] posToCheck = {pos.down().east(), pos.down().west(), pos.down().north(), pos.down().south(), pos};

        for (BlockPos waterPos : posToCheck) {
            if (!world.getBlockState(waterPos).getFluidState().isEmpty()) {
                return false;
            }
        }

        StructureManager manager = ((ServerWorld) world.getWorld()).getStructureManager();
        Structure structure = manager.getStructureOrBlank(VILLAGER_MOAI);
        BlockRotation blockRotation = BlockRotation.random(random);
        StructurePlacementData structurePlacementData = (new StructurePlacementData()).setMirror(BlockMirror.NONE).setRotation(blockRotation).setIgnoreEntities(false).setChunkPosition(null);
        structure.place(world, pos.add(0, -3, 0), structurePlacementData, random);
        return true;
    }
}
