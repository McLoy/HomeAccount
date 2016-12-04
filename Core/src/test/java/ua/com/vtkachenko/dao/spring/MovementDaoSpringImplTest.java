package ua.com.vtkachenko.dao.spring;

import org.fest.assertions.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import ua.com.vtkachenko.dao.GroupDao;
import ua.com.vtkachenko.dao.MovementDao;
import ua.com.vtkachenko.dao.ProductDao;
import ua.com.vtkachenko.entity.Description;
import ua.com.vtkachenko.entity.Group;
import ua.com.vtkachenko.entity.Movement;
import ua.com.vtkachenko.entity.Product;

import java.util.List;

public class MovementDaoSpringImplTest {

    private EmbeddedDatabase db;
    private JdbcTemplate jdbcTemplate;
    private MovementDao movementDao;

    @Before
    public void setUp() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        db = builder.setType(EmbeddedDatabaseType.DERBY).addScript("create-db.sql").addScript("insert-data.sql").build();
        jdbcTemplate = new JdbcTemplate(db);
        movementDao = new MovementDaoSpringImpl(jdbcTemplate);
    }

    @Test
    public void findAll() throws Exception {
        List movements = movementDao.findAll();
        Assertions.assertThat(movements.size()).isEqualTo(2);
    }

    @Test
    public void find() throws Exception {
        Movement movement = movementDao.find(1);
        Assertions.assertThat(movement.getProduct().getId()).isEqualTo(1);
    }

    @Test
    public void create() throws Exception {
        Product product = new Product();
        product.setName("Auto oil");
        product.setDescription(new Description("Engine oil"));
        ProductDao productDao = new ProductDaoSpringImpl(jdbcTemplate);
        List listOfProducts = productDao.findAll();
        if (! listOfProducts.contains(product.getName())){
            productDao.create(product);
        }
        Group group = new Group();
        group.setName("Transport");
        GroupDao groupDao = new GroupDaoSpringImpl(jdbcTemplate);
        List listOfGroups = groupDao.findAll();
        if (! listOfGroups.contains(group)){
            groupDao.create(group);
        }
        Movement movement = new Movement(product, group, 200);
        Movement res = movementDao.create(movement);
        Assertions.assertThat(res.getProduct().getId()).isEqualTo(product.getId());
    }

    @Test
    public void deleteById() throws Exception {
        movementDao.delete(1);
        Assertions.assertThat(movementDao.find(1)).isNull();
    }

    @Test
    public void delete() throws Exception {
        Movement movement = new Movement();
        movement.setId(2);
        movementDao.delete(movement);
        Assertions.assertThat(movementDao.find(2)).isNull();
    }

    @After
    public void tearDown(){
        db.shutdown();
    }
}
