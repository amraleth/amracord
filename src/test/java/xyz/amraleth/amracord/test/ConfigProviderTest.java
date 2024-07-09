package xyz.amraleth.amracord.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.amraleth.amracord.test.config.ConfigProvider;

public class ConfigProviderTest {

    @Test
    public void testConfigProvider() {
        ConfigProvider configProvider = new ConfigProvider();
        TestConfig testConfig = configProvider.loadConfig("testconfig.json", TestConfig.class);
        Assertions.assertEquals("world", testConfig.getHello());
    }


}
