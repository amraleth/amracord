package xyz.amraleth.amracord.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Thrown when a module class is missing the {@link xyz.amraleth.amracord.module.Module} annotation and therefor doesn't
 * contain any metadata
 *
 * @author amraleth
 * @see xyz.amraleth.amracord.registry.ModuleRegistry
 */
public class NoModuleException extends Exception {

    public NoModuleException(@NotNull String message) {
        super(message);
    }
}
