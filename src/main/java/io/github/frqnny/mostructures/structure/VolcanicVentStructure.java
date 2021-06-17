package io.github.frqnny.mostructures.structure;

import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Random;

public class VolcanicVentStructure extends StructureFeature<DefaultFeatureConfig> {
    public static final Identifier ID = MoStructures.id("volcanic_vent");

    public VolcanicVentStructure() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return VolcanicVentStructure.Start::new;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long worldSeed, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig featureConfig) {
        return chunkGenerator.getHeight(chunkX << 4, chunkZ << 4, Heightmap.Type.OCEAN_FLOOR_WG) < 50;
    }

    public static class Start extends MarginedStructureStart<DefaultFeatureConfig> {
        public Start(StructureFeature<DefaultFeatureConfig> structureFeature, int i, int j, BlockBox blockBox, int k, long l) {
            super(structureFeature, i, j, blockBox, k, l);
        }

        @Override
        public void init(DynamicRegistryManager registryManager, ChunkGenerator chunkGenerator, StructureManager manager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig config) {
            BlockPos blockPos = new BlockPos(chunkX << 4, 90, chunkZ << 4);
            //this.children.add(new VolcanicVentStructure.Piece(manager, blockPos));
            //this.setBoundingBoxFromChildren();
        }
    }

    public static class Piece extends StructurePiece {
        protected BlockPos pos;


        public Piece(StructureManager structureManager, BlockPos blockPos) {
            super(MoStructures.VOLCANIC_VENT_TYPE, 0);
            this.pos = blockPos;
            this.boundingBox = BlockBox.empty();
        }

        public Piece(StructureManager structureManager, NbtCompound nbt) {
            super(MoStructures.VOLCANIC_VENT_TYPE, nbt);
        }

        protected void toNbt(NbtCompound tag) {
            tag.putInt("TPX", this.pos.getX());
            tag.putInt("TPY", this.pos.getY());
            tag.putInt("TPZ", this.pos.getZ());
        }

        private static int getBaseHeight(StructureWorldAccess world, int x, int y) {
            return world.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, x, y) - 1;
        }

        public static BlockState getRandomBlock() {
            Random random = new Random();
            switch (random.nextInt(5)) {
                case 0:
                    return Blocks.STONE.getDefaultState();
                case 1:
                    return Blocks.MOSSY_COBBLESTONE.getDefaultState();
                case 2:
                    return Blocks.COBBLESTONE.getDefaultState();
                case 3:
                    return Blocks.GRAVEL.getDefaultState();
                case 4:
                    return Blocks.ANDESITE.getDefaultState();
                default:
                    return Blocks.COARSE_DIRT.getDefaultState();
            }
        }

        private static void placeBottom(ServerWorldAccess world, BlockPos pos, float percentage) {
            int max = ((int) (10 * percentage)) + new Random().nextInt(3);
            for (int i = 0; i < max; i++) {
                world.setBlockState(pos.add(0, i, 0), getRandomBlock(), 3);
            }

        }

        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos pos) {
            BlockPos actualPos = new BlockPos(pos.getX(), getBaseHeight(world, pos.getX(), pos.getZ()), pos.getZ());

            int x = pos.getX();
            int z = pos.getZ();
            float[] percentageToRadius = new float[]{1.0F, .95F, 0.75F, 0.7F, 0.59F, 0.50F, .45F, 0.40F, 0.3F, 0.3F, 0.3F, 0.25F, 0.22F, 0.2F};
            int length = percentageToRadius.length;
            BlockPos.Mutable mutable = BlockPos.ORIGIN.mutableCopy();

            for (int o = x - length; o <= x + length; ++o) {
                for (int p = z - length; p <= z + length; ++p) {
                    int q = Math.abs(o - x) + Math.abs(p - z);
                    int r = Math.max(0, q);
                    if (r < length) {
                        float f = percentageToRadius[r];
                        if (random.nextDouble() < (double) f) {
                            int t = getBaseHeight(world, o, p);
                            mutable.set(o, t, p);
                            placeBottom(world, mutable, f);

                        }
                    }
                }
            }

            for (int d = 0; d < 12; d++) {
                world.setBlockState(actualPos.add(0, d, 0), Blocks.WATER.getDefaultState(), 3);
            }
            for (int f = 0; f < 6; f++) {
                world.setBlockState(actualPos.add(0, f, 0), Blocks.MAGMA_BLOCK.getDefaultState(), 3);
            }

            return true;
        }
    }
}
