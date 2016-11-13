package ua.com.vtkachenko.dao;

import ua.com.vtkachenko.entity.Product;

import java.sql.*;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private final Connection connection;

    public ProductDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public Product create(Product entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Product (name) VALUES (?)");
        statement.setString(1, entity.getName());
        int result = statement.executeUpdate();
        Product product = new Product();
        product.setName(entity.getName());
        return product;
    }

    public Product update(Product entity) {
        return null;
    }

    public Product find(long id) throws SQLException{
        return null;
    }

    public List<Product> findAll() {
        return null;
    }

    public boolean delete(long id) {
        return false;
    }

    public boolean delete(Product entity) {
        return false;
    }

    public static void main(String[] args) throws SQLException {
//        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account", "root", "root")) {
//            ProductDao dao = new ProductDaoImpl(con);
//            System.out.println(dao.find(11).getName());
//        }

        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account", "root", "root")){
            ProductDao dao = new ProductDaoImpl(con);
            Product product = new Product();
            product.setName("Bread");
            Product res = dao.create(product);

            System.out.println(res);
        } finally {

        }

    }
}
