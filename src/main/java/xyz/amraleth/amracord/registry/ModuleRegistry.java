package xyz.amraleth.amracord.registry;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.amraleth.amracord.exception.NoModuleException;
import xyz.amraleth.amracord.module.CustomModule;
import xyz.amraleth.amracord.module.Module;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods for initialising and configuring modules
 *
 * @author amraleth
 * @see Module
 * @see CustomModule
 */
@Getter
public class ModuleRegistry {
    private final Logger logger;
    private final List<CustomModule> modules;

    public ModuleRegistry() {
        this.logger = LoggerFactory.getLogger("AmraCord Modules");
        this.modules = new ArrayList<>();
    }

    /**
     * Adds a new module to the module list
     *
     * @param customModule The module to add
     * @return ModuleManager
     */
    public ModuleRegistry addModule(@NotNull CustomModule customModule) {
        this.modules.add(customModule);
        return this;
    }

    /**
     * Extracts the {@link Module} annotation from each {@link CustomModule} and initialises it
     *
     * @throws NoModuleException If the module class is missing the module annotation
     */
    public void initModules() throws NoModuleException {
        for (CustomModule customModule : this.modules) {

            // check if the class contains the module annotation
            Class<?> moduleClazz = customModule.getClass();
            if (!moduleClazz.isAnnotationPresent(Module.class)) {
                throw new NoModuleException("The module " + moduleClazz.getName() + " is missing the Module.class annotation!");
            }

            // extracts the module annotation
            Module moduleAnnotation = moduleClazz.getAnnotation(Module.class);

            // Initialises the module
            this.logger.info("Loading module: {} version: {} in path: {}.",
                    moduleAnnotation.moduleId(),
                    moduleAnnotation.version(),
                    moduleClazz.getPackageName());
            customModule.initModule();
            this.logger.info("Module: {} loaded!", moduleAnnotation.moduleId());
        }
    }
}
