package ua.com.vtkachenko.entity;

import javax.persistence.*;


@MappedSuperclass
public abstract class IdName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", unique = true, nullable = false)
    private long id;

    @Column (name = "name")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdName idName = (IdName) o;

        return getName().equals(idName.getName());

    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
