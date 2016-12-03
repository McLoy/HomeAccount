package ua.com.vtkachenko.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T>{
    T create(T entity) throws SQLException;

    T update(T entity) throws SQLException;

    T find(long id) throws SQLException;

    List<T> findAll() throws SQLException;

    boolean delete(long id) throws SQLException;

    boolean delete(T entity) throws SQLException;
}
