import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesLoaderTest {
    private String propertyFile;
    private PropertiesLoader loader;

    @BeforeEach
    void Setup() {
        propertyFile = HibernatorTest.getCredentialsPropertiesPath();
        loader = new PropertiesLoader();
    }

    @Test
    void loadFromFile_withFileName_returnsProperties() throws Exception {
        Properties properties = loader.loadFromFile(propertyFile);
        assertNotNull(properties);
    }

    @Test
    void loadFromFile_withFileName_containsUsernameProperty() throws Exception {
        Properties properties = loader.loadFromFile(propertyFile);
        assertTrue(properties.containsKey("hibernate.connection.username"));
    }

    @Test
    void loadFromFile_withFileName_containsPasswordProperty() throws Exception {
        Properties properties = loader.loadFromFile(propertyFile);
        assertTrue(properties.containsKey("hibernate.connection.password"));
    }
}