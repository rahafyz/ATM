package Dao;


import Application.AccountApplication;
import Application.UserApplication;
import Exeptions.ExceptionHandler;
import UsefullClasses.MyMethod;
import database.DbConnector;
import model.Transaction;
import model.TransactionType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class TransactionDao implements Dao<Transaction> {


    private static final String FIND_BY_ID = "SELECT * FROM transaction WHERE transaction_id=?";
    private static final String FIND_ALL = "SELECT * FROM transaction ORDER BY transaction_id";
    private static final String UPDATE = "UPDATE transaction SET amount=?, Transaction_type=?," +
            " account_id=?  WHERE transaction_id=?";
    private static final String DELETE = "DELETE FROM transaction WHERE transaction_id=?";
    private static final String INSERT = "INSERT INTO transaction(amount, Transaction_type, account_id) VALUES(?, ?, ?)";


    @Override
    public Optional<Transaction> get(Integer id) {
        Optional<Transaction> findTransaction = Optional.empty();
        Connection connection = getConnection();
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Transaction transaction = new Transaction(resultSet.getDouble(2),
                        TransactionType.valueOf(resultSet.getString(3)),
                        AccountApplication.getAccount(resultSet.getInt(5))
                        );
                transaction.setId(resultSet.getInt(1));
                transaction.setDate(resultSet.getDate(4));

                findTransaction = Optional.of(transaction);
            }

        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
        return findTransaction;
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactions = new ArrayList<>();
        Connection connection = getConnection();
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);){
             ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Transaction transaction = new Transaction(resultSet.getDouble(2),
                        TransactionType.valueOf(resultSet.getString(3)),
                        AccountApplication.getAccount(resultSet.getInt(5)));
                transaction.setId(resultSet.getInt(1));
                transactions.add(transaction);
            }

        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
        return transactions;
    }

    @Override
    public void save(Transaction transaction) {
        Connection connection = getConnection();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT, RETURN_GENERATED_KEYS);){
            preparedStatement.setDouble(1, transaction.getAmount());
            preparedStatement.setString(2, String.valueOf(transaction.getTransactionType()));
            preparedStatement.setInt(4, transaction.getAccount().getId());
            int resultSet = preparedStatement.executeUpdate();
            ResultSet generatedKeysResultSet = preparedStatement.getGeneratedKeys();
            generatedKeysResultSet.next();
            Integer id = generatedKeysResultSet.getInt(1);

            transaction.setId(id);

            MyMethod.print(resultSet);


        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
    }

    @Override
    public void update(Transaction transaction, String[] params) {
        Connection connection = getConnection();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);){
            preparedStatement.setDouble(1, Double.parseDouble(params[0]));
            preparedStatement.setString(2, params[1]);
            preparedStatement.setDouble(3, Double.valueOf(params[2]));
            preparedStatement.setInt(4, transaction.getAccount().getId());
            preparedStatement.setInt(5,  transaction.getId());
            preparedStatement.executeUpdate();
            System.out.println("Table Updated Successfully");



        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
    }

    @Override
    public void delete(Transaction transaction) {
        Connection connection = getConnection();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE);){
            preparedStatement.setInt(1, transaction.getId());
            preparedStatement.executeUpdate();
            System.out.println("Data deleted Successfully");


        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
    }

    private Connection getConnection(){
        Connection conn = DbConnector.getConnection();
        return conn;
    }
}
