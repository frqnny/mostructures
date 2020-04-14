package io.github.franiscoder.mostructures.common.feature;

import com.mojang.datafixers.Dynamic;
import io.github.franiscoder.mostructures.common.generator.HouseGenerator;
import io.github.franiscoder.mostructures.common.init.ModStructures;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.AbstractTempleFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
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
        return "MoStructures_House";
    }

    @Override
    public int getRadius() {
        return 16;
    }

    @Override
    public boolean shouldStartAt(BiomeAccess biomeAccess, ChunkGenerator<?> chunkGenerator, Random random, int chunkX, int chunkZ, Biome biome) {
        return true;
    }

    public static class Start extends StructureStart {

        public Start(StructureFeature<?> feature, int chunkX, int chunkZ, BlockBox box, int references, long l) {
            super(feature, chunkX, chunkZ, box, references, l);
        }

        @Override
        public void initialize(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int x, int z, Biome biome) {
            //needs to get its own feature
            DefaultFeatureConfig defaultFeatureConfig = chunkGenerator.getStructureConfig(biome, ModStructures.HOUSE);
            BlockRotation blockRotation = BlockRotation.values()[this.random.nextInt(BlockRotation.values().length)];
            BlockPos pos = new BlockPos(x * 16, chunkGenerator.getHeightOnGround(x*16+15, z*16+15, Heightmap.Type.WORLD_SURFACE_WG), z * 16);
            HouseGenerator.addPieces(structureManager, pos, blockRotation, this.children, this.random, defaultFeatureConfig);
            this.setBoundingBoxFromChildren();
        }
    }
}
