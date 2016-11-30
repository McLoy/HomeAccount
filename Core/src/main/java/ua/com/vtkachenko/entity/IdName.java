package ua.com.vtkachenko.entity;

public abstract class IdName {
    private long id;
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
