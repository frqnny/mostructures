package io.github.franiscoder.mostructures.common.feature;


import com.mojang.datafixers.Dynamic;
import io.github.franiscoder.mostructures.MoStructures;
import io.github.franiscoder.mostructures.common.generator.HouseGenerator;
import io.github.franiscoder.mostructures.common.init.ModStructures;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.AbstractTempleFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Random;
import java.util.function.Function;

public class HouseStructureFeature extends AbstractTempleFeature<DefaultFeatureConfig> {
    public HouseStructureFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> c) {
        super(c);
    }

    @Override
    protected int getSeedModifier() {
        return 48945891;
    }

    @Override
    public StructureStartFactory getStructureStartFactory() {
        return HouseStructureFeature.Start::new;
    }

    @Override
    public String getName() {
        return MoStructures.MODID+":MoStructures_House";
    }

    @Override
    public int getRadius() {
        return 16;
    }

    protected ChunkPos getStart(ChunkGenerator<?> chunkGenerator, Random random, int startXChunk, int startZChunk, int currentXChunk, int currentZChunk) {
        int maxSeperation = 20;
        int minSeperation = 10;
        int k = startXChunk + maxSeperation * currentXChunk;
        int p = startZChunk + maxSeperation * currentZChunk;

        int q = k < 0 ? k - maxSeperation + 1 : k;
        int r = p < 0 ? p - maxSeperation + 1 : p;
        int validXChunk = q / maxSeperation;
        int validZChunk = r / maxSeperation;

        ((ChunkRandom)random).setRegionSeed(chunkGenerator.getSeed(), validXChunk, validZChunk, 48945891);
        validXChunk *= maxSeperation;
        validZChunk *= maxSeperation;
        validXChunk += random.nextInt(maxSeperation - minSeperation);
        validZChunk += random.nextInt(maxSeperation - minSeperation);
        return new ChunkPos(validXChunk, validZChunk);
    }

    public boolean shouldStartAt(BiomeAccess biomeAccess, ChunkGenerator<?> chunkGenerator, Random random, int chunkX, int chunkZ, Biome biome) {
        ChunkPos chunkPos = this.getStart(chunkGenerator, random, chunkX, chunkZ, 0, 0);
        return (chunkX == chunkPos.x && chunkZ == chunkPos.z) && chunkGenerator.hasStructure(biome, this);
    }

    public static class Start extends StructureStart {

        public Start(StructureFeature<?> feature, int chunkX, int chunkZ, BlockBox box, int references, long l) {
            super(feature, chunkX, chunkZ, box, references, l);
        }

        @Override
        public void initialize(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int x, int z, Biome biome) {
            //needs to get its own feature
            System.out.println("initialized got called!");
            DefaultFeatureConfig defaultFeatureConfig = chunkGenerator.getStructureConfig(biome, ModStructures.HOUSE);
            BlockRotation blockRotation = BlockRotation.values()[this.random.nextInt(BlockRotation.values().length)];
            BlockPos pos = new BlockPos(x * 16, 90, z * 16);
            HouseGenerator.addPieces(structureManager, pos, blockRotation, this.children, this.random, defaultFeatureConfig);
            this.setBoundingBoxFromChildren();
        }
    }
}
