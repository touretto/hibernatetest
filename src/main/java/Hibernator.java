import Models.ObjectWithId;
import Models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Hibernator {
    private SessionFactory sessionFactory = null;

    public void initialize() {
        Configuration config = new Configuration().configure();
        config.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        sessionFactory = config.buildSessionFactory();
    }

    public <T extends ObjectWithId> int create(T object) {
        runInTransaction(session -> session.save(object));

        return object.getId();
    }

    public Person retrieveById(int id) {
        return retrieveById(Person.class, id);
    }

    public <T> T retrieveById(Class<T> aClass, int id) {
        Session session = getSession();
        return session.get(aClass, id);
    }

    public List<Person> retrieveAll() {
        return retrieveAll(Person.class);
    }

    public <T> List<T> retrieveAll(Class<T> aClass) {
        Session session = getSession();

        Transaction transaction = null;

        try {
            return getList(aClass, session);
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();

            throw e;
        } finally {
            session.close();
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

    public void deleteList(List<Object> objects) {
        runInTransaction(session -> {
            for (Object object: objects)
                session.delete(object);
        });
    }

    private void runInTransaction(SessionAction sessionAction) {
        Session session = getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            sessionAction.runInTransaction(session);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private Session getSession() {
        if (sessionFactory == null)
            initialize();

        return sessionFactory.openSession();
    }
}
