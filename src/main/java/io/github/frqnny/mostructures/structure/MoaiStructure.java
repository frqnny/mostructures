package io.github.frqnny.mostructures.structure;

import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.util.FeatureHelper;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.structure.SimpleStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.HeightLimitView;
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

public class MoaiStructure extends StructureFeature<DefaultFeatureConfig> {
    public static final Identifier VILLAGER_MOAI = MoStructures.id("beach/villager_moai");

    public MoaiStructure() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return MoaiStructure.Start::new;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long worldSeed, ChunkRandom random, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig config, HeightLimitView world) {
        BlockPos pos = chunkPos.getStartPos();

        BlockPos[] posToCheck = {pos.down().east(), pos.down().west(), pos.down().north(), pos.down().south(), pos};

        for (BlockPos waterPos : posToCheck) {
            if (!chunkGenerator.getBlockSource().get(waterPos).getFluidState().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static class Start extends MarginedStructureStart<DefaultFeatureConfig> {
        public Start(StructureFeature<DefaultFeatureConfig> s, ChunkPos c, int i, long l) {
            super(s, c, i, l);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos pos, Biome biome, DefaultFeatureConfig ruinedPortalFeatureConfig, HeightLimitView heightLimitView) {
            BlockPos blockPos = new BlockPos(pos.getStartX(), 90, pos.getStartZ());
            this.addPiece(new MoaiStructure.Piece(structureManager, blockPos, MoStructures.id("empty")));
        }
    }

    public static class Piece extends SimpleStructurePiece {

        public Piece(StructureManager structureManager, BlockPos blockPos, Identifier identifier) {
            super(MoStructures.MOAI_TYPE, 0, structureManager, identifier, identifier.toString(), new StructurePlacementData(), blockPos);
        }

        public Piece(ServerWorld world, NbtCompound nbt) {
            super(MoStructures.MOAI_TYPE, nbt, world, (id) -> new StructurePlacementData());
        }

        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos pos) {
            FeatureHelper.placeStructure(VILLAGER_MOAI, pos.add(0, -3, 0), world, random);
            return true;
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, Random random, BlockBox boundingBox) {

        }
    }
}

