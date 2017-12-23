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

    public int create(Person person) {
        runInTransaction(session -> session.save(person));

        return person.getId();
    }

    public Person retrieveById(int id) {
        Session session = getSession();
        return session.get(Person.class, id);
    }

    public List<Person> retrieveAll() {
        Session session = getSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);

        Root<Person> root = criteriaQuery.from(Person.class);
        criteriaQuery.select(root);

        Query<Person> query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

    public void update(Person person) {
        runInTransaction(session -> session.update(person));
    }

    public void delete(Person person) {
        runInTransaction(session -> session.delete(person));
    }

    public void delete(List<Person> persons) {
        runInTransaction(session -> {
            for (Person person: persons)
                session.delete(person);
        });
    }

    private void runInTransaction(SessionAction sessionAction) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        sessionAction.runInTransaction(session);

        transaction.commit();
    }

    private Session getSession() {
        if (sessionFactory == null)
            initialize();

        return sessionFactory.openSession();
    }
}
