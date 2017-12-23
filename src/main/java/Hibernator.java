import Models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Hibernator {
    private SessionFactory sessionFactory;

    public void initHibernate() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public Person getPersonById(int id) {
        Session session = sessionFactory.openSession();

        return session.get(Person.class, id);
    }
}
