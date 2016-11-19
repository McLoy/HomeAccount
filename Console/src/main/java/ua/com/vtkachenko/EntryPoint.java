package ua.com.vtkachenko;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

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
        Terminal terminal = context.getBean(Terminal.class);
        terminal.start();

//        GroupDaoSpringImpl groupDAO = (GroupDaoSpringImpl) context.getBean(GroupDao.class);
//        Group group = new Group();
//        group.setName("Example");
//        groupDAO.create(group);
    }
}
