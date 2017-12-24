package Models;

import java.util.Objects;

public class Company {
    private int id;
    private String name;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return getId() == company.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
