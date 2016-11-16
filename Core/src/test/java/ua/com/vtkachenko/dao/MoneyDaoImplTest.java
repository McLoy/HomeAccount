package ua.com.vtkachenko.dao;

import org.fest.assertions.Assertions;
import org.junit.Test;
import ua.com.vtkachenko.entity.Group;
import ua.com.vtkachenko.entity.Money;
import ua.com.vtkachenko.entity.Product;

import java.sql.Connection;
import java.sql.DriverManager;

public class MoneyDaoImplTest {

    public static final String URL = "jdbc:mysql://localhost:3306/account";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    @Test
    public void create() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            MoneyDao dao = new MoneyDaoImpl(con);
            Product product = new Product();
            product.setName("Auto oil");
            product.setDescr("Engine oil");
            Group group = new Group();
            group.setName("Transport");
            Money movement = new Money(product, group, 800);
            Money res = dao.create(movement);
            Assertions.assertThat(res.getProduct().getName()).isEqualTo("Auto oil");
            Assertions.assertThat(res.getGroup().getName()).isEqualTo("Transport");
            Assertions.assertThat(res.getSumm()).isEqualTo(800);
        }
    }
}
