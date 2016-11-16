package ua.com.vtkachenko.dao;

import ua.com.vtkachenko.entity.Group;
import ua.com.vtkachenko.entity.Money;
import ua.com.vtkachenko.entity.Product;

import java.sql.*;
import java.util.List;

public class MoneyDaoImpl implements MoneyDao{

    private final Connection connection;

    public MoneyDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Money create(Money entity) throws SQLException {

        Product product = entity.getProduct();
        Group group = entity.getGroup();
        if (product.getId() == 0){
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM Product WHERE name = (?)");
            statement.setString(1, product.getName());
            ResultSet res = statement.executeQuery();
            if (! res.next()){
                ProductDao productDao = new ProductDaoImpl(connection);
                product = productDao.create(product);
            } else {
                product.setId(res.getLong("id"));
            }
        }

        if (group.getId() == 0){
            PreparedStatement statementGroup = connection.prepareStatement("SELECT id FROM `Group` WHERE name = (?)");
            statementGroup.setString(1, group.getName());
            ResultSet resGr = statementGroup.executeQuery();
            if (! resGr.next()){
                ProductDao productDao = new ProductDaoImpl(connection);
                product = productDao.create(product);
            } else {
                group.setId(resGr.getLong("id"));
            }
        }

        PreparedStatement statement = connection.prepareStatement("INSERT INTO Money (product_id, group_id, summ) VALUES (?,?,?)");
        statement.setLong(1, product.getId());
        statement.setLong(2, group.getId());
        statement.setDouble(3, entity.getSumm());
        int result = statement.executeUpdate();

        return entity;
    }

    @Override
    public Money update(Money entity) throws SQLException {
        return null;
    }

    @Override
    public Money find(long id) throws SQLException {
        return null;
    }

    @Override
    public List<Money> findAll() throws SQLException {
        return null;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Money entity) throws SQLException {
        return false;
    }

}
