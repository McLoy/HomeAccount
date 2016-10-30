package ua.com.vtkachenko;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class EntryPoint {
    public static void main(String[] args) {
//        App app = new App("Hello world!");
//        app.setName("Home account");
//        app.seyHello();
        ApplicationContext context = new GenericXmlApplicationContext("app-context.xml");
        Hellower app = context.getBean(Hellower.class);
        app.seyHello();
    }
}
