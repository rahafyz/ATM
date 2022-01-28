package Dao;

import Application.UserApplication;
import Exeptions.ExceptionHandler;
import util.MyMethod;
import database.DbConnector;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class AccountDao implements Dao<Account> {

    private static final String FIND_BY_ID = "SELECT * FROM account WHERE account_id=?";
    private static final String FIND_ALL = "SELECT * FROM account ORDER BY account_id";
    private static final String UPDATE = "UPDATE account SET account_number=?, password=?, balance=?, user_id=?  WHERE account_id=?";
    private static final String DELETE = "DELETE FROM account WHERE account_id=?";
    private static final String INSERT = "INSERT INTO account(account_number, password, balance, user_id) VALUES(?, ?, ?, ?)";




    private static final String FIND_BY_AccNUM = "SELECT * FROM account WHERE account_number=?";
    private static final String CHANGE_BALANCE = "UPDATE account SET balance=? WHERE account_id=?";



    @Override
    public Optional<Account> get(Integer id) {
        Optional<Account> findAccount = Optional.empty();
        Connection connection = getConnection();
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Account account = new Account(resultSet.getString(2),resultSet.getString(3)
                        ,resultSet.getDouble(4));
                account.setId(resultSet.getInt(1));
                account.setUser(UserApplication.getUser(resultSet.getInt(5)));
                findAccount = Optional.of(account);
            }

        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
        return findAccount;
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        Connection connection = getConnection();
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);){
             ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Account account = new Account(resultSet.getString(2),resultSet.getString(3)
                        ,resultSet.getDouble(4));
                account.setId(resultSet.getInt(1));
                account.setUser(UserApplication.getUser(resultSet.getInt(5)));
                accounts.add(account);
            }

        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
        return accounts;
    }

    @Override
    public void save(Account account) {
        Connection connection = getConnection();
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, RETURN_GENERATED_KEYS);){
            preparedStatement.setString(1, account.getAccountNumber());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.setInt(4, account.getUser().getId());
            int resultSet = preparedStatement.executeUpdate();
            ResultSet generatedKeysResultSet = preparedStatement.getGeneratedKeys();
            generatedKeysResultSet.next();
            Integer id = generatedKeysResultSet.getInt(1);

            account.setId(id);

            MyMethod.print(resultSet);


        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
    }

    @Override
    public void update(Account account, String[] params) {
        Connection connection = getConnection();
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);){
            preparedStatement.setString(1, params[0]);
            preparedStatement.setString(2, params[1]);
            preparedStatement.setDouble(3, Double.parseDouble(params[2]));
            preparedStatement.setInt(4, account.getUser().getId());
            preparedStatement.setInt(5,  account.getId());
            preparedStatement.executeUpdate();
            System.out.println("Table Updated Successfully");



        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
    }

    @Override
    public void delete(Account account) {
        Connection connection = getConnection();
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);){
            preparedStatement.setInt(1, account.getId());
            preparedStatement.executeUpdate();
            System.out.println("Data deleted Successfully");


        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
    }


    //get account by account number
    public Optional<Account> find_by_accNum(String accNum){
        Optional<Account> findAccount = Optional.empty();
        Connection connection = getConnection();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_AccNUM);
        ){
            preparedStatement.setString(1, accNum);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Account account = new Account(resultSet.getString(2),resultSet.getString(3)
                        ,resultSet.getDouble(4));
                account.setId(resultSet.getInt(1));
                account.setUser(UserApplication.getUser(resultSet.getInt(5)));
                findAccount = Optional.of(account);
            }

        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
        return findAccount;
    }


    public void change_balance(Account account, Double balance){
        Connection connection = getConnection();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_BALANCE);){
            preparedStatement.setDouble(1, balance);
            preparedStatement.setInt(2, account.getId());
            preparedStatement.executeUpdate();
            System.out.println("BALANCE Updated Successfully");



        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
    }

    private Connection getConnection(){
        Connection conn = DbConnector.getConnection();
        return conn;
    }
}
