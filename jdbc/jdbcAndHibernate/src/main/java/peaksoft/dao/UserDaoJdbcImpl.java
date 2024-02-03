package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String query = """
                create table if not exists users(
                id serial primary key,
                name varchar,
                last_name varchar,
                age smallint
                );
                """;
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println("Successfully created user table!!!");
        }catch (SQLException e){
            throw new RuntimeException("Doesn't created!!!");
        }
    }

    public void dropUsersTable() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("drop table if exists users;");
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Dropped table users!!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = """
                insert into users(name, last_name, age)
                values(?, ?, ?);
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Successfully saved user!!!");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try(PreparedStatement preparedStatementstatement = connection.prepareStatement("delete from users where id = ?")){
            preparedStatementstatement.setLong(1, id);
            preparedStatementstatement.execute();
            System.out.println("Successfully removed user by id.");
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("select * from users");
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                users.add(user);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        String query = "truncate table users;";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println("Cleaned table!!!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}