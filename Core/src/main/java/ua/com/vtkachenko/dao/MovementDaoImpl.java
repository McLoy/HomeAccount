package ua.com.vtkachenko.dao;

import ua.com.vtkachenko.entity.Group;
import ua.com.vtkachenko.entity.Movement;
import ua.com.vtkachenko.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovementDaoImpl implements MovementDao {

    private final Connection connection;

    public MovementDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Movement create(Movement entity) throws SQLException {
        entity = findAndSetIdForProductAndGroup(entity);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Movements (product_id, group_id, summ) VALUES (?,?,?)");
        statement.setLong(1, entity.getProduct().getId());
        statement.setLong(2, entity.getGroup().getId());
        statement.setDouble(3, entity.getSumm());
        int result = statement.executeUpdate();
        return entity;
    }

    private Movement findAndSetIdForProductAndGroup(Movement entity) throws SQLException{
        Product product = entity.getProduct();
        Group group = entity.getGroup();
        if (product.getId() == 0){
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM Products WHERE name = (?)");
            statement.setString(1, product.getName());
            ResultSet res = statement.executeQuery();
            if (! res.next()){
                ProductDao productDao = new ProductDaoImpl(connection);
                product = productDao.create(product);
            } else {
                product.setId(res.getLong("id"));
            }
            entity.setProduct(product);
        }
        if (group.getId() == 0){
            PreparedStatement statementGroup = connection.prepareStatement("SELECT id FROM Groups WHERE name = (?)");
            statementGroup.setString(1, group.getName());
            ResultSet resGr = statementGroup.executeQuery();
            if (! resGr.next()){
                GroupDao groupDao = new GroupDaoImpl(connection);
                group = groupDao.create(group);
            } else {
                group.setId(resGr.getLong("id"));
            }
            entity.setGroup(group);
        }
        return entity;
    }

    @Override
    public Movement update(Movement entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Movements WHERE id = (?)");
        statement.setLong(1, entity.getId());
        ResultSet res = statement.executeQuery();
        if (res.next()){
            ProductDao productDao = new ProductDaoImpl(connection);
            GroupDao groupDao = new GroupDaoImpl(connection);
            PreparedStatement statement1 = connection.prepareStatement("UPDATE Movements SET product_id = ?, group_id = ?, summ = ? WHERE id = ?");
            entity = findAndSetIdForProductAndGroup(entity);
            statement1.setLong(1, entity.getProduct().getId());
            statement1.setLong(2, entity.getGroup().getId());
            statement1.setDouble(3, entity.getSumm());
            statement1.setLong(4, entity.getId());
            int re = statement1.executeUpdate();
            return entity;
        }
        return null;
    }

    @Override
    public Movement find(long id) throws SQLException {
        Movement movement = new Movement();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Movements WHERE id = ?");
        statement.setLong(1, id);
        ResultSet res = statement.executeQuery();
        if (res.next()){
            Product product = new Product();
            product.setId(res.getLong("product_id"));
            movement.setProduct(product);
            Group group = new Group();
            group.setId(res.getLong("group_id"));
            movement.setGroup(group);
            movement.setSumm(res.getDouble("summ"));
            return movement;
        }
        return null;
    }

    @Override
    public List<Movement> findAll() throws SQLException {
        Statement statementFind = connection.createStatement();
        ResultSet resultFind = statementFind.executeQuery("SELECT * FROM Movements");
        List<Movement> listPr = new ArrayList<>();
        while (resultFind.next()) {
            Movement movement = new Movement();
            Product product = new Product();
            long id_prod = resultFind.getLong("product_id");
            product.setId(id_prod);
            ProductDao pd = new ProductDaoImpl(connection);
            Product prod = pd.find(id_prod);
            product.setName(prod.getName());
            product.setDescription(pd.find(prod.getId()).getDescription());
            Group group = new Group();
            long id_group = resultFind.getLong("group_id");
            GroupDao gr = new GroupDaoImpl(connection);
            group.setId(id_group);
            group.setName(gr.find(id_group).getName());
            movement.setProduct(product);
            movement.setGroup(group);
            movement.setSumm(resultFind.getDouble("summ"));
            listPr.add(movement);
        }
        return listPr;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        Statement statementFind = connection.createStatement();
        int res = statementFind.executeUpdate("DELETE FROM Movements WHERE id = " + id);
        return res == 1;
    }

    @Override
    public boolean delete(Movement entity) throws SQLException {
        if (entity != null){
            delete(entity.getId());
            return true;
        }
        return false;
    }
}
