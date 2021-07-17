package io.github.frqnny.mostructures.config;

public class StructureConfigEntry {

    public final boolean activated;
    public final int seperation;
    public final int spacing;
    public final int salt;

    private StructureConfigEntry(int seperation, int spacing, int salt) {
        this(true, seperation, spacing, salt);
    }

    private StructureConfigEntry(boolean activated, int seperation, int spacing, int salt) {
        this.activated = activated;
        this.spacing = spacing;
        this.seperation = seperation;
        this.salt = salt;
    }

    public static StructureConfigEntry of(int seperation, int spacing, int salt) {
        return new StructureConfigEntry(seperation, spacing, salt);
    }

    public static StructureConfigEntry ofExperimental(int seperation, int spacing, int salt) {
        return new StructureConfigEntry(false, seperation, spacing, salt);
    }


}
