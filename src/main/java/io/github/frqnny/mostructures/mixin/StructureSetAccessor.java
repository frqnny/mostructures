package io.github.frqnny.mostructures.mixin;

import net.minecraft.structure.StructureSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(StructureSet.class)
public interface StructureSetAccessor {

    @Mutable
    @Accessor(value = "structures")
    void setStructures(List<StructureSet.WeightedEntry> structures);
}
