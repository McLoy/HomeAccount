package ua.com.vtkachenko.dao;

import ua.com.vtkachenko.entity.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class GroupDaoImpl implements GroupDao{
    private final Connection connection;

    GroupDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Group create(Group entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO `Group` (name) VALUES (?)");
        statement.setString(1, entity.getName());
        int result = statement.executeUpdate();
        Group group = new Group();
        group.setName(entity.getName());
        getId_group(entity, group);
        return group;
    }

    private void getId_group(Group entity, Group group) throws SQLException {
        Statement statement1 = connection.createStatement();
        ResultSet result1 = statement1.executeQuery("SELECT id FROM `Group` WHERE name = '" + entity.getName() + "'");
        int id_group = 0;
        if (result1.next()){
            id_group = result1.getInt("id");
        }
        group.setId((long) id_group);
    }

    @Override
    public Group update(Group entity) throws SQLException {
        if (find(entity.getId()) != null){
            PreparedStatement statementUpd = connection.prepareStatement("UPDATE `Group` SET name = ? WHERE id = ?");
            statementUpd.setString(1, entity.getName());
            statementUpd.setLong(2, entity.getId());
            int rez = statementUpd.executeUpdate();
            return entity;
        }
        return null;
    }

    @Override
    public Group find(long id) throws SQLException {
        Statement statementFind = connection.createStatement();
        ResultSet res = statementFind.executeQuery("SELECT name FROM `Group` WHERE id = " + id);
        Group group = new Group();
        if (res.next()){
            group.setName(res.getString("name"));
            group.setId(id);
        }
        return group;
    }

    @Override
    public List<Group> findAll() throws SQLException {
        Statement statementFind = connection.createStatement();
        ResultSet resSet = statementFind.executeQuery("SELECT * FROM `Group`");
        List<Group> listOfGroup = new ArrayList<>();
        while (resSet.next()){
            Group group = new Group();
            group.setName(resSet.getString("name"));
            group.setId(resSet.getLong("id"));
            listOfGroup.add(group);
        }
        return listOfGroup;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        Statement statementFind = connection.createStatement();
        int res = statementFind.executeUpdate("DELETE FROM `Group` WHERE id = " + id);
        return res == 1 ? true : false;
    }

    @Override
    public boolean delete(Group entity) throws SQLException {
        if (entity != null){
            delete(entity.getId());
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account", "root", "root")){
//            GroupDao dao = new GroupDaoImpl(con);
//            Group group = new Group();
//            group.setName("Car");
//            Group res = dao.create(group);
//            System.out.println("Group: " + res.getName() + ", id: " + res.getId());

//            GroupDao dao = new GroupDaoImpl(con);
//            List<Group> f = new ArrayList();
//            f = dao.findAll();
//            for (Group group : f) {
//                System.out.println("Group: " + group.getName() + ", id: " + group.getId());
//            }

            GroupDao dao = new GroupDaoImpl(con);
            dao.delete(2);
            List<Group> f = new ArrayList();
            f = dao.findAll();
            for (Group group : f) {
                System.out.println("Group: " + group.getName() + ", id: " + group.getId());
            }

//            GroupDao dao = new GroupDaoImpl(con);
//            Group group = new Group();
//            group.setId(2);
//            group.setName("Bus");
//            Group res = dao.update(group);
//            System.out.println("Group: " + res.getName() + ", id: " + res.getId());

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
