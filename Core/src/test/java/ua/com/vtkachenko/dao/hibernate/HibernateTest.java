package ua.com.vtkachenko.dao.hibernate;


import org.hibernate.Session;
import ua.com.vtkachenko.entity.Description;
import ua.com.vtkachenko.entity.Group;
import ua.com.vtkachenko.entity.Product;

public class HibernateTest {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //Add new Group object
        Group group = new Group();
        group.setName("New group");
        session.save(group);

        //Des
        Description descr = new Description("New description");
        session.save(descr);
        //Add new Product with description
        Product product = new Product();
        product.setName("New product");
        product.setDescription(descr);
        session.save(product);

        session.getTransaction().commit();
        HibernateUtil.shutdown();

    }
}
