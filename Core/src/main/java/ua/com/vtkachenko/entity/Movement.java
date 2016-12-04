package ua.com.vtkachenko.entity;

import javax.persistence.*;

//@Entity
@Table(name = "Movements")
public class Movement {

//    @Column (name = "product_id")
    private Product product;

//    @Column (name = "group_id")
    private Group group;

    @Column (name = "summ")
    private double summ;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", unique = true, nullable = false)
    private long id;

    public Movement() {
    }

    public Movement(Product product, Group group, double summ) {
        this.product = product;
        this.group = group;
        this.summ = summ;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
