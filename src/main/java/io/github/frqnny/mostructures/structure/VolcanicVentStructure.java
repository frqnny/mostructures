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
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Random;

public class VolcanicVentStructure extends StructureFeature<DefaultFeatureConfig> {
    public VolcanicVentStructure() {
        super(DefaultFeatureConfig.CODEC, StructureGeneratorFactory.simple(VolcanicVentStructure::canGenerate, VolcanicVentStructure::addPieces));
    }

    private static boolean canGenerate(StructureGeneratorFactory.Context<DefaultFeatureConfig> context) {
        return context.isBiomeValid(Heightmap.Type.OCEAN_FLOOR_WG) && context.chunkGenerator().getHeight(context.chunkPos().x << 4, context.chunkPos().z << 4, Heightmap.Type.OCEAN_FLOOR_WG, context.world()) < 50;
    }

    private static void addPieces(StructurePiecesCollector collector, StructurePiecesGenerator.Context<DefaultFeatureConfig> context) {
        BlockPos blockPos = new BlockPos(context.chunkPos().getStartX(), 90, context.chunkPos().getStartZ());
        collector.addPiece(new Piece(context.structureManager(), blockPos, MoStructures.id("empty")));
    }

    public static class Piece extends SimpleStructurePiece {
        public Piece(StructureManager structureManager, BlockPos blockPos, Identifier identifier) {
            super(MoStructures.VOLCANIC_VENT_TYPE, 0, structureManager, identifier, identifier.toString(), new StructurePlacementData(), blockPos);
        }

        public Piece(StructureContext structureContext, NbtCompound nbtCompound) {
            super(MoStructures.VOLCANIC_VENT_TYPE, nbtCompound, structureContext.structureManager(), (id) -> new StructurePlacementData());
        }

        private static int getBaseHeight(StructureWorldAccess world, int x, int y) {
            return world.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, x, y) - 1;
        }

        private static BlockState getRandomBlock() {
            Random random = new Random();
            return switch (random.nextInt(5)) {
                case 0 -> Blocks.STONE.getDefaultState();
                case 1 -> Blocks.MOSSY_COBBLESTONE.getDefaultState();
                case 2 -> Blocks.COBBLESTONE.getDefaultState();
                case 3 -> Blocks.GRAVEL.getDefaultState();
                case 4 -> Blocks.ANDESITE.getDefaultState();
                default -> Blocks.COARSE_DIRT.getDefaultState();
            };
        }

        private static void placeBottom(ServerWorldAccess world, BlockPos pos, float percentage) {
            int max = ((int) (10 * percentage)) + new Random().nextInt(3);
            for (int i = 0; i < max; i++) {
                world.setBlockState(pos.add(0, i, 0), getRandomBlock(), 3);
            }

        }

        @Override
        public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos pos) {
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
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, Random random, BlockBox boundingBox) { }
    }
}
