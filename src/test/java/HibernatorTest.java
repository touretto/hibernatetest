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

        return hibernator.createPerson(newPerson);
    }

    @Test
    void createPerson_withNewPerson_returnsNewId() {
        int newId = createPerson();

        assertNotEquals(0, newId);
    }

    @Test
    void retrievePersonById_withIdOfCreatedPerson_returnsPerson() {
        int newId = createPerson();

        Person createdPerson = hibernator.retrievePersonById(newId);

        assertNotNull(createdPerson);
    }

    @Test
    void updatePerson_withDifferentName_updatesNameInDatabase() {
        int newId = createPerson();
        Person person = hibernator.retrievePersonById(newId);
        String newName = person.getName() + "foo";
        person.setName(newName);

        hibernator.update(person);

        Person retrievedPerson = hibernator.retrievePersonById(person.getId());

        assertEquals(newName, retrievedPerson.getName());
    }
}