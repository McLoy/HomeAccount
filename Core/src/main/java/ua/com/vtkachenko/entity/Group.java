package ua.com.vtkachenko.entity;

import java.util.Set;

public class Group extends IdName {
    private Set<Product> productSet;

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }



}
