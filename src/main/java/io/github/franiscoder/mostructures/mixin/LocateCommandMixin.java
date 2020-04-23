package io.github.franiscoder.mostructures.mixin;

import com.mojang.brigadier.CommandDispatcher;
import io.github.franiscoder.mostructures.MoStructures;
import net.minecraft.server.command.LocateCommand;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.server.command.CommandManager.literal;

@SuppressWarnings("unused")
@Mixin(LocateCommand.class)
public abstract class LocateCommandMixin {

    private LocateCommandMixin() {
        // NO-OP
    }

    @Shadow
    private static int execute(ServerCommandSource source, String name) {
        throw new AssertionError();
    }

    @Inject(method = "register", at = @At(value = "RETURN"))
    private static void onRegister(CommandDispatcher<ServerCommandSource> dispatcher, CallbackInfo info) {
        dispatcher.register(literal("locate").requires(source -> source.hasPermissionLevel(2))
                .then(literal(MoStructures.MODID + ":Barn_House").executes(ctx -> execute(ctx.getSource(), MoStructures.MODID + ":barn_house")))
        );
        dispatcher.register(literal("locate").requires(source -> source.hasPermissionLevel(2))
                .then(literal(MoStructures.MODID + ":Piglin_Outpost").executes(ctx -> execute(ctx.getSource(), MoStructures.MODID + ":piglin_outpost")))
        );
    }
}