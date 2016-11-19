package ua.com.vtkachenko;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import ua.com.vtkachenko.dao.GroupDao;
import ua.com.vtkachenko.dao.GroupDaoSpringImpl;
import ua.com.vtkachenko.entity.Group;

import java.sql.SQLException;

public class EntryPoint {
    public static void main(String[] args) throws SQLException {
//        App app = new App("Hello world!");
//        app.setName("Home account");
//        app.seyHello();

//        ApplicationContext context = new GenericXmlApplicationContext("app-context.xml");
//        Hellower app = context.getBean(Hellower.class);
//        app.seyHello();

        ApplicationContext context =
                new GenericXmlApplicationContext("app-context.xml");

        GroupDaoSpringImpl groupDAO = (GroupDaoSpringImpl) context.getBean(GroupDao.class);
        Group group = new Group();
        group.setName("Example");
        groupDAO.create(group);
    }
}
