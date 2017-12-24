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
}