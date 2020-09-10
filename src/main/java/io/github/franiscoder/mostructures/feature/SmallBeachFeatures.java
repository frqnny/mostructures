package io.github.franiscoder.mostructures.feature;

import io.github.franiscoder.mostructures.MoStructures;
import net.minecraft.block.Blocks;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SmallBeachFeatures extends Feature<DefaultFeatureConfig> {
    public static final Identifier VILLAGER_MOAI = MoStructures.id("beach/villager_moai");

    public static final Identifier ID = MoStructures.id("beach_features");


    public SmallBeachFeatures() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, DefaultFeatureConfig featureConfig) {
        boolean result = world.toServerWorld().getRegistryKey().equals(World.OVERWORLD) && world.getBlockState(pos).isOf(Blocks.SAND);

        if (result) {
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
                if (!chunk.getStructureReferences(MoStructures.THE_CASTLE_IN_THE_SKY.feature).isEmpty()) {
                    return false;
                }
            }

            if (!Objects.requireNonNull(BuiltinRegistries.BIOME.getId(world.getBiome(pos))).getNamespace().equals("minecraft")) {
                return false;
            }

            BlockPos[] posToCheck = {pos.down().east(), pos.down().west(), pos.down().north(), pos.down().south(), pos};

            for (BlockPos waterPos : posToCheck) {
                if (!world.getBlockState(waterPos).getFluidState().isEmpty()) {
                    return false;
                }
            }

            StructureManager manager = world.toServerWorld().getStructureManager();
            Structure structure = manager.getStructureOrBlank(VILLAGER_MOAI);
            BlockRotation blockRotation = BlockRotation.random(random);
            StructurePlacementData structurePlacementData = (new StructurePlacementData()).setMirror(BlockMirror.NONE).setRotation(blockRotation).setIgnoreEntities(false).setChunkPosition(null);
            structure.place(world, pos.add(0, -3, 0), structurePlacementData, random);
        }

        return result;
    }
}
