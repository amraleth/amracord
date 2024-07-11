package services.noloy.nolcord.exception;

import org.jetbrains.annotations.NotNull;
import services.noloy.nolcord.registry.ModuleRegistry;
import services.noloy.nolcord.module.Module;

/**
 * Thrown when a module class is missing the {@link Module} annotation and therefor doesn't
 * contain any metadata
 *
 * @author amraleth
 * @see ModuleRegistry
 */
public class NoModuleException extends Exception {

    public NoModuleException(@NotNull String message) {
        super(message);
    }
}
