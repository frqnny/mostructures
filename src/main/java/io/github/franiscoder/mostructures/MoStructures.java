package io.github.franiscoder.mostructures;

import io.github.franiscoder.mostructures.config.MoStructuresConfig;
import io.github.franiscoder.mostructures.init.ModStructures;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class MoStructures implements ModInitializer {
    public static final String MODID = "mostructures";
    private static MoStructuresConfig config;

    public static Identifier id(String name) {
        return new Identifier(MODID, name);
    }

    public static MoStructuresConfig getConfig() {
        config = AutoConfig.getConfigHolder(MoStructuresConfig.class).getConfig();
        return config;
    }

    @Override
    public void onInitialize() {
        AutoConfig.register(MoStructuresConfig.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(MoStructuresConfig.class).getConfig();
        ModStructures structures = new ModStructures();
        structures.init();
    }
}
