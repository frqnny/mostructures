package io.github.frqnny.mostructures;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.RegistrarManager;
import io.github.frqnny.mostructures.config.MoStructuresConfig;
import io.github.frqnny.mostructures.init.ProcessorTypes;
import io.github.frqnny.mostructures.init.Structures;
import io.github.frqnny.omegaconfig.OmegaConfig;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MoStructures {
    public static final MoStructuresConfig CONFIG = OmegaConfig.register(MoStructuresConfig.class);
    public static final String MOD_ID = "mostructures";
    public static final Logger LOGGER = LoggerFactory.getLogger("MoStructures");
    public static Supplier<RegistrarManager> MANAGER;

    public static void init() {
        MANAGER = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));
        ProcessorTypes.init();
        Structures.init();
    }

    public static Identifier id(String name) {
        return Identifier.of(MOD_ID, name);
    }
}
