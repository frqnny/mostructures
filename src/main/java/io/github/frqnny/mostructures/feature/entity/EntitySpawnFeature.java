package io.github.frqnny.mostructures.feature.entity;

import com.mojang.serialization.Codec;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.Random;

public abstract class EntitySpawnFeature<FC extends FeatureConfig> extends Feature<FC> {

    public EntitySpawnFeature(Codec<FC> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, FC config) {
        Entity entity = getEntity(world, pos, config);
        world.toServerWorld().loadEntity(entity);
        return true;
    }

    public abstract Entity getEntity(StructureWorldAccess world, BlockPos pos, FC config);
}
