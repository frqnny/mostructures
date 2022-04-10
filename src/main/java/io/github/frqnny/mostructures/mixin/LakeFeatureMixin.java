package io.github.frqnny.mostructures.mixin;

import io.github.frqnny.mostructures.init.Structures;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
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
    private void mostructures_lakeFix(FeatureContext<DefaultFeatureConfig> context, CallbackInfoReturnable<Boolean> info) {
        ChunkSectionPos chunkSectionPos = ChunkSectionPos.from(context.getOrigin());
        Chunk chunk = context.getWorld().getChunk(context.getOrigin());
        Registry<ConfiguredStructureFeature<?,?>> registry = context.getWorld().getRegistryManager().get(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY);
        StructureAccessor structureAccessor = ((ChunkRegionAccessor)context.getWorld()).getStructureAccessor();

        for (RegistryEntry<ConfiguredStructureFeature<?,?>> entry: registry.getOrCreateEntryList(Structures.NO_LAKES)) {
            StructureStart startForFeature = structureAccessor.getStructureStart(chunkSectionPos, entry.value(), chunk);
            if (startForFeature != null && startForFeature.hasChildren()) {
                info.setReturnValue(false);
                return;
            }
        }
    }
}