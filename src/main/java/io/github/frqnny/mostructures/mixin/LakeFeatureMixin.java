package io.github.frqnny.mostructures.mixin;

import io.github.frqnny.mostructures.util.FeatureHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.LakeFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("unused")
@Mixin(LakeFeature.class)
public class LakeFeatureMixin {

    @Inject(at = @At("HEAD"), method = "generate", cancellable = true)
    public void fixStructures(FeatureContext<DefaultFeatureConfig> context, CallbackInfoReturnable<Boolean> info) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();

        info.setReturnValue(FeatureHelper.checkChunksForStructures(world, pos));
    }
}