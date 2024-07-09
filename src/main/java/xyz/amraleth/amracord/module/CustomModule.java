package xyz.amraleth.amracord.module;

/**
 * Represents a module which can have its own events and commands
 *
 * @author amraleth
 * @see Module
 */
public interface CustomModule {

    /**
     * The main method of each module. It serves as the starting point and should be used to register events and commands
     * using the corresponding registries
     */
    void initModule(ModuleBase moduleBase);
}
