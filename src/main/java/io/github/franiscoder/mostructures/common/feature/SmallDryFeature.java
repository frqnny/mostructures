package io.github.franiscoder.mostructures.common.feature;

import io.github.franiscoder.mostructures.MoStructures;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class SmallDryFeature extends Feature<DefaultFeatureConfig> {
    public static final Identifier DEAD_TREE = MoStructures.id("deadtree");
    //public static final Identifier DESERT_GRAVE = MoStructures.id("desert_grave");
    public static final Identifier[] IDENTIFIERS = {DEAD_TREE};

    public SmallDryFeature() {
        super(DefaultFeatureConfig::deserialize);
    }

    @Override
    public boolean generate(IWorld world, StructureAccessor accessor, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        if (canGenerate(world, pos) && world.getBlockState(pos.down()) == Blocks.SAND.getDefaultState()) {
            int randomStructureToPlace = world.getRandom().nextInt(IDENTIFIERS.length);
            //to come back soon
            Identifier structureId = DEAD_TREE;
            Structure structure = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureManager().getStructure(structureId);

            BlockPos newPos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos);
            BlockRotation blockRotation = BlockRotation.random(random);
            StructurePlacementData structurePlacementData = (new StructurePlacementData()).setMirror(BlockMirror.NONE).setRotation(blockRotation).setIgnoreEntities(false).setChunkPosition(null);

            structure.place(world, newPos, structurePlacementData);
            return true;
        } else {
            return false;
        }
    }

    private boolean canGenerate(IWorld world, BlockPos pos) {
        Biome biome = world.getBiome(pos);

        return biome.getCategory() == Biome.Category.DESERT && world.getDimension().getType() == DimensionType.OVERWORLD;
    }


}
