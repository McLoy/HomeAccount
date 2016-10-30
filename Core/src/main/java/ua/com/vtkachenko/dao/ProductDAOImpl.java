package ua.com.vtkachenko.dao;

import ua.com.vtkachenko.entity.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private final Connection connection;

    public ProductDAOImpl(Connection connection) {
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
            ProductDAO dao = new ProductDAOImpl(con);
            Product product = new Product();
            product.setName("dfgsdf");
            Product res = dao.create(product);
            System.out.println(product);
        }

    }
}
