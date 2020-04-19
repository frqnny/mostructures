package io.github.franiscoder.mostructures.common.feature;

import com.google.common.collect.Lists;
import io.github.franiscoder.mostructures.MoStructures;
import io.github.franiscoder.mostructures.common.generator.BarnHouseGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.AbstractTempleFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.List;

public class BarnHouseFeature extends AbstractTempleFeature<DefaultFeatureConfig> {
    private static final List<Biome.SpawnEntry> MONSTER_SPAWNS = Lists.newArrayList(new Biome.SpawnEntry(EntityType.CREEPER, 1, 1, 1));

    public BarnHouseFeature() {
        super(DefaultFeatureConfig::deserialize);
    }

    @Override
    protected int getSeedModifier(ChunkGeneratorConfig chunkGeneratorConfig) {
        return 165755306;
    }

    @Override
    public List<Biome.SpawnEntry> getMonsterSpawns() {
        return MONSTER_SPAWNS;
    }

    @Override
    public StructureStartFactory getStructureStartFactory() {
        return BarnHouseFeature.Start::new;
    }

    @Override
    public String getName() {
        return MoStructures.MODID + ":Barn_House";
    }

    @Override
    public int getRadius() {
        return 3;
    }

    public static class Start extends StructureStart {

        public Start(StructureFeature<?> feature, int chunkX, int chunkZ, BlockBox box, int references, long seed) {
            super(feature, chunkX, chunkZ, box, references, seed);
        }

        @Override
        public void init(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int x, int z, Biome biome) {
            BarnHouseGenerator.addPieces(chunkGenerator, structureManager, new BlockPos(x * 16, 0, z * 16), children, random);
            this.setBoundingBoxFromChildren();
        }
    }
}
