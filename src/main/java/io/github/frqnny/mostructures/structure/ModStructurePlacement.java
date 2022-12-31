package io.github.frqnny.mostructures.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryElementCodec;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.CheckedRandom;
import net.minecraft.util.math.random.ChunkRandom;
import net.minecraft.world.gen.chunk.placement.SpreadType;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import net.minecraft.world.gen.chunk.placement.StructurePlacementCalculator;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;

import java.util.List;
import java.util.Optional;

public class ModStructurePlacement extends StructurePlacement {
    public static final Codec<ModStructurePlacement> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    Vec3i.createOffsetCodec(16).optionalFieldOf("locate_offset", Vec3i.ZERO).forGetter(ModStructurePlacement::getLocateOffset),
                    FrequencyReductionMethod.CODEC.optionalFieldOf("frequency_reduction_method", FrequencyReductionMethod.DEFAULT).forGetter(ModStructurePlacement::getFrequencyReductionMethod),
                    Codec.floatRange(0.0f, 1.0f).optionalFieldOf("frequency", 1.0f).forGetter(ModStructurePlacement::getFrequency),
                    Codecs.NONNEGATIVE_INT.fieldOf("salt").forGetter(ModStructurePlacement::getSalt),
                    ModExclusionZone.CODEC.listOf().optionalFieldOf("exclusion_zone").forGetter(placement -> placement.exclusionZones),
                    Codec.intRange(0, 4096).fieldOf("spacing").forGetter(ModStructurePlacement::getSpacing),
                    Codec.intRange(0, 4096).fieldOf("separation").forGetter(ModStructurePlacement::getSeparation),
                    SpreadType.CODEC.optionalFieldOf("spread_type", SpreadType.LINEAR).forGetter(ModStructurePlacement::getSpreadType))
            .apply(instance, ModStructurePlacement::new));
    private final Optional<List<ModExclusionZone>> exclusionZones;
    private final int spacing;
    private final int separation;
    private final SpreadType spreadType;

    public ModStructurePlacement(Vec3i locateOffset, StructurePlacement.FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, Optional<List<ModExclusionZone>> exclusionZones, int spacing, int separation, SpreadType spreadType) {
        super(locateOffset, frequencyReductionMethod, frequency, salt, Optional.empty());
        this.spacing = spacing;
        this.separation = separation;
        this.spreadType = spreadType;
        this.exclusionZones = exclusionZones;
    }

    public ModStructurePlacement(int spacing, int separation, SpreadType spreadType, int salt) {
        this(Vec3i.ZERO, StructurePlacement.FrequencyReductionMethod.DEFAULT, 1.0f, salt, Optional.empty(), spacing, separation, spreadType);
    }

    public int getSpacing() {
        return this.spacing;
    }

    public int getSeparation() {
        return this.separation;
    }

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
        if (!this.isStartChunk(calculator, chunkX, chunkZ)) {
            return false;
        }
        if (this.getFrequency() < 1.0f && !this.getFrequencyReductionMethod().shouldGenerate(calculator.getStructureSeed(), this.getSalt(), chunkX, chunkZ, this.getFrequency())) {
            return false;
        }

        if (exclusionZones.isPresent()) {
            var list = exclusionZones.get();
            for (ModExclusionZone zone : list) {
                if (zone.shouldExclude(calculator, chunkX, chunkZ)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public StructurePlacementType<?> getType() {
        return StructurePlacementType.RANDOM_SPREAD;
    }


    public record ModExclusionZone(RegistryEntry<StructureSet> otherSet, int chunkCount) {
        public static final Codec<ModExclusionZone> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                        RegistryElementCodec.of(RegistryKeys.STRUCTURE_SET, StructureSet.CODEC, false).fieldOf("other_set").forGetter(ModExclusionZone::otherSet),
                        Codec.intRange(1, 16).fieldOf("chunk_count").forGetter(ModExclusionZone::chunkCount))
                .apply(instance, ModExclusionZone::new)
        );

        boolean shouldExclude(StructurePlacementCalculator calculator, int centerChunkX, int centerChunkZ) {
            return calculator.canGenerate(this.otherSet, centerChunkX, centerChunkZ, this.chunkCount);
        }
    }
}
