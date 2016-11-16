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
            Money movement = new Money(product, group, 200);
            Money res = dao.create(movement);
            Assertions.assertThat(res.getProduct().getName()).isEqualTo("Auto oil");
            Assertions.assertThat(res.getGroup().getName()).isEqualTo("Transport");
            Assertions.assertThat(res.getSumm()).isEqualTo(200);
        }
    }

    @Test
    public void update() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            MoneyDao dao = new MoneyDaoImpl(con);
            Product product = new Product();
            product.setName("Machine oil");
            product.setDescr("Oil for transmission");
            Group group = new Group();
            group.setName("Transport");
            Money movement = new Money(product, group, 222);
            movement.setId(1);
            Money res = dao.update(movement);
            Assertions.assertThat(res.getProduct().getName()).isEqualTo("Machine oil");
            Assertions.assertThat(res.getGroup().getName()).isEqualTo("Transport");
            Assertions.assertThat(res.getSumm()).isEqualTo(222);
        }
    }

    @Test
    public void find() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            MoneyDao dao = new MoneyDaoImpl(con);
            Money money = dao.find(1);
            Assertions.assertThat(money.getSumm()).isEqualTo(800);
            Assertions.assertThat(money.getGroup().getId()).isEqualTo(4);
            Assertions.assertThat(money.getProduct().getId()).isEqualTo(6);
        }
    }

    @Test
    public void findAll() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            MoneyDao dao = new MoneyDaoImpl(con);
            Assertions.assertThat(dao.findAll().size()).isEqualTo(4);
        }
    }

    @Test
    public void delete() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            MoneyDao dao = new MoneyDaoImpl(con);
            Money pr = new Money();
            pr.setId(4);
            Assertions.assertThat(dao.delete(pr)).isTrue();
        }
    }
}
