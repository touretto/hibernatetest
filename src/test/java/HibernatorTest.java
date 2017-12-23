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

    private static int createPerson() {
        Person newPerson = new Person();
        newPerson.setName("Test");

        return hibernator.create(newPerson);
    }


    private Person retrievePerson() {
        int newId = createPerson();
        return hibernator.retrieveById(newId);
    }

    @Test
    void createPerson_returnsNewId() {
        int newId = createPerson();

        assertNotEquals(0, newId);
    }

    @Test
    void retrievePersonById_returnsPerson() {
        Person person = retrievePerson();

        assertNotNull(person);
    }

    @Test
    void updatePerson_updatesNameInDatabase() {
        Person person = retrievePerson();
        String newName = person.getName() + "foo";
        person.setName(newName);

        hibernator.update(person);

        Person retrievedPerson = hibernator.retrieveById(person.getId());

        assertEquals(newName, retrievedPerson.getName());
    }
}