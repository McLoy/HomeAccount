package ua.com.vtkachenko.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ua.com.vtkachenko.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ProductDaoSpringImpl implements ProductDao {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public class ProductMapper implements RowMapper {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setDescr(rs.getString("descr"));
            return product;
        }
    }

    @Override
    public Product create(final Product entity) throws SQLException {
        final String SQL = "INSERT INTO Products (name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement pst =
                                con.prepareStatement(SQL, new String[] {"id"});
                        pst.setString(1, entity.getName());
                        return pst;
                    }
                },
                keyHolder);
        long id = (Long)keyHolder.getKey();
        if (id != 0){
            entity.setId(id);
            String SQL2 = "INSERT INTO Descriptions (product_id, descr) VALUES (?, ?)";
            jdbcTemplate.update(SQL2, id, entity.getDescr());
        }
        return entity;
    }

    @Override
    public Product update(Product entity) throws SQLException {
        String SQL = "UPDATE Products SET name = ? WHERE id = ?";
        String SQL2 = "UPDATE Descriptions SET name = ? WHERE product_id = ?";
        jdbcTemplate.update(SQL, entity.getName(), entity.getId());
        jdbcTemplate.update(SQL2, entity.getDescr(), entity.getId());
        return null;
    }

    @Override
    public Product find(long id) throws SQLException {
        String SQL = "SELECT * FROM Products LEFT JOIN Descriptions ON id = product_id WHERE id = ?";
        Product product = (Product) jdbcTemplate.queryForObject(SQL, new Object[]{id}, new ProductMapper());
        return product;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        String SQL = "SELECT * FROM Products LEFT JOIN Descriptions ON id = product_id";
        List developers = jdbcTemplate.query(SQL, new ProductMapper());
        return developers;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Product entity) throws SQLException {
        return false;
    }
}
