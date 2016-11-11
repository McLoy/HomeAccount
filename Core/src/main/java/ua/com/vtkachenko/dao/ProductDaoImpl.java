package ua.com.vtkachenko.dao;

import ua.com.vtkachenko.entity.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        System.out.println(result);
        return null;
    }

    public Product update(Product entity) {
        return null;
    }

    public Product find(Long id) {
        return null;
    }

    public List<Product> findAll() {
        return null;
    }

    public boolean delete(Long id) {
        return false;
    }

    public boolean delete(Product entity) {
        return false;
    }

    public static void main(String[] args) throws SQLException {
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account", "root", "root")){
            ProductDao dao = new ProductDaoImpl(con);
            Product product = new Product();
            product.setName("Milk");
            Product res = dao.create(product);
            System.out.println(product);
        }

    }
}
