package xyz.amraleth.amracord.exception;

import org.jetbrains.annotations.NotNull;
import xyz.amraleth.amracord.registry.ModuleRegistry;
import xyz.amraleth.amracord.module.Module;

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
