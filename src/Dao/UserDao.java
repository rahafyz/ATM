package Dao;

import Exeptions.ExceptionHandler;
import UsefullClasses.MyMethod;
import database.DbConnector;
import model.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserDao implements Dao<User>{



    private static final String FIND_BY_ID = "SELECT * FROM user WHERE user_id=?";
    private static final String FIND_ALL = "SELECT * FROM user ORDER BY user_id";
    private static final String UPDATE = "UPDATE user SET first_name=?, last_name=?, nationalId=?, birthday=?  WHERE user_id=?";
    private static final String DELETE = "DELETE FROM user WHERE user_id=?";
    private static final String INSERT = "INSERT INTO user(first_name, last_name, nationalId, birthday) VALUES(?, ?, ?, ?)";


    @Override
    public Optional get(Integer id) {
        Optional<User> findUser = Optional.empty();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);){
             preparedStatement.setInt(1, id);
             ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User(resultSet.getString(2),resultSet.getString(3)
                        ,resultSet.getString(4),resultSet.getDate(5));
                user.setId(resultSet.getInt(1));
                findUser = Optional.of(user);
            }

        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
        return findUser;
    }

    @Override
    public List getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();){
            while (resultSet.next()){
                User user = new User(resultSet.getString(2),resultSet.getString(3)
                ,resultSet.getString(4),resultSet.getDate(5));
                user.setId(resultSet.getInt(1));
                users.add(user);
            }

        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
        return users;
    }

    @Override
    public void save(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, RETURN_GENERATED_KEYS);){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getFamily());
            preparedStatement.setString(3, user.getNationalId());
            preparedStatement.setDate(4, (Date) user.getBirthday());
            int resultSet = preparedStatement.executeUpdate();
            ResultSet generatedKeysResultSet = preparedStatement.getGeneratedKeys();
            generatedKeysResultSet.next();
            Integer id = generatedKeysResultSet.getInt(1);

            user.setId(id);

            MyMethod.print(resultSet);


        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
    }

    @Override
    public void update(User user, String[] params) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);){
            preparedStatement.setString(1, params[0]);
            preparedStatement.setString(2, params[1]);
            preparedStatement.setString(3, params[2]);
            preparedStatement.setDate(4, Date.valueOf(params[3]));
            preparedStatement.setInt(5,  user.getId());
            preparedStatement.executeUpdate();
            System.out.println("Table Updated Successfully");



        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
    }

    @Override
    public void delete(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);){
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
            System.out.println("Data deleted Successfully");


        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
    }


    private Connection getConnection(){
        Connection conn = DbConnector.getInstance().getConnection();
        return conn;
    }

}
