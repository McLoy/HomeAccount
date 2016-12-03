package ua.com.vtkachenko;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class EntryPoint {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");

        Scanner scan = new Scanner(System.in);
        String argums;
        List<String> arrArgs;
        while (true){
            argums = scan.nextLine();
            StringTokenizer st = new StringTokenizer(argums);
            arrArgs = new ArrayList<>();
            while (st.hasMoreTokens()){
                arrArgs.add(st.nextToken());
            }

            try {

                Command cmd = context.getBean(arrArgs.get(0), Command.class);

                CmdLineParser cmdLineParser = new CmdLineParser(cmd);
                try {
                    arrArgs.remove(0);
                    cmdLineParser.parseArgument(arrArgs.toArray(new String[arrArgs.size()]));
                    cmd.execute();
                } catch (CmdLineException e) {
                    System.err.println(e.getMessage());
                    cmdLineParser.printUsage(System.err);
                }
            } catch (NoSuchBeanDefinitionException e){
                System.out.println("Command not found");
            }

//            if (arrArgs.get(0).equals("exit")){
//                System.out.println("Bye!");
//                System.exit(0);
//            } else {
//                System.out.println("Wrong command");
//                System.exit(1);
//            }
            break;
        }
//        scan.close();

        //System.out.println("Hi Vlad");
    }
}
