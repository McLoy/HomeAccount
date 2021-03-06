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
import ua.com.vtkachenko.dao.spring.GroupDaoSpringImpl;
import ua.com.vtkachenko.entity.Group;

import java.util.List;

public class GroupDaoSpringImplTest {

    private EmbeddedDatabase db;
    private JdbcTemplate jdbcTemplate;
    private GroupDao groupDao;

    @Before
    public void setUp() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        db = builder.setType(EmbeddedDatabaseType.DERBY).addScript("create-db.sql").addScript("insert-data.sql").build();
        jdbcTemplate = new JdbcTemplate(db);
        groupDao = new GroupDaoSpringImpl(jdbcTemplate);
    }

    @Test
    public void findAll() throws Exception {
        List groups = groupDao.findAll();
        Assertions.assertThat(groups.size()).isEqualTo(3);
    }

    @Test
    public void find() throws Exception {
        Group group = groupDao.find(1);
        Assertions.assertThat(group.getName()).isEqualTo("Transport");
    }

    @Test
    public void create() throws Exception {
        Group group = new Group();
        group.setName("Bus");
        Group res = groupDao.create(group);
        Assertions.assertThat(res.getName()).isEqualTo("Bus");
        Assertions.assertThat(res.getId()).isNotEqualTo(4);
    }

    @Test
    public void deleteById() throws Exception {
        groupDao.delete(1);
        Assertions.assertThat(groupDao.find(1)).isNull();
    }

    @Test
    public void delete() throws Exception {
        Group group = new Group();
        group.setName("House");
        group.setId(2);
        groupDao.delete(group);
        Assertions.assertThat(groupDao.find(2)).isNull();
    }

    @After
    public void tearDown(){
        db.shutdown();
    }
}
