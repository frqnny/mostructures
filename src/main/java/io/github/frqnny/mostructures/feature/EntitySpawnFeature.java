package io.github.frqnny.mostructures.feature;

import com.mojang.serialization.Codec;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public abstract class EntitySpawnFeature<FC extends FeatureConfig> extends Feature<FC> {

    public EntitySpawnFeature(Codec<FC> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<FC> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();
        FC config = context.getConfig();

        Entity entity = getEntity(world, pos, config);
        world.toServerWorld().spawnEntity(entity);
        return true;
    }

    public abstract Entity getEntity(StructureWorldAccess world, BlockPos pos, FC config);
}
