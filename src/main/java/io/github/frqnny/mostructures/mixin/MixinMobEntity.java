package io.github.frqnny.mostructures.mixin;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public class MixinMobEntity {

    @Inject(at = @At("TAIL"), method = "initialize", cancellable = true)
    public void rabbitPlsWork(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityTag, CallbackInfoReturnable info) {

        if (((MobEntity) (Object) this) instanceof RabbitEntity && entityTag != null && entityTag.contains("RabbitType")) {
            ((RabbitEntity) (Object) this).setRabbitType(entityTag.getInt("RabbitType"));
        }
    }
}
