package ua.com.vtkachenko.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {
    T create(T entity) throws SQLException;

    T update(T entity);

    T find(Long id);

    List<T> findAll();

    boolean delete(Long id);

    boolean delete(T entity);
}
