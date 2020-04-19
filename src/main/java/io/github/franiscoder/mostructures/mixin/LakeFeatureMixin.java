package io.github.franiscoder.mostructures.mixin;

import io.github.franiscoder.mostructures.MoStructures;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
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
    public void fixBarnHouse(IWorld world, StructureAccessor structureAccessor, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator, Random random, BlockPos blockPos, SingleStateFeatureConfig singleStateFeatureConfig, CallbackInfoReturnable<Boolean> info) {
        List<Chunk> chunksToScan = new ArrayList<>();
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
            if (!chunk.getStructureReferences(MoStructures.MODID + ":Barn_House").isEmpty()) {
                info.setReturnValue(false);
                break;
            }
        }
    }
}
