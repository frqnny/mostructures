package io.github.frqnny.mostructures.config;

public class StructureConfigEntry {

    public final boolean activated;
    public final int separation;
    public final int spacing;
    public final int salt;

    private StructureConfigEntry(int separation, int spacing, int salt) {
        this(true, separation, spacing, salt);
    }

    private StructureConfigEntry(boolean activated, int separation, int spacing, int salt) {
        this.activated = activated;
        this.spacing = spacing;
        this.separation = separation;
        this.salt = salt;
    }

    public static StructureConfigEntry of(int seperation, int spacing, int salt) {
        return new StructureConfigEntry(seperation, spacing, salt);
    }

    public static StructureConfigEntry ofExperimental(int seperation, int spacing, int salt) {
        return new StructureConfigEntry(false, seperation, spacing, salt);
    }


}
