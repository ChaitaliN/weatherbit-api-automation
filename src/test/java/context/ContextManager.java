package context;

import java.util.Properties;

// NOTE: ContextManager can hold properties, logging etc
public class ContextManager implements Context {

    final private String propFileName = "test.config.properties";
    private Properties config;

    public ContextManager() {

        // Load config from properties file
        try {
            config = new ConfigManager().load(propFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Properties config() {
        return config;
    }
}
