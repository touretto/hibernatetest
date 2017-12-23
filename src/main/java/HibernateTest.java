import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {
    public void initHibernate() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
    }
}
