import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Properties;

class Hibernator {
    private final String credentialsPropertiesFile;

    private SessionFactory sessionFactory = null;

    public Hibernator(String credentialsPropertiesFile) {
        this.credentialsPropertiesFile = credentialsPropertiesFile;
    }

    public void initialize() {
        Configuration config = new Configuration().configure();
        addCredentialsProperties(config);
        config.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");

        sessionFactory = config.buildSessionFactory();
    }

    private void addCredentialsProperties(Configuration config) {
        /* The properties file needs to contain the following data:

            hibernate.connection.username=someusername
            hibernate.connection.password=theuserspassword
         */
        try {
            Properties credentialsProperties = new PropertiesLoader().loadFromFile(credentialsPropertiesFile);
            config.addProperties(credentialsProperties);
        } catch (Exception e) {
        }
    }

    public void create(Object object) {
        runInTransaction(session -> session.save(object));
    }

    public <T> T retrieveById(Class<T> aClass, int id) {
        Session session = getSession();
        return session.get(aClass, id);
    }

    public <T> List<T> retrieveAll(Class<T> aClass) {
        try (Session session = getSession()) {
            return getList(aClass, session);
        }
    }

    private <T> List<T> getList(Class<T> aClass, Session session) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(aClass);

        Root<T> root = criteriaQuery.from(aClass);
        criteriaQuery.select(root);

        Query<T> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public void update(Object object) {
        runInTransaction(session -> session.update(object));
    }

    public void delete(Object object) {
        runInTransaction(session -> session.delete(object));
    }

    public void delete(List<?> objects) {
        runInTransaction(session -> {
            for (Object object: objects)
                session.delete(object);
        });
    }

    private void runInTransaction(SessionAction sessionAction) {
        Transaction transaction = null;

        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            sessionAction.runInTransaction(session);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw e;
        }
    }

    private Session getSession() {
        if (sessionFactory == null)
            initialize();

        return sessionFactory.openSession();
    }
}
