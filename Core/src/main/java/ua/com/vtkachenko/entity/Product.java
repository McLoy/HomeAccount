package ua.com.vtkachenko.entity;

import javax.persistence.*;

@Entity
@Table(name = "Products")
public class Product extends IdName{


//    private Set<Group> groupSet;
//@Transient
@OneToOne()
    private Description description;

//    public Set<Group> getGroupSet() {
//        return groupSet;
//    }
//
//    public void setGroupSet(Set<Group> groupSet) {
//        this.groupSet = groupSet;
//
//    }

    public Description getDescription() {
        return description;
//        return null;
    }

    public void setDescription(Description descr) {
        this.description = descr;
    }
}
