package ua.com.vtkachenko.dao;

import org.fest.assertions.Assertions;
import org.junit.Test;
import ua.com.vtkachenko.entity.Group;

import java.sql.Connection;
import java.sql.DriverManager;

public class GroupDaoImplTest {

    public static final String URL = "jdbc:mysql://localhost:3306/account";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    public static final int ID = 6;//You need to set id!!!

    @Test
    public void create() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            GroupDao dao = new GroupDaoImpl(con);
            Group group = new Group();
            group.setName("Bus");
            Group res = dao.create(group);
            Assertions.assertThat(res.getName()).isEqualTo("Bus");
            Assertions.assertThat(res.getId()).isNotEqualTo(0);
        }
    }

    @Test
    public void update() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            GroupDao dao = new GroupDaoImpl(con);
            Group group = new Group();
            group.setId(ID);//You need to set id!!!
            group.setName("Transport");
            Group res = dao.update(group);
            Assertions.assertThat(res.getName()).isEqualTo("Transport");
        }
    }

    @Test
    public void findAll() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            GroupDao dao = new GroupDaoImpl(con);
            Assertions.assertThat(dao.findAll().size()).isEqualTo(3);
        }
    }

    @Test
    public void find() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            GroupDao dao = new GroupDaoImpl(con);
            Assertions.assertThat(dao.find(ID).getId()).isNotEqualTo(0);//You need to set id!!!
        }
    }

    @Test
    public void delete() throws Exception {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            GroupDao dao = new GroupDaoImpl(con);
            Group pr = new Group();
            pr.setId(ID);//You need to set id!!!
            Assertions.assertThat(dao.delete(pr)).isTrue();
        }
    }
}
