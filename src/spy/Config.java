package spy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cocos on 7/16/2017.
 */
public class Config {

    public static final String DB_PATH = "DB_PATH";
    public static final String DB_FOLDER = "db";

    private static Config instance;
    protected final Map<String, String> properties;

    private Config() {
        properties = new HashMap<>();
        initializeProperties();
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    private void initializeProperties() {
        String dbPath = getDbPath();
        properties.put(DB_PATH, dbPath);
    }

    public static String get(String property) {
        return getInstance().getProperties().getOrDefault(property, "");
    }

    public static String getDatabasePath() {
        return get(DB_PATH);
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    private String getDbPath() {
        File dbFile = new File(DB_FOLDER);
        return dbFile.getAbsolutePath();
    }
}
