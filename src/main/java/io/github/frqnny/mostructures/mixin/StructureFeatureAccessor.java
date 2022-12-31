package io.github.frqnny.mostructures.mixin;

import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(Structure.class)
public interface StructureFeatureAccessor {

    @Accessor(value = "STRUCTURE_TO_GENERATION_STEP")
    static Map<Structure<?>, GenerationStep.Feature> getStep() {
        throw new UnsupportedOperationException();
    }
}
