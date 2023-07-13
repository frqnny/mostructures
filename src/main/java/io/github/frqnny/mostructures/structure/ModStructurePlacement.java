package io.github.frqnny.mostructures.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.frqnny.mostructures.init.Structures;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryElementCodec;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.CheckedRandom;
import net.minecraft.util.math.random.ChunkRandom;
import net.minecraft.world.gen.chunk.placement.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModStructurePlacement extends RandomSpreadStructurePlacement {
    public static final Codec<ModStructurePlacement> CODEC = RecordCodecBuilder.create(instance -> instance.group(
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
    private boolean activated;

    public ModStructurePlacement(Vec3i locateOffset, StructurePlacement.FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, List<RegistryEntry<StructureSet>> structureSetToAvoid, int spacing, int separation, SpreadType spreadType) {
        super(locateOffset, frequencyReductionMethod, frequency, salt, Optional.empty(), spacing, separation, spreadType);
        this.spacing = spacing;
        this.separation = separation;
        this.spreadType = spreadType;
        this.structureSetToAvoid = structureSetToAvoid;
    }

    public static boolean shouldExclude(StructurePlacementCalculator calculator, RegistryEntry<StructureSet> structureSetEntry, int centerChunkX, int centerChunkZ, int chunkCount) {
        if (structureSetEntry.value().placement() instanceof ModStructurePlacement structurePlacement) {
            for (int i = centerChunkX - chunkCount; i <= centerChunkX + chunkCount; ++i) {
                for (int j = centerChunkZ - chunkCount; j <= centerChunkZ + chunkCount; ++j) {
                    if (!structurePlacement.shouldGenerateNoExclusionCheck(calculator, i, j)) continue;
                    return true;
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

    public ChunkPos getStartChunk(long seed, int chunkX, int chunkZ) {
        int regionX = Math.floorDiv(chunkX, this.spacing);
        int regionZ = Math.floorDiv(chunkZ, this.spacing);
        ChunkRandom chunkRandom = new ChunkRandom(new CheckedRandom(0L));
        chunkRandom.setRegionSeed(seed, regionX, regionZ, this.getSalt());
        int distance = this.spacing - this.separation;
        int nextXDistance = this.spreadType.get(chunkRandom, distance);
        int nextZDistance = this.spreadType.get(chunkRandom, distance);
        return new ChunkPos(regionX * this.spacing + nextXDistance, regionZ * this.spacing + nextZDistance);
    }

    @Override
    protected boolean isStartChunk(StructurePlacementCalculator calculator, int chunkX, int chunkZ) {
        ChunkPos chunkPos = this.getStartChunk(calculator.getStructureSeed(), chunkX, chunkZ);
        return chunkPos.x == chunkX && chunkPos.z == chunkZ;
    }

    @Override
    public boolean shouldGenerate(StructurePlacementCalculator calculator, int chunkX, int chunkZ) {
        if (!activated) {
            return false;
        }

        if (!this.isStartChunk(calculator, chunkX, chunkZ)) {
            return false;
        }
        if (this.getFrequency() < 1.0f && !this.getFrequencyReductionMethod().shouldGenerate(calculator.getStructureSeed(), this.getSalt(), chunkX, chunkZ, this.getFrequency())) {
            return false;
        }

        if (!structureSetToAvoid.isEmpty()) {
            for (RegistryEntry<StructureSet> entry : structureSetToAvoid) {
                if (shouldExclude(calculator, entry, chunkX, chunkZ, 3)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean shouldGenerateNoExclusionCheck(StructurePlacementCalculator calculator, int chunkX, int chunkZ) {
        if (!activated) {
            return false;
        }
        if (!this.isStartChunk(calculator, chunkX, chunkZ)) {
            return false;
        }
        return !(this.getFrequency() < 1.0f) || this.getFrequencyReductionMethod().shouldGenerate(calculator.getStructureSeed(), this.getSalt(), chunkX, chunkZ, this.getFrequency());
    }

    @Override
    public StructurePlacementType<?> getType() {
        return Structures.TYPE;
    }
}
