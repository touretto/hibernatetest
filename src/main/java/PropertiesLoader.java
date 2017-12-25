import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesLoader {
    public Properties loadFromFile(String filename) throws Exception {
        Properties properties = new Properties();

        try (FileInputStream stream = new FileInputStream(filename)) {
            properties.load(stream);
        }

        return properties;
    }
}
