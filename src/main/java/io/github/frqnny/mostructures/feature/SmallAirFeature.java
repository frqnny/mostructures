package io.github.frqnny.mostructures.feature;

import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.util.FeatureHelper;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class SmallAirFeature extends Feature<DefaultFeatureConfig> {
    public static final Identifier ID = MoStructures.id("airballoon");
    private static final Identifier AIR_BALLOON_1 = MoStructures.id("air/airballoon1");
    private static final Identifier AIR_BALLOON_2 = MoStructures.id("air/airballoon2");
    private static final Identifier AIR_BALLOON_3 = MoStructures.id("air/airballoon3");
    private static final Identifier AIR_BALLOON_4 = MoStructures.id("air/airballoon4");
    private static final Identifier AIR_BALLOON_5 = MoStructures.id("air/airballoon5");
    public static final Identifier[] AIR_FEATURES = new Identifier[]{AIR_BALLOON_1, AIR_BALLOON_2, AIR_BALLOON_3, AIR_BALLOON_4, AIR_BALLOON_5};


    public SmallAirFeature() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();
        Random random = context.getRandom();

        int i = random.nextInt(AIR_FEATURES.length);
        int yToAdd = Math.max(random.nextInt(100), 45);
        BlockPos finalPos = pos.add(0, yToAdd, 0);
        FeatureHelper.placeStructure(AIR_FEATURES[i], finalPos, world, random);
        return true;
    }
}