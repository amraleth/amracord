package xyz.amraleth.amracord.test.config;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.jetbrains.annotations.NotNull;

import java.io.FileReader;
import java.io.IOException;

public class ConfigProvider {
    private final Gson gson;

    public ConfigProvider() {
        this.gson = new Gson();
    }

    public <T> T loadConfig(@NotNull String config, @NotNull Class<T> configClazz) {
        try (JsonReader jsonReader = new JsonReader(new FileReader(config))) {
            return this.gson.fromJson(jsonReader, configClazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}