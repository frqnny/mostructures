package io.github.frqnny.mostructures.mixin;

import io.github.frqnny.mostructures.ConfiguredFeatures;
import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.LakeFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
@Mixin(LakeFeature.class)
public class LakeFeatureMixin {

    @Inject(at = @At("HEAD"), method = "generate", cancellable = true)
    public void fixStructures(FeatureContext<DefaultFeatureConfig> context, CallbackInfoReturnable<Boolean> info) {
        Set<Chunk> chunksToScan = new HashSet<>(9);
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();
        chunksToScan.add(world.getChunk(pos));
        chunksToScan.add(world.getChunk(pos.add(16, 0, 16)));
        chunksToScan.add(world.getChunk(pos.add(-16, 0, -16)));
        chunksToScan.add(world.getChunk(pos.add(0, 0, 16)));
        chunksToScan.add(world.getChunk(pos.add(16, 0, 0)));
        chunksToScan.add(world.getChunk(pos.add(-16, 0, 0)));
        chunksToScan.add(world.getChunk(pos.add(0, 0, -16)));
        chunksToScan.add(world.getChunk(pos.add(16, 0, -16)));
        chunksToScan.add(world.getChunk(pos.add(-16, 0, 16)));
        for (Chunk chunk : chunksToScan) {
            if (
                    !chunk.getStructureReferences(ConfiguredFeatures.BARN_HOUSE.feature).isEmpty()
                            || !chunk.getStructureReferences(ConfiguredFeatures.JUNGLE_PYRAMID.feature).isEmpty()
                            || !chunk.getStructureReferences(ConfiguredFeatures.VILLAGER_TOWER.feature).isEmpty()
                            || !chunk.getStructureReferences(MoStructures.ABANDONED_CHURCH).isEmpty()
                            || !chunk.getStructureReferences(ConfiguredFeatures.VILLAGER_MARKET.feature).isEmpty()
                            || !chunk.getStructureReferences(ConfiguredFeatures.PILLAGER_FACTORY.feature).isEmpty()
                            || !chunk.getStructureReferences(ConfiguredFeatures.TAVERN.feature).isEmpty()
                            || !chunk.getStructureReferences(ConfiguredFeatures.KILLER_BUNNY_CASTLE.feature).isEmpty()
                            || !chunk.getStructureReferences(ConfiguredFeatures.VILLAGER_BAZAAR.feature).isEmpty()
                            || !chunk.getStructureReferences(ConfiguredFeatures.LIGHTHOUSE.feature).isEmpty()
                            || !chunk.getStructureReferences(ConfiguredFeatures.ICE_TOWER.feature).isEmpty()

            ) {
                info.setReturnValue(false);
            }
        }

    }
}