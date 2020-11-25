package io.github.frqnny.mostructures.mixin;

import io.github.frqnny.mostructures.ConfiguredFeatures;
import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.LakeFeature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("unused")
@Mixin(LakeFeature.class)
public class LakeFeatureMixin {

    @Inject(at = @At("HEAD"), method = "generate", cancellable = true)
    public void fixStructures(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, SingleStateFeatureConfig featureConfig, CallbackInfoReturnable<Boolean> info) {
        List<Chunk> chunksToScan = new ArrayList<>(9);
        chunksToScan.add(world.getChunk(blockPos));
        chunksToScan.add(world.getChunk(blockPos.add(16, 0, 16)));
        chunksToScan.add(world.getChunk(blockPos.add(-16, 0, -16)));
        chunksToScan.add(world.getChunk(blockPos.add(0, 0, 16)));
        chunksToScan.add(world.getChunk(blockPos.add(16, 0, 0)));
        chunksToScan.add(world.getChunk(blockPos.add(-16, 0, 0)));
        chunksToScan.add(world.getChunk(blockPos.add(0, 0, -16)));
        chunksToScan.add(world.getChunk(blockPos.add(16, 0, -16)));
        chunksToScan.add(world.getChunk(blockPos.add(-16, 0, 16)));
        for (Chunk chunk : chunksToScan) {
            if (
                    !chunk.getStructureReferences(ConfiguredFeatures.BARN_HOUSE.feature).isEmpty()
                            || !chunk.getStructureReferences(ConfiguredFeatures.JUNGLE_PYRAMID.feature).isEmpty()
                            || !chunk.getStructureReferences(ConfiguredFeatures.VILLAGER_TOWER.feature).isEmpty()
                            || !chunk.getStructureReferences(MoStructures.ABANDONED_CHURCH).isEmpty()
                            || !chunk.getStructureReferences(ConfiguredFeatures.VILLAGER_MARKET.feature).isEmpty()
                            || !chunk.getStructureReferences(ConfiguredFeatures.PILLAGER_FACTORY.feature).isEmpty()
            ) {
                info.setReturnValue(false);
                break;
            }
        }
    }
}