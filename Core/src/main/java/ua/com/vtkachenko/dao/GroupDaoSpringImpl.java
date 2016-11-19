package ua.com.vtkachenko.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.com.vtkachenko.entity.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class GroupDaoSpringImpl implements GroupDao {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public class GroupMapper implements RowMapper{
        @Override
        public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
            Group group = new Group();
            group.setId(rs.getLong("id"));
            group.setName(rs.getString("name"));
            return group;
        }
    }

    @Override
    public Group create(Group entity) throws SQLException {
        String SQL = "INSERT INTO Groups (name) VALUES (?)";
        jdbcTemplate.update(SQL, entity.getName());
        return entity;
    }

    @Override
    public Group update(Group entity) throws SQLException {
        if (find(entity.getId()) != null) {
            long id = entity.getId();
            String SQL = "UPDATE Groups SET name = ? WHERE id = ?";
            jdbcTemplate.update(SQL, entity.getName(), id);
            return entity;
        }
        return null;
    }

    @Override
    public Group find(long id) throws SQLException {
        String SQL = "SELECT * FROM Groups WHERE id = ?";
        Group group = (Group) jdbcTemplate.queryForObject(SQL, new Object[]{id}, new GroupMapper());
        return group;
    }

    @Override
    public List<Group> findAll() throws SQLException {
        String SQL = "SELECT * FROM Groups";
        List developers = jdbcTemplate.query(SQL, new GroupMapper());
        return developers;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        String SQL = "DELETE FROM Groups WHERE id = ?";
        jdbcTemplate.update(SQL, id);
        return true;
    }

    @Override
    public boolean delete(Group entity) throws SQLException {
        if (entity != null){
            delete(entity.getId());
            return true;
        }
        return false;
    }
}
