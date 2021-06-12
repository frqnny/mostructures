package io.github.frqnny.mostructures.util;

import com.google.common.collect.ImmutableList;
import io.github.frqnny.mostructures.MoStructures;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class RegUtils {

    public static ConfiguredStructureFeature<StructurePoolFeatureConfig, ?> config(StructureFeature<StructurePoolFeatureConfig> s, StructurePool p) {
        return s.configure(new StructurePoolFeatureConfig(() -> p, 2));
    }

    public static void addToBiome(Identifier id, Predicate<BiomeSelectionContext> selectorPredicate, Consumer<BiomeModificationContext> biomeAdditionConsumer) {
        BiomeModifications.create(id).add(ModificationPhase.ADDITIONS, selectorPredicate, biomeAdditionConsumer);
    }

    public static void addStructure(BiomeModificationContext context, ConfiguredStructureFeature<?, ?> feature) {
        context.getGenerationSettings().addBuiltInStructure(feature);
    }

    public static Predicate<BiomeSelectionContext> booleanToPredicate(boolean bol) {
        return (context) -> bol;
    }

    public static StructureProcessorList registerStructureProcessorList(String id, ImmutableList<StructureProcessor> processorList) {
        Identifier identifier = MoStructures.id(id);
        StructureProcessorList structureProcessorList = new StructureProcessorList(processorList);
        return BuiltinRegistries.add(BuiltinRegistries.STRUCTURE_PROCESSOR_LIST, identifier, structureProcessorList);
    }

    public static Predicate<BiomeSelectionContext> getNoHillsPredicate() {
        return (context) -> !context.getBiomeKey().getValue().getPath().contains("hill") && !context.getBiomeKey().getValue().getPath().contains("mountain");
    }
}
