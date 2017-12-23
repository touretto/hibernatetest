import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class HibernatorTest {
    private Hibernator hibernator;

    @BeforeEach
    void SetUp() {
        hibernator = new Hibernator();
    }

    @Test
    void initHibernate_doesNotThrow() {
        hibernator.initHibernate();
    }

    @Test
    void getPersonById_withId1_returnsObject() {
        hibernator.initHibernate();
        Person person = hibernator.getPersonById(1);
        assertNotNull(person);
    }
}