package ua.com.vtkachenko.entity;

public class Money {

    private Product product;
    private Group group;
    private double summ;
    private long id;

    public Money() {
    }

    public Money(Product product, Group group, double summ) {
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
