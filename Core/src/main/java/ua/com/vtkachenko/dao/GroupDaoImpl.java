package ua.com.vtkachenko.dao;

import ua.com.vtkachenko.entity.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDaoImpl implements GroupDao{
    private final Connection connection;

    public GroupDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Group create(Group entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Groups (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getName());
        int result = statement.executeUpdate();
        ResultSet ret_id = statement.getGeneratedKeys();
        if (ret_id.next()) {
            entity.setId(ret_id.getLong(1));
        }
        return entity;
    }

    @Override
    public Group update(Group entity) throws SQLException {
        if (find(entity.getId()) != null){
            PreparedStatement statementUpd = connection.prepareStatement("UPDATE Groups SET name = ? WHERE id = ?");
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
        ResultSet res = statementFind.executeQuery("SELECT name FROM Groups WHERE id = " + id);
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
        ResultSet resSet = statementFind.executeQuery("SELECT * FROM Groups");
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
        int res = statementFind.executeUpdate("DELETE FROM Groups WHERE id = " + id);
        return res == 1;
    }

    @Override
    public boolean delete(Group entity) throws SQLException {
        if (entity != null){
            delete(entity.getId());
            return true;
        }
        return false;
    }
}
