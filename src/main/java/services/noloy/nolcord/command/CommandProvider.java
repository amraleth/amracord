package services.noloy.nolcord.command;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import services.noloy.nolcord.module.CustomModule;

/**
 * Provides data about a command
 *
 * @param name         The name of the command
 * @param commandData  The actual command data
 * @param customModule The module which this command is a part of, if null the command is considered module-less
 */
public record CommandProvider(@NotNull String name, @NotNull CommandData commandData,
                              @Nullable CustomModule customModule) {
}
