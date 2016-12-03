package ua.com.vtkachenko;

import org.kohsuke.args4j.CmdLineParser;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.SystemEnvironmentPropertySource;

import java.beans.Beans;
import java.util.List;
import java.util.Map;

public class HelpCommand implements Command, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void execute() {
        Map<String, Command> beans = applicationContext.getBeansOfType(Command.class);
        for (Map.Entry<String, Command> entry : beans.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getDescription());
            CmdLineParser lineParser = new CmdLineParser(entry.getValue());
            lineParser.printUsage(System.out);
        }
    }

    @Override
    public String getDescription() {
        return "Help info";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
