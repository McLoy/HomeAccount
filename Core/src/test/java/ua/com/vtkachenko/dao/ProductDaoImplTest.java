package ua.com.vtkachenko.dao;

import org.fest.assertions.Assertions;
import org.junit.Test;
import ua.com.vtkachenko.entity.Product;

import java.sql.Connection;
import java.sql.DriverManager;

public class ProductDaoImplTest{

    public static final String URL = "jdbc:mysql://localhost:3306/account?autoReconnect=true&useSSL=false";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    public static final int ID = 16; //don't forget it


    /**
     * I start test one by one, after creating I remember ID wich DB auto creates and put it to string "don't forget it"
     * @throws Exception
     */
    @Test
    public void create() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            ProductDao dao = new ProductDaoImpl(con);
            Product product = new Product();
            product.setName("Bread");
            product.setDescr("White");
            Product res = dao.create(product);
            Assertions.assertThat(res.getName()).isEqualTo("Bread");
            Assertions.assertThat(res.getDescr()).isEqualTo("White");
            Assertions.assertThat(res.getId()).isNotEqualTo(0);
        }
    }

    @Test
    public void update() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            ProductDao dao = new ProductDaoImpl(con);
            Product pr = new Product();
            pr.setName("Bread");
            pr.setDescr("Change description");
            Assertions.assertThat(dao.update(pr).getDescr()).isNotNull();
        }
    }

    @Test
    public void findAll() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            ProductDao dao = new ProductDaoImpl(con);
            Assertions.assertThat(dao.findAll().size()).isEqualTo(1);
        }
    }

    @Test
    public void find() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            ProductDao dao = new ProductDaoImpl(con);
            Assertions.assertThat(dao.find(ID).getId()).isNotEqualTo(0);//You need to set id!!!
        }
    }

    @Test
    public void delete() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            ProductDao dao = new ProductDaoImpl(con);
            Product pr = new Product();
            pr.setId(ID); //You need to set id for deleting!!!
            Assertions.assertThat(dao.delete(pr)).isTrue();
        }
    }
}
