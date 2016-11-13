package ua.com.vtkachenko.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {
    T create(T entity) throws SQLException;

    T update(T entity);

    T find(long id) throws SQLException;

    List<T> findAll();

    boolean delete(long id);

    boolean delete(T entity);
}
