package io.github.frqnny.mostructures.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.frqnny.mostructures.init.Structures;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.dynamic.RegistryElementCodec;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.CheckedRandom;
import net.minecraft.util.math.random.ChunkRandom;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;
import net.minecraft.world.gen.noise.NoiseConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModStructurePlacement extends RandomSpreadStructurePlacement {
    public static final Codec<ModStructurePlacement> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    Vec3i.createOffsetCodec(16).optionalFieldOf("locate_offset", Vec3i.ZERO).forGetter(ModStructurePlacement::getLocateOffset),
                    FrequencyReductionMethod.CODEC.optionalFieldOf("frequency_reduction_method", FrequencyReductionMethod.DEFAULT).forGetter(ModStructurePlacement::getFrequencyReductionMethod),
                    Codec.floatRange(0.0f, 1.0f).optionalFieldOf("frequency", 1.0f).forGetter(ModStructurePlacement::getFrequency),
                    Codecs.NONNEGATIVE_INT.fieldOf("salt").forGetter(ModStructurePlacement::getSalt),
                    RegistryElementCodec.of(Registry.STRUCTURE_SET_KEY, StructureSet.CODEC, false).listOf().fieldOf("structure_set_to_avoid").orElse(new ArrayList<>(20)).forGetter(config -> config.structureSetToAvoid),
                    Codec.intRange(0, 4096).fieldOf("spacing").forGetter(ModStructurePlacement::getSpacing),
                    Codec.intRange(0, 4096).fieldOf("separation").forGetter(ModStructurePlacement::getSeparation),
                    SpreadType.CODEC.optionalFieldOf("spread_type", SpreadType.LINEAR).forGetter(ModStructurePlacement::getSpreadType))
            .apply(instance, ModStructurePlacement::new));
    public final List<RegistryEntry<StructureSet>> structureSetToAvoid;
    private int spacing;
    private int separation;
    private final SpreadType spreadType;
    private boolean activated;

    public ModStructurePlacement(Vec3i locateOffset, StructurePlacement.FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, List<RegistryEntry<StructureSet>> structureSetToAvoid, int spacing, int separation, SpreadType spreadType) {
        super(locateOffset, frequencyReductionMethod, frequency, salt, Optional.empty(), spacing, separation, spreadType);
        this.spacing = spacing;
        this.separation = separation;
        this.spreadType = spreadType;
        this.structureSetToAvoid = structureSetToAvoid;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public int getSpacing() {
        return this.spacing;
    }

    @Override
    public int getSeparation() {
        return this.separation;
    }

    public void setSeparation(int separation) {
        this.separation = separation;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
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
    protected boolean isStartChunk(ChunkGenerator chunkGenerator, NoiseConfig noiseConfig, long seed, int chunkX, int chunkZ) {
        ChunkPos chunkPos = this.getStartChunk(seed, chunkX, chunkZ);
        return chunkPos.x == chunkX && chunkPos.z == chunkZ;
    }

    @Override
    public boolean shouldGenerate(ChunkGenerator chunkGenerator, NoiseConfig noiseConfig, long seed, int chunkX, int chunkZ) {
        if (!activated) {
            return false;
        }

        if (!this.isStartChunk(chunkGenerator, noiseConfig, seed, chunkX, chunkZ)) {
            return false;
        }
        if (this.getFrequency() < 1.0f && !this.getFrequencyReductionMethod().shouldGenerate(seed, this.getSalt(), chunkX, chunkZ, this.getFrequency())) {
            return false;
        }

        if (!structureSetToAvoid.isEmpty()) {
            for (RegistryEntry<StructureSet> entry : structureSetToAvoid) {
                if (shouldExclude(chunkGenerator, noiseConfig, seed, entry, chunkX, chunkZ, 3)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean shouldGenerateNoExclusionCheck(ChunkGenerator chunkGenerator, NoiseConfig noiseConfig, long seed, int chunkX, int chunkZ) {
        if (!activated) {
            return false;
        }
        if (!this.isStartChunk(chunkGenerator, noiseConfig, seed, chunkX, chunkZ)) {
            return false;
        }
        return !(this.getFrequency() < 1.0f) || this.getFrequencyReductionMethod().shouldGenerate(seed, this.getSalt(), chunkX, chunkZ, this.getFrequency());
    }

    public static boolean shouldExclude(ChunkGenerator chunkGenerator, NoiseConfig noiseConfig, long seed, RegistryEntry<StructureSet> structureSetEntry, int centerChunkX, int centerChunkZ, int chunkCount) {
        if (structureSetEntry.value().placement() instanceof ModStructurePlacement structurePlacement) {
            for (int i = centerChunkX - chunkCount; i <= centerChunkX + chunkCount; ++i) {
                for (int j = centerChunkZ - chunkCount; j <= centerChunkZ + chunkCount; ++j) {
                    if (!structurePlacement.shouldGenerateNoExclusionCheck(chunkGenerator, noiseConfig, seed, i, j)) continue;
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public StructurePlacementType<?> getType() {
        return Structures.TYPE;
    }
}
