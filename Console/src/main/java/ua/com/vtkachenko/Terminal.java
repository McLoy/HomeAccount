package ua.com.vtkachenko;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.vtkachenko.entity.Group;
import ua.com.vtkachenko.entity.Movement;
import ua.com.vtkachenko.entity.Product;

import java.sql.Connection;
import java.util.List;
@Component
public class Terminal {

    public static final String URL = "jdbc:mysql://localhost:3306/account";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    public static final String SEPARATOR = "@";
    @Autowired
    private UbuntuConsole console;

    public static void main(String[] args) {
        System.out.println("Greetings from Home account!");
        System.out.println("Choose 1 - for reading all data from DB, 2 - for adding new");

        Connection con = null;
//        try {

    }

    public static void showStructure(List obj){
        for (Object o :obj) {
            if (o instanceof Product){
                Product p = (Product) o;
                System.out.println("Product: " + p.getName() + ", Description: " + p.getDescr());
            } else if (o instanceof Group){
                Group g = (Group) o;
                System.out.println("Group: " + g.getName());
            } else if (o instanceof Movement){
                Movement m = (Movement)o;
                System.out.println("Product(Descr): " + m.getProduct().getName() + "(" + m.getProduct().getDescr() + "), Group: " + m.getGroup().getName() + ", Summ: " + m.getSumm());
            }
        }
    }


    public void start() {
        while (true) {
            Command command = console.readCommand();
            if (Command.EXIT == command) {
                console.println("Bye.");
                break;
            }
        }


//        con = DriverManager.getConnection(URL, USER, PASSWORD);
//        Scanner scan = new Scanner(System.in);
//        int choise = scan.nextInt();
//        if (choise == 1) {
//            MovementDao md = new MovementDaoImpl(con);
//            showStructure(md.findAll());
//        } else if (choise == 2) {
//            System.out.println("Enter row in format: Product" + SEPARATOR + "Description" + SEPARATOR + "Group" + SEPARATOR + "Summ like 'Bread" + SEPARATOR + "White" + SEPARATOR + "Food" + SEPARATOR + "8.14'");
//            boolean repeat = false;
//            do {
//                String scanned = scan.next();
//                if (scanned.length() > 0) {
//                    String newLine[] = scanned.split(SEPARATOR);
//                    String productName = newLine[0];
//                    String productDescription = newLine[1];
//                    String groupName = newLine[2];
//                    double summ = Double.parseDouble(newLine[3]);
//
//                    Product myProduct = new Product();
//                    ProductDao productDao = new ProductDaoImpl(con);
//                    myProduct.setDescr(productDescription);
//                    myProduct.setName(productName);
//                    if (productDao.update(myProduct) == null) productDao.create(myProduct);
//
//
//                    Group myGroup = new Group();
//                    GroupDao groupDao = new GroupDaoImpl(con);
//                    myGroup.setName(groupName);
//                    if (groupDao.update(myGroup) == null) groupDao.create(myGroup);
//
//                    MovementDao movementDao = new MovementDaoImpl(con);
//                    Movement myMovement = new Movement(myProduct, myGroup, summ);
//                    if (movementDao.update(myMovement) == null) movementDao.create(myMovement);
//                }
//                System.out.println("For adding one more choose 3, for exit choose 0");
//                int rez = scan.nextInt();
//                repeat = rez == 3;
//                if (rez == 0) break;
//            } while (repeat == true);
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
    }
}
