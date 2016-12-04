package ua.com.vtkachenko.dao;

import ua.com.vtkachenko.entity.Description;
import ua.com.vtkachenko.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private final Connection connection;

    public ProductDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public Product create(Product entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Products (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getName());
        int result = statement.executeUpdate();
        ResultSet ret_id = statement.getGeneratedKeys();
        if (ret_id.next()) {
            entity.setId(ret_id.getLong(1));
            PreparedStatement statement3 = connection.prepareStatement("INSERT INTO Descriptions (product_id, descr) VALUES (?, ?)");
            statement3.setLong(1, ret_id.getLong(1));
            statement3.setString(2, entity.getDescription().getData());
            int result3 = statement3.executeUpdate();
            return entity;
        }
        return null;
    }

    public Product update(Product entity) throws SQLException {
        Statement statement1 = connection.createStatement();
        ResultSet result1 = statement1.executeQuery("SELECT id FROM Products WHERE name = '" + entity.getName() + "'");
        if (result1.next()) {
            PreparedStatement statement3 = connection.prepareStatement("UPDATE Descriptions SET descr = ? WHERE product_id = ?", Statement.RETURN_GENERATED_KEYS);
            statement3.setString(1, entity.getDescription().getData());
            statement3.setLong(2, result1.getInt("id"));
            int result3 = statement3.executeUpdate();
            return entity;
        }
        return null;
    }

    public Product find(long id) throws SQLException {
        Statement statementFind = connection.createStatement();
        ResultSet resultFind = statementFind.executeQuery("SELECT name FROM Products WHERE id = " + id);
        Product product = new Product();
        if (resultFind.next()) {
            PreparedStatement statementFind2 = connection.prepareStatement("SELECT * FROM Descriptions WHERE product_id = ?");
            statementFind2.setLong(1, id);
            ResultSet resultFind2 = statementFind2.executeQuery();
            if (resultFind2.next()) product.setDescription(new Description(resultFind2.getString("descr")));
            product.setName(resultFind.getString("name"));
            product.setId(id);
            return product;
        }
        return null;
    }

    public List<Product> findAll() throws SQLException {
        Statement statementFind = connection.createStatement();
        ResultSet resultFind = statementFind.executeQuery("SELECT * FROM Products");
        List<Product> listPr = new ArrayList<>();
        while (resultFind.next()) {
            Product product = new Product();
            product.setName(resultFind.getString("name"));
            product.setId(resultFind.getLong("id"));
            listPr.add(product);
        }
        return listPr;
    }

    public boolean delete(long id) throws SQLException {
        Statement statementFind = connection.createStatement();
        int res = statementFind.executeUpdate("DELETE FROM Products WHERE id = " + id);
        Statement statementDescr = connection.createStatement();
        int resDescr = statementDescr.executeUpdate("DELETE FROM Descriptions WHERE product_id = " + id);
        return res == 1 && resDescr == 1;
    }

    public boolean delete(Product entity) throws SQLException {
        if (entity != null) {
            return delete(entity.getId());
        }
        return false;
    }
}
