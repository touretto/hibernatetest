import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class HibernateTestTest {
    private HibernateTest hibernateTest;

    @BeforeEach
    void SetUp() {
        hibernateTest = new HibernateTest();
    }

    @Test
    void initHibernate_doesNotThrow() {
        hibernateTest.initHibernate();
    }

    @Test
    void getPersonById_withId1_returnsObject() {
        hibernateTest.initHibernate();
        Person person = hibernateTest.getPersonById(1);
        assertNotNull(person);
    }
}