package ua.com.vtkachenko.dao;

import org.fest.assertions.Assertions;
import org.junit.Test;
import ua.com.vtkachenko.entity.Group;
import ua.com.vtkachenko.entity.Movement;
import ua.com.vtkachenko.entity.Product;

import java.sql.Connection;
import java.sql.DriverManager;

public class MovementDaoImplTest {

    public static final String URL = "jdbc:mysql://localhost:3306/account";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    @Test
    public void create() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            MovementDao dao = new MovementDaoImpl(con);
            Product product = new Product();
            product.setName("Auto oil");
            product.setDescr("Engine oil");
            Group group = new Group();
            group.setName("Transport");
            Movement movement = new Movement(product, group, 200);
            Movement res = dao.create(movement);
            Assertions.assertThat(res.getProduct().getName()).isEqualTo("Auto oil");
            Assertions.assertThat(res.getGroup().getName()).isEqualTo("Transport");
            Assertions.assertThat(res.getSumm()).isEqualTo(200);
        }
    }

    @Test
    public void update() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            MovementDao dao = new MovementDaoImpl(con);
            Product product = new Product();
            product.setName("Machine oil");
            product.setDescr("Oil for transmission");
            Group group = new Group();
            group.setName("Transport");
            Movement movement = new Movement(product, group, 222);
            movement.setId(1);
            Movement res = dao.update(movement);
            Assertions.assertThat(res.getProduct().getName()).isEqualTo("Machine oil");
            Assertions.assertThat(res.getGroup().getName()).isEqualTo("Transport");
            Assertions.assertThat(res.getSumm()).isEqualTo(222);
        }
    }

    @Test
    public void find() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            MovementDao dao = new MovementDaoImpl(con);
            Movement movement = dao.find(1);
            Assertions.assertThat(movement.getSumm()).isEqualTo(800);
            Assertions.assertThat(movement.getGroup().getId()).isEqualTo(4);
            Assertions.assertThat(movement.getProduct().getId()).isEqualTo(6);
        }
    }

    @Test
    public void findAll() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            MovementDao dao = new MovementDaoImpl(con);
            Assertions.assertThat(dao.findAll().size()).isEqualTo(4);
        }
    }

    @Test
    public void delete() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            MovementDao dao = new MovementDaoImpl(con);
            Movement pr = new Movement();
            pr.setId(4);
            Assertions.assertThat(dao.delete(pr)).isTrue();
        }
    }
}
