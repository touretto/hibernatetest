import org.hibernate.Session;

interface SessionAction {
    void RunInTransaction(Session session);
}
