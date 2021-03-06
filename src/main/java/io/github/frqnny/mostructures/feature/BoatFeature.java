package io.github.frqnny.mostructures.feature;

import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.util.FeatureHelper;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class BoatFeature extends Feature<DefaultFeatureConfig> {
    public static final Identifier BOAT1 = MoStructures.id("boat/boat1");

    public static final Identifier ID = MoStructures.id("boat");

    public BoatFeature() {
        super(DefaultFeatureConfig.CODEC);
    }


    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        BlockPos newPos = context.getWorld().getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, context.getOrigin());
        FeatureHelper.placeStructureWithNewPos(BOAT1, context, newPos);
        return true;
    }
}