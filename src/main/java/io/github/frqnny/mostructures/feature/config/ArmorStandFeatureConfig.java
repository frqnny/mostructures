package io.github.frqnny.mostructures.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;

public class ArmorStandFeatureConfig implements FeatureConfig {
    public static final Codec<ArmorStandFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.FLOAT.fieldOf("headx").forGetter(armorStandFeatureConfig -> armorStandFeatureConfig.headx),
            Codec.FLOAT.fieldOf("heady").forGetter(armorStandFeatureConfig -> armorStandFeatureConfig.heady),
            Codec.FLOAT.fieldOf("headz").forGetter(armorStandFeatureConfig -> armorStandFeatureConfig.headz),
            Codec.FLOAT.fieldOf("bodyx").forGetter(armorStandFeatureConfig -> armorStandFeatureConfig.bodyx),
            Codec.FLOAT.fieldOf("bodyy").forGetter(armorStandFeatureConfig -> armorStandFeatureConfig.bodyy),
            Codec.FLOAT.fieldOf("bodyz").forGetter(armorStandFeatureConfig -> armorStandFeatureConfig.bodyz))
            .apply(instance, ArmorStandFeatureConfig::new));

    public float headx;
    public float heady;
    public float headz;
    public float bodyx;
    public float bodyy;
    public float bodyz;


    public ArmorStandFeatureConfig(float headx, float heady, float headz, float bodyx, float bodyy, float bodyz) {
        this.headx = headx;
        this.heady = heady;
        this.headz = headz;
        this.bodyx = bodyx;
        this.bodyy = bodyy;
        this.bodyz = bodyz;
    }
}
