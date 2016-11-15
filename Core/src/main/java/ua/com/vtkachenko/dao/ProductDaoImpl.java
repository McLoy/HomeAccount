package ua.com.vtkachenko.dao;

import ua.com.vtkachenko.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private final Connection connection;

    public ProductDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public Product create(Product entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Product (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getName());
        int result = statement.executeUpdate();
        ResultSet ret_id = statement.getGeneratedKeys();
        if (ret_id.next()){
            entity.setId(ret_id.getLong(1));
            PreparedStatement statement3 = connection.prepareStatement("INSERT INTO Description (product_id, descr) VALUES (?, ?)");
            statement3.setLong(1, ret_id.getLong(1));
            statement3.setString(2, entity.getDescr());
            int result3 = statement3.executeUpdate();
        }
        return entity;
//        return null;
    }

    private int getId_product(Product entity, Product product) throws SQLException {
        Statement statement1 = connection.createStatement();
        ResultSet result1 = statement1.executeQuery("SELECT id FROM Product WHERE name = '" + entity.getName() + "'");
        int id_product = 0;
        if (result1.next()){
            id_product = result1.getInt("id");
        }
        product.setId((long) id_product);
        return id_product;
    }

    public Product update(Product entity) throws SQLException{
        Product product = new Product();
        product.setName(entity.getName());
        long id_product = getId_product(entity, product);
        PreparedStatement statement3 = connection.prepareStatement("UPDATE Description SET descr = ? WHERE product_id = ?");
        statement3.setString(1, entity.getDescr());
        statement3.setLong(2, id_product);
        int result3 = statement3.executeUpdate();
        product.setDescr(entity.getDescr());
        product.setId(id_product);
        return product;
    }

    public Product find(long id) throws SQLException{
        Statement statementFind = connection.createStatement();
        ResultSet resultFind = statementFind.executeQuery("SELECT name FROM Product WHERE id = " + id);
        Product product = new Product();
        if (resultFind.next()){
            product.setName(resultFind.getString("name"));
            product.setId(id);
        }
        return product;
    }

    public List<Product> findAll() throws SQLException{
        Statement statementFind = connection.createStatement();
        ResultSet resultFind = statementFind.executeQuery("SELECT * FROM Product");
        List<Product> listPr = new ArrayList<>();
        while (resultFind.next()){
            Product product = new Product();
            product.setName(resultFind.getString("name"));
            product.setId(resultFind.getLong("id"));
            listPr.add(product);
        }
        return listPr;
    }

    public boolean delete(long id) throws SQLException {
        Statement statementFind = connection.createStatement();
        int res = statementFind.executeUpdate("DELETE FROM Product WHERE id = " + id);
        int resDescr = 0;
        if (res == 1){
            Statement statementDescr = connection.createStatement();
            resDescr = statementDescr.executeUpdate("DELETE FROM Description WHERE id = " + id);
        }
        return res == 1 && resDescr == 1 ? true : false;
    }

    public boolean delete(Product entity) throws SQLException{
        if (entity != null){
            delete(entity.getId());
            return true;
        }
        return false;
    }

//    public static void main(String[] args) throws SQLException {
//        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account", "root", "root")){
//
//            ProductDao dao = new ProductDaoImpl(con);
//            Product product = new Product();
//            product.setName("Bread");
//            product.setDescr("White");
//            Product res = dao.create(product);
//            System.out.println("Product: " + res.getName() + ", id: " + res.getId() + ", descr: " + res.getDescr());
//
////            ProductDao dao = new ProductDaoImpl(con);
////            List<Product> f = new ArrayList();
////            f = dao.findAll();
////            for (Product product : f) {
////                System.out.println("Product: " + product.getName() + ", id: " + product.getId());
////            }
//
////            ProductDao dao = new ProductDaoImpl(con);
////            dao.delete(new Product());
////            List<Product> f = new ArrayList();
////            f = dao.findAll();
////            for (Product product : f) {
////                System.out.println("Product: " + product.getName() + ", id: " + product.getId());
////            }
//
////            ProductDao dao = new ProductDaoImpl(con);
////            Product product = new Product();
////            product.setName("Bread");
////            product.setDescr("Grey");
////            Product res = dao.update(product);
////            System.out.println("Product: " + res.getName() + ", id: " + res.getId() + ", descr: " + res.getDescr());
//
//
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//
//    }
}
