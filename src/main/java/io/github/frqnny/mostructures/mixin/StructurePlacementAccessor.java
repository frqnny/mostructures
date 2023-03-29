package io.github.frqnny.mostructures.mixin;

import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StructurePlacement.class)
public interface StructurePlacementAccessor {

    @Mutable
    @Accessor(value = "salt")
    void setSalt(int salt);
}
