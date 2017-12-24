import Models.Company;
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

    private static Person createPerson() {
        Person newPerson = new Person();
        newPerson.setName("Test");

        hibernator.create(newPerson);

        return newPerson;
    }

    private static Company createCompany() {
        Company company = new Company();
        company.setName("ACME Inc.");

        hibernator.create(company);

        return company;
    }

    @Test
    void create_Person_createsNewPerson() {
        Person person = createPerson();

        assertNotEquals(0, person.getId());
    }

    @Test
    void retrieveById_Person_returnsPerson() {
        Person createdPerson = createPerson();

        Person retrievedPerson = hibernator.retrieveById(Person.class, createdPerson.getId());

        assertNotNull(retrievedPerson);
    }

    @Test
    void retrieveAll_Person_returnsListOfPersons() {
        List<Person> persons = hibernator.retrieveAll(Person.class);

        assertNotNull(persons);
        System.out.println("Number of results: " + persons.size());
    }

    @Test
    void update_Person_updatesPersonInDatabase() {
        Person person = createPerson();

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
    void delete_Person_deletesPersonFromDatabase() {
        Person person = createPerson();
        int id = person.getId();

        hibernator.delete(person);

        Person retrievedPerson = hibernator.retrieveById(Person.class, id);
        assertNull(retrievedPerson);
    }

    @Test
    void delete_listOfPersons_deletesAllPersons() {
        createPerson();
        List<Person> allPersons = hibernator.retrieveAll(Person.class);
        assertFalse(allPersons.isEmpty());

        hibernator.delete(allPersons);

        List<Person> remainingPersons = hibernator.retrieveAll(Person.class);
        assertTrue(remainingPersons.isEmpty());
    }

    @Test
    void create_Company_createsNewCompany() {
        Company company = createCompany();

        assertNotEquals(0, company.getId());
    }

    @Test
    void person_setCompany_savesAssociation() {
        Company company = createCompany();
        Person person = createPerson();

        person.setCompany(company);

        hibernator.update(person);

        Person retrievedPerson = hibernator.retrieveById(Person.class, person.getId());

        assertEquals(company, retrievedPerson.getCompany());
    }
}