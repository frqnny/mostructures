package io.github.frqnny.mostructures.mixin;

import io.github.frqnny.mostructures.ConfiguredFeatures;
import io.github.frqnny.mostructures.MoStructures;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(NoiseChunkGenerator.class)
public class MixinNoiseChunkGenerator {

    @Inject(at = @At("HEAD"), method = "getEntitySpawnList", cancellable = true)
    public void injectSpawnList(Biome biome, StructureAccessor accessor, SpawnGroup group, BlockPos pos, CallbackInfoReturnable<List<SpawnSettings.SpawnEntry>> info) {
        if (accessor.getStructureAt(pos, false, ConfiguredFeatures.PILLAGER_FACTORY.feature).hasChildren()) {
            if (group == SpawnGroup.MONSTER) {
                info.setReturnValue(StructureFeature.PILLAGER_OUTPOST.getMonsterSpawns());
            }
        }
        if (accessor.getStructureAt(pos, false, ConfiguredFeatures.ICE_TOWER.feature).hasChildren()) {
            if (group == SpawnGroup.MONSTER) {
                info.setReturnValue(MoStructures.ICE_TOWER.getMonsterSpawns());
            }
        }
    }
}
