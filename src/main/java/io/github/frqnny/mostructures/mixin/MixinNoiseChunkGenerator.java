package io.github.frqnny.mostructures.mixin;

import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.util.StructureHelper;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.collection.Pool;
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

@Mixin(NoiseChunkGenerator.class)
public class MixinNoiseChunkGenerator {

    @Inject(at = @At("HEAD"), method = "getEntitySpawnList", cancellable = true)
    public void injectSpawnList(Biome biome, StructureAccessor accessor, SpawnGroup group, BlockPos pos, CallbackInfoReturnable<Pool<SpawnSettings.SpawnEntry>> info) {
        if (group == SpawnGroup.MONSTER) {
            if (accessor.getStructureAt(pos, false, MoStructures.PILLAGER_FACTORY).hasChildren()) {
                info.setReturnValue(StructureFeature.PILLAGER_OUTPOST.getMonsterSpawns());
            } else if (accessor.getStructureAt(pos, false, MoStructures.ICE_TOWER).hasChildren()) {
                info.setReturnValue(StructureHelper.ICE_TOWER_SPAWNS);
            } else if (accessor.getStructureAt(pos, false, MoStructures.ABANDONED_CHURCH).hasChildren()) {
                info.setReturnValue(StructureHelper.ABANDONED_CHURCH_SPAWNS);
            }
        } else if (group == SpawnGroup.AMBIENT) {
            if (accessor.getStructureAt(pos, false, MoStructures.BARN_HOUSE).hasChildren()) {
                info.setReturnValue(StructureHelper.BARN_HOUSE_SPAWNS);
            }
        }


    }
}
