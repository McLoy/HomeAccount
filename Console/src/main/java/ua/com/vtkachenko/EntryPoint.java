package ua.com.vtkachenko;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import ua.com.vtkachenko.dao.*;
import ua.com.vtkachenko.entity.Group;
import ua.com.vtkachenko.entity.Money;
import ua.com.vtkachenko.entity.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EntryPoint {

    public static final String URL = "jdbc:mysql://localhost:3306/account";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    public static final String SEPARATOR = "@";

    public static void main(String[] args) {
//        App app = new App("Hello world!");
//        app.setName("Home account");
//        app.seyHello();
        ApplicationContext context = new GenericXmlApplicationContext("app-context.xml");
        Hellower app = context.getBean(Hellower.class);
        app.seyHello();

        //Work with DB
        System.out.println("Greetings from Home account!");
        System.out.println("Choose 1 - for reading all data from DB, 2 - for adding new");

        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            Scanner scan = new Scanner(System.in);
            int choise = scan.nextInt();
            if (choise == 1) {
                MoneyDao md = new MoneyDaoImpl(con);
                showStructure(md.findAll());
            } else if (choise == 2) {
                System.out.println("Enter row in format: Product" + SEPARATOR + "Description" + SEPARATOR + "Group" + SEPARATOR + "Summ like 'Bread" + SEPARATOR + "White" + SEPARATOR + "Food" + SEPARATOR + "8.14'");
                boolean repeat = false;
                do {
                    String scanned = scan.next();
                    if (scanned.length() > 0) {
                        String newLine[] = scanned.split(SEPARATOR);
                        String productName = newLine[0];
                        String productDescription = newLine[1];
                        String groupName = newLine[2];
                        double summ = Double.parseDouble(newLine[3]);

                        Product myProduct = new Product();
                        ProductDao productDao = new ProductDaoImpl(con);
                        myProduct.setDescr(productDescription);
                        myProduct.setName(productName);
                        if (productDao.update(myProduct) == null) productDao.create(myProduct);


                        Group myGroup = new Group();
                        GroupDao groupDao = new GroupDaoImpl(con);
                        myGroup.setName(groupName);
                        if (groupDao.update(myGroup) == null) groupDao.create(myGroup);

                        MoneyDao moneyDao = new MoneyDaoImpl(con);
                        Money myMoney = new Money(myProduct, myGroup, summ);
                        if (moneyDao.update(myMoney) == null) moneyDao.create(myMoney);
                    }
                    System.out.println("For adding one more choose 3, for exit choose 0");
                    int rez = scan.nextInt();
                    repeat = rez == 3;
                    if (rez == 0) break;
                } while (repeat == true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showStructure(List obj){
        for (Object o :obj) {
            if (o instanceof Product){
                Product p = (Product) o;
                System.out.println("Product: " + p.getName() + ", Description: " + p.getDescr());
            } else if (o instanceof Group){
                Group g = (Group) o;
                System.out.println("Group: " + g.getName());
            } else if (o instanceof Money){
                Money m = (Money)o;
                System.out.println("Product(Descr): " + m.getProduct().getName() + "(" + m.getProduct().getDescr() + "), Group: " + m.getGroup().getName() + ", Summ: " + m.getSumm());
            }
        }
    }
}
