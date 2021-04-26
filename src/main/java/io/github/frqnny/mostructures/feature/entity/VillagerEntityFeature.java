package io.github.frqnny.mostructures.feature.entity;

import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.VillagerType;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class VillagerEntityFeature extends EntitySpawnFeature<DefaultFeatureConfig> {
    public static final Identifier ID = MoStructures.id("villager_entity");

    public VillagerEntityFeature() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public Entity getEntity(StructureWorldAccess world, BlockPos pos, DefaultFeatureConfig config) {

        VillagerEntity villager = new VillagerEntity(EntityType.VILLAGER, world.toServerWorld(), VillagerType.forBiome(world.getBiomeKey(pos)));
        villager.updateTrackedPosition(pos.getX(), pos.getY(), pos.getZ());
        return villager;
    }
}
