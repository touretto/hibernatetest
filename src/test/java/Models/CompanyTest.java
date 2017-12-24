package Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    private Company company;

    @BeforeEach
    void Setup() {
        company = new Company();
    }

    @Test
    void getId_afterSetId_returnsId() {
        final int id = 123;
        company.setId(id);
        assertEquals(id, company.getId());
    }

    @Test
    void getName_afterSetName_returnsName() {
        final String name = "company name";
        company.setName(name);
        assertEquals(name, company.getName());
    }

    @Test
    void equals_withSameObject_returnsTrue() {
        Company company = new Company();
        assertTrue(company.equals(company));
    }

    @Test
    void equals_withDifferentObjectType_returnsFalse() {
        Company company = new Company();
        assertFalse(company.equals(new Person()));
    }

    @Test
    void equals_withEqualId_returnsTrue() {
        Company company1 = new Company();
        company1.setId(1);

        Company company2 = new Company();
        company2.setId(1);

        assertTrue(company1.equals(company2));
    }

    @Test
    void equals_withDifferentId_returnsFalse() {
        Company company1 = new Company();
        company1.setId(1);

        Company company2 = new Company();
        company2.setId(2);

        assertFalse(company1.equals(company2));
    }
}