package io.github.frqnny.mostructures.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.structure.StructureSet;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.ArrayList;
import java.util.List;

public class ConfigMS extends StructurePoolFeatureConfig {
    public static final Codec<ConfigMS> CODEC = RecordCodecBuilder.create(
            (instance) ->
                    instance
                            .group(
                                    StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter(ConfigMS::getStartPool),
                                    RegistryKey.createCodec(Registry.STRUCTURE_SET_KEY).listOf().fieldOf("structure_set_to_avoid").orElse(new ArrayList<>(20)).forGetter(config -> config.structureSetToAvoid),
                                    Codec.intRange(0, 7).fieldOf("size").forGetter(ConfigMS::getSize),
                                    Codec.intRange(-1,100).fieldOf("heightRange").orElse(-1).forGetter(config -> config.heightRange)
                            )
                            .apply(instance, ConfigMS::new)
    );
    public final List<RegistryKey<StructureSet>> structureSetToAvoid;
    public final int heightRange;


    public ConfigMS(RegistryEntry<StructurePool> startPool, List<RegistryKey<StructureSet>> structureSetToAvoid, int size, int heightRange) {
        super(startPool, size);
        this.structureSetToAvoid = structureSetToAvoid;
        this.heightRange = heightRange;
    }

}