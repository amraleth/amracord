package xyz.amraleth.amracord.test.exception;

import org.jetbrains.annotations.NotNull;
import xyz.amraleth.amracord.test.module.Module;
import xyz.amraleth.amracord.test.registry.ModuleRegistry;

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
