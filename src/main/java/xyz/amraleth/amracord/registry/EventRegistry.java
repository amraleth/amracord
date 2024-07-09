package xyz.amraleth.amracord.registry;

import net.dv8tion.jda.api.JDA;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.amraleth.amracord.module.Module;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles the registering of events. Each listener needs to extend the {@link net.dv8tion.jda.api.hooks.ListenerAdapter} class
 *
 * @author amraleth
 * @see net.dv8tion.jda.api.hooks.ListenerAdapter
 */
public class EventRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger("AmraCord Events");
    private static final Map<Module, Object> LISTENERS = new HashMap<>();

    /**
     * Adds a listener to the event list
     *
     * @param module The module the listener is a part of
     * @param listener The listener
     */
    public static void addEvent(@NotNull Module module, @NotNull Object listener) {
        LISTENERS.put(module, listener);
    }

    /**
     * Registers all listeners
     *
     * @param jda An instance of the bot
     */
    public static void registerListeners(@NotNull JDA jda) {
        LISTENERS.forEach((module, listener) -> {
            LOGGER.info("Loading listener {} for module {}", listener.getClass().getName(), module.moduleId());
            jda.addEventListener(listener);
        });
    }
}
