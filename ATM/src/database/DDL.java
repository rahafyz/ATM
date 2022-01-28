package database;

import Exeptions.ExceptionHandler;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import static database.DbConfig.loadDDL;

public class DDL {


    static Properties prop = loadDDL();

    private static final String dbCheck = prop.getProperty("dbCheck");
    private static final String tblCheck = prop.getProperty("tblCheck");

    private static final String selectDb = prop.getProperty("selectDb");

    private static final String createDb = prop.getProperty("createDb");
    private static final String createUserTbl = prop.getProperty("createUser");
    private static final String createAccountTbl = prop.getProperty("createAccount");
    private static final String createTransactionTbl = prop.getProperty("createTransaction");





    private static boolean dbExist(String dbName){
        Connection connection = DbConnector.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(dbCheck)) {
            preparedStatement.setString(1, dbName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) != 0;
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }

        return false;
    }



    private static boolean tableExist( String tableName){
        Connection connection = DbConnector.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(tblCheck)) {
            preparedStatement.setString(1, tableName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) != 0;
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }

        return false;
    }


    private static void useDb(){
        Connection connection = DbConnector.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(selectDb)) {
            preparedStatement.execute();
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }

    }


    private static void createDb(){
        Connection connection = DbConnector.getConnection();
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(createDb);
        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
    }

    private static void createUserTbl(){
        Connection connection = DbConnector.getConnection();
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(createUserTbl);
        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
    }

    private static void createAccountTbl(){
        Connection connection = DbConnector.getConnection();
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(createAccountTbl);
        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
    }

    private static void createTransactionTbl(){
        Connection connection = DbConnector.getConnection();
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(createTransactionTbl);
        }catch (Exception e){
            ExceptionHandler.handleException(e);
        }
    }


    public static void setDb(){
        if (dbExist("atm"))
            useDb();
        else {
            createDb();
            useDb();
        }
        if (!tableExist("user"))
            createUserTbl();
        if (!tableExist("account"))
            createAccountTbl();
        if (!tableExist("transaction"))
            createTransactionTbl();

    }

}
