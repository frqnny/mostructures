package io.github.frqnny.mostructures.mixin;

import io.github.frqnny.mostructures.util.FeatureHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.LakeFeature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@SuppressWarnings("unused")
@Mixin(LakeFeature.class)
public class LakeFeatureMixin {

    @Inject(at = @At("HEAD"), method = "generate", cancellable = true)
    public void fixStructures(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, SingleStateFeatureConfig featureConfig, CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(FeatureHelper.checkChunksForStructures(world, blockPos));
    }
}