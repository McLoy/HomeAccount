package ua.com.vtkachenko.entity;

import java.util.Set;

public class Product extends IdName{

    private Set<Group> groupSet;

    public Set<Group> getGroupSet() {
        return groupSet;
    }

    public void setGroupSet(Set<Group> groupSet) {
        this.groupSet = groupSet;
    }
}
