package ua.com.vtkachenko.entity;

import java.util.Set;

public class Product extends IdName{

    private Set<Group> groupSet;
    private String descr;

    public Set<Group> getGroupSet() {
        return groupSet;
    }

    public void setGroupSet(Set<Group> groupSet) {
        this.groupSet = groupSet;

    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
