package services.noloy.nolcord.module;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Contains meta information about a module
 *
 * @author amraleth
 * @see CustomModule
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Module {
    /**
     * The id of the module
     */
    @NotNull String moduleId();

    /**
     * The name of the module, can be different from the moduleId
     */
    @NotNull String moduleName();

    /**
     * The version of the module
     */
    @NotNull String version();

    /**
     * A short description of what the module is about
     */
    @NotNull String description();
}
