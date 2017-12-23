import org.hibernate.Session;

interface SessionAction {
    void runInTransaction(Session session);
}
