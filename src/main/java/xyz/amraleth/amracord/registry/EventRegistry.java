package xyz.amraleth.amracord.registry;

import net.dv8tion.jda.api.JDA;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the registering of events. Each listener needs to extend the {@link net.dv8tion.jda.api.hooks.ListenerAdapter} class
 *
 * @author amraleth
 * @see net.dv8tion.jda.api.hooks.ListenerAdapter
 */
public class EventRegistry {
    private static final List<Object> LISTENERS = new ArrayList<>();

    /**
     * Adds a listener to the event list
     *
     * @param listener The listener
     */
    public static void addEvent(Object listener) {
        LISTENERS.add(listener);
    }

    /**
     * Registers all listeners
     *
     * @param jda An instance of the bot
     */
    public static void registerListeners(JDA jda) {
        for (Object listener : LISTENERS) {
            jda.addEventListener(listener);
        }
    }
}
