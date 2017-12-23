import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HibernateTestTest {
    private HibernateTest test;

    @BeforeEach
    void SetUp() {
        test = new HibernateTest();
    }

    @Test
    void initHibernate() {
        test.initHibernate();
    }
}