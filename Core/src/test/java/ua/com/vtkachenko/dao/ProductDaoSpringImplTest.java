package ua.com.vtkachenko.dao;

import org.fest.assertions.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import ua.com.vtkachenko.entity.Product;

import java.util.List;

public class ProductDaoSpringImplTest {

    private EmbeddedDatabase db;
    private JdbcTemplate jdbcTemplate;
    private ProductDao productDao;

    @Before
    public void setUp() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        db = builder.setType(EmbeddedDatabaseType.DERBY).addScript("create-db.sql").addScript("insert-data.sql").build();
        jdbcTemplate = new JdbcTemplate(db);
        productDao = new ProductDaoSpringImpl(jdbcTemplate);
    }

    @Test
    public void findAll() throws Exception {
        List products = productDao.findAll();
        Assertions.assertThat(products.size()).isEqualTo(2);
    }

    @Test
    public void find() throws Exception {
        Product product = productDao.find(1);
        Assertions.assertThat(product.getName()).isEqualTo("Bread");
    }

    @Test
    public void create() throws Exception {
//        Product product = new Product();
//        product.setName("Milk");
//        product.setDescr("For children");
//        Product res = productDao.create(product);
//        Assertions.assertThat(res.getName()).isEqualTo("Milk");
//        Assertions.assertThat(res.getDescr()).isEqualTo("For children");
//        Assertions.assertThat(res.getId()).isEqualTo(3);
    }

    @Test
    public void deleteById() throws Exception {
//        groupDao.delete(1);
//        Assertions.assertThat(groupDao.find(1)).isNull();
    }

    @Test
    public void delete() throws Exception {
//        Group group = new Group();
//        group.setName("House");
//        group.setId(2);
//        groupDao.delete(group);
//        Assertions.assertThat(groupDao.find(2)).isNull();
    }

    @After
    public void tearDown(){
        db.shutdown();
    }
}
