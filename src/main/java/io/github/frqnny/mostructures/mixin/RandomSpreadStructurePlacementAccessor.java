package io.github.frqnny.mostructures.mixin;

import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RandomSpreadStructurePlacement.class)
public interface RandomSpreadStructurePlacementAccessor {
    @Mutable
    @Accessor(value = "spacing")
    void setSpacing(int spacing);

    @Mutable
    @Accessor(value = "separation")
    void setSeparation(int separation);

}
