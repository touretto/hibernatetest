import Models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        final int id = 1;

        hibernator.initHibernate();
        Person person = hibernator.getPersonById(id);
        assertEquals(id, person.getId());

        System.out.println("Person " + person.getId() + ": " + person.getName());
    }
}