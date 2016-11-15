package ua.com.vtkachenko.dao;

import org.fest.assertions.Assertions;
import org.junit.Test;
import ua.com.vtkachenko.entity.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProductDaoImplTest{

    @Test
    public void create() throws Exception{
        Product res = null;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account", "root", "root")) {
            ProductDao dao = new ProductDaoImpl(con);
            Product product = new Product();
            product.setName("Bread");
            product.setDescr("White");
            res = dao.create(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        Assert.assertEquals(res.getName(), "Bread");
        if (res != null) {
            Assertions.assertThat(res.getName()).isEqualTo("Bread");
            Assertions.assertThat(res.getDescr()).isEqualTo("White");
            Assertions.assertThat(res.getId()).isNotEqualTo(0);
        }
    }

    @Test
    public void delete() throws Exception {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account", "root", "root")) {
            ProductDao dao = new ProductDaoImpl(con);
            Assertions.assertThat(dao.delete(17)).isTrue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
