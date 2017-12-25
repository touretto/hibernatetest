import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class SessionFactoryBuilder {
    private final String credentialsPropertiesFile;

    public SessionFactoryBuilder(String credentialsPropertiesFile) {
        this.credentialsPropertiesFile = credentialsPropertiesFile;
    }

    public SessionFactory buildSessionFactory() {
        Configuration config = new Configuration().configure();
        addCredentialsProperties(config);
        config.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");

        return config.buildSessionFactory();
    }

    private void addCredentialsProperties(Configuration config) {
        try {
            Properties credentialsProperties = new PropertiesLoader().loadFromFile(credentialsPropertiesFile);
            config.addProperties(credentialsProperties);
        } catch (Exception ignored) {
        }
    }
}
