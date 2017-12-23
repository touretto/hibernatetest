import Models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Hibernator {
    private SessionFactory sessionFactory = null;

    private void initialize() {
        Configuration config = new Configuration().configure();
        config.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        sessionFactory = config.buildSessionFactory();
    }

    private Session getSession() {
        if (sessionFactory == null)
            initialize();

        return sessionFactory.openSession();
    }

    public int create(Person person) {
        Session session = getSession();

        runInTransaction(session, (s) -> s.save(person));

        return person.getId();
    }

    public Person retrieveById(int id) {
        Session session = getSession();
        return session.get(Person.class, id);
    }

    public void update(Person person) {
        Session session = getSession();

        runInTransaction(session, (s) -> s.update(person));
    }

    private void runInTransaction(Session session, SessionAction sessionAction) {
        Transaction transaction = session.beginTransaction();
        sessionAction.RunInTransaction(session);
        transaction.commit();
    }
}
