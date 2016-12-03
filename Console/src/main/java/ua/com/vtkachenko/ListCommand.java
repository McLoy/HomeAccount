package ua.com.vtkachenko;

import org.kohsuke.args4j.Option;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import ua.com.vtkachenko.dao.GenericDao;

import java.sql.SQLException;

public class ListCommand implements Command, ApplicationContextAware {

//    @Option(name = "-file", usage = "Input filename with cities")
//    private String fileName;
    @Option(name = "-e", usage = "Entity type")
    private String e;
    private ApplicationContext applicationContext;


    @Override
    public void execute() {
        GenericDao genericDao = applicationContext.getBean(e + "DAO", GenericDao.class);
        try {
            for (Object entity : genericDao.findAll()) {
                System.out.println(entity);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "List of entities";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
