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
}