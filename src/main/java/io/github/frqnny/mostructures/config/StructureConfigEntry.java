package io.github.frqnny.mostructures.config;

public class StructureConfigEntry {

    public final boolean activated;
    public final int separation;
    public final int spacing;

    private StructureConfigEntry(int separation, int spacing) {
        this(true, separation, spacing);
    }

    private StructureConfigEntry(boolean activated, int separation, int spacing) {
        this.activated = activated;
        this.spacing = spacing;
        this.separation = separation;
    }

    public static StructureConfigEntry of(int seperation, int spacing) {
        return new StructureConfigEntry(seperation, spacing);
    }
}
