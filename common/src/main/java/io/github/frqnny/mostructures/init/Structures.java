package io.github.frqnny.mostructures.init;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.structure.ModStructure;
import io.github.frqnny.mostructures.structure.ModStructurePlacement;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

public class Structures {
    public static final TagKey<Structure> NO_LAKES = TagKey.of(RegistryKeys.STRUCTURE, MoStructures.id("no_lakes"));

    public static RegistrySupplier<StructureType<ModStructure>> GENERIC;
    public static RegistrySupplier<StructurePlacementType<ModStructurePlacement>> TYPE;

    public static void init() {
        var structureTypes = MoStructures.MANAGER.get().get(RegistryKeys.STRUCTURE_TYPE);
        StructureType<ModStructure> generic = () -> ModStructure.CODEC;
        GENERIC = structureTypes.register(MoStructures.id("generic"), () -> generic);

        var structurePlacements = MoStructures.MANAGER.get().get(RegistryKeys.STRUCTURE_PLACEMENT);
        StructurePlacementType<ModStructurePlacement> type = () -> ModStructurePlacement.CODEC;
        TYPE = structurePlacements.register(MoStructures.id("type"), () -> type);
    }
}
