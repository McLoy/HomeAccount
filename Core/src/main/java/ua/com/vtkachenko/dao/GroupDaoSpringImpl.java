package ua.com.vtkachenko.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ua.com.vtkachenko.entity.Group;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class GroupDaoSpringImpl implements GroupDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

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

    public static void main(String[] args) throws SQLException {
        GroupDao groupDao = new GroupDaoSpringImpl();
        Group group = new Group();
        group.setName("Example");
        groupDao.create(group);
    }
}
