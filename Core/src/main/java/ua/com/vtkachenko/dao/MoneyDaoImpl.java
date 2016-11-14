package ua.com.vtkachenko.dao;

import ua.com.vtkachenko.entity.Money;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MoneyDaoImpl implements MoneyDao {

    private final Connection connection;

    public MoneyDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Money create(Money entity) throws SQLException {
        return null;
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

    public static void main(String[] args) {

    }
}
