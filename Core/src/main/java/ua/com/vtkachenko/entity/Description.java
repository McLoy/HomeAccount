package ua.com.vtkachenko.entity;

import javax.persistence.*;

@Entity
@Table (name = "Descriptions")
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", unique = true, nullable = false)
    private long id;

//    @OneToOne(fetch = FetchType.LAZY)
//    @PrimaryKeyJoinColumn
    @Column
    public String data;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Product product;

    public Description() {
    }

    public Description(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

}
