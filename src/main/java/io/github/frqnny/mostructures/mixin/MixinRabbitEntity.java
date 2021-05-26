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

@Mixin(RabbitEntity.class)
public abstract class MixinRabbitEntity extends LivingEntity {

    protected MixinRabbitEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }


    public void onDeath(DamageSource source) {
        RabbitEntity thisEntity = ((RabbitEntity) (Object) this);
        if (thisEntity.getRabbitType() == 99) {
            ItemStack diamonds = new ItemStack(Items.DIAMOND);
            diamonds.setCount(Math.max(8, random.nextInt(10)));
            ItemHelper.spawnStack(thisEntity.world, thisEntity.getX(), thisEntity.getY() + 0.5, thisEntity.getZ(), diamonds);

            ItemStack emeralds = new ItemStack(Items.EMERALD);
            emeralds.setCount(Math.max(10, random.nextInt(12)));
            ItemHelper.spawnStack(thisEntity.world, thisEntity.getX(), thisEntity.getY() + 0.5, thisEntity.getZ(), emeralds);

            ItemStack ironIngots = new ItemStack(Items.IRON_INGOT);
            ironIngots.setCount(Math.max(18, random.nextInt(20)));
            ItemHelper.spawnStack(thisEntity.world, thisEntity.getX(), thisEntity.getY() + 0.5, thisEntity.getZ(), ironIngots);
        }

        super.onDeath(source);
    }
}
