import Models.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HibernatorTest {
    private static Hibernator hibernator;

    @BeforeAll
    static void SetUp() {
        hibernator = new Hibernator();
        hibernator.initialize();
    }

    private static int createPerson() {
        Person newPerson = new Person();
        newPerson.setName("Test");

        return hibernator.create(newPerson);
    }


    private Person retrievePerson() {
        int newId = createPerson();
        return hibernator.retrieveById(Person.class, newId);
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
    void retrieveAllPersons_returnsListOfPersons() {
        List<Person> persons = hibernator.retrieveAll(Person.class);

        assertNotNull(persons);
        System.out.println("Number of results: " + persons.size());
    }

    @Test
    void updatePerson_updatesValuesInDatabase() {
        Person person = retrievePerson();

        String newName = person.getName() + "foo";
        int newAge = person.getAge() + 1;
        person.setName(newName);
        person.setAge(newAge);

        hibernator.update(person);

        Person retrievedPerson = hibernator.retrieveById(Person.class, person.getId());

        assertEquals(newName, retrievedPerson.getName());
        assertEquals(newAge, retrievedPerson.getAge());
    }

    @Test
    void deletePerson_deletesPersonFromDatabase() {
        Person person = retrievePerson();
        int id = person.getId();

        hibernator.delete(person);

        Person retrievedPerson = hibernator.retrieveById(Person.class, id);
        assertNull(retrievedPerson);
    }

    @Test
    void deleteAll_deletesListOfPersons() {
        createPerson();
        List<Person> allPersons = hibernator.retrieveAll(Person.class);

        List<Object> objects = (List<Object>)(List<?>)allPersons;

        hibernator.deleteList(objects);

        List<Person> remainingPersons = hibernator.retrieveAll(Person.class);
        assertTrue(remainingPersons.isEmpty());
    }
}