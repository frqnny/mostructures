package io.github.frqnny.mostructures.mixin;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RabbitEntity.class)
public class MixinRabbitEntity {

    @Inject(at = @At("HEAD"), method = "initialize", cancellable = true)
    public void rabbitPlsWork(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, CallbackInfoReturnable<EntityData> info) {
        if (((RabbitEntity) (Object) this).getVariant() == RabbitEntity.RabbitType.EVIL) {
            ((RabbitEntity) (Object) this).setVariant(RabbitEntity.RabbitType.EVIL);
        }
        info.setReturnValue(new RabbitEntity.RabbitData(RabbitEntity.RabbitType.EVIL));
    }
}
