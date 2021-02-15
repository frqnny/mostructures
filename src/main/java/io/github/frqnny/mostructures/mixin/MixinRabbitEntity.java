package io.github.frqnny.mostructures.mixin;

import io.github.frqnny.mostructures.util.ItemHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RabbitEntity.class)
public abstract class MixinRabbitEntity extends LivingEntity {

    protected MixinRabbitEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }


    public void onDeath(DamageSource source) {
        RabbitEntity thisEntity = ((RabbitEntity) (Object) this);
        if (thisEntity.getRabbitType() == 99) {
            ItemStack diamonds = new ItemStack(Items.DIAMOND);
            diamonds.setCount(8);
            ItemHelper.spawnStack(thisEntity.world, thisEntity.getX(), thisEntity.getY() + 0.5, thisEntity.getZ(), diamonds);

            ItemStack emeralds = new ItemStack(Items.EMERALD);
            emeralds.setCount(10);
            ItemHelper.spawnStack(thisEntity.world, thisEntity.getX(), thisEntity.getY() + 0.5, thisEntity.getZ(), emeralds);

            ItemStack ironIngots = new ItemStack(Items.IRON_INGOT);
            ironIngots.setCount(18);
            ItemHelper.spawnStack(thisEntity.world, thisEntity.getX(), thisEntity.getY() + 0.5, thisEntity.getZ(), ironIngots);


        }
    }
}
