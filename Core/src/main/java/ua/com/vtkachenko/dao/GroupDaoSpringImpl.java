package ua.com.vtkachenko.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.com.vtkachenko.entity.Group;

import java.sql.SQLException;
import java.util.List;

@Component
public class GroupDaoSpringImpl implements GroupDao {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public Group create(Group entity) throws SQLException {
        String SQL = "INSERT INTO `Group` (name) VALUES (?)";
        jdbcTemplate.update(SQL, entity.getName());
        return entity;
    }

    @Override
    public Group update(Group entity) throws SQLException {
        return null;
    }

    @Override
    public Group find(long id) throws SQLException {
        return null;
    }

    @Override
    public List<Group> findAll() throws SQLException {
        return null;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Group entity) throws SQLException {
        return false;
    }
}
