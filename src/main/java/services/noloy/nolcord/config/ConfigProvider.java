package services.noloy.nolcord.config;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.jetbrains.annotations.NotNull;

import java.io.FileReader;
import java.io.IOException;

/**
 * Provides an easy way for loading json configs
 *
 * @author amraleth
 */
public class ConfigProvider {
    private final Gson gson;

    public ConfigProvider() {
        this.gson = new Gson();
    }

    /**
     * Loads the json configuration
     *
     * @param configPath  The path to the configuration file
     * @param configClazz The class to parse the config to
     * @param <T>         The config class
     * @return An instance of the configClazz
     */
    public <T> T loadConfig(@NotNull String configPath, @NotNull Class<T> configClazz) {
        try (JsonReader jsonReader = new JsonReader(new FileReader(configPath))) {
            return this.gson.fromJson(jsonReader, configClazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}