package io.github.frqnny.mostructures.feature.entity;

import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.feature.config.ArmorStandFeatureConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EulerAngle;
import net.minecraft.world.StructureWorldAccess;

import java.util.Random;

public class ArmorStandFeature extends EntitySpawnFeature<ArmorStandFeatureConfig> {
    public static final Identifier ID = MoStructures.id("armor_stand");

    public ArmorStandFeature() {
        super(ArmorStandFeatureConfig.CODEC);
    }

    @Override
    public Entity getEntity(StructureWorldAccess world, BlockPos pos, ArmorStandFeatureConfig config) {
        //TODO get the angle shit actually working
        ArmorStandEntity entity = new ArmorStandEntity(world.toServerWorld(), pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F);
        entity.setHeadRotation(new EulerAngle(config.headx, config.heady, config.headz));
        entity.setBodyRotation(new EulerAngle(config.bodyx, config.bodyy, config.bodyz));
        ItemStack stack;
        Random random = world.toServerWorld().random;
        if (random.nextBoolean()) {
            if (random.nextBoolean()) {
                stack = new ItemStack(Items.DIAMOND_HELMET);
                entity.equip(103, stack);
            }
            stack = new ItemStack(Items.GOLDEN_BOOTS);
            entity.equip(100, stack);

        } else {
            if (random.nextBoolean()) {
                stack = new ItemStack(Items.IRON_CHESTPLATE);
                entity.equip(102, stack);
            }
            stack = new ItemStack(Items.IRON_LEGGINGS);
            entity.equip(101, stack);
        }
        entity.updateTrackedPosition(pos.getX(), pos.getY(), pos.getZ());
        entity.refreshPositionAndAngles(entity.getX(), entity.getY(), entity.getZ(), config.bodyy, 0.0F);

        return entity;
    }
}
