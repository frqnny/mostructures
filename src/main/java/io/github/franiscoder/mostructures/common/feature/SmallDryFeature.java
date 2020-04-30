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
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class SmallDryFeature extends Feature<DefaultFeatureConfig> {
    public static final Identifier DEAD_TREE = MoStructures.id("deadtree");
    public static final Identifier DESERT_ATRIUM = MoStructures.id("desert_atrium");
    public static final Identifier[] IDENTIFIERS = {DEAD_TREE, DESERT_ATRIUM};

    public SmallDryFeature() {
        super(DefaultFeatureConfig::deserialize);
    }

    @Override
    public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        if (canGenerate(world, pos) && world.getBlockState(pos.down()) == Blocks.SAND.getDefaultState()) {
            int randomStructureToPlace = world.getRandom().nextInt(IDENTIFIERS.length);
            //to come back soon
            Identifier structureId = IDENTIFIERS[randomStructureToPlace];
            Structure structure = ((ServerWorld) world.getWorld()).getStructureManager().getStructureOrBlank(structureId);

            BlockPos newPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, pos);
            BlockRotation blockRotation = BlockRotation.random(random);
            StructurePlacementData structurePlacementData = (new StructurePlacementData()).setMirrored(BlockMirror.NONE).setRotation(blockRotation).setIgnoreEntities(false).setChunkPosition(null);

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
