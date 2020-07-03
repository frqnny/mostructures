package io.github.franiscoder.mostructures.feature;

import io.github.franiscoder.mostructures.MoStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class LamppostFeature extends Feature<DefaultFeatureConfig> {
    public static final Identifier LAMPPOST = MoStructures.id("miscellaneous/lamppost");
    public static final Identifier NETHER_LAMPPOST = MoStructures.id("miscellaneous/netherlamppost");

    public LamppostFeature() {
        super(DefaultFeatureConfig.CODEC);
    }

    public static BlockPos getCorrectNetherHeight(BlockPos pos, ServerWorldAccess world) {

        //It'll check for lava at lava ocean level (32) now
        BlockPos posToWorkOn = new BlockPos(pos.getX(), 30, pos.getZ());
        BlockState block = world.getBlockState(posToWorkOn);
        BlockState state = Blocks.AIR.getDefaultState();
        while (block != state) {
            posToWorkOn = posToWorkOn.up();
            block = world.getBlockState(posToWorkOn);

            if (!world.getBlockState(posToWorkOn).getFluidState().isEmpty()) return null;
        }

        return posToWorkOn.down();
    }

    @Override
    public boolean generate(ServerWorldAccess world, ChunkGenerator generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        Identifier lamppost = world.getBiome(pos).getCategory() == Biome.Category.NETHER ? NETHER_LAMPPOST : LAMPPOST;
        boolean inWater = false;
        BlockPos newPos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos);
        if (world.getBlockState(newPos.down()) == Blocks.WATER.getDefaultState()) {
            inWater = true;
        }
        Biome.Category category = world.getBiome(pos).getCategory();
        if (category == Biome.Category.FOREST && !inWater && random.nextBoolean()) {

            BlockRotation blockRotation = BlockRotation.random(random);
            StructurePlacementData structurePlacementData = (new StructurePlacementData()).setMirror(BlockMirror.NONE).setRotation(blockRotation).setIgnoreEntities(false).setChunkPosition(null);
            StructureManager manager = ((ServerWorld) world.getWorld()).getStructureManager();
            Structure structure = manager.getStructureOrBlank(lamppost);

            structure.place(world, newPos, structurePlacementData, random);
            return true;
        } else if (category == Biome.Category.NETHER) {
            BlockRotation blockRotation = BlockRotation.random(random);
            StructurePlacementData structurePlacementData = (new StructurePlacementData()).setMirror(BlockMirror.NONE).setRotation(blockRotation).setIgnoreEntities(false).setChunkPosition(null);
            StructureManager manager = ((ServerWorld) world.getWorld()).getStructureManager();
            Structure structure = manager.getStructureOrBlank(lamppost);

            BlockPos correctPos = getCorrectNetherHeight(pos, world);
            if (correctPos == null) return false;

            structure.place(world, correctPos, structurePlacementData, random);
            return true;
        }
        return false;
    }

}
