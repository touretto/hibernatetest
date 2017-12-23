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

    public Person retrievePersonById(int id) {
        Session session = getSession();

        return session.get(Person.class, id);
    }

    public int createPerson(Person person) {
        Session session = getSession();

        Transaction transaction = session.beginTransaction();
        session.save(person);
        transaction.commit();

        return person.getId();
    }
}
