import Models.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HibernatorTest {
    private static Hibernator hibernator;

    @BeforeAll
    static void SetUp() {
        hibernator = new Hibernator();
    }

    @Test
    void getPersonById_withId1_returnsObject() {
        final int id = 1;

        Person person = hibernator.getPersonById(id);
        assertEquals(id, person.getId());

        System.out.println("Person " + person.getId() + ": " + person.getName());
    }

    @Test
    void createPerson_returnsNewId() {
        Person newPerson = new Person();
        newPerson.setName("Test");

        int newId = hibernator.createPerson(newPerson);
        assertNotEquals(0, newId);
        System.out.println("Create person ID: " + newId);
    }

    @Test
    void getPersonById_withIdFromCreatePerson_returnsCreatedPerson() {
        Person newPerson = new Person();
        newPerson.setName("Test");

        int newId = hibernator.createPerson(newPerson);
        assertNotEquals(0, newId);

        Person createdPerson = hibernator.getPersonById(newId);
        assertNotNull(createdPerson);
        assertEquals(newId, createdPerson.getId());
    }
}