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
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Random;

public class AirBalloonStructure extends StructureFeature<DefaultFeatureConfig> {
    private static final Identifier AIR_BALLOON_1 = MoStructures.id("air/airballoon1");
    private static final Identifier AIR_BALLOON_2 = MoStructures.id("air/airballoon2");
    private static final Identifier AIR_BALLOON_3 = MoStructures.id("air/airballoon3");
    private static final Identifier AIR_BALLOON_4 = MoStructures.id("air/airballoon4");
    private static final Identifier AIR_BALLOON_5 = MoStructures.id("air/airballoon5");
    public static final Identifier[] AIR_FEATURES = new Identifier[]{AIR_BALLOON_1, AIR_BALLOON_2, AIR_BALLOON_3, AIR_BALLOON_4, AIR_BALLOON_5};


    public AirBalloonStructure() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return AirBalloonStructure.Start::new;
    }

    public static class Start extends MarginedStructureStart<DefaultFeatureConfig> {

        public Start(StructureFeature<DefaultFeatureConfig> structureFeature, ChunkPos chunkPos, int i, long l) {
            super(structureFeature, chunkPos, i, l);
        }

        @Override
        public void init(DynamicRegistryManager registryManager, ChunkGenerator chunkGenerator, StructureManager manager, ChunkPos pos, Biome biome, DefaultFeatureConfig config, HeightLimitView world) {
            BlockPos blockPos = new BlockPos(pos.getStartX(), 90, pos.getStartZ());
            this.addPiece(new AirBalloonStructure.Piece(manager, blockPos, MoStructures.id("empty")));
        }
    }


    public static class Piece extends SimpleStructurePiece {

        public Piece(StructureManager structureManager, BlockPos blockPos, Identifier identifier) {
            super(MoStructures.AIR_BALLOON_TYPE, 0, structureManager, identifier, identifier.toString(), new StructurePlacementData(), blockPos);
        }

        public Piece(ServerWorld world, NbtCompound nbt) {
            super(MoStructures.AIR_BALLOON_TYPE, nbt, world, (id) -> new StructurePlacementData());
        }

        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos pos) {
            int i = random.nextInt(AIR_FEATURES.length);
            int yToAdd = Math.max(random.nextInt(100), 45);
            BlockPos finalPos = pos.add(0, yToAdd, 0);
            FeatureHelper.placeStructure(AIR_FEATURES[i], finalPos, world, random);
            return true;
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, Random random, BlockBox boundingBox) {

        }
    }
}
