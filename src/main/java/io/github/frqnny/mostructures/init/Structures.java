package io.github.frqnny.mostructures.init;

import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.structure.ModStructure;
import io.github.frqnny.mostructures.structure.ModStructurePlacement;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

public class Structures {
    public static final StructureType<ModStructure> GENERIC = () -> ModStructure.CODEC;
    public static final TagKey<Structure> NO_LAKES = TagKey.of(Registry.STRUCTURE_KEY, MoStructures.id("no_lakes"));
    public static final StructurePlacementType<ModStructurePlacement> TYPE = () -> ModStructurePlacement.CODEC;

    public static void init() {
        Registry.register(Registry.STRUCTURE_TYPE, MoStructures.id("generic"), GENERIC);
        Registry.register(Registry.STRUCTURE_PLACEMENT, MoStructures.id("type"), TYPE);
    }
}
