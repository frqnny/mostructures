package io.github.frqnny.mostructures.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.frqnny.mostructures.init.Structures;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryElementCodec;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.gen.chunk.placement.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModStructurePlacement extends RandomSpreadStructurePlacement {
    public static final MapCodec<ModStructurePlacement> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Vec3i.createOffsetCodec(16).optionalFieldOf("locate_offset", Vec3i.ZERO).forGetter(ModStructurePlacement::getLocateOffset),
                    FrequencyReductionMethod.CODEC.optionalFieldOf("frequency_reduction_method", FrequencyReductionMethod.DEFAULT).forGetter(ModStructurePlacement::getFrequencyReductionMethod),
                    Codec.floatRange(0.0f, 1.0f).optionalFieldOf("frequency", 1.0f).forGetter(ModStructurePlacement::getFrequency),
                    Codecs.NONNEGATIVE_INT.fieldOf("salt").forGetter(ModStructurePlacement::getSalt),
                    RegistryElementCodec.of(RegistryKeys.STRUCTURE_SET, StructureSet.CODEC, false).listOf().fieldOf("structure_set_to_avoid").orElse(new ArrayList<>(20)).forGetter(config -> config.structureSetToAvoid),
                    Codec.intRange(0, 4096).fieldOf("spacing").forGetter(ModStructurePlacement::getSpacing),
                    Codec.intRange(0, 4096).fieldOf("separation").forGetter(ModStructurePlacement::getSeparation),
                    SpreadType.CODEC.optionalFieldOf("spread_type", SpreadType.LINEAR).forGetter(ModStructurePlacement::getSpreadType))
            .apply(instance, ModStructurePlacement::new));
    public final List<RegistryEntry<StructureSet>> structureSetToAvoid;
    private final SpreadType spreadType;
    private int spacing;
    private int separation;
    private boolean activated = true;

    public ModStructurePlacement(Vec3i locateOffset, StructurePlacement.FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, List<RegistryEntry<StructureSet>> structureSetToAvoid, int spacing, int separation, SpreadType spreadType) {
        super(locateOffset, frequencyReductionMethod, frequency, salt, Optional.empty(), spacing, separation, spreadType);
        this.spacing = spacing;
        this.separation = separation;
        this.spreadType = spreadType;
        this.structureSetToAvoid = structureSetToAvoid;
    }

    public static boolean isStructureSetNearby(StructurePlacementCalculator calculator, RegistryEntry<StructureSet> structureSetEntry, int centerChunkX, int centerChunkZ, int chunkCount) {

        for (int i = centerChunkX - chunkCount; i <= centerChunkX + chunkCount; ++i) {
            for (int j = centerChunkZ - chunkCount; j <= centerChunkZ + chunkCount; ++j) {
                var placement = structureSetEntry.value().placement();
                //avoid looping exclusion checks with our structure placement
                //if vanilla's, simply use their method
                if (placement instanceof ModStructurePlacement structurePlacement) {
                    if (structurePlacement.shouldGenerateNoExclusionCheck(calculator, i, j)) {
                        return true;
                    }
                } else {
                    if (placement.shouldGenerate(calculator, i, j)) {
                        return true;
                    }
                }

            }
        }

        return false;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public int getSpacing() {
        return this.spacing;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public int getSeparation() {
        return this.separation;
    }

    public void setSeparation(int separation) {
        this.separation = separation;
    }

    @Override
    public SpreadType getSpreadType() {
        return this.spreadType;
    }

    @Override
    public boolean shouldGenerate(StructurePlacementCalculator calculator, int chunkX, int chunkZ) {
        return this.activated && super.shouldGenerate(calculator, chunkX, chunkZ) && exclusionZoneCheck(calculator, chunkX, chunkZ);
    }

    public boolean shouldGenerateNoExclusionCheck(StructurePlacementCalculator calculator, int chunkX, int chunkZ) {
        return this.activated && super.shouldGenerate(calculator, chunkX, chunkZ);
    }

    public boolean exclusionZoneCheck(StructurePlacementCalculator calculator, int chunkX, int chunkZ) {
        if (!structureSetToAvoid.isEmpty()) {
            for (RegistryEntry<StructureSet> entry : structureSetToAvoid) {
                if (isStructureSetNearby(calculator, entry, chunkX, chunkZ, 4)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public StructurePlacementType<?> getType() {
        return Structures.TYPE.get();
    }
}
