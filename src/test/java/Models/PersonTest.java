package Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private Person person;

    @BeforeEach
    void Setup() {
        person = new Person();
    }

    @Test
    void getId_afterSetId_returnsId() {
        final int id = 123;
        person.setId(id);
        assertEquals(id, person.getId());
    }

    @Test
    void getName_afterSetName_returnsName() {
        final String name = "some name";
        person.setName(name);
        assertEquals(name, person.getName());
    }

    @Test
    void getAge_afterSetAge_returnsAge() {
        final int age = 66;
        person.setAge(age);
        assertEquals(age, person.getAge());
    }

    @Test
    void getCompany_afterSetCompany_returnsCompany() {
        Company company = new Company();
        person.setCompany(company);

        assertEquals(company, person.getCompany());
    }

    @Test
    void equals_withSameObject_returnsTrue() {
        Person person = new Person();
        assertTrue(person.equals(person));
    }

    @Test
    void equals_withDifferentObjectType_returnsFalse() {
        Person person = new Person();
        assertFalse(person.equals(new Company()));
    }

    @Test
    void equals_withEqualId_returnsTrue() {
        Person person1 = new Person();
        person1.setId(1);

        Person person2 = new Person();
        person2.setId(1);

        assertTrue(person1.equals(person2));
    }

    @Test
    void equals_withDifferentId_returnsFalse() {
        Person person1 = new Person();
        person1.setId(1);

        Person person2 = new Person();
        person2.setId(2);

        assertFalse(person1.equals(person2));
    }
}