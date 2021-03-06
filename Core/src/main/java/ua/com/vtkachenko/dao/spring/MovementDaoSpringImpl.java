package ua.com.vtkachenko.dao.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.com.vtkachenko.dao.MovementDao;
import ua.com.vtkachenko.dao.ProductDao;
import ua.com.vtkachenko.dao.ProductDaoImpl;
import ua.com.vtkachenko.entity.Group;
import ua.com.vtkachenko.entity.Movement;
import ua.com.vtkachenko.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovementDaoSpringImpl implements MovementDao {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public MovementDaoSpringImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public MovementDaoSpringImpl() {
    }

    public class MovementMapper implements RowMapper {
        @Override
        public Movement mapRow(ResultSet rs, int rowNum) throws SQLException {
            Movement movement = new Movement();
            Product product = new Product();
            product.setId(rs.getLong("product_id"));
            Group group = new Group();
            group.setId(rs.getLong("group_id"));
            movement.setId(rs.getLong("id"));
            movement.setProduct(product);
            movement.setGroup(group);
            movement.setSumm(rs.getDouble("summ"));
            return movement;
        }
    }

    @Override
    public Movement create(Movement entity) throws SQLException {
        String SQL = "INSERT INTO Movements (product_id, group_id, summ) VALUES (?,?,?)";
        jdbcTemplate.update(SQL, entity.getProduct().getId(), entity.getGroup().getId(), entity.getSumm());
        return entity;
    }

    @Override
    public Movement update(Movement entity) throws SQLException {
        if (find(entity.getId()) != null) {
            String SQL = "UPDATE Movements SET product_id = ?, group_id = ?, summ = ? WHERE id = ?";
            jdbcTemplate.update(SQL, entity.getProduct().getId(), entity.getGroup().getId(), entity.getSumm(), entity.getId());
            return entity;
        }
        return null;
    }

    @Override
    public Movement find(long id) throws SQLException {
        String SQL = "SELECT * FROM Movements WHERE id = ?";
        Object result =  jdbcTemplate.query(SQL,new Object[]{id}, new MovementMapper());
        if (result.getClass() == ArrayList.class && ((ArrayList) result).size() ==0){
            return null;
        } else {
            return (Movement) ((ArrayList) result).get(0);
        }
    }

    @Override
    public List<Movement> findAll() throws SQLException {
        String SQL = "SELECT * FROM Movements";
        List movements = jdbcTemplate.query(SQL, new MovementMapper());
        return movements;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        String SQL = "DELETE FROM Movements WHERE id = ?";
        jdbcTemplate.update(SQL, id);
        return true;
    }

    @Override
    public boolean delete(Movement entity) throws SQLException {
        if (entity != null){
            delete(entity.getId());
            return true;
        }
        return false;
    }
}
