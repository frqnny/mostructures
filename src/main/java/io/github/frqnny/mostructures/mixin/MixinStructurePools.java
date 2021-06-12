package io.github.frqnny.mostructures.mixin;

import io.github.frqnny.mostructures.util.StructureUtils;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePools;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StructurePools.class)
public class MixinStructurePools {

    @Inject(at = @At("TAIL"), method = "initDefaultPools", cancellable = true)
    private static void initModPools(CallbackInfoReturnable<StructurePool> info) {
        StructureUtils.initPools();
    }
}
