package xyz.amraleth.amracord.registry;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.amraleth.amracord.command.CommandProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the creation of command using {@link CommandProvider}s
 *
 * @author amraleth
 */
public class CommandRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger("AmraCord Commands");
    private static final List<CommandProvider> COMMANDS = new ArrayList<>();

    /**
     * Adds a new command
     *
     * @param commandProvider The command provider
     */
    public static void addCommand(CommandProvider commandProvider) {
        COMMANDS.add(commandProvider);
    }

    /**
     * Registers all commands for a specific guild
     *
     * @param guild The guild to register for
     */
    public static void registerCommands(@NotNull Guild guild) {
        List<CommandData> commands = new ArrayList<>();
        COMMANDS.forEach(command -> {
            LOGGER.info("Loading command: {} for module: {}", command.name(), command.customModule() == null ?
                    "Core" :
                    command.customModule().getClass().getName());
            commands.add(command.commandData());
        });
        guild.updateCommands().addCommands(commands).queue();
    }
}
